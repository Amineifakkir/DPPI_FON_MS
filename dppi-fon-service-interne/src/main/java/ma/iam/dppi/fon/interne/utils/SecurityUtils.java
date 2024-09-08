package ma.iam.dppi.fon.interne.utils;

import java.sql.Timestamp;
import java.util.Date;

import ma.iam.dppi.fon.communs.domain.Utilisateur;
import ma.iam.dppi.fon.interne.constants.Constants;

/**
 *
 * @author Z.BELGHAOUTI
 */
public class SecurityUtils {


    /**
     * Vérifier l’expiration du compte
     *
     * @param u L'utilisateur à verifier
     */
    public static boolean verifierReglesConnexionInactivite(Utilisateur u) {
        if (u.getDateLastConnection() != null) {
            Timestamp sysdate = new Timestamp(new Date().getTime());
            long nbreJour = (sysdate.getTime() - u.getDateLastConnection().getTime()) / (1000 * 3600 * 24);
            if (nbreJour >= Constants.LOCK_INACTIVE) {
                return true;
            }
        }
        return false;
    }

    /**
     * Permet de vérifier si l'utilisateur à déppaser le seuil de nombre de tentatives
     *
     * @param utilisateur l'utilisateur à vérifier
     * @return true ou false
     */
    public static boolean isAttemptExceeded(Utilisateur utilisateur) {
        return (utilisateur.getTentativeConnexion() != null && utilisateur.getTentativeConnexion() >= Constants.LOCK_ATTEMPT);
    }

    /**
     * Permet de vérifier si un compte est vérouillé
     *
     * @param utilisateur l'utilisateur à vérifier
     * @return true si le compte est verrouillé
     */
    public static boolean isLocked(Utilisateur utilisateur) {
        return (isAttemptExceeded(utilisateur)|| verifierReglesConnexionInactivite(utilisateur));
    }
        
    public static boolean isBlocked(Utilisateur utilisateur) {
        return (utilisateur != null 
        		&& ( utilisateur.getEnabled() == null 
        		|| (utilisateur.getEnabled() != null && !utilisateur.getEnabled()) ));
    }
}
