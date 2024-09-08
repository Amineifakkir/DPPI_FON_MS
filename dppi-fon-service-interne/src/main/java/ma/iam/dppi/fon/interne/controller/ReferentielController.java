package ma.iam.dppi.fon.interne.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ma.iam.dppi.fon.interne.dtos.SiteDto;
import ma.iam.dppi.fon.interne.dtos.CommandeListDto;
import ma.iam.dppi.fon.interne.dtos.DirectionDto;
import ma.iam.dppi.fon.interne.services.IReferentielsService;

/**
 * @author Z.BELGHAOUTI
 *
 */
@RequestMapping("/services/referentiel")
@RestController
public class ReferentielController {

	@Autowired
	private IReferentielsService referentielsService;

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

	@GetMapping("/getListCommuneByDc")
	public List<DirectionDto> getListCommuneByDc(@RequestParam("idtDc") Long idtDc) {
		return referentielsService.getListCommuneByDc(idtDc);
	}

	@GetMapping("/getCommuneByIdt")
	public DirectionDto getCommuneByIdt(@RequestParam("idt") Long idt) {
		return referentielsService.getCommuneByIdt(idt);
	}

	@GetMapping("/getListOperateurs")
	public List<DirectionDto> getListOperateurs() {
		return referentielsService.getListOperateur();
	}

	@GetMapping("/getOperateurByIdt")
	public DirectionDto getOperateurByIdt(@RequestParam("idt") Long idt) {
		return referentielsService.getOperateurByIdt(idt);
	}

	@GetMapping("/getListEtatDemande")
	public List<DirectionDto> getListEtatDemande() {
		return referentielsService.getListEtatDemande();
	}

	@GetMapping("/getListEtatLiaison")
	public List<DirectionDto> getListEtatLiaison() {
		return referentielsService.getListEtatLiaison();
	}

	@GetMapping("/getListTypeDemande")
	public List<DirectionDto> getListTypeDemande() {
		return referentielsService.getListTypeDemande();
	}

	@GetMapping("/getListEtatDemandeForDON")
	public List<DirectionDto> getListEtatDemandeForDON() {
		return referentielsService.getListEtatDemandeForDON();
	}

	@GetMapping("/getListEtatDemandeForDEF")
	public List<DirectionDto> getListEtatDemandeForDEF() {
		return referentielsService.getListEtatDemandeForDEF();
	}

	@GetMapping("/getListSiteByDr")
	public List<SiteDto> getListChambreByCommune(@RequestParam("idtDr") String idtDr) {
		return referentielsService.getSitesFromUrlExterne(idtDr);
	}

	@GetMapping("/getListEntite")
	public List<DirectionDto> getListEntite() {
		return referentielsService.getListEntite();
	}

	@GetMapping("/getListEtatDemandeSousLiaison")
	public List<DirectionDto> getListEtatDemandeTroncon() {
		return referentielsService.getListEtatDemandeTroncon();
	}

	@GetMapping("/getListEtatDemandeConsolidation")
	public List<DirectionDto> getListEtatDemandeConsolidation() {
		return referentielsService.getListEtatDemandeConsolidation();
	}

	@GetMapping("/getListLiaison")
	public List<CommandeListDto> getListLiaisonEtatPartage() {
		return referentielsService.getListLiaisonTypePartage();
	}
	
	@GetMapping("/getListRaisonInfaisabilite")
	public List<DirectionDto> getListRaisonInfaisabilite() {
		return referentielsService.getListRaisonInfaisabilite();
	}
	
	@GetMapping("/getListSiteByDr/{idtDr}")
	public List<DirectionDto> getListSiteByDr(@PathVariable Long idtDr) {
		return referentielsService.getListSiteByDr(idtDr);
	}
}
