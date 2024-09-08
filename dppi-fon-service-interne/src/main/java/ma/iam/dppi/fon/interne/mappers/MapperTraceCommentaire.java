package ma.iam.dppi.fon.interne.mappers;

import java.util.ArrayList;
import java.util.List;

import ma.iam.dppi.fon.domain.TraceCommentaire;
import ma.iam.dppi.fon.interne.dtos.TraceCommentaireDto;
import ma.iam.dppi.fon.interne.utils.DateUtils;

import org.springframework.stereotype.Service;

/**
 * 
 * @author Z.BELGHAOUTI
 * 
 */
@Service
public class MapperTraceCommentaire {
		
	public TraceCommentaireDto toDto(TraceCommentaire c) {
		TraceCommentaireDto dto = null;
		if(c != null) {
			dto = new TraceCommentaireDto();
			
			dto.setIdt(c.getIdt());
			dto.setCommentaire(c.getCommentaire());
			if (c.getDateCommentaire() != null) {
				dto.setDateCommentaire(DateUtils.toDateTime(c.getDateCommentaire()));
			}
			dto.setDemandeurLogin(c.getDemandeurLogin());
			dto.setDemandeurNom(c.getDemandeurNom());
			dto.setDemandeurPrenom(c.getDemandeurPrenom());
			if (c.getDemande() != null) {
				dto.setDemandeIdt(c.getDemande().getIdt());
			}
			dto.setEntite(c.getEntite());
		}
		return dto;
	}
	
	public List<TraceCommentaireDto> toDtos(List<TraceCommentaire> entities) {
		List<TraceCommentaireDto> dtos = new ArrayList<>();
		if(entities != null && !entities.isEmpty()) {
			for(TraceCommentaire entity : entities) {
				dtos.add(toDto(entity));
			}			
		}
		return dtos;
	}
	
}
