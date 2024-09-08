package ma.iam.dppi.fon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ma.iam.dppi.fon.dtos.LibelleDto;
import ma.iam.dppi.fon.services.IProfilService;

/**
 * @author Z.BELGHAOUTI
 *
 */
@RequestMapping("/services/profil")
@RestController
public class ProfilController {

	@Autowired
	private IProfilService profilService;
	
	@GetMapping("/getProfilsExternes")
	public List<LibelleDto> getProfilsExternes() {
		return profilService.getProfilsExternes();
	}
}
