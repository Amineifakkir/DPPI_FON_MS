package ma.iam.dppi.fon.interne.mappers;

import java.util.ArrayList;
import java.util.List;

import ma.iam.dppi.fon.domain.TraceEtatDemandeSousLiaison;
import ma.iam.dppi.fon.interne.dtos.TraceEtatSousLiaisonDto;
import ma.iam.dppi.fon.interne.utils.DateUtils;

import org.springframework.stereotype.Service;

/**
 * 
 * @author Z.BELGHAOUTI
 * 
 */
@Service
public class MapperTraceEtatTroncon {
		
	public TraceEtatSousLiaisonDto toDto(TraceEtatDemandeSousLiaison c) {
		TraceEtatSousLiaisonDto dto = null;
		if(c != null) {
			dto = new TraceEtatSousLiaisonDto();
			
			dto.setIdt(c.getIdt());
			if (c.getDateEtat() != null) {
				dto.setDateEtat(DateUtils.toDateTime(c.getDateEtat()));
			}
			if (c.getEtatDemandeSousLiaison() != null) {
				dto.setEtatCode(c.getEtatDemandeSousLiaison().getCode());
				dto.setEtatLabel(c.getEtatDemandeSousLiaison().getLabel());
				dto.setEtatIdt(c.getEtatDemandeSousLiaison().getIdt());
			}
			
			if (c.getSousLiaison() != null) {
				dto.setTronconIdt(c.getSousLiaison().getIdt());
			}
		}
		return dto;
	}
	
	public List<TraceEtatSousLiaisonDto> toDtos(List<TraceEtatDemandeSousLiaison> entities) {
		List<TraceEtatSousLiaisonDto> dtos = new ArrayList<>();
		if(entities != null && !entities.isEmpty()) {
			for(TraceEtatDemandeSousLiaison entity : entities) {
				dtos.add(toDto(entity));
			}			
		}
		return dtos;
	}
	
}
