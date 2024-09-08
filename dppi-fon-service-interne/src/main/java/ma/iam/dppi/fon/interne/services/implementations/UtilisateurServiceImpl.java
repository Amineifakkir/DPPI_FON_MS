package ma.iam.dppi.fon.interne.services.implementations;

import java.math.BigInteger;
import java.text.MessageFormat;
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

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.AbstractContextMapper;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.ldap.filter.LikeFilter;
import org.springframework.ldap.filter.OrFilter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;

import ma.iam.dppi.fon.communs.domain.Dc;
import ma.iam.dppi.fon.communs.domain.Dr;
import ma.iam.dppi.fon.communs.domain.Entite;
import ma.iam.dppi.fon.communs.domain.Profil;
import ma.iam.dppi.fon.communs.domain.Utilisateur;
import ma.iam.dppi.fon.communs.repository.DcRepository;
import ma.iam.dppi.fon.communs.repository.DrRepository;
import ma.iam.dppi.fon.communs.repository.EntiteRepository;
import ma.iam.dppi.fon.communs.repository.ProfilRepository;
import ma.iam.dppi.fon.communs.repository.UtilisateurRepository;
import ma.iam.dppi.fon.constants.ActionCode;
import ma.iam.dppi.fon.interne.constants.ErrorConstants;
import ma.iam.dppi.fon.interne.constants.UtilisateurConstants;
import ma.iam.dppi.fon.interne.dtos.ProfilDto;
import ma.iam.dppi.fon.interne.dtos.UserInterneCritDto;
import ma.iam.dppi.fon.interne.dtos.UtilisateurDto;
import ma.iam.dppi.fon.interne.services.ITraceService;
import ma.iam.dppi.fon.interne.services.IUtilisateurService;
import ma.iam.dppi.fon.interne.utils.DateUtils;
import ma.iam.dppi.fon.interne.utils.SecurityUtils;
import ma.iam.dppi.fon.interne.utils.Utils;

/**
 * 
 * @author Z.BELGHAOUTI
 *
 */
@Service
@Transactional
public class UtilisateurServiceImpl implements IUtilisateurService {

	private final Logger logger = LoggerFactory.getLogger(UtilisateurServiceImpl.class);

	@Autowired
	private UtilisateurRepository utilisateurRepository;
	@Autowired
	private ProfilRepository profilRepository;
	@Autowired
	private ITraceService traceService;
//	@Autowired
//	private OperateurRepository operateurRepository;
	@Autowired
	private DrRepository drDaoImpl;
	@Autowired
	private DcRepository dcDaoImpl;
	@Autowired
	private EntiteRepository entiteRepository;
	@Autowired
	public DefaultSpringSecurityContextSource contextSource;

	@Value("${ldap.search.base}")
	private String userSearchBase;

	@Value("${ldap.type.search}")
	private String typeSearch;

	@Value("${ldap.base.dn}")
	private String userBaseDn;

	public LdapTemplate getLdapTemplate() {
		return new LdapTemplate(contextSource);
	}

	@Override
	public UtilisateurDto getCurrentUtilisateur() {
		UtilisateurDto dto = null;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.getPrincipal() != null) {
			String login = authentication.getName();
			Utilisateur utilisateur = utilisateurRepository.findByLogin(login);
			UtilisateurDto utilisateurDto = toDto(utilisateur);
			if (!CollectionUtils.isEmpty(authentication.getAuthorities())) {
				if (utilisateurDto.getRoles() == null)
					utilisateurDto.setRoles(new ArrayList<>());
				for (GrantedAuthority authority : authentication.getAuthorities()) {
					if (!utilisateurDto.getRoles().contains(authority.getAuthority())) {
						utilisateurDto.getRoles().add(authority.getAuthority());
					}
				}
			}
			logger.info(
					Utils.formatMessage(UtilisateurConstants.MESSAGE_GET_USER_CONNECTED) + utilisateurDto.getRoles());
			return utilisateurDto;
		}
		return dto;
	}

	@Override
	public List<UtilisateurDto> getByCompleteName(String name) {
		List<Utilisateur> utilisateurs = utilisateurRepository.searchByCompleteNameLike(name);
		List<UtilisateurDto> list = new ArrayList<>();
		if (utilisateurs != null) {
			for (Utilisateur utilisateur : utilisateurs) {
				list.add(toDto(utilisateur));
			}
		}
		String operation = "Récupération de la liste des utilisateurs de tailles: " + list.size()
				+ " et par critère de recherche: " + name;

		traceService.traceOperation(ActionCode.CONSULTATION, operation, operation, "Consultation", "Utilisateur", null,
				null);
		logger.info(Utils.formatMessage(UtilisateurConstants.MESSAGE_GET_USERS_CONNECTED));
		return list;
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
		if (utilisateur.getEntite() != null) {
			dto.setIdtEntite(utilisateur.getEntite().getIdt());
			dto.setEntite(utilisateur.getEntite().getLabel());
		}
		dto.setUserIdSession(utilisateur.getUserIdSession());
		if (utilisateur.getEnabled() != null) {
			dto.setEnable(utilisateur.getEnabled());
		}
		if (utilisateur.getOperateur() != null) {
			dto.setIdtOperateur(utilisateur.getOperateur().getIdt());
			dto.setOperateur(utilisateur.getOperateur().getLabel());
		}
		if (utilisateur.getDr() != null) {
			dto.setIdtDr(utilisateur.getDr().getIdt());
			dto.setDr(utilisateur.getDr().getLabel());
		}
		if (utilisateur.getDc() != null) {
			dto.setIdtDc(utilisateur.getDc().getIdt());
			dto.setDc(utilisateur.getDc().getLabel());
		}
		dto.setLocked(SecurityUtils.isLocked(utilisateur));
		if (utilisateur.getDateCreation() != null) {
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
		if (CollectionUtils.isNotEmpty(utilisateur.getListProfils())) {
			List<String> list = new ArrayList<>();
			utilisateur.getListProfils().forEach(pr -> list.add(pr.getLibelle()));
			dto.setRoles(list);
			String profilsExport = StringUtils.join(list, " / ");
			dto.setProfilsExport(profilsExport);
		}
		String statutExport = "";
		if (dto.isEnable() != null) {
			if (Boolean.TRUE.equals(dto.isEnable())) {
				statutExport = "Activé";
			} else {
				statutExport = "Désactivé";
			}
		} else {
			statutExport = "Désactivé";
		}

		if (dto.isLocked() != null) {
			if (Boolean.TRUE.equals(dto.isLocked())) {
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
		Utilisateur utilisateur = utilisateurRepository.findUserInterneByLogin(dto.getLogin());
		String typeOperation = "";
		String operation = "";
		String oldDr = "";
		String oldDc = "";
		String oldEntite = "";
		String oldProfil = "";
		String oldValue = "";
		String newDr = "";
		String newEntite = "";
		String newDc = "";
		ActionCode actionCode = null;
		if (utilisateur == null) {
			utilisateur = new Utilisateur();
			utilisateur.setLogin(dto.getLogin());
			utilisateur.setEnabled(true);
			utilisateur.setDateCreation(new Date());
			utilisateur.setTentativeConnexion(0);

			typeOperation = "AJOUT";
			actionCode = ActionCode.ADD;
			operation = "Ajout de l'utilisateur: " + dto.getLogin() + " à l'application";
		} else {
			oldDr = utilisateur.getDr() != null ? (utilisateur.getDr().getLabel()) : "";
			oldDc = utilisateur.getDc() != null ? (utilisateur.getDc().getLabel()) : "";
			oldEntite = utilisateur.getEntite() != null ? utilisateur.getEntite().getLabel() : "";

			if (CollectionUtils.isNotEmpty(utilisateur.getListProfils())) {
				List<String> list = new ArrayList<>();
				utilisateur.getListProfils().forEach(pr -> list.add(pr.getLibelle()));
				oldProfil = StringUtils.join(list, '/');
			}

			typeOperation = "MODIFICATION";
			actionCode = ActionCode.MODIF;
			operation = "Modification de l'utilisateur: " + utilisateur.getLogin();

			oldValue = "DR: " + oldDr + ", DC: " + oldDc + ", Entité: " + oldEntite + " et Profils: " + oldProfil;
		}

		utilisateur.setNom(dto.getNom());
		utilisateur.setPrenom(dto.getPrenom());
		utilisateur.setMail(dto.getEmail());

		if (dto.getIdtEntite() != null && dto.getIdtEntite() != 0L) {
			Optional<Entite> optionalEntite = entiteRepository.findById(dto.getIdtEntite());
			if (optionalEntite.isPresent()) {
				Entite entite = optionalEntite.get();
				utilisateur.setEntite(entite);
				newEntite = entite.getLabel();
			}
		}

		if (dto.getIdtDr() != null && dto.getIdtDr() != 0L) {
			Optional<Dr> optionalDr = drDaoImpl.findById(dto.getIdtDr());
			if (optionalDr.isPresent()) {
				Dr dr = optionalDr.get();
				utilisateur.setDr(dr);
				newDr = dr.getLabel();
			}

		} else {
			utilisateur.setDr(null);
			newDr = "";
		}
		if (dto.getIdtDc() != null && dto.getIdtDc() != 0L) {
			Optional<Dc> optionalDc = dcDaoImpl.findById(dto.getIdtDc());
			if (optionalDc.isPresent()) {
				Dc dc = optionalDc.get();
				utilisateur.setDc(dc);
				newDc = dc.getLabel();
			}
		} else {
			utilisateur.setDc(null);
			newDc = "";
		}

		if (dto.getRoles() != null && CollectionUtils.isNotEmpty(dto.getRoles())) {
			List<Profil> list = new ArrayList<>();
			for (String role : dto.getRoles()) {
				Profil profil = profilRepository.findByLibelle(role);
				if (profil != null) {
					list.add(profil);
				}
			}
			utilisateur.setListProfils(new ArrayList<>());
			utilisateur.getListProfils().addAll(list);
		}
		String newProfil = "";
		if (CollectionUtils.isNotEmpty(utilisateur.getListProfils())) {
			List<String> list = new ArrayList<>();
			utilisateur.getListProfils().forEach(pr -> list.add(pr.getLibelle()));
			newProfil = StringUtils.join(list, '/');
		}

		String newValue = "DR: " + newDr + ", DC: " + newDc + ", Entité: " + newEntite + " et Profils: " + newProfil;

		Utilisateur user = utilisateurRepository.save(utilisateur);

		traceService.traceOperation(actionCode, operation, operation, typeOperation, "Utilisateur", oldValue, newValue);
		logger.info(Utils.formatMessage(UtilisateurConstants.MESSAGE_FIN_RATTACHEMENT + utilisateur.getLogin()));
		return toDto(user);

	}

	@Override
	public ResponseEntity<List<UtilisateurDto>> getUtilisateursByParam(String param, HttpServletRequest request) {
		List<UtilisateurDto> dtos = new ArrayList<>();
		MessageFormat messageFormat = null;
		try {
			if (param.length() >= 3) {
				param = param.replace(" ", "%20");
				dtos.addAll(findByFilter(param));
			}
			messageFormat = new MessageFormat(UtilisateurConstants.MESSAGE_TOTAL_USER);
			logger.info(Utils.formatMessage(messageFormat.format(new Object[] { dtos.size(), param })));

			String operation = "Récupération de la liste des utilisateurs via recherche AD de tailles: " + dtos.size()
					+ " et par critère de recherche: " + param;
			traceService.traceOperation(ActionCode.CONSULTATION, operation, operation, "Consultation", "Utilisateur",
					null, null);
			return new ResponseEntity<>(dtos, HttpStatus.OK);
		} catch (HttpClientErrorException e) {
			logger.error(Utils.formatMessage(ErrorConstants.MESSAGE_ERROR_USER_HTTP_CLIENT + param));
			return new ResponseEntity<>(dtos, HttpStatus.FORBIDDEN);
		} catch (RestClientException e) {
			logger.error(Utils.formatMessage(ErrorConstants.MESSAGE_ERROR_USER_REST_CLIENT + param));
			return new ResponseEntity<>(dtos, HttpStatus.FORBIDDEN);
		}
	}

	@SuppressWarnings("unchecked")
	private List<UtilisateurDto> findByFilter(String query) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		List<UtilisateurDto> dtos = new ArrayList<>();
		if (auth != null) {
			getUserDn(auth);
			if (query != null && !query.equals("") && !Utils.containSpecChar(query)) {
				String filter = "*" + query + "*";

				OrFilter orfilter = new OrFilter();
				orfilter.or(new LikeFilter("cn", filter));
				orfilter.or(new LikeFilter(typeSearch, filter));
				orfilter.or(new LikeFilter("sn", filter));

				AndFilter andFilter = new AndFilter();
				andFilter.and(new EqualsFilter("objectclass", "person"));
				andFilter.and(orfilter);

				dtos = getLdapTemplate().search(userSearchBase, andFilter.encode(), new PersonContextMapper());

			}
		}

		logger.info(Utils.getLogParam() + "Recherche LDAP: " + query);

		return dtos;
	}

	private String getUserDn(Authentication auth) {
		String userDn = "";
		if (!Utils.contSpecChar(userBaseDn)) {
			userDn = MessageFormat.format("uid={0},{1},{2}",
					new Object[] { auth.getName(), userBaseDn, contextSource.getBaseLdapPathAsString() });
		}
		return userDn;
	}

	@SuppressWarnings("rawtypes")
	protected class PersonContextMapper extends AbstractContextMapper {
		public Object doMapFromContext(DirContextOperations context) {
			UtilisateurDto person = new UtilisateurDto();
			person.setNom(context.getStringAttribute("sn"));
			if (context.getStringAttribute("givenName") == null || "".equals(context.getStringAttribute("givenName"))) {
				person.setPrenom(context.getStringAttribute("cn"));
			} else {
				person.setPrenom(context.getStringAttribute("givenName"));
			}
			person.setEmail(context.getStringAttribute("mail"));
			person.setLogin(context.getStringAttribute(typeSearch));
			return person;
		}
	}

	@Override
	public UtilisateurDto getEditedUser(String login) {
		Utilisateur utilisateur = utilisateurRepository.findUserInterneByLogin(login);
		logger.info(Utils.formatMessage(UtilisateurConstants.MESSAGE_GET_USER_LOGIN + login));
		return toDto(utilisateur);
	}

	@Override
	@Transactional
	public UtilisateurDto checkCurrentUser(boolean connexion) throws ParseException {
		logger.info(Utils.formatMessage(UtilisateurConstants.MESSAGE_GET_USER_CONNECTED_ACTIVITE));
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.getPrincipal() != null) {
			Utilisateur utilisateur = utilisateurRepository.findUserInterneByLogin(authentication.getName());
			UtilisateurDto dto = toDto(utilisateur);
			if (dto != null && !dto.isSuspended()) {
				if (!connexion) {
					utilisateur.setDateLastConnection(new Date());
					traceService.traceConnexion(ActionCode.CONNEXION, "Connexion réussie", "CONNEXION",
							utilisateur.getLogin());
				}
				utilisateur.setTentativeConnexion(0);
				utilisateurRepository.save(utilisateur);
			}
			return dto;
		}
		return null;
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
	public List<ProfilDto> getAllRoles() {
		List<Profil> profils = profilRepository.getAllProfilInternes();
		List<ProfilDto> list = new ArrayList<>();
		if (CollectionUtils.isNotEmpty(profils)) {
			profils.forEach(pr -> list.add(new ProfilDto(pr.getIdt(), pr.getLibelle())));
		}
		return list;
	}

	@Override
	public void consulterEtModifierSessionIDParLogin(String login) {
		Utilisateur utilisateur = utilisateurRepository.findUserInterneByLogin(login);
		if (utilisateur == null) {
			utilisateur = new Utilisateur();
			utilisateur.setLogin(login);
		}
		utilisateur.setUserIdSession(UUID.randomUUID().toString());
		utilisateurRepository.save(utilisateur);
	}

	@Override
	public UtilisateurDto deleteRoleUser(UtilisateurDto u) {
		Utilisateur utilisateur = utilisateurRepository.findUserInterneByLogin(u.getLogin());
		String removeRoles = "";
		if (utilisateur == null) {
			return null;
		}
		String oldRoles = "";
		String newRoles = "";
		List<String> existeRoles = new ArrayList<>();
		if (CollectionUtils.isNotEmpty(utilisateur.getListProfils())) {
			for (Profil p : utilisateur.getListProfils()) {
				existeRoles.add(p.getLibelle());
				if (CollectionUtils.isNotEmpty(u.getRoles()) && !u.getRoles().contains(p.getLibelle())) {
					removeRoles = p.getLibelle();
				}
			}
			oldRoles = StringUtils.join(existeRoles, '/');
		}
		utilisateur.setListProfils(new ArrayList<>());
		if (CollectionUtils.isNotEmpty(u.getRoles())) {
			newRoles = StringUtils.join(u.getRoles(), '/');
			for (String role : u.getRoles()) {
				Profil profil = profilRepository.findByLibelle(role);
				if (profil != null) {
					utilisateur.getListProfils().add(profil);
				}
			}
		}
		utilisateur = utilisateurRepository.save(utilisateur);
		String oldValue = "Profils: " + oldRoles;
		String newValue = "Profils: " + newRoles;
		String operation = "Suppression du rôle: " + removeRoles + " de l'utilisateur: " + utilisateur.getLogin();
		traceService.traceOperation(ActionCode.DELETE, operation, operation, "Suppression", "Utilisateur", oldValue,
				newValue);
		return toDto(utilisateur);
	}

	@Override
	public List<UtilisateurDto> getUtilisateursDBByParam(UserInterneCritDto dto) {
		List<Utilisateur> listUsers = null;

		Boolean enable = null;
		if (dto.getIdtStatut() != null) {
			if (dto.getIdtStatut() == 1) {
				enable = true;
			} else if (dto.getIdtStatut() == 2) {
				enable = false;
			}
		}
		UtilisateurDto currentUser = getCurrentUtilisateur();
		List<UtilisateurDto> dtos = new ArrayList<>();
		if (currentUser != null) {
			List<Object> objects = utilisateurRepository.getListUsersInterneByParams(dto.getLogin(), dto.getIdtDr(),
					dto.getIdtDc(), dto.getIdtProfil(), enable);
			List<Long> idsUsers = new ArrayList<>();
			if (objects != null && CollectionUtils.isNotEmpty(objects)) {
				for (Object obj : objects) {
					idsUsers.add(((BigInteger) obj).longValue());
				}
			}
			if (CollectionUtils.isNotEmpty(idsUsers)) {
				listUsers = utilisateurRepository.findUsersByIdts(idsUsers);
			}

			if (listUsers != null && CollectionUtils.isNotEmpty(listUsers)) {

				listUsers.forEach(u -> {
					boolean isLock = SecurityUtils.isLocked(u);
					if (dto.getIdtStatut() != null) {
						if (dto.getIdtStatut() == 3 && isLock) {
							dtos.add(toDto(u));
						}
						if (dto.getIdtStatut() == 4 && !isLock) {
							dtos.add(toDto(u));
						}
						if (dto.getIdtStatut() == 1 || dto.getIdtStatut() == 2) {
							dtos.add(toDto(u));
						}
					} else {
						dtos.add(toDto(u));
					}
				});
				String operation = "Récupération de la base de données la liste des utilisateurs de tailles: "
						+ dtos.size() + " et par critères de recherche: [NAME]: " + dto.getLogin() + ", [DR]: "
						+ dto.getIdtDr() + ", [DC]: " + dto.getIdtDc();
				traceService.traceOperation(ActionCode.CONSULTATION, operation, operation, "Consultation",
						"Utilisateur", null, null);
			}
		}

		return dtos;
	}

	@Override
	public List<ProfilDto> getAllRolesNotAssignToUser(Long idtUser) {
		List<ProfilDto> result = new ArrayList<>();
		if (idtUser != null && idtUser != 0) {
			Optional<Utilisateur> optionalUser = utilisateurRepository.findById(idtUser);
			if (optionalUser.isPresent()) {
				Utilisateur user = optionalUser.get();
				if (user != null) {
					List<Profil> profilList = user.getListProfils();
					List<Long> listIds = new ArrayList<>();
					if (CollectionUtils.isNotEmpty(profilList)) {
						profilList.forEach(pr -> listIds.add(pr.getIdt()));
					}
					List<Profil> profils = null;
					if (CollectionUtils.isNotEmpty(listIds)) {
						profils = profilRepository.getAllProfilNotList(listIds);
					} else {
						profils = profilRepository.findAll();
					}

					if (CollectionUtils.isNotEmpty(profils)) {
						profils.forEach(pr -> result.add(new ProfilDto(pr.getIdt(), pr.getLibelle())));
					}
				}
			}

		}
		return result;
	}

	@Override
	public Utilisateur getUserByLogin(String login) {
		return utilisateurRepository.findByLogin(login);
	}

	@Override
	public UtilisateurDto unlockUser(Long idt) {
		if (idt != null) {
			Optional<Utilisateur> optionalUser = utilisateurRepository.findById(idt);
			if (optionalUser.isPresent()) {
				Utilisateur user = optionalUser.get();
				if (user != null) {
					if (SecurityUtils.isAttemptExceeded(user)) {
						user.setTentativeConnexion(0);
					}
					if (SecurityUtils.verifierReglesConnexionInactivite(user)) {
						user.setDateLastConnection(new Date());
					}
					utilisateurRepository.save(user);
					String operation = "Déverrouiller l'utilisateur ayant login: " + user.getLogin();
					traceService.traceOperation(ActionCode.MODIF, operation, operation, "MODIFICATION", "Utilisateur",
							null, null);
					return toDto(user);
				}
			}
		}
		return null;
	}

	@Override
	public UtilisateurDto enableUser(Long idt, boolean isEnable) {
		if (idt != null) {
			Optional<Utilisateur> optionalUser = utilisateurRepository.findById(idt);
			if (optionalUser.isPresent()) {
				Utilisateur user = optionalUser.get();
				if (user != null) {
					user.setEnabled(isEnable);
					utilisateurRepository.save(user);
					String enable = isEnable ? "Activer" : "Désactiver";
					String operation = enable + " l'utilisateur ayant login: " + user.getLogin();
					traceService.traceOperation(ActionCode.MODIF, operation, operation, "MODIFICATION", "Utilisateur",
							null, null);
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
	public boolean checkLoginUser(String login, boolean isEdit) {
		Utilisateur utilisateur = utilisateurRepository.findUserInterneByLogin(login);
		if (utilisateur != null && !isEdit) {
			return true;
		}
		return false;
	}
}