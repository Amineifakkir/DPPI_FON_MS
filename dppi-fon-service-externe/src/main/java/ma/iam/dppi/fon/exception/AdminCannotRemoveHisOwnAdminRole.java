package ma.iam.dppi.fon.exception;


public class AdminCannotRemoveHisOwnAdminRole extends DppiFonException {

	private static final long serialVersionUID = -4196323792990481187L;

	public AdminCannotRemoveHisOwnAdminRole(IErrorCode code, String message) {
		super(code, message);
	}
}
