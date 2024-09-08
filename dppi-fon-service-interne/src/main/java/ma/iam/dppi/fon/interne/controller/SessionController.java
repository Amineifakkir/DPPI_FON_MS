package ma.iam.dppi.fon.interne.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ma.iam.dppi.fon.interne.dtos.UtilisateurDto;
import ma.iam.dppi.fon.interne.services.IUtilisateurService;

/**
 * @author Z.BELGHAOUTI
 *
 */
@RestController
@RequestMapping("/services")
public class SessionController {
	
	@Autowired 
    private IUtilisateurService utilisateurService;
	
	@GetMapping(value = "/session")
	public String getSessionConnected() {
		UtilisateurDto utilisateurDto = utilisateurService.getCurrentUtilisateur();
	    return utilisateurDto.getUserIdSession();
	}
	
	
}
