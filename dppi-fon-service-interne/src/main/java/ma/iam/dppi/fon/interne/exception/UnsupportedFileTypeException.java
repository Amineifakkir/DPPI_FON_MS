package ma.iam.dppi.fon.interne.exception;

public class UnsupportedFileTypeException extends IllegalArgumentException {

	private static final long serialVersionUID = -2179177867917103976L;

	public UnsupportedFileTypeException(String msg) {
		super(msg);
	}

	public UnsupportedFileTypeException(String message, Throwable cause) {
		super(message, cause);
	}
}
