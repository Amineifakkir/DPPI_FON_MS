package ma.iam.dppi.fon.interne.exception;



/**
 * 
 * @author Z.BELGHAOUTI
 * 
 */
public class DataAlreadyExistException extends DppiGcException {

	private static final long serialVersionUID = 1L;
	
	/** Constructeur
	 * @param code code d'erreur
	 * @param message le message d'erreur
	 */
	public DataAlreadyExistException(IErrorCode code, String message) {
		super(code, message);
	}
}
