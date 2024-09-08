package ma.iam.dppi.fon.interne.controller;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ma.iam.dppi.fon.interne.dtos.DirectionDto;
import ma.iam.dppi.fon.interne.dtos.ProfilDto;
import ma.iam.dppi.fon.interne.dtos.UserInterneCritDto;
import ma.iam.dppi.fon.interne.dtos.UtilisateurDto;
import ma.iam.dppi.fon.interne.exception.UnsupportedFileNameException;
import ma.iam.dppi.fon.interne.services.IReferentielsService;
import ma.iam.dppi.fon.interne.services.IUtilisateurService;

/**
 * 
 * @author Z.BELGHAOUTI
 *
 */
@RestController
@RequestMapping("/services/user-interne")
public class UtilisateurController {

	@Autowired
	private IUtilisateurService utilisateurService;
	
	@Autowired
	private IReferentielsService referentielsService;

	@GetMapping("/getCurrentUtilisateur")
	public UtilisateurDto getCurrentUtilisateur() {
		return utilisateurService.getCurrentUtilisateur();
	}

	@GetMapping("/getUtilisateursByName")
	public List<UtilisateurDto> getUtilisateursByName(@RequestParam("name") String name) {
		return utilisateurService.getByCompleteName(name);
	}

	@PostMapping("/updateUtilisateur")
	public UtilisateurDto updateUtilisateur(@RequestBody UtilisateurDto u, HttpServletRequest request) {
		return utilisateurService.updateUtilisateur(u, request);
	}

	@GetMapping("/getUtilisateursByParam")
	public ResponseEntity<List<UtilisateurDto>> getUtilisateursByParam(@RequestParam("param") String param,
			HttpServletRequest request) {
		validateParams(param);
		return utilisateurService.getUtilisateursByParam(param, request);
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
	
	@PostMapping("/deleteRoleUser")
	public UtilisateurDto deleteRoleUser(@RequestBody UtilisateurDto u) {
		return utilisateurService.deleteRoleUser(u);
	}
	
	@PostMapping("/getUtilisateursDBByParam")
	public List<UtilisateurDto> getUtilisateursDBByParam(@RequestBody UserInterneCritDto dto) {
		return utilisateurService.getUtilisateursDBByParam(dto);
	}
	
	@GetMapping("/getAllRolesNotAssignToUser")
	public List<ProfilDto> getAllRolesNotAssignToUser(@RequestParam("idtUser") Long idtUser) {
		return utilisateurService.getAllRolesNotAssignToUser(idtUser);
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
	
	@GetMapping("/getListDr")
	public List<DirectionDto> getListDr() {
		return referentielsService.getListDr();
	}
	
	@GetMapping("/getDrByIdt")
	public DirectionDto getDrByIdt(@RequestParam("idt") Long idt) {
		return referentielsService.getDrByIdt(idt);
	}
	
	@GetMapping("/getListDcByDr")
	public List<DirectionDto> getListDcByDr(@RequestParam("idtDr") Long idtDr) {
		return referentielsService.getListDcByDr(idtDr);
	}
	
	@GetMapping("/getDcByIdt")
	public DirectionDto getDcByIdt(@RequestParam("idt") Long idt) {
		return referentielsService.getDcByIdt(idt);
	}
	
	@GetMapping("/getListEntite")
	public List<DirectionDto> getListEntite() {
		return referentielsService.getListEntite();
	}
	
	private void validateParams(String search) throws UnsupportedFileNameException {
		if (nameContainsCharToExclude(search)) {
			throw new UnsupportedFileNameException(
					"le nom : " + search + " n'est pas supportée !!");
		}
		if(search != null && !search.equals("")){
			if(search.contains("../") || search.contains("/")){
				throw new UnsupportedFileNameException(
						"le nom : " + search + " n'est pas supportée !!");
			}				
		}
	}
	
	private static final List<String> CHARS_TO_EXCLUDE = Arrays.asList("<", ">", "%", "$", "|", "!", "?", "=", "$", "*",
			"+", "-", "/", "’", "‘", "’", "^", ")", "(", "[", "]");
	
	private static boolean nameContainsCharToExclude(String fileName) {
		return CHARS_TO_EXCLUDE.stream().anyMatch(fileName::contains);
	}
	
}
