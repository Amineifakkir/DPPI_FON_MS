package ma.iam.dppi.fon.interne.exception;

import org.springframework.security.core.AuthenticationException;


/**
 * ForcedChangePwdException
 * 
 * @author Z.BELGHAOUTI
 * 
 */
@SuppressWarnings("serial")
public class ForcedChangePwdException extends AuthenticationException {

	public ForcedChangePwdException(String msg) {
		super(msg);
	}
}
