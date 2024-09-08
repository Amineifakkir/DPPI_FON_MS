package ma.iam.dppi.fon.exception;

import org.springframework.security.core.AuthenticationException;


/**
 * LimitConsultationException
 * 
 * @author Z.BELGHAOUTI
 * 
 */
@SuppressWarnings("serial")
public class LimitConsultationException extends AuthenticationException {

	public LimitConsultationException(String msg) {
		super(msg);
	}
}
