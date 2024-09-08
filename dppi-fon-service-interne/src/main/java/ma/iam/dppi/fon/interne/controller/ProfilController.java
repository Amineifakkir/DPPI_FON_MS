package ma.iam.dppi.fon.interne.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ma.iam.dppi.fon.interne.dtos.LibelleDto;
import ma.iam.dppi.fon.interne.services.IProfilService;

/**
 * @author Z.BELGHAOUTI
 *
 */
@RequestMapping("/services/profil")
@RestController
public class ProfilController {

	@Autowired
	private IProfilService profilService;
	
	@GetMapping("/getProfilsInternes")
	public List<LibelleDto> getProfilsInternes() {
		return profilService.getProfilsInternes();
	}
	
	@GetMapping("/getProfilsExternes")
	public List<LibelleDto> getProfilsExternes() {
		return profilService.getProfilsExternes();
	}
}
