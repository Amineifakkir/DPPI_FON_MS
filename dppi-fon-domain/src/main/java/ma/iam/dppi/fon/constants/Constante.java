package ma.iam.dppi.fon.constants;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * 
 * @author Z.BELGHAOUTI
 *
 */
public class Constante {

    public final static DateFormat dfDate = new SimpleDateFormat("dd/MM/yyyy");
    public final static DateFormat dfDateHeure = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    public final static DateFormat DF_DATE_SECONDE = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    // PROFIL EXTERNE
    public final static String PROFIL_CONSULTATION_ERPT = "ERPT_CONSULTATION";
    public final static String PROFIL_MODIFICATION_ERPT = "ERPT_MODIFICATION";
    public final static String PROFIL_ADMINISTRATION_ERPT = "ERPT_ADMINSTRATION";
    
    // PROFIL INTERNE
    public final static String PROFIL_ADMINISTRATEUR = "ADMINISTRATEUR";
    public final static String PROFIL_CHEF_CENTRE = "CHEF_CENTRE";
    public final static String PROFIL_CONSULTATION = "CONSULTATION";

    // changed to 3 after mail from Adnane Raja, sent : lundi 9 novembre 2015 09:23
    public final static int LOCK_ATTEMPT = 3;
    public final static int LOCK_INACTIVE = 60;
    public final static int LOCK_CHANGE_PWD = 90;

    public final static int LIMIT_PWD_HIST = 5;
    
    public final static String oui = "oui";
    public final static String non = "non";
    
    public final static String ETAT_FIABLE = "Fiable";
    public final static String ETAT_NON_FIABLE = "Non fiable: manque d'informations";
    
    public final static String MSG_ND_INEXISTANT = "Ce ND n'existe pas";
    public final static String MSG_NON_FIABLE = "Informations en cours de fiabilisation";
    public final static String MSG_FACT= ". Cette consultation n'est pas facturée";
//    public final static String MSG_NON_FIABLE = "Informations en cours de fiabilisation";
    public final static String MSG_ADRESSE_INEXISTANTE = "Cette adresse n'existe pas";
    
//Externe
    //Technique
    public final static String MSG_WS_SERVER_DOWN="Une erreur est survenue lors de l'appel du Web service : ";
    public final static String MSG_WIMTECH_REQ_EXEC_ERR="Une erreur est survenue lors du traitement de votre requête,  veuillez réessayer";
    //Fonctionnelles
    public final static String MSG_NO_PC_NEARBY="Requête non concluante. Cette consultation n'est pas facturée";
    public final static String MSG_EXT_FIELDS_UNAVAILABLE="Informations en cours de fiabilisation. Cette consultation n'est pas facturée";
    public final static String MSG_FORMAT_ERR="Informations en cours de fiabilisation. Cette consultation n'est pas facturée";
    public final static String MSG_FIELD_NOT_ASSIGNED_WIMTECH="Paramètres d’entrée incomplets";
    public final static String MSG_WIMTECH_UNKNOWN_ERR="Une erreur est survenue lors du traitement de votre requête,  veuillez réessayer";
    //Autres
    public final static String MSG_UNKNOWN_ERR="Une erreur est survenue lors du traitement de votre requête, veuillez réessayer";
//Interne
    //Technique
    public final static String MSG_WS_CNX_ERR="Erreur technique : Problème de communication lors de l'appel du Web service. Merci de contacter l’administrateur";
    public final static String MSG_WS_EX_ERR="Erreur technique : Problème lors de l'exécution du Web service. Merci de contacter l’administrateur ";
  //Fonctionnelles
    public final static String MSG_INCOMPLETE_FIELDS="";
    public final static String MSG_INT_FIELDS_UNAVAILABLE="Informations à fiabiliser.";
    //Autres
    public final static String MSG_OTHER_EXCEPTION="Une erreur est survenue lors du traitement de votre requête, veuillez réessayer";
    public final static String NNRA_POTENTIEL_VIDE="Le champ NNRA Potentiel est vide.";
    
    public final static String MSG_ND_INEXISTANT_INCORRECT = "Ce ND n'existe pas ou format ND incorrect";
    public final static String MSG_ERROR_TECH = "Erreur technique";
}
