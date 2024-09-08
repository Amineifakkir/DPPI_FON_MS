package ma.iam.dppi.fon.interne.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ma.iam.dppi.fon.interne.dtos.OperateurDto;
import ma.iam.dppi.fon.interne.services.IOperateurService;

/**
 * @author Z.BELGHAOUTI
 *
 */
@RequestMapping("/services/operateur")
@RestController
public class OperateurController {

	@Autowired
	private IOperateurService operateurService;
	
	@GetMapping("/getOperateurById/{idt}")
	public List<OperateurDto> getOperateurById(@PathVariable Long idt) {
		return operateurService.getOperateurById(idt);
	}
	
	@GetMapping("/getAllOperateurs")
	public List<OperateurDto> getAllOperateurs() {
		return operateurService.getAllOperateurs();
	}
	
	@GetMapping("/getListeLibelleOperateurs")
	public List<String> getListeLibelleOperateurs() {
		return operateurService.getListeLibelleOperateurs();
	}
}
