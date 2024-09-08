package ma.iam.dppi.fon.interne.exception;

/**
 * @author Z.BELGHAOUTI
 *
 */
public class DppiGcException extends ExceptionGeneric{


	private static final long serialVersionUID = 193687419648842870L;

	public DppiGcException(IErrorCode code, String message) {
		super(code,message);
	}
    
	public DppiGcException(IErrorCode code, String message,Throwable cause) {
		super(code,message,cause);
	}

	@Override
	public IErrorCode getErrorCode() {		
		return null;
	}
}
