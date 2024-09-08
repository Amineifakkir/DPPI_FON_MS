package ma.iam.dppi.fon.security;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ma.iam.dppi.fon.constants.ActionCode;
import ma.iam.dppi.fon.services.ITraceService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

/**
 * @author Z.BELGHAOUTI
 *
 */
public class CustomLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {

	private final Logger logger = LoggerFactory.getLogger(CustomLogoutSuccessHandler.class);

	@Autowired
	private SessionRegistry sessionRegistry;

	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
    private ITraceService traceService;

	@Value("${disable.multiple.sessions}")
	private String disableMultipleSessions;

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication auth) throws IOException, ServletException {
		logger.info("Deconnexion de l'application ...");
		String username = request.getParameter("username");
		logger.info("Deconnexion de l'application de login: " + username);
		if(username != null) {
			if(disableMultipleSessions.equals("true")){
				removeAuthSession(username);
			}
			Map<String, Integer> expiredMap = DBAuthenticationProvider.expiredMap;
			expiredMap.remove(username);
			expiredMap.remove("expireMT_" + username);
			if(!"".equals(username) && !"null".equalsIgnoreCase(username)) {
				traceService.traceConnexion(ActionCode.DECONNEXION, "Déconnexion de l'application", "Déconnexion", 
						username);
			}
		}
		request.getSession().invalidate();
	}
	private void removeAuthSession(String username) {
		findPrincipal: for (final Object principal : sessionRegistry.getAllPrincipals()) {
			if(principal instanceof UserDetails) {
				final UserDetails user = (UserDetails) principal;
				if(username.equals(user.getUsername())) {
					for (final SessionInformation session : sessionRegistry.getAllSessions(user, true)) {
						logger.info("expire session {} for user '{}' ", session.getSessionId() , username);
						session.expireNow();
						logger.info("removing session {} from registry for user '{}' ", session.getSessionId() , username);
						sessionRegistry.removeSessionInformation(session.getSessionId());
						publisher.publishEvent(AskToExpireSessionEvent.of(session.getSessionId()));
					}
					break findPrincipal;
				}
			} else {
				logger.warn("encountered a session for a none user object {} while invalidating '{}' " , principal, username);
			}
		}
	}

}
