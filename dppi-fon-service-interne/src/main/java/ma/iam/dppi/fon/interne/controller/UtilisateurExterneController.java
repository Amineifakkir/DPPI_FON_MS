package ma.iam.dppi.fon.interne.controller;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ma.iam.dppi.fon.interne.dtos.OperateurDto;
import ma.iam.dppi.fon.interne.dtos.ProfilDto;
import ma.iam.dppi.fon.interne.dtos.UserCriteresDto;
import ma.iam.dppi.fon.interne.dtos.UtilisateurDto;
import ma.iam.dppi.fon.interne.services.IOperateurService;
import ma.iam.dppi.fon.interne.services.IUtilisateurExterneService;

/**
 * 
 * @author Z.BELGHAOUTI
 *
 */
@RestController
@RequestMapping("/services/user-externe")
public class UtilisateurExterneController {

	@Autowired
	private IUtilisateurExterneService utilisateurService;
	
	@Autowired
	private IOperateurService operateurService;

	@GetMapping("/getCurrentUtilisateur")
	public UtilisateurDto getCurrentUtilisateur() {
		return utilisateurService.getCurrentUtilisateur();
	}
	
	@PostMapping("/saveUtilisateur")
	public UtilisateurDto saveUtilisateur(@RequestBody UtilisateurDto u, HttpServletRequest request) {
		return utilisateurService.updateUtilisateur(u, request);
	}

	@GetMapping("/getEditedUser")
	public UtilisateurDto getEditedUser(@RequestParam("param") String login) {
		return utilisateurService.getEditedUser(login);
	}

	@GetMapping("/checkCurrentUser")
	public UtilisateurDto checkCurrentUser(@RequestParam("connexion") boolean connexion) throws ParseException {
		return utilisateurService.checkCurrentUser(connexion);
	}
	
	@GetMapping("/getAllRoles")
	public List<ProfilDto> getAllRoles() {
		return utilisateurService.getAllRoles();
	}
	
	@PostMapping("/getUtilisateursDBByParam")
	public List<UtilisateurDto> getUtilisateursDBByParam(@RequestBody UserCriteresDto dto) {
		return utilisateurService.getUtilisateursDBByParam(dto);
	}
	
	@GetMapping("/unlockUser")
	public UtilisateurDto unlockUser(@RequestParam("idt") Long idt) {
		return utilisateurService.unlockUser(idt);
	}
	
	@GetMapping("/enableUser")
	public UtilisateurDto enableUser(@RequestParam("idt") Long idt, 
			@RequestParam("enable") boolean enable) {
		return utilisateurService.enableUser(idt, enable);
	}
	
	@GetMapping("/checkLoginUser")
	public boolean checkLoginUser(@RequestParam("login") String login, @RequestParam("isEdit") boolean isEdit) {
		return utilisateurService.checkLoginUser(login, isEdit);
	}
	
	@GetMapping("/getAllOperateurs")
	public List<OperateurDto> getAllOperateurs() {
		return operateurService.getAllOperateurs();
	}
	
}
