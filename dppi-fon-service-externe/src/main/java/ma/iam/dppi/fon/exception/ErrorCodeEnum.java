package ma.iam.dppi.fon.exception;

/**
 * @author Z.BELGHAOUTI
 *
 */
public enum ErrorCodeEnum implements IErrorCode {

	ALPHA_NUM_ERR("10"), 
	CODE_UNIQUE("11"), 
	LABEL_UNIQUE("12"), 
	HISTORY_NOT_FOUND("13"),
	ERROR_PSW("14"),
	ERROR_CRUD("15"),
	AUTH("16")
	;

	private String code;

	private ErrorCodeEnum(String code) {
		this.code = code;
	}

	@Override
	public String value() {
		return code;
	}

}
