package ma.iam.dppi.fon.interne.services;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import ma.iam.dppi.fon.communs.domain.Utilisateur;
import ma.iam.dppi.fon.interne.dtos.ProfilDto;
import ma.iam.dppi.fon.interne.dtos.UserCriteresDto;
import ma.iam.dppi.fon.interne.dtos.UtilisateurDto;

/**
 * 
 * @author Z.BELGHAOUTI
 *
 */
public interface IUtilisateurExterneService {

	UtilisateurDto getCurrentUtilisateur();

	UtilisateurDto updateUtilisateur(UtilisateurDto u, HttpServletRequest request);

	UtilisateurDto getEditedUser(String login);

	UtilisateurDto checkCurrentUser(boolean connexion) throws ParseException;

	List<String> getUserRoles();
	
	List<ProfilDto> getAllRoles();
	
	void consulterEtModifierSessionIDParLogin(String login);
	
	List<UtilisateurDto> getUtilisateursDBByParam(UserCriteresDto dto);
	
	Utilisateur getUserByLogin(String login);
	
	UtilisateurDto unlockUser(Long idt);
	
	UtilisateurDto enableUser(Long idt, boolean isEnable);
	
	Utilisateur save(Utilisateur utilisateur);
	
	boolean checkLoginUser(String login, boolean isEdit);
}
