package ma.iam.dppi.fon.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;


//import ma.iam.dppi.services.ITraceService;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import ma.iam.dppi.fon.dtos.HistoryDto;
import ma.iam.dppi.fon.exception.AppException;
import ma.iam.dppi.fon.exception.UnsupportedFileNameException;
import ma.iam.dppi.fon.exception.UnsupportedFileTypeException;
import ma.iam.dppi.fon.services.IImportDemandeService;

@RequestMapping("/services/imports")
@RestController
public class ImportsController {

	@Autowired
	private IImportDemandeService importCommandeServiceImpl;
	
//	@Autowired
//	private ITraceService traceService;
	
	@PostMapping(value = "/importCommandesSM", produces = { "application/json" }, consumes = {
			"application/javascript", "multipart/form-data", "application/x-www-form-urlencoded" })
	public ResponseEntity<HistoryDto> importCommandesSM(@RequestParam("file") MultipartFile file) {
		validateFile(file);
		return importCommandeServiceImpl.importCommandesSMFile(file);
	}

	@GetMapping("/downloadFile")
	public ResponseEntity<Resource> downloadFile(@RequestParam("id") Long historyId,
			@RequestParam("isLog") Boolean isLog, HttpServletRequest request)
			throws AppException, FileNotFoundException {

		Resource resource = importCommandeServiceImpl.loadFileAsResource(historyId, isLog);
		String contentType = null;
		try {
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		} catch (IOException ex) {
		}
		if (contentType == null) {
			contentType = "application/octet-stream";
		}		
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}
	
	public void validateFile(MultipartFile file) throws UnsupportedFileTypeException, UnsupportedFileNameException {
		String extension = FilenameUtils.getExtension(file.getOriginalFilename());
		if (!SUPPORTED_FILE_TYPES.contains(extension)) {
			throw new UnsupportedFileTypeException("l'extention " + extension + " n'est pas supportée !!");
		}
		if (nameContainsCharToExclude(file.getOriginalFilename())) {
			throw new UnsupportedFileNameException(
					"le nom du fichier " + file.getOriginalFilename() + " n'est pas supportée !!");
		}
	}
	
	public static final List<String> SUPPORTED_FILE_TYPES = Arrays.asList("csv", "xls", "xlsx", "xlsm", "xlsb");

	private static final List<String> CHARS_TO_EXCLUDE = Arrays.asList("<", ">", "%", "$", "|", "!", "?", "=", "$", "*",
			"+", "-", "/", "’", "‘", "’", "^", ")", "(", "[", "]");
	
	public static boolean nameContainsCharToExclude(String fileName) {
		return CHARS_TO_EXCLUDE.stream().anyMatch(fileName::contains);
	}
}
