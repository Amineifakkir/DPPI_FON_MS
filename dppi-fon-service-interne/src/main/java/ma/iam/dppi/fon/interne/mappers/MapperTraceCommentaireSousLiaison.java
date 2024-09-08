package ma.iam.dppi.fon.interne.mappers;

import java.util.ArrayList;
import java.util.List;

import ma.iam.dppi.fon.domain.TraceCommentaireSousLiaison;
import ma.iam.dppi.fon.interne.dtos.TraceCommentaireSousLiaisonDto;
import ma.iam.dppi.fon.interne.utils.DateUtils;

import org.springframework.stereotype.Service;

/**
 * 
 * @author Z.BELGHAOUTI
 * 
 */
@Service
public class MapperTraceCommentaireSousLiaison {
		
	public TraceCommentaireSousLiaisonDto toDto(TraceCommentaireSousLiaison c) {
		TraceCommentaireSousLiaisonDto dto = null;
		if(c != null) {
			dto = new TraceCommentaireSousLiaisonDto();
			
			dto.setIdt(c.getIdt());
			dto.setCommentaire(c.getCommentaire());
			if (c.getDateCommentaire() != null) {
				dto.setDateCommentaire(DateUtils.toDateTime(c.getDateCommentaire()));
			}
			dto.setDemandeurLogin(c.getDemandeurLogin());
			dto.setDemandeurNom(c.getDemandeurNom());
			dto.setDemandeurPrenom(c.getDemandeurPrenom());
			if (c.getSousLiaison() != null) {
				dto.setTronconIdt(c.getSousLiaison().getIdt());
			}
			dto.setEntite(c.getEntite());
		}
		return dto;
	}
	
	public List<TraceCommentaireSousLiaisonDto> toDtos(List<TraceCommentaireSousLiaison> entities) {
		List<TraceCommentaireSousLiaisonDto> dtos = new ArrayList<>();
		if(entities != null && !entities.isEmpty()) {
			for(TraceCommentaireSousLiaison entity : entities) {
				dtos.add(toDto(entity));
			}			
		}
		return dtos;
	}
	
}
