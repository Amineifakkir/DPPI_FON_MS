package ma.iam.dppi.fon.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author Z.BELGHAOUTI
 * 
 */
public class OperateurNonExistException extends AuthenticationException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4940388115649346324L;

	public OperateurNonExistException(String msg) {
		super(msg);
	}
}
