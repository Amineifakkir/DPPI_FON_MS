package ma.iam.dppi.fon.exception;

public class UnsupportedFileNameException extends IllegalArgumentException {

	private static final long serialVersionUID = -8644204608250261476L;

	public UnsupportedFileNameException(String msg) {
		super(msg);
	}

	public UnsupportedFileNameException(String message, Throwable cause) {
		super(message, cause);
	}
}
