package ma.iam.dppi.fon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ma.iam.dppi.fon.dtos.EtatLiaisonDto;
import ma.iam.dppi.fon.services.IEtatLiaisonService;

@RequestMapping("/services")
@RestController
public class EtatLiaisonController {

	@Autowired
	private IEtatLiaisonService etatLiaisonService;
	
	@GetMapping("/getListLiaison")
	public List<EtatLiaisonDto> getEtatLiaison() {
		return etatLiaisonService.getListLiaison();
	}
}
