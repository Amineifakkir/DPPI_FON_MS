package ma.iam.dppi.fon.services;

import ma.iam.dppi.fon.constants.ActionCode;

/**
 * 
 * @author Z.BELGHAOUTI
 *
 */
public interface ITraceService {

	void traceOperation(ActionCode code, String description, 
			String operation, String typeOperation,
			String objetModifie,
			String oldValue, String newValue);
	
	void traceConnexion(ActionCode code, String description, 
			String typeConnexion, String login);

}
