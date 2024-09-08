package ma.iam.dppi.fon.interne.services;

import java.text.ParseException;
import java.util.List;

import ma.iam.dppi.fon.interne.dtos.CommandeListDto;
import ma.iam.dppi.fon.interne.dtos.CriteriaCommandeDto;
import ma.iam.dppi.fon.interne.dtos.HistoriqueDemandeDto;
import ma.iam.dppi.fon.interne.dtos.HistoriqueTronconDto;
import ma.iam.dppi.fon.interne.dtos.LiaisonDto;

/**
 * 
 * @author Z.BELGHAOUTI
 *
 */
public interface ILiaisonService {

	List<CommandeListDto> getAllCommandesByCriteres(CriteriaCommandeDto critDto) throws ParseException;

	Long getTotalCommandesByCriteres(CriteriaCommandeDto critDto) throws ParseException;

	HistoriqueDemandeDto getHistoriquesByDemande(Long idtDemande);

	HistoriqueTronconDto getHistoriquesByTroncon(Long idtTroncon);

	LiaisonDto updateLiaison(LiaisonDto liaison);
}
