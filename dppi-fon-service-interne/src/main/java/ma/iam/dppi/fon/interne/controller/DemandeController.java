package ma.iam.dppi.fon.interne.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ma.iam.dppi.fon.interne.dtos.ConsolidationDto;
import ma.iam.dppi.fon.interne.dtos.CriteriaCommandeDto;
import ma.iam.dppi.fon.interne.dtos.DemandeDto;
import ma.iam.dppi.fon.interne.dtos.DemandeListDto;
import ma.iam.dppi.fon.interne.dtos.InteractionDto;
import ma.iam.dppi.fon.interne.dtos.PieceJointeDemande;
import ma.iam.dppi.fon.interne.dtos.SousLiaisonDto;
import ma.iam.dppi.fon.interne.dtos.TraceCommentaireDto;
import ma.iam.dppi.fon.interne.dtos.ValidateDemandeDto;
import ma.iam.dppi.fon.interne.services.IDemandeService;
import ma.iam.dppi.fon.interne.utils.Utils;

/**
 * @author Z.BELGHAOUTI
 *
 */
@RequestMapping("/services/demande")
@RestController
public class DemandeController {

	private final Logger logger = LoggerFactory.getLogger(DemandeController.class);

	@Autowired
	private IDemandeService demandeService;

	@PostMapping("/countDemandesByParams")
	public Long countDemandesByParams(@RequestBody CriteriaCommandeDto params) throws ParseException {
		String message = Utils.formatMessage("Récupérer le total des demandes par params ");
		logger.info(message);
		return demandeService.getTotalDemandesByCriteres(params);
	}

	@PostMapping("/loadByParams")
	public List<DemandeListDto> loadByParams(@RequestBody CriteriaCommandeDto params) throws ParseException {
		String message = Utils.formatMessage("Récupérer la liste des demandes par params ");
		logger.info(message);
		return demandeService.getAllDemandesByCriteres(params);
	}

	@PostMapping("/validerDemande")
	public Long validerDemande(@RequestBody ValidateDemandeDto params) throws ParseException {
		return demandeService.validerDemande(params);
	}

	@PostMapping("/addReponseInteraction")
	public InteractionDto addReponseInteraction(@RequestBody InteractionDto params) throws ParseException {
		return demandeService.addReponseInteraction(params);
	}

	@PostMapping("/addInteraction")
	public InteractionDto addInteraction(@RequestBody InteractionDto params) throws ParseException {
		return demandeService.addInteraction(params);
	}

	@PostMapping("/addReponseSousLiaison")
	public SousLiaisonDto addReponseSousLiaison(@RequestBody SousLiaisonDto params) throws ParseException {
		return demandeService.addReponseSousLiaison(params);
	}

	@PostMapping("/addComment")
	public TraceCommentaireDto addComment(@RequestBody TraceCommentaireDto params) throws ParseException {
		return demandeService.addComment(params);
	}

	@PostMapping("/addCommentSousLiaison")
	public SousLiaisonDto addCommentSousLiaison(@RequestBody SousLiaisonDto params) throws ParseException {
		return demandeService.addCommentSousLiaison(params);
	}

	@PostMapping("/devalidationSousLiaison")
	public SousLiaisonDto devalidationSousLiaison(@RequestBody SousLiaisonDto params) throws ParseException {
		return demandeService.devalidationSousLiaison(params);
	}

	@PostMapping("/clotureSousLiaison")
	public SousLiaisonDto clotureSousLiaison(@RequestBody SousLiaisonDto params) throws ParseException {
		return demandeService.clotureSousLiaison(params);
	}

	@PostMapping("/addSousLiaison")
	public SousLiaisonDto addNewSousLiaison(@RequestBody SousLiaisonDto params) throws ParseException {
		return demandeService.addNewSousLiaison(params);
	}

	@PostMapping("/removeSousLiaison")
	public boolean removeSousLiaison(@RequestBody SousLiaisonDto params) {
		return demandeService.removeSousLiaison(params);
	}

	@PostMapping("/validateLiaison")
	public Long validateLiaison(@RequestBody ConsolidationDto params) throws ParseException {
		return demandeService.validateLiaison(params);
	}

	@PostMapping("/validateConsolidation")
	public Long validateConsolidation(@RequestBody ValidateDemandeDto params) throws ParseException {
		return demandeService.validateConsolidation(params);
	}

	@GetMapping(value = "/downloadFile/{fileName}")
	public ResponseEntity<InputStreamResource> downloadFile(@PathVariable String fileName) throws IOException {

		File file = demandeService.downloadFile(fileName);
		InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
		return ResponseEntity.ok().body(resource);

	}

	@PostMapping("/addTravauxProgramme")
	public DemandeDto addDemandeTypeTravauxProgramme(@ModelAttribute DemandeDto demandeDto) throws ParseException {
		return demandeService.addDemandeTypeTravaux(demandeDto);
	}

	@PostMapping("/uploadFiles")
	public void getJointByDemande(@ModelAttribute PieceJointeDemande pieceJointeDemande) {
		demandeService.getJointByDemande(pieceJointeDemande.getIdtDemande(), pieceJointeDemande.getMultipartFile());
	}

	@GetMapping("/changeEtatDemande/{idtDemande}/{etatDemande}")
	public void changeEtatDemande(@PathVariable Long idtDemande, @PathVariable String etatDemande) {
		demandeService.changeEtatDemande(idtDemande, etatDemande);
	}

	@GetMapping("/interactionSource/{idtDemande}/{entitySource}")
	public List<InteractionDto> getListInteractionByEntitySource(@PathVariable Long idtDemande,
			@PathVariable String entitySource) {
		return demandeService.getListInteractionByEntitySource(idtDemande, entitySource);
	}

	@PostMapping("/ajouterSousLiaison")
	public void ajouterSousLiaison(@RequestBody SousLiaisonDto sousLiaisonDto) {
		demandeService.ajouterSousLiaison(sousLiaisonDto);
	}

	@PutMapping("/updateSousLiaison")
	public void updateSousLiaison(@RequestBody SousLiaisonDto sousLiaisonDto) {
		demandeService.updateSousLiaison(sousLiaisonDto);
	}
	
	@PostMapping("/saveDemandeWithFile")
	public void saveDemandeWithFile(@ModelAttribute DemandeDto demandeDto) {
		demandeService.saveDemandeWithFile(demandeDto);
	}
	
	@GetMapping("/changEtatLiaison/{idtLiaison}/{codeEtatLiaison}")
	public void changEtatLiaison(@PathVariable Long idtLiaison, @PathVariable String codeEtatLiaison ) {
		demandeService.changeEtatLiaison(idtLiaison, codeEtatLiaison);
	}
	
	@PostMapping("/loadDemandeTravauxProgrammeByParams")
	public List<DemandeListDto> loadDemandeTravauxProgrammeByParams(@RequestBody CriteriaCommandeDto params) throws ParseException {
		String message = Utils.formatMessage("Récupérer la liste des demandes par params ");
		logger.info(message);
		return demandeService.getAllDemandesTravauxProgrammeeByCriteres(params);
	}
	
	@PostMapping("/countDemandesTravauxProgrammeByParams")
	public Long countDemandesTravauxProgrammeByParams(@RequestBody CriteriaCommandeDto params) throws ParseException {
		String message = Utils.formatMessage("Récupérer le total des demandes par params ");
		logger.info(message);
		return demandeService.getTotalDemandesTravauxProgrammeByCriteres(params);
	}

}
