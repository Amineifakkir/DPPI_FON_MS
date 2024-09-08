package ma.iam.dppi.fon.utils;


import ma.iam.dppi.fon.domain.ImportHistory;
import ma.iam.dppi.fon.exception.UnsupportedFileNameException;
import ma.iam.dppi.fon.exception.UnsupportedFileTypeException;
import ma.iam.dppi.utils.Utils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Component
public class ExcelHelper {

	private final Logger logger = LoggerFactory.getLogger(ExcelHelper.class);

	@Value("${uploaded.files.dir}")
	private String fileUploadDir;

	@Value("${file.upload.logs.dir}")
	private String fileUploadLogsDir;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((fileUploadDir == null) ? 0 : fileUploadDir.hashCode());
		result = prime
				* result
				+ ((fileUploadLogsDir == null) ? 0 : fileUploadLogsDir
						.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ExcelHelper other = (ExcelHelper) obj;
		if (fileUploadDir == null) {
			if (other.fileUploadDir != null)
				return false;
		} else if (!fileUploadDir.equals(other.fileUploadDir))
			return false;
		if (fileUploadLogsDir == null) {
			if (other.fileUploadLogsDir != null)
				return false;
		} else if (!fileUploadLogsDir.equals(other.fileUploadLogsDir))
			return false;
		return true;
	}

	public XSSFWorkbook createFileExcelLogs(Row rowLogs) {
		logger.info(Utils.formatMessage("Création du format du fichier log"));
		int i = 0;
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Journalisation");
		CellStyle cellStyle = workbook.createCellStyle();
		XSSFFont font = sheet.getWorkbook().createFont();
		font.setBold(true);
		font.setFontHeightInPoints((short) 12);
		cellStyle.setFont(font);
		XSSFRow rowhead = sheet.createRow(0);
		while(i < rowLogs.getLastCellNum()) {
			XSSFCell cell1 = rowhead.createCell(i);
			cell1.setCellStyle(cellStyle);
			cell1.setCellValue(rowLogs.getCell(i).getStringCellValue());
			i++;
		}
		XSSFCell cell2 = rowhead.createCell(i);
		cell2.setCellStyle(cellStyle);
		cell2.setCellValue("Avec erreur ?");
		
		XSSFCell cell3 = rowhead.createCell(++i);
		cell3.setCellStyle(cellStyle);
		cell3.setCellValue("Catégorie");

		XSSFCell cell4 = rowhead.createCell(++i);
		cell4.setCellStyle(cellStyle);
		cell4.setCellValue("Motif de rejet");

		return workbook;

	}

	private String getFileName(String fileName, String uploadDir, String prefix) {
		return uploadDir + File.separator + prefix + getDateTime(fileName);
	}

	public void addErrorLogRow(ExcelParserInfo parserInfo, String category, String errorMsg) {
		addLogRow(parserInfo, parserInfo.getIndex(), parserInfo.getRowNum(), false, category,
				errorMsg);
	}

	public void addSuccesRow(ExcelParserInfo parserInfo, String category) {
		addLogRow(parserInfo, parserInfo.getIndex(), parserInfo.getRowNum(), true, category, "");
	}

	private void addLogRow(ExcelParserInfo parserInfo, int index, int rowNum, boolean isOk, String category,
			String errorMsg) {
		XSSFRow row = parserInfo.getSheet().createRow(index);
		int i = 0;
		if(parserInfo.getCurrentRow() != null && parserInfo.getCurrentRow().getLastCellNum() > 0) {
			while(i < parserInfo.getCurrentRow().getLastCellNum()) {
				if(parserInfo.getCurrentRow().getCell(i) != null) {
					if(CellType.STRING.equals(parserInfo.getCurrentRow().getCell(i).getCellType())){
						row.createCell(i).setCellValue(parserInfo.getCurrentRow().getCell(i).getStringCellValue());
					}else if(CellType.NUMERIC.equals(parserInfo.getCurrentRow().getCell(i).getCellType())){
						row.createCell(i).setCellValue(parserInfo.getCurrentRow().getCell(i).getNumericCellValue());
					}
				}
				i++;
			}
		}
		//row.createCell(0).setCellValue(rowNum == 0 ? "" : rowNum + "");
		row.createCell(i).setCellValue(isOk ? "Non" : "Oui");
		row.createCell(++i).setCellValue(category);
		row.createCell(++i).setCellValue(errorMsg);
	}

	public File createFileLog(boolean isLog, String fileName, String prefix) throws IOException {
		logger.info(Utils.formatMessage("Création de fichier Excel des " + (isLog ? "logs" : "imports")));
		if (isLog) {
			return createFile(getFileName(fileName, fileUploadLogsDir, prefix), fileUploadLogsDir);
		}
		return createFile(fileUploadDir + File.separator + fileName, fileUploadDir);
	}

	public File createFile(String fileName, String direcoryPath) throws IOException {
		String extension = FilenameUtils.getExtension(fileName);
		if(valideExtensionFile(extension)) {
			File file = new File(fileName);
			boolean created = true;
			if (!file.exists()) {
				createDirectoryIfNotExist(direcoryPath);
				created = file.createNewFile();
			}
			if (!created) {
				throw new IOException("Impossible de créer le fichier des logs. Merci de réessayer!");
			}
			return file;
		}
		return null;
	}

	public void saveExcel(XSSFWorkbook workbook, File file) throws IOException {
		logger.info(Utils.formatMessage("Sauvegarde du fichier excel : " + file.getName()));
		if(workbook.getSheetAt(0) != null && workbook.getSheetAt(0).getRow(0) != null ) {
			for(int i = 0; i< workbook.getSheetAt(0).getRow(0).getLastCellNum() ; i++) {
				workbook.getSheetAt(0).autoSizeColumn(i);
			}
		}
		FileOutputStream fileOut = new FileOutputStream(file);
		workbook.write(fileOut);
		fileOut.close();
		workbook.close();
	}

	private void createDirectoryIfNotExist(String direcoryPath) throws IOException {
		File directory = new File(direcoryPath);
		boolean created = true;
		if (!directory.exists()) {
			logger.info(Utils.formatMessage("Création du dossier path = " + direcoryPath));
			created = directory.mkdir();
		}
		if (!created) {
			throw new IOException("Impossible de créer le dossier des logs. Merci de réessayer!");
		}
	}

	public String getDateTime(String fileName) {
		DateFormat df = new SimpleDateFormat("dd_MM_yyyy HH_mm_ss");
		return df.format(new Date()) + (fileName == null ? ".xlsx" : (fileName.endsWith(".xlsx") ? ".xlsx" : ".xls"));
	}

	public String getValue(Row currentRow, int cellNum) {
		Cell cell = currentRow.getCell(cellNum);
		if (cell == null) {
			return null;
		}
		cell.setCellType(CellType.STRING);
		return cell.getStringCellValue();
	}

	public Integer getNumericValue(Row currentRow, int cellNum) {
		if (currentRow.getCell(cellNum) == null) {
			return null;
		}
		return (int) currentRow.getCell(cellNum).getNumericCellValue();
	}

	public Double getDoubleValue(Row currentRow, int cellNum) {
		if (currentRow.getCell(cellNum) == null) {
			return null;
		}
		return currentRow.getCell(cellNum).getNumericCellValue();
	}

	public String getValue(ExcelParserInfo parserInfo, int cellNum, String columnName)  {
		String value = null;
		try {
			value = this.getValue(parserInfo.getCurrentRow(), cellNum);
		} catch (IllegalStateException e) {
			this.addErrorLogRow(parserInfo, "Le type de données n'est pas accepté !",
					"La valeur de la colonne " + columnName + " doit être une chaine de caractères");
		
		}
		return value;
	}
	
	public Double getGpsValue(ExcelParserInfo parserInfo, int cellNum, String columnName)  {
		Double value = null;
		try {
			String stringValue = this.getValue(parserInfo.getCurrentRow(), cellNum);
			if (StringUtils.isBlank(stringValue)) {
				return value;
			}
			value = Double.parseDouble(stringValue);
		} catch (IllegalStateException | NumberFormatException e) {
			this.addErrorLogRow(parserInfo, "Le type de données n'est pas accepté !",
					"La valeur de la colonne " + columnName + " doit être un nombre");
		
		}
		return value;
	}
	
	public Integer getIntegerValue(ExcelParserInfo parserInfo, int cellNum, String columnName)  {
		Integer value = null;
		try {
			value = this.getNumericValue(parserInfo.getCurrentRow(), cellNum);
		} catch (IllegalStateException e) {
			this.addErrorLogRow(parserInfo, "Le type de données n'est pas accepté !",
					"La valeur de la colonne " + columnName + " doit être un nombre");
		
		}
		return value;
	}
	
	public void saveImportExcel(Workbook workbook, File importFile) throws IOException {
		logger.info(Utils.formatMessage("Sauvegarde du fichier excel : " + importFile.getName()));
		FileOutputStream fileOut = new FileOutputStream(importFile);
		workbook.write(fileOut);
		fileOut.close();
		workbook.close();
	}

	public File getHistoryFile(ImportHistory history, Boolean isLog) {
		String path = Boolean.TRUE.equals(isLog) ? fileUploadLogsDir : fileUploadDir;
		String fileName = Boolean.TRUE.equals(isLog) ? history.getLog() : history.getFile();
		return new File(path + File.separator + fileName);
	}
	
	public static final List<String> SUPPORTED_FILE_TYPES = Arrays.asList("xls", "xlsx", "xlsm", "xlsb");

	private static final List<String> CHARS_TO_EXCLUDE = Arrays.asList("<", ">", "%", "$", "|", "!", "?", "=", "$", "*",
			"+", "-", "/", "’", "‘", "’", "^", ")", "(", "[", "]");
	
	public static boolean nameContainsCharToExclude(String fileName) {
		return CHARS_TO_EXCLUDE.stream().anyMatch(fileName::contains);
	}
	
	public void validateFile(MultipartFile file) throws UnsupportedFileTypeException, UnsupportedFileNameException {
		String extension = FilenameUtils.getExtension(file.getOriginalFilename());
		if (!SUPPORTED_FILE_TYPES.contains(extension)) {
			throw new UnsupportedFileTypeException("l'extention " + extension + " n'est pas supportée !!");
		}
		if (nameContainsCharToExclude(file.getOriginalFilename()) || !validePathAndFileName(file.getOriginalFilename())) {
			throw new UnsupportedFileNameException(
					"le nom du fichier " + file.getOriginalFilename() + " n'est pas supportée !!");
		}
	}
	
	public Boolean validePathAndFileName(String fileName){
		if(fileName != null && !fileName.equals("")){
			if(!fileName.contains("../") && !fileName.contains("/")){
				return true;
			}				
		}
		return false;
	}
	
	public Boolean valideExtensionFile(String extension){
		if(extension != null && !extension.equals("")){
			if(SUPPORTED_FILE_TYPES.contains(extension)){
				return true;
			}				
		}
		return false;
	}


}
