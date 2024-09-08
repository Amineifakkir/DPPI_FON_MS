package ma.iam.dppi.fon.interne.services;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import ma.iam.dppi.fon.communs.domain.Utilisateur;
import ma.iam.dppi.fon.interne.dtos.ProfilDto;
import ma.iam.dppi.fon.interne.dtos.UserInterneCritDto;
import ma.iam.dppi.fon.interne.dtos.UtilisateurDto;

import org.springframework.http.ResponseEntity;

/**
 * 
 * @author Z.BELGHAOUTI
 *
 */
public interface IUtilisateurService {

	UtilisateurDto getCurrentUtilisateur();
	
	List<UtilisateurDto> getByCompleteName(String name);

	UtilisateurDto updateUtilisateur(UtilisateurDto u, HttpServletRequest request);
	
	ResponseEntity<List<UtilisateurDto>> getUtilisateursByParam(String param, HttpServletRequest request);

	UtilisateurDto getEditedUser(String login);

	UtilisateurDto checkCurrentUser(boolean connexion) throws ParseException;

	List<String> getUserRoles();
	
	List<ProfilDto> getAllRoles();
	
	void consulterEtModifierSessionIDParLogin(String login);
	
	UtilisateurDto deleteRoleUser(UtilisateurDto u);
	
	List<UtilisateurDto> getUtilisateursDBByParam(UserInterneCritDto dto);
	
	List<ProfilDto> getAllRolesNotAssignToUser(Long idtUser);
	
	Utilisateur getUserByLogin(String login);
	
	UtilisateurDto unlockUser(Long idt);
	
	UtilisateurDto enableUser(Long idt, boolean isEnable);
	
	Utilisateur save(Utilisateur utilisateur);
	
	boolean checkLoginUser(String login, boolean isEdit);
}
