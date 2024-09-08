package ma.iam.dppi.fon.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * FirstConnectionException
 * 
 * @author Z.BELGHAOUTI
 * 
 */
@SuppressWarnings("serial")
public class FirstConnectionException extends AuthenticationException {

	public FirstConnectionException(String msg) {
		super(msg);
	}
}
