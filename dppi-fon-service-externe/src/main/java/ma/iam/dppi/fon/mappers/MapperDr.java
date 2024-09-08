package ma.iam.dppi.fon.mappers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ma.iam.dppi.fon.communs.domain.Dr;
import ma.iam.dppi.fon.dtos.DrDto;

import org.apache.commons.collections.CollectionUtils;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MapperDr {

	@Autowired
	private DozerBeanMapper mapper;
	
	
	public DrDto entityToDto(Dr dr) {
		if(dr == null) {
			return null;
		}
		DrDto drDto = mapper.map(dr, DrDto.class);
		return drDto;
	}
	
	public Dr dtoToDto(DrDto drDto) {
		if(drDto == null) {
			return null;
		}
		Dr dr = mapper.map(drDto, Dr.class);
		return dr;
	}
	public List<DrDto> listEntityToListDto(List<Dr> drs) {

		if (CollectionUtils.isEmpty(drs))
			return Collections.emptyList();

		List<DrDto> drDtos = new ArrayList<DrDto>();

		for (Dr dr : drs) {
			drDtos.add(entityToDto(dr));
			
		}

		return drDtos;
	}

}
