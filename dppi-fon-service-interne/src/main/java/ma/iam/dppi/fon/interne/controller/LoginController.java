package ma.iam.dppi.fon.interne.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ma.iam.dppi.fon.interne.services.IUtilisateurService;

/**
 * @author Z.BELGHAOUTI
 *
 */
@RestController
@RequestMapping
public class LoginController {

	@Autowired 
    private IUtilisateurService utilisateurService;
	
	@GetMapping("/login")
	public void authentication(@RequestParam("username") String username) {
		utilisateurService.consulterEtModifierSessionIDParLogin(username);        
	}
}
