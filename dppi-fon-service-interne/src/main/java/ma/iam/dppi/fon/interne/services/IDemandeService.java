package ma.iam.dppi.fon.interne.services;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import ma.iam.dppi.fon.interne.dtos.ConsolidationDto;
import ma.iam.dppi.fon.interne.dtos.CriteriaCommandeDto;
import ma.iam.dppi.fon.interne.dtos.DemandeDto;
import ma.iam.dppi.fon.interne.dtos.DemandeListDto;
import ma.iam.dppi.fon.interne.dtos.InteractionDto;
import ma.iam.dppi.fon.interne.dtos.SousLiaisonDto;
import ma.iam.dppi.fon.interne.dtos.TraceCommentaireDto;
import ma.iam.dppi.fon.interne.dtos.ValidateDemandeDto;


/**
 * 
 * @author Z.BELGHAOUTI
 *
 */
public interface IDemandeService {
	
	List<DemandeListDto> getAllDemandesByCriteres(CriteriaCommandeDto critDto) throws ParseException;
	Long getTotalDemandesByCriteres(CriteriaCommandeDto critDto) throws ParseException;
	Long validerDemande(ValidateDemandeDto dto) throws ParseException;
	InteractionDto addReponseInteraction(InteractionDto dto) throws ParseException;
	InteractionDto addInteraction(InteractionDto dto) throws ParseException;
	SousLiaisonDto addReponseSousLiaison(SousLiaisonDto dto) throws ParseException;
	TraceCommentaireDto addComment(TraceCommentaireDto dto) throws ParseException;
	SousLiaisonDto addCommentSousLiaison(SousLiaisonDto dto) throws ParseException;
	SousLiaisonDto devalidationSousLiaison(SousLiaisonDto dto) throws ParseException;
	SousLiaisonDto clotureSousLiaison(SousLiaisonDto dto) throws ParseException;
	SousLiaisonDto addNewSousLiaison(SousLiaisonDto dto) throws ParseException;
	boolean removeSousLiaison(SousLiaisonDto dto);
	Long validateLiaison(ConsolidationDto dto) throws ParseException;
	Long validateConsolidation(ValidateDemandeDto dto) throws ParseException;
	 DemandeDto addDemandeTypeTravaux(DemandeDto dto) throws ParseException;
	
	public File downloadFile(String fileName) throws IOException;
	 
	 public void getJointByDemande(Long idtDemande, MultipartFile multipartFile);
	 public List<InteractionDto> getListInteractionByEntitySource(Long idtDemande, String entitySource);
	 public void changeEtatDemande(Long idtDemande, String etatDemande);
	 
	 public void ajouterSousLiaison(SousLiaisonDto sousLiaisonDto);
	 
	 public void updateSousLiaison(SousLiaisonDto sousLiaisonDto);
	 
	 public void saveDemandeWithFile(DemandeDto demandeDto);
	 
	 public void changeEtatLiaison(Long idtLiaison, String codeEtatLiaison);
	 
	 public  List<DemandeListDto> getAllDemandesTravauxProgrammeeByCriteres(CriteriaCommandeDto critDto)
				throws ParseException;
	 
	 public Long getTotalDemandesTravauxProgrammeByCriteres(CriteriaCommandeDto critDto) throws ParseException;
}

