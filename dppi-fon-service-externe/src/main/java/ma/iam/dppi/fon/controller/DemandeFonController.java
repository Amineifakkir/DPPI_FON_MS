package ma.iam.dppi.fon.controller;

import java.text.ParseException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import ma.iam.dppi.fon.dtos.AddDemandeDevisDto;
import ma.iam.dppi.fon.dtos.DemandeAccesCriteresDto;
import ma.iam.dppi.fon.dtos.DemandeGcCriteresDto;
import ma.iam.dppi.fon.dtos.DemandeOperationCriteresDto;
import ma.iam.dppi.fon.dtos.LiaisonDemandeAddDto;
import ma.iam.dppi.fon.dtos.LiaisonDemandeUppdateDto;
import ma.iam.dppi.fon.dtos.LiaisonDto;
import ma.iam.dppi.fon.services.ILiaisonService;
import ma.iam.dppi.fon.utils.Utils;

/**
 * @author Z.BELGHAOUTI
 *
 */


@RequestMapping("/services/demandeGc")
@RestController
@RequiredArgsConstructor
public class DemandeFonController {

	/**
	 *
	 */
	private ILiaisonService liaisonService;

	private final Logger logger = LoggerFactory.getLogger(DemandeFonController.class);

	@PostMapping("/countDemandesByParams")
	public Long getCountDemandeGcByParams(@RequestBody DemandeGcCriteresDto critDto) throws ParseException {
		return liaisonService.getCountDemandeGcByParams(critDto);
	}

	@PostMapping("/loadByParams")
	public List<LiaisonDto> getListDemandeGcByParams(@RequestBody DemandeGcCriteresDto critDto) throws ParseException {

		return liaisonService.getListDemandeGcByParams(critDto);
	}

	@PostMapping("/addDemande")
	public Long saveDemande(@RequestBody LiaisonDemandeAddDto demandeGcDto) throws ParseException {
		return liaisonService.saveDemande(demandeGcDto);
	}

	@PostMapping("/addDemandeByType")
	public Long saveDemandeByType(@ModelAttribute DemandeOperationCriteresDto demandeDto)
			throws ParseException {

		return liaisonService.saveDemandeByType(demandeDto);
	}

	@PostMapping("/addDemandeAcces")
	public Long saveDemandeAcces(@RequestBody DemandeAccesCriteresDto demandeDto) throws ParseException {

		return liaisonService.saveDemandeAcces(demandeDto);
	}

	@PutMapping("/updateDemande")
	public LiaisonDemandeUppdateDto updateDemande(@RequestBody LiaisonDemandeUppdateDto dto) throws ParseException {

		return liaisonService.update(dto);
	}

	@PutMapping("/deleteDemande/{idt}")
	public void suppDemande(@PathVariable("idt") Long idt) {

		liaisonService.suppDemande(idt);
	}

	@PutMapping("/archiverDemande/{idt}")
	public void archiberDemande(@PathVariable("idt") Long idt) {

		liaisonService.archiveDemande(idt);
	}

	@PostMapping(value = "/uploadFile", produces = { "application/json" }, consumes = { "application/javascript",
			"multipart/form-data", "application/x-www-form-urlencoded" })
	public Long uplaodFiles(@RequestParam("file") MultipartFile file) {
		Long result = liaisonService.uploadFile(file);
		if (result != null) {
			logger.info(Utils.formatMessage("Upload de pièce jointe : " + file.getOriginalFilename()));
		} else {
			logger.error(Utils.formatMessage("Erreur d'upload de pièce jointe : " + file.getOriginalFilename()));
		}
		return result;
	}

	@PostMapping("/enregistereDemande")
	public void enregistereDemande(@ModelAttribute DemandeAccesCriteresDto demandeUpdateDto) throws ParseException {
		liaisonService.enregistereDemande(demandeUpdateDto);
	}

	@PostMapping("/saveDemandeWithIntervenant")
	public void saveDemandeWithIntervenant(@RequestBody DemandeAccesCriteresDto accesCriteresDto)
			throws ParseException {
		liaisonService.saveDemandeWithIntervenant(accesCriteresDto);
	}

	@GetMapping("/getListLiaison")
	public List<LiaisonDto> getListLiaison() {
		return liaisonService.getListLiaison();
	}

	@PostMapping("/addDemandeSignalisation")
	public void addDemandeSignalisation(@ModelAttribute DemandeAccesCriteresDto demandeDto) throws ParseException {
		liaisonService.addDemandeSignalisation(demandeDto);
	}

	@GetMapping("/getListLiaisonFaisable")
	public List<LiaisonDto> getListLiaisonFaisable() {
		return liaisonService.getListLiaisonFaisable();
	}

	@PostMapping("/addDemandeDevis")
	public void addDemandeDevis(@RequestBody AddDemandeDevisDto demandeUpdateDto) throws ParseException {
		 liaisonService.addDemandeDevis(demandeUpdateDto);
	}
//	@PostMapping(value = "/importCommandesSM", produces = { "application/json" }, consumes = {
//			"application/javascript", "multipart/form-data", "application/x-www-form-urlencoded" })
//	public ResponseEntity<HistoryDto> importCommandesSM(@RequestParam("file") MultipartFile file) {
//		validateFile(file);
//		return importCommandeServiceImpl.importCommandesSMFile(file);
//	}
//	public void validateFile(MultipartFile file) throws UnsupportedFileTypeException, UnsupportedFileNameException {
//		String extension = FilenameUtils.getExtension(file.getOriginalFilename());
//		if (!SUPPORTED_FILE_TYPES.contains(extension)) {
//			throw new UnsupportedFileTypeException("l'extention " + extension + " n'est pas supportée !!");
//		}
//		if (nameContainsCharToExclude(file.getOriginalFilename())) {
//			throw new UnsupportedFileNameException(
//					"le nom du fichier " + file.getOriginalFilename() + " n'est pas supportée !!");
//		}
//	}
//	
//	public static final List<String> SUPPORTED_FILE_TYPES = Arrays.asList("csv", "xls", "xlsx", "xlsm", "xlsb");
//	private static final List<String> CHARS_TO_EXCLUDE = Arrays.asList("<", ">", "%", "$", "|", "!", "?", "=", "$", "*",
//			"+", "-", "/", "’", "‘", "’", "^", ")", "(", "[", "]");
//	
//	public static boolean nameContainsCharToExclude(String fileName) {
//		return CHARS_TO_EXCLUDE.stream().anyMatch(fileName::contains);
//	}
//	

}
