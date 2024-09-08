package ma.iam.dppi.fon.interne.services;

import java.text.ParseException;
import java.util.List;

import ma.iam.dppi.fon.interne.dtos.DemandeListDto;

/**
 * 
 * @author Z.BELGHAOUTI
 *
 */
public interface IReportingDemandeService {
	
	List<DemandeListDto> getListDemandeByCriteres(String dateDebut, String dateFin, Long idDr,
			Long idDc, Long idCommune, Long idTypeDemande, Long idEtatLiaison, 
			Long idEtatDemande, Long idOperateur, String reference) throws ParseException;
}
