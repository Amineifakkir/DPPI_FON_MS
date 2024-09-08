package ma.iam.dppi.fon.exception;

/**
 * @author K.ELBAGUARI
 *
 */
public class AppException extends ExceptionGeneric{


	private static final long serialVersionUID = 193687419648842870L;

	public AppException(IErrorCode code, String message) {
		super(code,message);
	}
    
	public AppException(IErrorCode code, String message,Throwable cause) {
		super(code,message,cause);
	}

	@Override
	public IErrorCode getErrorCode() {		
		return null;
	}
}
