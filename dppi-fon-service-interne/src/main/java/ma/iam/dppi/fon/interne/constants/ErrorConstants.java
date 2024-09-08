package ma.iam.dppi.fon.interne.constants;

/**
 * @author Z.BELGHAOUTI
 *
 */
public interface ErrorConstants {
	
	public String ERROR_ANCIEN_PASSWORD = "L'ancien mot de passe est erroné";
	public String MESSAGE_ERROR_PARSE_DATE = "Erreur de parser la date : ";
	public String MESSAGE_ERROR_CHARGEMENT_FICHIER = "Erreur : {0} lors du chargement de fichier d''injection en masse des {1}";
	public String MESSAGE_ERROR_USER_HTTP_CLIENT = "Erreur HttpClientErrorException, au niveau de la recherche des utilisateurs par 'params' ";	
	public String MESSAGE_ERROR_USER_REST_CLIENT = "Erreur RestClientException, au niveau de la recherche des utilisateurs par 'params' ";
	
	// USER
	public String MESSAGE_USER_DR_VIDE = "DR et Direction centrale ne peuvent pas être vides";
	
	public String MESSAGE_ERROR_EXPORT = "Erreur lors de la génération de l''export : ";
	public String MESSAGE_ERROR_EXPORT_IOEXCEPTION = "IOException lors de la generation de l''export : ";
	
}
