package ma.iam.dppi.fon.interne.security;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import ma.iam.dppi.fon.communs.domain.Profil;
import ma.iam.dppi.fon.communs.domain.Utilisateur;
import ma.iam.dppi.fon.communs.repository.UtilisateurRepository;
import ma.iam.dppi.fon.constants.ActionCode;
import ma.iam.dppi.fon.interne.constants.Constants;
import ma.iam.dppi.fon.interne.services.ITraceService;
import ma.iam.dppi.fon.interne.utils.SecurityUtils;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.ldap.userdetails.LdapAuthoritiesPopulator;
import org.springframework.stereotype.Component;

/**
 * 
 * @author Z.BELGHAOUTI
 *
 */
@Component
public class AppAuthoritiesPopulator implements LdapAuthoritiesPopulator {
	
	protected static final Log logger = LogFactory.getLog(AppAuthoritiesPopulator.class);
	
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	
	@Autowired
	private ITraceService traceService;
	
	@Value("${session.timeout}")
	private String sessionTimeOut;

	protected static final Map<String, Integer> expiredMap = new HashMap<>();
	

	public Collection<GrantedAuthority> getGrantedAuthorities(DirContextOperations userData, String username) {
		Collection<GrantedAuthority> authorities = new HashSet<>();
		
		logger.info("Tentative de connexion de l'utilisateur ayant le login: "+username);				
		Utilisateur currentUser = utilisateurRepository.findByLogin(username);
		if (currentUser == null) {
			traceService.traceConnexion(ActionCode.TENTATIVE_CONNEXION, "Le compte saisi n'est pas valide",
            		"CONNEXION", username);
            logger.info("L'utilisateur ayant le login: " + username + " n'est pas paramètré dans la base de données");
            throw new InsufficientAuthenticationException("L'utilisateur n'existe pas dans la base de donnée");
        }
		if (SecurityUtils.verifierReglesConnexionInactivite(currentUser)) {
			traceService.traceConnexion(ActionCode.TENTATIVE_CONNEXION, "Compte : " + currentUser.getLogin() + " verrouillé par inactivité",
            		"CONNEXION", username);
            throw new LockedException("COMPTE VERROUILLE");
        }
		if (SecurityUtils.isAttemptExceeded(currentUser)) {
			traceService.traceConnexion(ActionCode.TENTATIVE_CONNEXION, "Le compte a été bloqué après " 
					+ Constants.LOCK_ATTEMPT + " échecs tentatives",
            		"Tentative de connexion", username);
			 throw new LockedException("LOCK_ATTEMPT");
        }
		if (SecurityUtils.isBlocked(currentUser)) {
			traceService.traceConnexion(ActionCode.TENTATIVE_CONNEXION, "Compte désactivé",
            		"Tentative de connexion", username);
            throw new DisabledException("COMPTE DISABLED");
        }
		
		if(currentUser.getListProfils() == null 
				|| CollectionUtils.isEmpty(currentUser.getListProfils())){
			traceService.traceConnexion(ActionCode.TENTATIVE_CONNEXION, "Aucuns profils",
            		"CONNEXION", username);
            throw new DisabledException("ACCES DENIED");
		}
		
		Integer expireSession = expiredMap.get("expireMT_" + username);
		if(expireSession == null) {
			expiredMap.put(username, 0);
		} else {
			int defaultTimeOut = Integer.parseInt(sessionTimeOut);
			if (expireSession >= defaultTimeOut) {
				
				long start = new Date().getTime();
				while(new Date().getTime() - start <= 900L) {
					expiredMap.remove("expireMT_" + username);
				}
				throw new DisabledException("SESSION EXPIRED");
			} else {
				expiredMap.put(username, 0);
			}
		}
		
		List<Profil> listProfil = currentUser.getListProfils();
		for (Profil profil : listProfil){
			authorities.add(new SimpleGrantedAuthority(profil.getLibelle()));
		}
		currentUser.setTentativeConnexion(0);
		utilisateurRepository.save(currentUser);
		logger.info("Connexion reussie pour l'utilisateur ayant le login: "+username);				
		return authorities;

	}

}