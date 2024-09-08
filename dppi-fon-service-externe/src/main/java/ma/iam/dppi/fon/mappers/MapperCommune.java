package ma.iam.dppi.fon.mappers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ma.iam.dppi.fon.communs.domain.Commune;
import ma.iam.dppi.fon.dtos.CommuneDto;

import org.apache.commons.collections.CollectionUtils;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MapperCommune {

	@Autowired
	private DozerBeanMapper mapper;
	
	public CommuneDto entityToDto(Commune commune) {
		if(commune == null) {
			return null;
		}
		CommuneDto communeDto = mapper.map(commune, CommuneDto.class);
		return communeDto;

	}
	
	public Commune dtoToEntity(CommuneDto communeDto) {
		if(communeDto == null) {
			return null;
		}
		Commune commune = mapper.map(communeDto, Commune.class);
		return commune;
	}
	
	public List<CommuneDto> listEntityToListDto(List<Commune> communes) {

		if (CollectionUtils.isEmpty(communes))
			return Collections.emptyList();

		List<CommuneDto> communeDtos = new ArrayList<CommuneDto>();

		for (Commune commune : communes) {
			communeDtos.add(entityToDto(commune));
			
		}

		return communeDtos;
	}

}
