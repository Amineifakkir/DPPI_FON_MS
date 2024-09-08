package ma.iam.dppi.fon.interne.services.implementations;

import java.math.BigInteger;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import ma.iam.dppi.fon.communs.domain.Operateur;
import ma.iam.dppi.fon.communs.domain.Profil;
import ma.iam.dppi.fon.communs.domain.Utilisateur;
import ma.iam.dppi.fon.communs.repository.OperateurRepository;
import ma.iam.dppi.fon.communs.repository.ProfilRepository;
import ma.iam.dppi.fon.communs.repository.UtilisateurRepository;
import ma.iam.dppi.fon.constants.ActionCode;
import ma.iam.dppi.fon.interne.constants.UtilisateurConstants;
import ma.iam.dppi.fon.interne.dtos.ProfilDto;
import ma.iam.dppi.fon.interne.dtos.UserCriteresDto;
import ma.iam.dppi.fon.interne.dtos.UtilisateurDto;
import ma.iam.dppi.fon.interne.services.ITraceService;
import ma.iam.dppi.fon.interne.services.IUtilisateurExterneService;
import ma.iam.dppi.fon.interne.utils.DateUtils;
import ma.iam.dppi.fon.interne.utils.SecurityUtils;
import ma.iam.dppi.fon.interne.utils.Utils;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author Z.BELGHAOUTI
 *
 */
@Service
@Transactional
public class UtilisateurExterneServiceImpl implements IUtilisateurExterneService {

	private final Logger logger = LoggerFactory.getLogger(UtilisateurExterneServiceImpl.class);

	@Autowired
	private UtilisateurRepository utilisateurRepository;
	@Autowired
	private ProfilRepository profilRepository;
	@Autowired
	private ITraceService traceService;
	@Autowired
	private OperateurRepository operateurRepository;
	
	@Override
	public UtilisateurDto getCurrentUtilisateur() {
		UtilisateurDto dto = null;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.getPrincipal() != null) {
			String login = authentication.getName();
			Utilisateur utilisateur = utilisateurRepository.findByLogin(login);
			UtilisateurDto utilisateurDto = toDto(utilisateur);
			if(!CollectionUtils.isEmpty(authentication.getAuthorities())) {
				if(utilisateurDto.getRoles() == null)
					utilisateurDto.setRoles(new ArrayList<>());
				for(GrantedAuthority authority : authentication.getAuthorities()) {
					if(!utilisateurDto.getRoles().contains(authority.getAuthority())){
						utilisateurDto.getRoles().add(authority.getAuthority());
					}
				}
			}
			logger.info(Utils.formatMessage(UtilisateurConstants.MESSAGE_GET_USER_CONNECTED) +utilisateurDto.getRoles());
			return utilisateurDto;
		}
		return dto;
	}

	private UtilisateurDto toDto(Utilisateur utilisateur) {
		UtilisateurDto dto = new UtilisateurDto();
		if (utilisateur == null) {
			return dto;
		}
		dto.setIdt(utilisateur.getIdt());
		dto.setLogin(utilisateur.getLogin());
		dto.setEmail(utilisateur.getMail());
		dto.setNom(utilisateur.getNom());
		dto.setPrenom(utilisateur.getPrenom());
		dto.setUserIdSession(utilisateur.getUserIdSession());
		if(utilisateur.getEnabled() != null) {
			dto.setEnable(utilisateur.getEnabled());
		}
		if(utilisateur.getOperateur() != null) {
			dto.setIdtOperateur(utilisateur.getOperateur().getIdt());
			dto.setOperateur(utilisateur.getOperateur().getLabel());
		}
		dto.setLocked(SecurityUtils.isLocked(utilisateur));
		if(utilisateur.getDateCreation() != null) {
			dto.setDateCreation(DateUtils.toDateTimeMin(utilisateur.getDateCreation()));
		}
		Date dt = utilisateur.getDateLastConnection();
		if (dt == null) {
			dto.setDerniereConnexion(null);
			dto.setSuspended(false);
		} else {
			dto.setDerniereConnexion(dt);
			LocalDate dtLocal = dt.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			LocalDate now = LocalDate.now();

			Period period = Period.between(dtLocal, now);
			dto.setSuspended(period.getYears() > 0 || period.getMonths() >= 2);
		}
		if(CollectionUtils.isNotEmpty(utilisateur.getListProfils())) {
			List<String> list = new ArrayList<>();
			utilisateur.getListProfils().forEach(pr -> list.add(pr.getLibelle()));
			dto.setRoles(list);
			String profilsExport = StringUtils.join(list, " / ");
			dto.setProfilsExport(profilsExport);
		}
		String statutExport = "";
		if(dto.isEnable() != null) {
			if(Boolean.TRUE.equals(dto.isEnable())) {
				statutExport = "Activé";
			} else {
				statutExport = "Désactivé";
			}
		} else {
			statutExport = "Désactivé";
		}
		
		if(dto.isLocked() != null) {
			if(Boolean.TRUE.equals(dto.isLocked())) {
				statutExport += " / Vérrouillé";
			} else {
				statutExport += " / Déverrouillé";
			}
		} else {
			statutExport += " / Déverrouillé";
		}
		dto.setStatutExport(statutExport);
		return dto;

	}

	public UtilisateurDto updateUtilisateur(UtilisateurDto dto, HttpServletRequest request) {
		Utilisateur utilisateur = utilisateurRepository.findUserExterneByLogin(dto.getLogin());
		String typeOperation = ""; 
		String operation = ""; 
		String oldOperateur = "";
		String oldRoles = "";
		String oldValue = "";
		ActionCode actionCode = null;
		if (utilisateur == null) {
			utilisateur = new Utilisateur();
			utilisateur.setLogin(dto.getLogin());
			utilisateur.setEnabled(true);
			utilisateur.setDateCreation(new Date());
			utilisateur.setTentativeConnexion(0);
			typeOperation = "Ajout";
			actionCode = ActionCode.ADD;
			operation = "Ajout de l'utilisateur: " + dto.getLogin() + " à l'application";
		} else {
			oldOperateur = utilisateur.getOperateur() != null ? (utilisateur.getOperateur().getLabel()) : "";
			if(CollectionUtils.isNotEmpty(utilisateur.getListProfils())) {
				List<String> list = new ArrayList<>();
				utilisateur.getListProfils().forEach(pr -> list.add(pr.getLibelle()));
				oldRoles = StringUtils.join(list, '/');
			}
			
			if(utilisateur.getEnabled() == null) {
				utilisateur.setEnabled(true);
			}
			typeOperation = "Modification";
			actionCode = ActionCode.MODIF;
			operation = "Modification de l'utilisateur: " + utilisateur.getLogin();
			
			oldValue = "Nom: " + utilisateur.getNom() + ", Prénom: " + utilisateur.getPrenom() 
					+ ", Opérateur: " + oldOperateur 
					+ " et Profils: " + oldRoles;
		}
		utilisateur.setNom(dto.getNom());
		utilisateur.setPrenom(dto.getPrenom());
		utilisateur.setMail(dto.getEmail());
		if(dto.getIdtOperateur() != null) {
			Optional<Operateur> optionalOperateur = operateurRepository.findById(dto.getIdtOperateur());
			if (optionalOperateur.isPresent()) {
				Operateur operateur = optionalOperateur.get();
				utilisateur.setOperateur(operateur);
			}
		}
		if(dto.getPassword() != null && !"".equals(dto.getPassword())) {
			utilisateur.setPassword(BCrypt.hashpw(dto.getPassword(), Utilisateur.USER_HASH_CODE));
		}
		
		if(dto.getRoles() != null && CollectionUtils.isNotEmpty(dto.getRoles())) {
			List<Profil> list = new ArrayList<>();
			for(String role: dto.getRoles()) {
				Profil profil = profilRepository.findByLibelle(role);
				if(profil != null) {
					list.add(profil);
				}
			}
			utilisateur.setListProfils(new ArrayList<>());
			utilisateur.getListProfils().addAll(list);
		}
		
		String newOperateur = "";
		String newRoles = "";
		
		if(utilisateur.getOperateur() != null) {
			newOperateur= utilisateur.getOperateur() != null ? utilisateur.getOperateur().getLabel() : "";
		}
		if(CollectionUtils.isNotEmpty(utilisateur.getListProfils())) {
			List<String> list = new ArrayList<>();
			utilisateur.getListProfils().forEach(pr -> list.add(pr.getLibelle()));
			newRoles = StringUtils.join(list, '/');
		}
		String newValue = "Nom: " + utilisateur.getNom() 
				+ ", Prénom: " + utilisateur.getPrenom() 
				+ ", Opérateur: " + newOperateur
				+ " et Profils: " + newRoles;
		
		Utilisateur user = utilisateurRepository.save(utilisateur);
		
		traceService.traceOperation(actionCode, 
				operation, 
				operation,
				typeOperation, "Utilisateur", oldValue, newValue);
		logger.info(Utils.formatMessage(UtilisateurConstants.MESSAGE_FIN_RATTACHEMENT + utilisateur.getLogin()));
		return toDto(user);

	}

	@Override
	public UtilisateurDto getEditedUser(String login) {
		Utilisateur utilisateur = utilisateurRepository.findUserExterneByLogin(login);
		logger.info(Utils.formatMessage(UtilisateurConstants.MESSAGE_GET_USER_LOGIN + login));
		return toDto(utilisateur);
	}
	

	@Override
	public UtilisateurDto checkCurrentUser(boolean connexion) throws ParseException {
		logger.info(Utils.formatMessage(UtilisateurConstants.MESSAGE_GET_USER_CONNECTED_ACTIVITE));
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.getPrincipal() != null) {
			Utilisateur utilisateur = utilisateurRepository.findUserExterneByLogin(authentication.getName());
			UtilisateurDto dto = toDto(utilisateur);
			if (!dto.isSuspended()) {
				if(!connexion) {
					utilisateur.setDateLastConnection(new Date());
					traceService.traceConnexion(ActionCode.CONNEXION, "Connexion réussie",
							"CONNEXION", utilisateur.getLogin());
				}
				utilisateur.setTentativeConnexion(0);
				utilisateurRepository.save(utilisateur);
			}
			return dto;
		}
		return null;
	}
	
	@Override
	public List<ProfilDto> getAllRoles() {
		List<Profil> profils = profilRepository.getAllProfilExternes();
		List<ProfilDto> list = new ArrayList<>();
		if(CollectionUtils.isNotEmpty(profils)) {
			profils.forEach(pr -> list.add(new ProfilDto(pr.getIdt(), pr.getLibelle())));
		}
		return list;
	}

	@Override
	public void consulterEtModifierSessionIDParLogin(String login) {
		Utilisateur utilisateur = utilisateurRepository.findUserExterneByLogin(login);
		if(utilisateur == null) {
			utilisateur = new Utilisateur();
			utilisateur.setLogin(login);
		}
		utilisateur.setUserIdSession(UUID.randomUUID().toString());
		utilisateurRepository.save(utilisateur);
	}

	@Override
	public List<UtilisateurDto> getUtilisateursDBByParam(UserCriteresDto dto) {
		List<Utilisateur> listUsers = null;
		
		Boolean enable = null;
		if(dto.getIdtStatut() != null) {
			if(dto.getIdtStatut() == 1) {
				enable = true;
			} else if(dto.getIdtStatut() == 2) {
				enable = false;
			}
		}
		UtilisateurDto currentUser = getCurrentUtilisateur();
		if(currentUser != null) {
			String nom = "".equals(dto.getNom()) ? null : dto.getNom();
			String prenom = "".equals(dto.getPrenom()) ? null : dto.getPrenom();
			List<Object> objects = utilisateurRepository
					.getListUsersExterneByParams(dto.getLogin(), nom, prenom,
							dto.getIdtProfil(), enable, dto.getIdtOperateur());
			List<Long> idsUsers = new ArrayList<>();
			if(objects != null && CollectionUtils.isNotEmpty(objects)) {
				for(Object obj: objects){
					idsUsers.add(((BigInteger)obj).longValue());
				}
			}
			if(CollectionUtils.isNotEmpty(idsUsers)) {
				listUsers = utilisateurRepository.findUsersByIdts(idsUsers);
			}
			
			if(listUsers != null && CollectionUtils.isNotEmpty(listUsers)) {
				List<UtilisateurDto> dtos = new ArrayList<>();
				listUsers.forEach(u -> {
					boolean isLock = SecurityUtils.isLocked(u);
					if(dto.getIdtStatut() != null) {
						if (dto.getIdtStatut() == 3 && isLock) {
							dtos.add(toDto(u));
						} 
						if(dto.getIdtStatut() == 4 && !isLock) {
							dtos.add(toDto(u));
						}
						if(dto.getIdtStatut() == 1 || dto.getIdtStatut() == 2) {
							dtos.add(toDto(u));
						}
					} else {
						dtos.add(toDto(u));
					}
				});
				String operation = "Récupération de la base de données la liste des utilisateurs de tailles: " 
				+ dtos.size() + " et par critères de recherche: [LOGIN]: " + dto.getLogin()
				+ ", [NOM]: " + dto.getNom() + ", [Prénom]: " + dto.getPrenom();
				traceService.traceOperation(ActionCode.CONSULTATION, operation, operation,
						"Consultation", "Utilisateur", null, null);
				return dtos;
			}
		}
		
		return null;
	}
	
	@Override
	public Utilisateur getUserByLogin(String login) {
		return utilisateurRepository.findByLogin(login);
	}
	
	@Override
	public UtilisateurDto unlockUser(Long idt) {
		if(idt != null) {
			Optional<Utilisateur> optionalUser = utilisateurRepository.findById(idt);
			if (optionalUser.isPresent()) {
				Utilisateur user = optionalUser.get();
				if(user != null) {
					
					if (SecurityUtils.isAttemptExceeded(user)) {
			            user.setTentativeConnexion(0);
			        }
			        if (SecurityUtils.verifierReglesConnexionInactivite(user)) {
			            user.setDateLastConnection(new Date());
			        }
			        utilisateurRepository.save(user);
			        String operation = "Déverrouiller l'utilisateur ayant login: " 
			    			+ user.getLogin();
			        traceService.traceOperation(ActionCode.MODIF, operation, operation,
	    					"MODIFICATION", "Utilisateur", null, null);
			        return toDto(user);
				}
			}
			
		}
		return null;
	}
	
	@Override
	public UtilisateurDto enableUser(Long idt, boolean isEnable) {
		if(idt != null) {
			Optional<Utilisateur> optionalUser = utilisateurRepository.findById(idt);
			if (optionalUser.isPresent()) {
				Utilisateur user = optionalUser.get();
				if(user != null) {
					user.setEnabled(isEnable);
			        utilisateurRepository.save(user);
			        String enable = isEnable ? "Activer" : "Désactiver";
			        String operation = enable + " l'utilisateur ayant login: " 
			    			+ user.getLogin();
			        traceService.traceOperation(ActionCode.MODIF, operation, operation,
	    					"MODIFICATION", "Utilisateur", null, null);
			        return toDto(user);
				}
			}
		}
		return null;
	}
	
	@Override
	public Utilisateur save(Utilisateur utilisateur) {
		return utilisateurRepository.save(utilisateur);
	}
	
	@Override
	public List<String> getUserRoles() {
		List<String> roles = new ArrayList<>();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.getPrincipal() != null) {
			Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
			if (CollectionUtils.isEmpty(authorities)) {
				return roles;
			}
			for (GrantedAuthority auth : authorities) {
				if (auth.getAuthority() != null && !auth.getAuthority().equals("ROLE_offline_access")
						&& !auth.getAuthority().equals("ROLE_uma_authorization")) {
					String role = auth.getAuthority();
					role = role.substring(role.indexOf("_") + 1);
					roles.add(role);
				}
			}
		}
		return roles;
	}
	
	@Override
	public boolean checkLoginUser(String login, boolean isEdit) {
		Utilisateur utilisateur = utilisateurRepository.findUserExterneByLogin(login);
		if(utilisateur != null && !isEdit) {
			return true;	
		}
		return false;
	}
}