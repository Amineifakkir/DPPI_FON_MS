package ma.iam.dppi.fon.constants;

/**
 * @author Z.BELGHAOUTI
 *
 */
public interface ErrorConstants {
	
	public String MESSAGE_ERROR_USER_HTTP_CLIENT = "Erreur HttpClientErrorException, au niveau de la recherche des utilisateurs par 'params' ";	
	public String MESSAGE_ERROR_USER_REST_CLIENT = "Erreur RestClientException, au niveau de la recherche des utilisateurs par 'params' ";
	public String MESSAGE_ERROR_USER_INTROUVABLE = "Utilisateur non trouvé !";
	public String MESSAGE_ERROR_FILE_INTROUVABLE = "Fichier non trouvé ";
	public String MESSAGE_ERROR_USER_LOGIN_INTROUVABLE = "L''utilisateur avec le login : {0} est introuvable";
	public String MESSAGE_ERROR_USER_DROIT = "Manque de droits !";
	public String MESSAGE_ERROR_USER_LOGIN_DROIT = "L''utilisateur avec le login : {0} n'a pas les droits pour faire l''injection";
	public String MESSAGE_ERROR_DR_INTROUVABLE = "Pas de DRs impactées !";
	public String MESSAGE_ERROR_DC_INTROUVABLE = "Pas de DCs impactées !";
	public String MESSAGE_ERROR_DR_MULTIPLE = "Plusieurs DRs impactées !";
	public String MESSAGE_ERROR_DC_MULTIPLE = "Plusieurs DCs impactées !";
	public String MESSAGE_ERROR_DR_LISTE_VIDE = "La liste des DRs impacté est vide ";
	public String MESSAGE_ERROR_DC_LISTE_VIDE = "La liste des DCs impacté est vide ";
	public String MESSAGE_ERROR_DR_IMPACTE = "Les DRs : {0} sont impactées alors que l''utilisateur : {1} est un injecteur "
			+ "régional de la DR : {2}";
	public String MESSAGE_ERROR_DC_IMPACTE = "Les DCs : {0} sont impactées alors que l''utilisateur : {1} est un injecteur "
			+ "délégué de la DC : {2}";
	public String MESSAGE_ERROR_DR_PERIMETRE = "DR hors périmètre de l''injecteur !";
	public String MESSAGE_ERROR_DC_PERIMETRE = "DC hors périmètre de l''injecteur !";
	public String MESSAGE_ERROR_PARSE_DATE = "Erreur de parser la date : ";
	public String MESSAGE_ERROR_CHARGEMENT_FICHIER = "Erreur : {0} lors du chargement de fichier d''injection en masse des {1}";
	public String MESSAGE_ERROR_DC_INVALID = "DC invalid !";
	public String MESSAGE_ERROR_DC_LABEL_INTROUVABLE = "DC : {0} est introuvable";
	public String MESSAGE_ERROR_DR_INVALID = "DR invalid !";
	public String MESSAGE_ERROR_DR_LABEL_INTROUVABLE = "DR : {0} est introuvable";
	public String MESSAGE_ERROR_MULTIPLE_DATA = "Il existe plus qu''un{0} {1} avec le {2} ";
	public String MESSAGE_ERROR_COMMUNE_INVALID = "Commune invalid !";
	public String MESSAGE_ERROR_COMMUNE_LABEL_INTROUVABLE = "Commune : {0} est introuvable";
	public String MESSAGE_ERROR_REPARTITEUR_INVALID = "Code répartiteur invalid !";
	public String MESSAGE_ERROR_REPARTITEUR_CODE_INTROUVABLE = "Répartiteur avec le code : {0} est introuvable";
	public String MESSAGE_ERROR_SOUS_REPARTITEUR_INVALID = "Code sous répartiteur invalid !";
	public String MESSAGE_ERROR_SOUS_REPARTITEUR_CODE_INTROUVABLE = "Sous répartiteur avec le code : {0} est introuvable";
	public String MESSAGE_ERROR_HISTORIQUE_INTROUVABLE = "L''historique avec l''id = {0} n''est pas trouvé !";
	public String MESSAGE_ERROR_DATA_FIABLE = "Les données ne sont pas fiable !";
	public String MESSAGE_ERROR_ID_MAPPING_TRONCON = "Le numéro : {0} n''est pas celui du tronçon avec le code : ";
	public String MESSAGE_ERROR_EXPORT = "Erreur lors de la génération de l''export : ";
	public String MESSAGE_ERROR_EXPORT_IOEXCEPTION = "IOException lors de la generation de l''export : ";
	
	public String MESSAGE_ERROR_ONE_DR_IMPACTE = "La DR : {0} est impactée alors que l''utilisateur : {1} est un injecteur "
			+ "régional de la DR : {2}";
	public String MESSAGE_ERROR_ONE_DC_IMPACTE = "La DC : {0} est impactée alors que l''utilisateur : {1} est un injecteur "
			+ "délégué de la DC : {2}";

	// sous répartiteurs
	public String MESSAGE_ERROR_AJOUT_SOUS_REPARTITEUR = "Erreur d'ajout d'un sous répartiteur : utilisateur n'est plus connecté";
	public String MESSAGE_CODE_SOUS_REPARTITEUR_VIDE = "Le code de sous répartiteur ne peut pas être vide";
	public String MESSAGE_NOM_SOUS_REPARTITEUR_VIDE = "Le nom de sous répartiteur ne peut pas être vide";
	public String MESSAGE_NORD_SOUS_REPARTITEUR_VIDE = "La coordonnée nord de sous répartiteur ne peut pas être vide";
	public String MESSAGE_OUEST_SOUS_REPARTITEUR_VIDE = "La coordonnée ouest de sous répartiteur ne peut pas être vide";
	public String MESSAGE_ERROR_ADD_SOUS_REPARTITEUR = "Erreur d'ajout, il existe un autre sous répartiteur avec ce code !";
	public String MESSAGE_ERROR_ADD_SOUS_REPARTITEUR_NORD_NUMBER = "La coordonnée nord de sous répartiteur ne peut pas avoir des caractères";
	public String MESSAGE_ERROR_ADD_SOUS_REPARTITEUR_OUEST_NUMBER = "La coordonnée ouest de sous répartiteur ne peut pas avoir des caractères";
	public String MESSAGE_ERROR_DELETE_SOUS_REPARTITEUR = "Erreur de suppression, il existe plusieurs tronçons en liaision !";
	
	// répartiteurs
	public String MESSAGE_ERROR_AJOUT_REPARTITEUR = "Erreur d'ajout d'un répartiteur : utilisateur n'est plus connecté";
	public String MESSAGE_CODE_REPARTITEUR_VIDE = "Le code de répartiteur ne peut pas être vide";
	public String MESSAGE_NOM_REPARTITEUR_VIDE = "Le nom de répartiteur ne peut pas être vide";
	public String MESSAGE_NORD_REPARTITEUR_VIDE = "La coordonnée nord de répartiteur ne peut pas être vide";
	public String MESSAGE_OUEST_REPARTITEUR_VIDE = "La coordonnée ouest de répartiteur ne peut pas être vide";
	public String MESSAGE_ERROR_ADD_REPARTITEUR = "Erreur d'ajout, il existe un autre répartiteur avec ce code !";
	public String MESSAGE_ERROR_ADD_REPARTITEUR_NORD_NUMBER = "La coordonnée nord de répartiteur ne peut pas avoir des caractères";
	public String MESSAGE_ERROR_ADD_REPARTITEUR_OUEST_NUMBER = "La coordonnée ouest de répartiteur ne peut pas avoir des caractères";
	public String MESSAGE_ERROR_DELETE_REPARTITEUR = "Erreur de suppression, il existe plusieurs sous répartiteurs en liaision !";
	
	// tronçons
	public String MESSAGE_ERROR_AJOUT_TRONCON = "Erreur d''ajout d''un troncon : utilisateur n''est plus connecte";
	public String MESSAGE_ERROR_SUPPRESSION_TRONCON = "Erreur de suppression des troncons : utilisateur n''est plus connecté";
	public String MESSAGE_ERROR_MODIFICATION_CHAMBRE = "Erreur de mise à jour des chambres : utilisateur n''est plus connecté";
	public String MESSAGE_ERROR_MODIFICATION_TRONCON = "Erreur de modification d''un troncon : utilisateur n''est plus connecté";
	public String MESSAGE_ERROR_TRONCON_ID_VIDE = "Le numéro ne peut pas être vide";
	public String MESSAGE_ERROR_TRONCON_CODE_VIDE = "Le code du tronçon ne peut pas être vide";
	public String MESSAGE_ERROR_TRONCON_CHAMBRE1_NORD_VIDE = "La coordonnée nord de la chambre 1 ne peut pas être vide";
	public String MESSAGE_ERROR_TRONCON_CHAMBRE1_OUEST_VIDE = "La coordonnée ouest de la Chambre 1 ne peut pas être vide";
	public String MESSAGE_ERROR_TRONCON_CHAMBRE2_NORD_VIDE = "La coordonnée nord de la Chambre 2 ne peut pas être vide";
	public String MESSAGE_ERROR_TRONCON_CHAMBRE2_OUEST_VIDE = "La coordonnée ouest de la Chambre 2 ne peut pas être vide";
	
	// communes
	public String MESSAGE_LABEL_COMMUNE_VIDE = "Commune ne peut pas être vide";
	public String MESSAGE_ERROR_ADD_COMMUNE = "Erreur d'ajout, il existe une autre commune avec ce code !";
	public String MESSAGE_ERROR_DELETE_COMMUNE = "Erreur de suppression, il existe plusieurs liaisons avec cette commune !";
	public String MESSAGE_ERROR_INEXIST_COMMUNE = "Commune n'existe pas !!";
	// DC
	public String MESSAGE_CODE_DC_VIDE = "Le code de DC ne peut pas être vide";
	public String MESSAGE_ERROR_ADD_DC = "Erreur d'ajout, il existe une autre DC avec ce code !";
	public String MESSAGE_ERROR_DELETE_DC = "Erreur de suppression, il existe plusieurs communes en liaison !";

	// DR
	public String MESSAGE_CODE_DR_VIDE = "Le code de DR ne peut pas être vide";
	public String MESSAGE_ERROR_ADD_DR = "Erreur d'ajout, il existe une autre DR avec ce code !";
	public String MESSAGE_ERROR_DELETE_DR = "Erreur de suppression, il existe plusieurs DCs en liaison !";

	// USER
	public String MESSAGE_USER_DR_VIDE = "DR et Direction centrale ne peuvent pas être vides";
	
	// CHAMBRE
	public String MESSAGE_CODE_CHAMBRE_1_VIDE = "Le code de la chambre 1 ne peut pas être vide";
	public String MESSAGE_CODE_CHAMBRE_2_VIDE = "Le code de la chambre 2 ne peut pas être vide";
	public String MESSAGE_ERROR_CHAMBRE_1_INVALID = "Code Chambre 1 invalid !";
	public String MESSAGE_ERROR_CHAMBRE_2_INVALID = "Code Chambre 2 invalid !";
	public String MESSAGE_ERROR_CHAMBRE_1_CODE_INTROUVABLE = "Chambre 1 avec le code : {0} est introuvable";
	public String MESSAGE_ERROR_CHAMBRE_2_CODE_INTROUVABLE = "Chambre 2 avec le code : {0} est introuvable";
	public String MESSAGE_CODE_TYPE_CH1_VIDE = "Le type chambre 1 ne peut pas être vide";
	public String MESSAGE_CODE_TYPE_CH2_VIDE = "Le type chambre 2 ne peut pas être vide";
	public String MESSAGE_ERROR_TYPE_CH1_INVALID = "Type chambre 1 invalid !";
	public String MESSAGE_ERROR_TYPE_CH2_INVALID = "Type chambre 2 invalid !";
	public String MESSAGE_ERROR_TYPE_CH1_CODE_INTROUVABLE = "Type Chambre 1 avec le code : {0} est introuvable";
	public String MESSAGE_ERROR_TYPE_CH2_CODE_INTROUVABLE = "Type Chambre 2 avec le code : {0} est introuvable";
	
	public String MESSAGE_TYPE_CHAMBRE_VIDE = "Le code de type chambre ne peut pas être vide";
	public String MESSAGE_ERROR_TYPE_CHAMBRE_INVALID = "Type chambre invalid !";
	public String MESSAGE_ERROR_TYPE_CHAMBRE_CODE_INTROUVABLE = "Type chambre avec le code : {0} est introuvable";
	public String MESSAGE_ERROR_TYPE_DEMANDE_INVALID = null;
	public String MESSAGE_ERROR_TYPE_DEMANDE_LABEL_INTROUVABLE = null;
	public String MESSAGE_ERROR_SITE_MOBILE_INVALID = null;
	public String MESSAGE_ERROR_SITE_MOBILE_LABEL_INTROUVABLE = null;
	public String MESSAGE_ERROR_CMD_SM_CODE_ERPT_VIDE = null;
	public String MESSAGE_ERROR_CMD_SM_NOM_SITE_2G_VIDE = null;
	public String MESSAGE_ERROR_CMD_SM_REGION_VIDE = null;
	public String MESSAGE_ERROR_CMD_SM_COMMUNE_VIDE = null;
	public String MESSAGE_ERROR_CMD_SM_TYPE_DEMANDE_VIDE = null;
	public String MESSAGE_ERROR_CMD_SM_COMMENTAIRE_VIDE = null;
	public String MESSAGE_ERROR_OPERATEUR_INVALID = null;
	public String MESSAGE_ERROR_SITE_OPERATEUR_LABEL_INTROUVABLE = null;
	
}
