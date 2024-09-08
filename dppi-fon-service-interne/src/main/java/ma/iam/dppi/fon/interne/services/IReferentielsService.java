package ma.iam.dppi.fon.interne.services;

import java.util.List;

import ma.iam.dppi.fon.interne.dtos.SiteDto;
import ma.iam.dppi.fon.interne.dtos.CommandeListDto;
import ma.iam.dppi.fon.interne.dtos.DirectionDto;

/**
 * @author Z.BELGHAOUTI
 *
 */
public interface IReferentielsService {

	List<DirectionDto> getListDr();

	DirectionDto getDrByIdt(Long idt);

	List<DirectionDto> getListDcByDr(Long idtDr);

	DirectionDto getDcByIdt(Long idt);

	DirectionDto getCommuneByIdt(Long idt);

	List<DirectionDto> getListCommuneByDc(Long idtDc);

	List<DirectionDto> getListOperateur();

	DirectionDto getOperateurByIdt(Long idt);

	List<DirectionDto> getListEtatLiaison();

	List<DirectionDto> getListEtatDemande();

	List<DirectionDto> getListTypeDemande();

	List<DirectionDto> getListEtatDemandeForDON();

	List<DirectionDto> getListEtatDemandeForDEF();

	List<SiteDto> getSitesFromUrlExterne(String idtDr) ;

	List<DirectionDto> getListEntite();

	List<DirectionDto> getListEtatDemandeTroncon();

	List<DirectionDto> getListEtatDemandeConsolidation();

	public List<CommandeListDto> getListLiaisonTypePartage();
	
	List<DirectionDto> getListRaisonInfaisabilite();
	
	public List<DirectionDto> getListSiteByDr(Long idtDr);
}
