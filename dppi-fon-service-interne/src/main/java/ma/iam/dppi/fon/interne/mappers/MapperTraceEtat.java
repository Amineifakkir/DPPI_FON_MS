package ma.iam.dppi.fon.interne.mappers;

import java.util.ArrayList;
import java.util.List;

import ma.iam.dppi.fon.domain.TraceEtatDemande;
import ma.iam.dppi.fon.interne.dtos.TraceEtatDto;
import ma.iam.dppi.fon.interne.utils.DateUtils;

import org.springframework.stereotype.Service;

/**
 * 
 * @author Z.BELGHAOUTI
 * 
 */
@Service
public class MapperTraceEtat {
		
	public TraceEtatDto toDto(TraceEtatDemande c) {
		TraceEtatDto dto = null;
		if(c != null) {
			dto = new TraceEtatDto();
			
			dto.setIdt(c.getIdt());
			if (c.getDateEtat() != null) {
				dto.setDateEtat(DateUtils.toDateTime(c.getDateEtat()));
			}
			if (c.getEtatDemande() != null) {
				dto.setEtatCode(c.getEtatDemande().getCode());
				dto.setEtatLabel(c.getEtatDemande().getLabel());
				dto.setEtatIdt(c.getEtatDemande().getIdt());
			}
			
			if (c.getDemande() != null) {
				dto.setDemandeIdt(c.getDemande().getIdt());
			}
		}
		return dto;
	}
	
	public List<TraceEtatDto> toDtos(List<TraceEtatDemande> entities) {
		List<TraceEtatDto> dtos = new ArrayList<>();
		if(entities != null && !entities.isEmpty()) {
			for(TraceEtatDemande entity : entities) {
				dtos.add(toDto(entity));
			}			
		}
		return dtos;
	}
	
}
