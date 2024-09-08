package ma.iam.dppi.fon.interne.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import ma.iam.dppi.fon.constants.ActionCode;
import ma.iam.dppi.fon.interne.constants.ErrorConstants;
import ma.iam.dppi.fon.interne.dtos.DemandeListDto;
import ma.iam.dppi.fon.interne.services.IReportingDemandeService;
import ma.iam.dppi.fon.interne.services.ITraceService;
import ma.iam.dppi.fon.interne.utils.DateUtils;
import ma.iam.dppi.fon.interne.utils.Utils;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Z.BELGHAOUTI
 *
 */
@RequestMapping("/services")
@RestController
public class ReportingController {

	private final Logger logger = LoggerFactory.getLogger(ReportingController.class);

	@Autowired
	private IReportingDemandeService reportingDemandeService;
	@Autowired
	private ITraceService traceService;
	

	private static final String JASPER_FILE_DEMANDE = "demandesList.jasper";
	private static final String CONTENT_TYPE_EXCEL = "application/vnd.ms-excel";

	@RequestMapping("/commandesListReportGestion.xlsx")
	public void exportAccesPeriode(
			@RequestParam(value = "dateDebut", required = false) String dateDebut,
			@RequestParam(value = "dateFin", required = false) String dateFin,
			@RequestParam(value = "idDr", required = false) Long idDr,
			@RequestParam(value = "idDc", required = false) Long idDc,
			@RequestParam(value = "idCommune", required = false) Long idCommune,
			@RequestParam(value = "idTypeDemande", required = false) Long idTypeDemande,
			@RequestParam(value = "idEtatLiaison", required = false) Long idEtatLiaison,
			@RequestParam(value = "idEtatDemande", required = false) Long idEtatDemande,
			@RequestParam(value = "idOperateur", required = false) Long idOperateur,
			@RequestParam(value = "reference", required = false) String reference,
			OutputStream out, HttpServletResponse response) throws ParseException {
		response.setContentType(CONTENT_TYPE_EXCEL);
		String dateExport = DateUtils.dateTimeFileName(new Date());
		response.setHeader("Content-Disposition", "attachment; filename=liste_demandes_" + dateExport + ".xlsx");
		List<DemandeListDto> listResultats = reportingDemandeService
				.getListDemandeByCriteres(dateDebut, dateFin, idDr, idDc, idCommune, idTypeDemande, 
						idEtatLiaison, idEtatDemande, idOperateur, reference);
		Map<String, Object> parameters = new HashMap<>();
		
		String operation = "Export Excel de la liste des demandes par critères Date début: " + dateDebut 
				+ ", Date Fin: " + dateFin + ", DR: " + idDr + ", DC: " + idDc + ", Commune: " + idCommune
				+ ", Type Demande: " + idTypeDemande + ", Etat Liaison: " + idEtatLiaison 
				+ ", Etat Demande: " + idEtatDemande + ", Opérateur: " + idOperateur 
				+ " et Référence: " + reference;
		traceService.traceOperation(ActionCode.EXPORT, operation, operation, 
				"EXPORT", "Demande", null, null);
		renderExport(listResultats, parameters, out, "xlsx", JASPER_FILE_DEMANDE);
	}
	
	
	@SuppressWarnings({ "rawtypes" })
	private void renderExport(List listResultats, Map<String, Object> parameters, 
			OutputStream out, String fileType, String jasperName) {
		
		if (listResultats == null) {
			listResultats = new ArrayList<>();
		}
		try {
			if ("xlsx".equalsIgnoreCase(fileType)) {
				out.write(generateXlsx(jasperName, listResultats, parameters));
			} else if ("xls".equalsIgnoreCase(fileType)) {
				out.write(generateXls(jasperName, listResultats, parameters));
			}
		} catch (JRException e) {
			logger.error(Utils.formatMessage(ErrorConstants.MESSAGE_ERROR_EXPORT), e);
		} catch (IOException e) {
			logger.error(Utils.formatMessage(ErrorConstants.MESSAGE_ERROR_EXPORT_IOEXCEPTION), e);
		}
	}
	
	private byte[] generateXlsx(String report, List<?> datas, Map<String, Object> parameters) throws JRException {

		InputStream stream = null;
		JasperPrint jasperPrint = null;
		try {
			stream = new ClassPathResource(report).getInputStream();
			if (stream != null) {
				JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(datas);
				ByteArrayOutputStream outputByteArray = new ByteArrayOutputStream();
				jasperPrint = JasperFillManager.fillReport(stream, parameters, jrBeanCollectionDataSource);

				JRXlsxExporter xlsExporter = new JRXlsxExporter();
				xlsExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
				xlsExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputByteArray));

				SimpleXlsxReportConfiguration xlsReportConfiguration = new SimpleXlsxReportConfiguration();
				xlsReportConfiguration.setOnePagePerSheet(false);
				xlsReportConfiguration.setRemoveEmptySpaceBetweenRows(true);
				xlsReportConfiguration.setDetectCellType(true);
				xlsReportConfiguration.setWhitePageBackground(false);
				xlsExporter.setConfiguration(xlsReportConfiguration);
				xlsExporter.exportReport();
				return outputByteArray.toByteArray();
			}
		} catch (IOException e) {
			logger.error(Utils.formatMessage(ErrorConstants.MESSAGE_ERROR_EXPORT_IOEXCEPTION), e);
		} finally {
			closeInputStreamSafely(stream);
		}
		return null;

	}

	private byte[] generateXls(String report, List<?> datas, Map<String, Object> parameters) throws JRException {

		InputStream stream = null;
		JasperPrint jasperPrint = null;
		try {
			stream = new ClassPathResource(report).getInputStream();
			if (stream != null) {
				JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(datas);
				ByteArrayOutputStream outputByteArray = new ByteArrayOutputStream();
				jasperPrint = JasperFillManager.fillReport(stream, parameters, jrBeanCollectionDataSource);

				JRXlsExporter xlsExporter = new JRXlsExporter();
				xlsExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
				xlsExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputByteArray));

				SimpleXlsReportConfiguration xlsReportConfiguration = new SimpleXlsReportConfiguration();
				xlsReportConfiguration.setOnePagePerSheet(false);
				xlsReportConfiguration.setRemoveEmptySpaceBetweenRows(true);
				xlsReportConfiguration.setDetectCellType(true);
				xlsReportConfiguration.setWhitePageBackground(false);
				xlsExporter.setConfiguration(xlsReportConfiguration);
				xlsExporter.exportReport();
				return outputByteArray.toByteArray();
			}
		} catch (IOException e) {
			logger.error(Utils.formatMessage(ErrorConstants.MESSAGE_ERROR_EXPORT_IOEXCEPTION), e);
		} finally {
			closeInputStreamSafely(stream);
		}
		return null;

	}
	
	public void closeInputStreamSafely(InputStream stream) {
		if (stream != null) {
			try {
				stream.close();
			} catch (IOException e) {
				logger.error(Utils.formatMessage("Erreur de la fermeture du fichier excel."), e);
			}
		}
	}
}
