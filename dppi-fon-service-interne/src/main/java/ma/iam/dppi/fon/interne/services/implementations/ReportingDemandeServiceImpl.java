package ma.iam.dppi.fon.interne.services.implementations;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.iam.dppi.fon.domain.Demande;
import ma.iam.dppi.fon.interne.dtos.DemandeListDto;
import ma.iam.dppi.fon.interne.mappers.MapperDemande;
import ma.iam.dppi.fon.interne.services.IReportingDemandeService;
import ma.iam.dppi.fon.repository.DemandeRepository;

/**
 * 
 * @author Z.BELGHAOUTI
 *
 */
@Service
public class ReportingDemandeServiceImpl implements IReportingDemandeService {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private DemandeRepository demandeRepository;

//	@Autowired
//	private LiaisonRepository liaisonDaoImpl;

	@Autowired
	private MapperDemande mapperDemande;

	public List<DemandeListDto> getListDemandeByCriteres(String dateDebut, String dateFin, Long idDr, Long idDc,
			Long idCommune, Long idTypeDemande, Long idEtatLiaison, Long idEtatDemande, Long idOperateur,
			String reference) throws ParseException {

		List<DemandeListDto> dtos = new ArrayList<>();
		List<Demande> demandes = null;
		if (dateDebut != null && !"".equals(dateDebut) && dateFin != null && !"".equals(dateFin)) {

			String dDebut = dateDebut + " 00:00:00";
			String dFin = dateFin + " 23:59:00";
			SimpleDateFormat frenchFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date1 = frenchFormat.parse(dDebut);
			Date date2 = frenchFormat.parse(dFin);

			demandes = demandeRepository.findDemandesReportingByParamsDates(date1, date2, idDr, idDc, idCommune,
					idTypeDemande, idEtatLiaison, idEtatDemande, idOperateur, reference);

		} else {
			demandes = demandeRepository.findDemandesReportingByParams(idDr, idDc, idCommune, idTypeDemande,
					idEtatLiaison, idEtatDemande, idOperateur, reference);
		}

		if (demandes != null && !demandes.isEmpty()) {
			dtos = mapperDemande.toDtosDemandes(demandes);
		}

		return dtos;
	}

}
