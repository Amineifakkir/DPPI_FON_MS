package ma.iam.dppi.fon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ma.iam.dppi.fon.domain.EtatDemande;
import ma.iam.dppi.fon.domain.Site;
import ma.iam.dppi.fon.dtos.ChambreAggcDto;
import ma.iam.dppi.fon.dtos.LabelDto;
import ma.iam.dppi.fon.dtos.RepartiteurDto;
import ma.iam.dppi.fon.services.IReferentielService;

@RequestMapping("/services")
@RestController
public class ReferentielController {

	@Autowired
	private IReferentielService referentielsService;

	@GetMapping("/listEtat")
	public List<LabelDto> allEtat() {
		return referentielsService.getAllEtat();
	}

	@GetMapping("/listTypeDemande")
	public List<LabelDto> allTypeDemande() {
		return referentielsService.getAllTypeDemande();
	}

	@GetMapping("/listTypeLiaison")
	public List<LabelDto> allTypeLiaison() {
		return referentielsService.getAllTypeLiaison();
	}

	@GetMapping("/listDR")
	public List<LabelDto> allDR() {
		return referentielsService.getAllDr();
	}

	@GetMapping("/getDR/{id}")
	public LabelDto oneDR(@PathVariable Long id) {
		return referentielsService.getOneDR(id);
	}

	@GetMapping("/listDC/{id}")
	public List<LabelDto> getDCByDR(@PathVariable Long id) {
		return referentielsService.getListDcByIdDr(id);
	}

	@GetMapping("/listCommune/{id}")
	public List<LabelDto> getCommuneByProvince(@PathVariable Long id) {
		return referentielsService.getListCommuneByIdDc(id);
	}

	@GetMapping("/getListRepartiteurByCommune")
	public List<RepartiteurDto> getRepartiteurByCommune(@RequestParam("codeCommune") String codeCommune) {
		return referentielsService.getRepartiteurByCommuneExterne(codeCommune);
	}

	@GetMapping("/getListSrbyReaprtiteur")
	public List<RepartiteurDto> getSousRepartiteurByRepartiteur(@RequestParam("codeRep") String codeRep) {
		return referentielsService.getSousRepartiteurByRepartiteurExterne(codeRep);
	}

	@GetMapping("/getListChambreBySr")
	public List<ChambreAggcDto> getListChambreByCommune(@RequestParam("codeSr") String codeSr) {
		return referentielsService.getChambrerBySousRepartiteurExterne(codeSr);
	}

	@GetMapping("/getListSite")
	public List<Site> getListSite() {

		return referentielsService.getAllSites();

	}
	
	@PostMapping("/saveAll")
	public void saveAll(@RequestBody EtatDemande demandes) {
		referentielsService.addEtatDemande(demandes);
	}

}
