package ma.iam.dppi.fon.services.implementations;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import ma.iam.dppi.fon.communs.domain.Utilisateur;
import ma.iam.dppi.fon.communs.repository.UtilisateurRepository;
import ma.iam.dppi.fon.constants.Constants;
import ma.iam.dppi.fon.constants.ErrorConstants;
import ma.iam.dppi.fon.domain.ImportHistory;
import ma.iam.dppi.fon.domain.ImportType;
import ma.iam.dppi.fon.dtos.HistoryDto;
import ma.iam.dppi.fon.dtos.LiaisonDto;
import ma.iam.dppi.fon.exception.AppException;
import ma.iam.dppi.fon.exception.ErrorCodeEnum;
import ma.iam.dppi.fon.mappers.HistoryMapper;
import ma.iam.dppi.fon.repository.ImportHistoryRepository;
import ma.iam.dppi.fon.services.IImportDemandeService;
import ma.iam.dppi.fon.services.IUtilisateurService;
import ma.iam.dppi.fon.utils.ExcelHelper;
import ma.iam.dppi.fon.utils.ExcelParserInfo;
import ma.iam.dppi.utils.Utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
@Service
public class ImportCommandeServiceImpl implements IImportDemandeService{
	
	private final Logger logger = LoggerFactory.getLogger(ImportCommandeServiceImpl.class);
	private static String COMMANDE_SM_LOG_FILE_PRIFIX = "CommandeSM_log_";
	private static String COMMANDE_SM_FILE_PRIFIX = "CommandeSM_";
	
	@Autowired
	private ExcelHelper excelHelper;
	@Autowired
	private HistoryMapper historyMapper;
	@Autowired
	private IUtilisateurService userService;
	
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	@Autowired
	private ImportHistoryRepository historyRepository;
	


	
	List<LiaisonDto> listLiaisonToAdd;
	LiaisonDto dto = new LiaisonDto();

	@Override
	public ResponseEntity<HistoryDto> importCommandesSMFile(MultipartFile input) {

		excelHelper.validateFile(input);
		logger.info(
				Utils.getLogParam() 
					+ new MessageFormat(Constants.MESSAGE_DEBUT_CHARGEMENT_FICHIER).format(new Object[] {Constants.DEMANDES_SM})
						+ input.getOriginalFilename());
		int index = 1;
		try (InputStream istream = input.getInputStream(); Workbook workbook = WorkbookFactory.create(istream);) {
			ImportHistory history = new ImportHistory();
			history.setDateImport(new Date());
			File logFile = excelHelper.createFileLog(true, null, COMMANDE_SM_LOG_FILE_PRIFIX);
			File importFile = excelHelper.createFileLog(false, input.getOriginalFilename(), COMMANDE_SM_FILE_PRIFIX);
			Sheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rows = sheet.iterator();
			XSSFWorkbook logWorkbook = excelHelper.createFileExcelLogs(sheet.getRow(0));
			ExcelParserInfo parserInfo = new ExcelParserInfo();
			parserInfo.setIndex(index);
			parserInfo.setWorkbook(logWorkbook);
			parserInfo.setSheet(logWorkbook.getSheetAt(0));
			parserInfo.setHistory(history);
//			boolean isUserValid = validationUtils.invokeValidator(, target, errors);(parserInfo);
			boolean isUserValid = true;
			boolean isOk = isUserValid;
			
			if (isUserValid) {
				listLiaisonToAdd = new ArrayList<LiaisonDto>();
				while (rows.hasNext()) {
					Row currentRow = rows.next();
					if (currentRow.getRowNum() == 0) {
						continue;
					}
					logger.debug(Constants.MESSAGE_TRAITEMENT_LIGNE + currentRow.getRowNum());
					parserInfo.setCurrentRow(currentRow);
					try {
						isOk &= traitCurrentRow(parserInfo);
					} catch (AppException e) {
						logger.error(e.getMessage());
						isOk = false;
					}
				}
				
//				dto.setListDemande(listLiaisonToAdd);
//				commandeService.saveCommandeSmMassif(parserInfo, dto);
			}
			excelHelper.saveExcel(logWorkbook, logFile);
			excelHelper.saveImportExcel(workbook, importFile);
//			logger.info(Utils.getLogParam() + new MessageFormat(Constants.MESSAGE_FIN_CHARGEMENT_FICHIER).format(new Object[] )
//					+ (isOk ? Constants.MESSAGE_SANS_ERREUR : Constants.MESSAGE_AVEC_ERREUR));
			history.setFile(importFile.getName());
			history.setLog(logFile.getName());
			String login = Utils.getCurrentUserLogin();
			history.setImportedBy(login);
			if(login != null) {
				Utilisateur user = utilisateurRepository.findUserExterneByLogin(login);
				if(user != null) {
//					history.setEntite(user.getEntite());
				}
			}
			history.setType(ImportType.COMMANDESM);
			history.setSuccess(isOk);
			history.setRoles(String.join(", ", userService.getUserRoles()));
			history.setDateFinImport(new Date());
			history = historyRepository.save(history);
//			traceService.traceOperation(logFile.getName(), null, "Imports massives des commandes Sites Mobile", 
//					"Import", "Import Commandes Sites Mobile", "Import Commandes Sites Mobile", null, null);
			return new ResponseEntity<>(historyMapper.toDto(history), HttpStatus.OK);
		} catch (IOException e) {
//			traceService.traceOperation(null, null, new MessageFormat(ErrorConstants.MESSAGE_ERROR_CHARGEMENT_FICHIER)
//			.format(new Object[] {e.getMessage(), Constants.COMMANDES_SM}), 
//					"Import", "Import Commandes Sites Mobile", "Import Commandes Sites Mobile", null, null);
			logger.error(Utils.getLogParam() + new MessageFormat(ErrorConstants.MESSAGE_ERROR_CHARGEMENT_FICHIER)
					.format(new Object[] {e.getMessage(), }));
		}
//		traceService.traceOperation(null, null, HttpStatus.INTERNAL_SERVER_ERROR.toString(), 
//				"Import", "Import Commandes Sites Mobile", "Import Commandes Sites Mobile", null, null);
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}


	
	private boolean traitCurrentRow(ExcelParserInfo parserInfo) throws AppException {
		Row currentRow = parserInfo.getCurrentRow();
		String codeErpt = excelHelper.getValue(parserInfo, 0, "Code_ERPT");
		String nomSite2G = excelHelper.getValue(parserInfo, 1, "Nom_2G_SiteIAM");
		String region = excelHelper.getValue(parserInfo, 2, "Region");
		String province = excelHelper.getValue(parserInfo, 3, "Province");
		String commune = excelHelper.getValue(parserInfo, 4, "Commune");
		String typeDemande = excelHelper.getValue(parserInfo, 5, "Type_Demande");
		String interlocuteurERPT = excelHelper.getValue(parserInfo, 6, "InterlocuteurERPT");
		String commentaire = excelHelper.getValue(parserInfo, 7, "Commentaire");
		if (currentRow.getLastCellNum() > 8) {
			excelHelper.addErrorLogRow(parserInfo, Constants.MESSAGE_FORMAT, Constants.MESSAGE_NOMBRE_CELLULE);
			return false;
		}

		boolean isEmpty = checkIfCellsEmpty(parserInfo, codeErpt, nomSite2G, region, province,
				commune, typeDemande, interlocuteurERPT, commentaire);

		if (isEmpty) {
			return false;
		}
		
//		
//		LiaisonDto siteMobilePartageDto = new LiaisonDto();
//		siteMobilePartageDto.setIdt(siteMobile.getIdt());
//		siteMobilePartageDto.setCommentaire(commentaire);
//		siteMobilePartageDto.setInterlocuteurERPT(interlocuteurERPT);
//		siteMobilePartageDto.setIdtTypeDemande(typeDemandeEntity.getIdt());
//		listSiteMobileToAdd.add(siteMobilePartageDto);
		excelHelper.addSuccesRow(parserInfo, Constants.MESSAGE_AJOUT);
		return true;
	}

	private boolean checkIfCellsEmpty(ExcelParserInfo parserInfo, String codeErpt,
			String nomSite2G, String region, String province, String commune, String typeDemande, String interlocuteurERPT, String commentaire) {
		int rowNum = parserInfo.getRowNum();
		String errorCategory = new MessageFormat(Constants.MESSAGE_FORMAT_LIGNE).format(new Object[] {rowNum});
		boolean isEmpty = false;
		if (StringUtils.isBlank(codeErpt)) {
			excelHelper.addErrorLogRow(parserInfo, errorCategory, ErrorConstants.MESSAGE_ERROR_CMD_SM_CODE_ERPT_VIDE);
			isEmpty = true;
		}
		if (nomSite2G == null) {
			excelHelper.addErrorLogRow(parserInfo, errorCategory, ErrorConstants.MESSAGE_ERROR_CMD_SM_NOM_SITE_2G_VIDE);
			isEmpty = true;
		}
		
		if (commune == null) {
			excelHelper.addErrorLogRow(parserInfo, errorCategory, ErrorConstants.MESSAGE_ERROR_CMD_SM_COMMUNE_VIDE);
			isEmpty = true;
		}
		if (StringUtils.isBlank(typeDemande)) {
			excelHelper.addErrorLogRow(parserInfo, errorCategory, ErrorConstants.MESSAGE_ERROR_CMD_SM_TYPE_DEMANDE_VIDE);
			isEmpty = true;
		}
//		if (StringUtils.isBlank(interlocuteurERPT)) {
//			excelHelper.addErrorLogRow(parserInfo, errorCategory, ErrorConstants.MESSAGE_ERROR_CMD_SM_INTERLOCUTEUR_ERPT_VIDE);
//			isEmpty = true;
//		}
		if (StringUtils.isBlank(commentaire)) {
			excelHelper.addErrorLogRow(parserInfo, errorCategory, ErrorConstants.MESSAGE_ERROR_CMD_SM_COMMENTAIRE_VIDE);
			isEmpty = true;
		}
		return isEmpty;
	}



	@Override
	public Resource loadFileAsResource(Long historyId, Boolean isLog)
			throws FileNotFoundException, AppException {
		try {
			Optional<ImportHistory> history = historyRepository.findById(historyId);
			if (history == null) {
				throw new AppException(ErrorCodeEnum.HISTORY_NOT_FOUND, 
						new MessageFormat(ErrorConstants.MESSAGE_ERROR_HISTORIQUE_INTROUVABLE).format(new Object[] {historyId}));
			}
			File file = excelHelper.getHistoryFile(history.get(), isLog);
			Resource resource = new UrlResource(file.toURI());
			if (resource.exists()) {
				return resource;
			} else {
				throw new FileNotFoundException(ErrorConstants.MESSAGE_ERROR_FILE_INTROUVABLE + file.getName());
			}
		} catch (MalformedURLException ex) {
			logger.error(ErrorConstants.MESSAGE_ERROR_FILE_INTROUVABLE, ex);
			throw new FileNotFoundException(ErrorConstants.MESSAGE_ERROR_FILE_INTROUVABLE + ex.getMessage());
		}
	}
}
