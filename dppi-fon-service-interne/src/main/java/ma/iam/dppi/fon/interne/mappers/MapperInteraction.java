package ma.iam.dppi.fon.interne.mappers;

import java.util.ArrayList;
import java.util.List;

import ma.iam.dppi.fon.domain.Interaction;
import ma.iam.dppi.fon.interne.dtos.InteractionDto;
import ma.iam.dppi.fon.interne.utils.DateUtils;

import org.springframework.stereotype.Service;

/**
 * 
 * @author Z.BELGHAOUTI
 * 
 */
@Service
public class MapperInteraction {
	
	public InteractionDto toDto(Interaction d) {
		InteractionDto dto = null;
		if(d != null) {
			dto = new InteractionDto();
			
			dto.setIdt(d.getIdt());
			dto.setEntiteCible(d.getEntiteCible());
			dto.setEntiteSource(d.getEntiteSource());
			if(d.getDateInteraction() != null) {
				dto.setDateInteraction(DateUtils.toDateTime(d.getDateInteraction()));
			}
			
			if (d.getDemande() != null) {
				dto.setDemandeIdt(d.getDemande().getIdt());
				if (d.getDemande().getTypeDemande() != null) {
					dto.setTypeDemandeCode(d.getDemande().getTypeDemande().getCode());
					dto.setTypeDemandeIdt(d.getDemande().getTypeDemande().getIdt());
					dto.setTypeDemandeLabel(d.getDemande().getTypeDemande().getDesignation());
				}
				if (d.getDemande().getEtatDemande() != null) {
					dto.setEtatDemandeCode(d.getDemande().getEtatDemande().getCode());
					dto.setEtatDemandeIdt(d.getDemande().getEtatDemande().getIdt());
					dto.setEtatDemandeLabel(d.getDemande().getEtatDemande().getLabel());
				}
			}
			if(d.getDateReponse() != null) {
				dto.setDateReponse(DateUtils.toDateTime(d.getDateReponse()));
			}
			dto.setInteractionLabel(d.getInteractionLabel());
			dto.setReponse(d.getReponse());
			dto.setRepondeurLogin(d.getRepondeurLogin());
			dto.setRepondeurNom(d.getRepondeurNom());
			dto.setRepondeurPrenom(d.getRepondeurPrenom());
			
			dto.setDemandeurLogin(d.getDemandeurLogin());
			dto.setDemandeurNom(d.getDemandeurNom());
			dto.setDemandeurPrenom(d.getDemandeurPrenom());
			
		}
		return dto;
	}
	
	public List<InteractionDto> toDtos(List<Interaction> entities) {
		List<InteractionDto> dtos = new ArrayList<>();
		if(entities != null && !entities.isEmpty()) {
			for(Interaction entity : entities) {
				dtos.add(toDto(entity));
			}			
		}
		return dtos;
	}
	
}
