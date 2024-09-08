package ma.iam.dppi.fon.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * ConditionsNotAcceptedException
 * 
 * @author Z.BELGHAOUTI
 * 
 */
public class ConditionsNotAcceptedException extends AuthenticationException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4940388115649346324L;

	public ConditionsNotAcceptedException(String msg) {
		super(msg);
	}
}
