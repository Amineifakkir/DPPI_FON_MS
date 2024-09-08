package ma.iam.dppi.fon.services;

import java.util.List;

import ma.iam.dppi.fon.domain.EtatDemande;
import ma.iam.dppi.fon.domain.Site;
import ma.iam.dppi.fon.dtos.ChambreAggcDto;
import ma.iam.dppi.fon.dtos.LabelDto;
import ma.iam.dppi.fon.dtos.RepartiteurDto;

public interface IReferentielService {

	List<LabelDto> getAllDr();

	LabelDto getOneDR(Long id);

	List<LabelDto> getListDcByIdDr(Long id);

	List<LabelDto> getListCommuneByIdDc(Long id);

	List<LabelDto> getAllEtat();

	List<Site> getAllSites();

	List<LabelDto> getAllTypeDemande();

	List<LabelDto> getAllTypeLiaison();

	List<RepartiteurDto> getRepartiteurByCommuneExterne(String codeCommune);

	List<RepartiteurDto> getSousRepartiteurByRepartiteurExterne(String codeRep);

	List<ChambreAggcDto> getChambrerBySousRepartiteurExterne(String codeSr);
	
	public void addEtatDemande(EtatDemande demandes) ;

}
