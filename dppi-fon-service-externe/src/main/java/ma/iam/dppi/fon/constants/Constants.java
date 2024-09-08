package ma.iam.dppi.fon.constants;

/**
 * @author Z.BELGHAOUTI
 *
 */
public interface Constants {
	
	public String DCS = "DCs";
	public String DC = "DC";
	public String COMMUNES = "communes";
	public String COMMUNE = "commune";
	public String REPARTITEURS = "répartiteurs";
	public String REPARTITEUR = "répartiteur";
	public String SOUS_REPARTITEURS = "sous répartiteurs";
	public String SOUS_REPARTITEUR = "sous répartiteur";
	public String TRONCONS = "tronçons";
	public String TRONCON = "tronçon";
	public String UTILISATEURS = "utilisateurs";
	public String UTILISATEUR = "utilisateur";
	public String CHAMBRES = "chambres";
	public String CHAMBRE = "chambre";
	public String TYPE_CHAMBRE = "Type chambre";
//	public String DEMANDES_GC = "DEMANDE LIAISON";
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
	public Object DEMANDES_GC = "DEMANDE LIAISON";
	public Object DEMANDES_SM = null;
	
}
