package ma.iam.dppi.fon.interne.controller;

import java.text.ParseException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ma.iam.dppi.fon.interne.dtos.CommandeListDto;
import ma.iam.dppi.fon.interne.dtos.CriteriaCommandeDto;
import ma.iam.dppi.fon.interne.dtos.HistoriqueDemandeDto;
import ma.iam.dppi.fon.interne.dtos.HistoriqueTronconDto;
import ma.iam.dppi.fon.interne.dtos.LiaisonDto;
import ma.iam.dppi.fon.interne.services.ILiaisonService;
import ma.iam.dppi.fon.interne.utils.Utils;

/**
 * @author Z.BELGHAOUTI
 *
 */
@RequestMapping("/services/liaison")
@RestController
public class LiaisonController {

	private final Logger logger = LoggerFactory.getLogger(LiaisonController.class);

	@Autowired
	private ILiaisonService liaisonService;

	@PostMapping("/countCommandesByParams")
	public Long countCommandesByParams(@RequestBody CriteriaCommandeDto params) throws ParseException {
		String message = Utils.formatMessage("Récupérer le total des liaisons par params ");
		logger.info(message);
		return liaisonService.getTotalCommandesByCriteres(params);
	}

	@PostMapping("/loadByParams")
	public List<CommandeListDto> loadByParams(@RequestBody CriteriaCommandeDto params) throws ParseException {
		String message = Utils.formatMessage("Récupérer la liste des Liaisons par params ");
		logger.info(message);
		return liaisonService.getAllCommandesByCriteres(params);
	}

	@GetMapping("/getHistoriquesByDemande")
	public HistoriqueDemandeDto getHistoriquesByDemande(@RequestParam("idtDemande") Long idtDemande) {
		return liaisonService.getHistoriquesByDemande(idtDemande);
	}

	@GetMapping("/getHistoriquesByTroncon")
	public HistoriqueTronconDto getHistoriquesByTroncon(@RequestParam("idtTroncon") Long idtTroncon) {
		return liaisonService.getHistoriquesByTroncon(idtTroncon);
	}

	@PostMapping("/addSiteInLiaison")
	public LiaisonDto loadByParams(@RequestBody LiaisonDto liaisonDto) throws ParseException {

		return liaisonService.updateLiaison(liaisonDto);
	}

}
