package ma.iam.dppi.fon.exception;

/**
 * @author Z.BELGHAOUTI
 *
 */
public class DppiFonException extends ExceptionGeneric{


	private static final long serialVersionUID = 193687419648842870L;

	public DppiFonException(IErrorCode code, String message) {
		super(code,message);
	}
    
	public DppiFonException(IErrorCode code, String message,Throwable cause) {
		super(code,message,cause);
	}

	@Override
	public IErrorCode getErrorCode() {		
		return null;
	}
}
