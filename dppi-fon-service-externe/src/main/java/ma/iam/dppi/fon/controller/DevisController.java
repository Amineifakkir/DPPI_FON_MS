package ma.iam.dppi.fon.controller;

import java.text.ParseException;
import java.util.List;

import ma.iam.dppi.fon.domain.Devis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ma.iam.dppi.fon.dtos.DevisDto;
import ma.iam.dppi.fon.services.IDevisService;

@RequestMapping("/services/devis")
@RestController
public class DevisController {

	@Autowired
	private IDevisService devisService;

	@PostMapping("/saveDevis")
	public DevisDto saveDevis(@RequestBody DevisDto devisDto) throws ParseException {

		return devisService.saveDevis(devisDto);
	}

	@GetMapping("/devisExistans/{idtDemande}")
	public Boolean getDevisByDemande(@PathVariable Long idtDemande) {
		return devisService.devisExistans(idtDemande);
	}

	@GetMapping("/getDevisByIdtDemande/{idtDemande}")
	public List<DevisDto> getDevisByIdtDemande(@PathVariable Long idtDemande) {
		return devisService.getDevisByDemande(idtDemande);
	}

	@GetMapping("/getDevis/{idtDevis}")
	public Devis getDevisbyId(@PathVariable Long idtDevis) {
		return devisService.getDevisById(idtDevis);
	}
}
