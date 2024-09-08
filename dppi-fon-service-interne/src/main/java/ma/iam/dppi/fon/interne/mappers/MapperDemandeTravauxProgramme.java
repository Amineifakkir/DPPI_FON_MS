package ma.iam.dppi.fon.interne.mappers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.iam.dppi.fon.domain.DemandeTravauxProgramme;
import ma.iam.dppi.fon.interne.dtos.DemandeTravauxProgrammeDto;



/**
 * 
 * @author Z.BELGHAOUTI
 * 
 */

@Service
public class MapperDemandeTravauxProgramme {

	@Autowired
	private MapperLiaison mapperLiaison;


	public DemandeTravauxProgrammeDto toDto(DemandeTravauxProgramme tp) {
		DemandeTravauxProgrammeDto dto = null;
		if (tp != null) {
			dto = new DemandeTravauxProgrammeDto();

			dto.setIdt(tp.getIdt());
			dto.setIdtDemande(tp.getDemande().getIdt());
			dto.setIdtLiaisson(tp.getLiaison().getIdt());
			dto.setLiaisons(mapperLiaison.toDtoLiaison(tp.getLiaison()));

		}
		return dto;
	}

	public List<DemandeTravauxProgrammeDto> toDtos(List<DemandeTravauxProgramme> entities) {
		List<DemandeTravauxProgrammeDto> dtos = new ArrayList<>();
		if (entities != null && !entities.isEmpty()) {
			for (DemandeTravauxProgramme entity : entities) {
				dtos.add(toDto(entity));
			}
		}
		return dtos;
	}
	
	public DemandeTravauxProgramme toEntity(DemandeTravauxProgrammeDto dto) {
		DemandeTravauxProgramme entity = null;
		if (dto != null) {
			entity = new DemandeTravauxProgramme();
			
			
		}
		return entity;
	}

}
