package ma.iam.dppi.fon.interne.exception;


public class AdminCannotRemoveHisOwnAdminRole extends DppiGcException {

	private static final long serialVersionUID = -4196323792990481187L;

	public AdminCannotRemoveHisOwnAdminRole(IErrorCode code, String message) {
		super(code, message);
	}
}
