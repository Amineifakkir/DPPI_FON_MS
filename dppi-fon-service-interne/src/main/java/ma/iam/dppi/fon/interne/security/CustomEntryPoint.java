package ma.iam.dppi.fon.interne.security;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

import ma.iam.dppi.fon.interne.exception.ConditionsNotAcceptedException;
import ma.iam.dppi.fon.interne.exception.OperateurNonExistException;

/**
 * 
 * @author Z.BELGHAOUTI
 *
 */
public class CustomEntryPoint extends BasicAuthenticationEntryPoint {

	@Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authEx) 
    		throws IOException {
        if (authEx instanceof BadCredentialsException){
        	response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
        if(authEx instanceof InsufficientAuthenticationException) {
        	response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
        if (authEx instanceof LockedException){
        	if("COMPTE VERROUILLE".equalsIgnoreCase(authEx.getMessage())) {
        		response.setStatus(470);
        	}
        	if("LOCK_ATTEMPT".equalsIgnoreCase(authEx.getMessage())) {
        		response.setStatus(471);
        	}
        }
        if (authEx instanceof DisabledException) {
        	if("COMPTE DISABLED".equalsIgnoreCase(authEx.getMessage())) {
        		response.setStatus(472);
        	}
        	if("ACCES DENIED".equalsIgnoreCase(authEx.getMessage())) {
        		response.setStatus(403);
        	}
        	if("SESSION EXPIRED".equalsIgnoreCase(authEx.getMessage())) {
        		response.setStatus(473);
        	}
		}
        if (authEx instanceof ConditionsNotAcceptedException){
        	response.setStatus(474);
        }
        if (authEx instanceof OperateurNonExistException){
        	response.setStatus(475);
        }
    }
	
	@Override
	public void afterPropertiesSet() {
		setRealmName("Baeldung");
		super.afterPropertiesSet();
	}
}