package ma.iam.dppi.fon.mappers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ma.iam.dppi.fon.domain.EtatLiaison;
import ma.iam.dppi.fon.dtos.EtatLiaisonDto;

import org.apache.commons.collections.CollectionUtils;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MapperEtatLiaison {

	@Autowired
	private DozerBeanMapper mapper;
	
	public EtatLiaisonDto entityToDto(EtatLiaison liaison) {
		if (liaison == null) {
			return null;
		}
		EtatLiaisonDto liaisonDto = mapper.map(liaison, EtatLiaisonDto.class);

		return liaisonDto;
	}

	public EtatLiaison dtoToEntity(EtatLiaisonDto liaisonDto) {
		if (liaisonDto == null) {
			return null;
		}
		EtatLiaison liaison = mapper.map(liaisonDto, EtatLiaison.class);

		return liaison;
	}
	
	public  List<EtatLiaisonDto> listToListDto(List<EtatLiaison> list) {
		if (CollectionUtils.isEmpty(list))
			return Collections.emptyList();

		List<EtatLiaisonDto> liaisonDto = new ArrayList<EtatLiaisonDto>();

		for (EtatLiaison liaison : list) {
			liaisonDto.add(entityToDto(liaison));
			
		}

		return liaisonDto;
	
	}
}
