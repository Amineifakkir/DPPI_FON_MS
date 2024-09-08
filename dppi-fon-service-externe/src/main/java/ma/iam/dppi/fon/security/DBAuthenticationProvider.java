package ma.iam.dppi.fon.security;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import ma.iam.dppi.fon.communs.domain.Profil;
import ma.iam.dppi.fon.communs.domain.Utilisateur;
import ma.iam.dppi.fon.constants.ActionCode;
import ma.iam.dppi.fon.constants.Constants;
import ma.iam.dppi.fon.exception.ConditionsNotAcceptedException;
import ma.iam.dppi.fon.exception.FirstConnectionException;
import ma.iam.dppi.fon.exception.ForcedChangePwdException;
import ma.iam.dppi.fon.exception.OperateurNonExistException;
import ma.iam.dppi.fon.services.ITraceService;
import ma.iam.dppi.fon.services.IUtilisateurService;
import ma.iam.dppi.fon.utils.SecurityUtils;
import ma.iam.dppi.fon.utils.Utils;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 
 * @author Z.BELGHAOUTI
 *
 */
@Component
public class DBAuthenticationProvider implements AuthenticationProvider {
	
    @Autowired
    private ITraceService traceService;

    @Autowired
    private IUtilisateurService utilisateurService;
    
    @Value("${session.timeout}")
	private String sessionTimeOut;

	protected static final Map<String, Integer> expiredMap = new HashMap<>();

    Logger logger = LoggerFactory.getLogger(this.getClass());

	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    	
    	String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        Utilisateur user = utilisateurService.getUserByLogin(username);
        
        logger.info("Login saisie : {}", username);
        
        if (user == null) {
        	traceService.traceConnexion(ActionCode.TENTATIVE_CONNEXION, "Le compte saisi n'est pas valide",
            		"CONNEXION", username);
        	logger.info("Le compte : " + username + " n'est pas valide");
        	throw new InsufficientAuthenticationException("Le compte saisi n'est pas valide");
        }
        
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    	boolean acceptConditions = "true".equalsIgnoreCase(request.getParameter("isGCAccepted"))
    			|| "false".equalsIgnoreCase(request.getParameter("isGCAccepted"));
    	boolean isConditionsAccepted = false;
        isConditionsAccepted = BooleanUtils.isTrue(user.isConditionsAccepted()) || acceptConditions;
        
        /**
         * ADD BY Z.BELGHAOUTI
         * Remove Conditions to users who have profiles ADMIN_ERPT & INTERLOCUTEUR_ERPT
         */
        List<Profil> lisProfils = user.getListProfils();
        if(lisProfils != null && lisProfils.size() == 1){
        	if(lisProfils.get(0).getLibelle().equals("ADMIN_ERPT") || lisProfils.get(0).getLibelle().equals("INTERLOCUTEUR_ERPT")){
        		isConditionsAccepted = true;
        	}
        }else if(lisProfils != null && lisProfils.size() == 2){
        	if((lisProfils.get(0).getLibelle().equals("ADMIN_ERPT") || lisProfils.get(0).getLibelle().equals("INTERLOCUTEUR_ERPT")) 
        			&& (lisProfils.get(1).getLibelle().equals("ADMIN_ERPT") || lisProfils.get(1).getLibelle().equals("INTERLOCUTEUR_ERPT"))){
        		isConditionsAccepted = true;
        	}
        }
        String crypt = BCrypt.hashpw(password, Utilisateur.USER_HASH_CODE);
        if (!crypt.equals(user.getPassword())) {
        	traceService.traceConnexion(ActionCode.TENTATIVE_CONNEXION, "Mot de passe erroné",
            		"CONNEXION", username);
        	logger.info("Mot de passe erroné du compte " + username);
            throw new BadCredentialsException("Mot de passe erroné");
        }
        
        if (user.isFirstConnexion()) {
        	traceService.traceConnexion(ActionCode.TENTATIVE_CONNEXION, "Première connexion",
            		"CONNEXION", username);
        	logger.info(Utils.getLogParam() + "Première connexion");
            throw new FirstConnectionException("Première connexion");
        }
        
        if (BooleanUtils.isTrue(user.getForcedChangePwd())) {
        	traceService.traceConnexion(ActionCode.TENTATIVE_CONNEXION, "Le changement du mot de passe est forcé",
            		"CONNEXION", username);
        	logger.info(Utils.getLogParam() + "Le changement du mot de passe est forcé");
            throw new ForcedChangePwdException("Le changement du mot de passe est forcé");
        }
        
        if (SecurityUtils.verifierReglesConnexionInactivite(user)) {
        	traceService.traceConnexion(ActionCode.TENTATIVE_CONNEXION, "Compte : " + user.getLogin() + " verrouillé par inactivité",
            		"CONNEXION", username);
			logger.info("Compte vérrouillé: " + username);
            throw new LockedException("COMPTE VERROUILLE");
        }
        
        if (SecurityUtils.isAttemptExceeded(user)) {
        	traceService.traceConnexion(ActionCode.TENTATIVE_CONNEXION, "Le compte a été bloqué après " 
					+ Constants.LOCK_ATTEMPT + " échecs tentatives",
            		"Tentative de connexion", username);
			logger.info("Le compte a été bloqué après " 
					+ Constants.LOCK_ATTEMPT + " échecs tentatives: " + username);
			 throw new LockedException("LOCK_ATTEMPT");
        }
        
        if (SecurityUtils.isBlocked(user)) {
        	traceService.traceConnexion(ActionCode.TENTATIVE_CONNEXION, "Compte désactivé",
            		"Tentative de connexion", username);
			logger.info("Compte désactivé: " + username);
            throw new DisabledException("COMPTE DISABLED");
        }
        
        if(user.getOperateur() == null) {
        	traceService.traceConnexion(ActionCode.TENTATIVE_CONNEXION, "Compte n'a aucun Opérateur", 
        			"Tentative de connexion", username);
			logger.info("Compte n'a aucun Opérateur : " + username);
            throw new OperateurNonExistException("Compte n'a aucun Opérateur");
        }
        
        if(user.getListProfils() == null 
				|| CollectionUtils.isEmpty(user.getListProfils())){
        	traceService.traceConnexion(ActionCode.TENTATIVE_CONNEXION, "Aucuns profils",
            		"CONNEXION", username);
			logger.info("Aucuns profils: " + username);
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
		
		//general conditions not accepted
        if(!isConditionsAccepted){
        	traceService.traceConnexion(ActionCode.TENTATIVE_CONNEXION, "Conditions générales d'utilisation non acceptées",
            		"CONNEXION", username);
        	logger.info("Conditions générales d'utilisation non acceptées par l'utilisateur: " + username);
        	throw new ConditionsNotAcceptedException("Conditions générales d'utilisation non acceptées.");
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        
        for (Profil p : user.getListProfils()) {
        	authorities.add(new SimpleGrantedAuthority(p.getLibelle()));
        }

        UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), authorities);
        
        return createSuccessfulAuthentication(authentication, userDetails);
    }
    
    private Authentication createSuccessfulAuthentication(final Authentication authentication, final UserDetails user) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUsername(), authentication.getCredentials(), user.getAuthorities());
        token.setDetails(authentication.getDetails());
        return token;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.springframework.security.providers.AuthenticationProvider#supports(java.lang.Class)
     */
    public boolean supports(@SuppressWarnings("rawtypes") Class arg0) {
        return arg0.equals(UsernamePasswordAuthenticationToken.class);
    }
}
