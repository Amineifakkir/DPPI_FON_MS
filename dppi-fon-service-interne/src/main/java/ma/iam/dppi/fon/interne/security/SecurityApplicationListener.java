package ma.iam.dppi.fon.interne.security;


import ma.iam.dppi.fon.communs.domain.Utilisateur;
import ma.iam.dppi.fon.constants.ActionCode;
import ma.iam.dppi.fon.interne.services.ITraceService;
import ma.iam.dppi.fon.interne.services.IUtilisateurService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Service;


/**
 * @author Z.BELGHAOUTI
 * 
 */
@Service
public class SecurityApplicationListener implements ApplicationListener<ApplicationEvent> {

	Logger logger = LoggerFactory.getLogger(SecurityApplicationListener.class);
	
    @Autowired
    private ITraceService traceService;
    
    @Autowired
	private IUtilisateurService utilisateurService;
    

    public void onApplicationEvent(ApplicationEvent event) {
        if(event instanceof AuthenticationFailureBadCredentialsEvent) {
    		UsernamePasswordAuthenticationToken source = (UsernamePasswordAuthenticationToken) event.getSource();
    		if(source != null) {
    			String login = source.getPrincipal().toString();
    			traceService.traceConnexion(ActionCode.TENTATIVE_CONNEXION, "Login ou mot de passe incorrect", 
    					"Tentative de connexion", login);
    			Utilisateur user = utilisateurService.getUserByLogin(login);
    			if(user != null) {
    				int failedLoginAttempts = user.getTentativeConnexion() == null ? 0 : user.getTentativeConnexion();
    				user.setTentativeConnexion(++failedLoginAttempts);
    				utilisateurService.save(user);
    			}
    		}
        }
    }

}
