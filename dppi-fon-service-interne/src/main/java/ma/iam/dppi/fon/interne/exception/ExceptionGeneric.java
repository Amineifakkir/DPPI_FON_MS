package ma.iam.dppi.fon.interne.exception;

/**
 * @author Z.BELGHAOUTI
 *
 */
public abstract class ExceptionGeneric extends Exception implements IErrorCodeGeneric {

	
	private static final long serialVersionUID = 6608943503386714224L;
	private IErrorCode code;

	/**
	 * @return the code
	 */
	public IErrorCode getCode() {
		return code;
	}

	public String codeValue() {
		return this.code.value();
	}

	public ExceptionGeneric(IErrorCode code, String message) {
		super(message);
		this.code = code;
	}

	public ExceptionGeneric(IErrorCode code, String message, Throwable cause) {
		super(message, cause);
		this.code = code;
	}

}
