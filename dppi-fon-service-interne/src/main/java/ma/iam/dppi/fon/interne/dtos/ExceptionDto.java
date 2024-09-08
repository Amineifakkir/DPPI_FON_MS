package ma.iam.dppi.fon.interne.dtos;

import javax.xml.bind.annotation.XmlRootElement;

import ma.iam.dppi.fon.interne.exception.DppiGcException;

/**
 * DTO Exception
 */
@XmlRootElement(name="exception")
public class ExceptionDto {

	/**
	 * Type de l'erreur
	 */

	private String type;
	/**
	 * La cause de l'erreur
	 */

	private String cause;

	/**
	 * Le detail de l'erreur
	 */
	private String message;
	
	/**
	 * Le code de l'erreur
	 */
	private String code;

	/**
	 * @return the cause
	 */
	public String getCause() {
		return cause;
	}

	/**
	 * @param cause
	 *            the cause to set
	 */
	public void setCause(String cause) {
		this.cause = cause;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	public ExceptionDto(Exception exception) {
		this.cause = exception.getStackTrace()[0].toString();
		this.message = exception.getMessage();
		this.type = exception.getClass().getSimpleName();
		if (exception instanceof DppiGcException) {
			this.code = ((DppiGcException) exception).getErrorCode().value();
		}
	}

	public ExceptionDto() {
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
}
