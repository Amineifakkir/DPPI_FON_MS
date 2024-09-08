package ma.iam.dppi.fon.interne.constants;

/**
 * @author Z.BELGHAOUTI
 *
 */
public interface Constants {
	
	public String MATRICE_ESCALADE = "Matrices escalades";
	public String NRA = "NRA";
	public static final String DR ="DR";
	public static final String NBRIS ="NBRIS";
	public static final String DEDDP ="DEDDP";
	public static final String DEDDU ="DEDDU";
	public static final String VGMT ="VGMT";
	
	public static final String DEFAULT_DR ="3";
	public static final String DEFAULT_NBRIS ="4";
	public static final String DEFAULT_DEDDP ="24";
	public static final String DEFAULT_DEDDU ="1";
	public static final String DEFAULT_VGMT ="1";
	
	// Import
	public String MESSAGE_DEBUT_CHARGEMENT_FICHIER = "Début de chargement de fichier d''injection en masse des {0} avec le nom : ";
	public String MESSAGE_FIN_CHARGEMENT_FICHIER = "Fin chargement de fichier d''injection en masse des {0} ";
	public String MESSAGE_TRAITEMENT_LIGNE = "Traitement de la ligne : ";
	public String MESSAGE_AVEC_ERREUR = "Avec erreurs";
	public String MESSAGE_SANS_ERREUR = "Sans erreurs";
	public String MESSAGE_FORMAT = "Le format n''est pas respecté !";
	public String MESSAGE_FORMAT_LIGNE = "La ligne {0} ne respecte pas le format !!";
	public String MESSAGE_NOMBRE_CELLULE = "Le nombre de cellules n'est pas respecté";
	public String MESSAGE_AJOUT = "Ajout avec succès";
	public String MESSAGE_MODIFICATION = "Modification avec succès";
	public String MESSAGE_CONSULTER_HISTORIQUE = "Récupérer la liste d'historique par type ";
	
	 public final static int LOCK_ATTEMPT = 3;
	 public final static int LOCK_INACTIVE = 60;
	 
	 public String MESSAGE_AJOUT_DEMANDE = "Ajout nouvelle demande : ";
	
}
