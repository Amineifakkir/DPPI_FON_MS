package ma.iam.dppi.fon.mappers;

import java.util.ArrayList;
import java.util.List;

import ma.iam.dppi.fon.communs.domain.Dc;
import ma.iam.dppi.fon.dtos.DcDto;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MapperDc {

	@Autowired
	private DozerBeanMapper mapper;
	
	private MapperDr drMapper;
	
	public DcDto entityToDto(Dc dc) {
		if(dc == null) {
			return null;
		}
		DcDto dcDto = mapper.map(dc, DcDto.class);
		if (dc.getDr() != null) {
			dcDto.setDr(drMapper.entityToDto(dc.getDr()));
		}
		return dcDto;
	}
	public Dc dtoToEntity(DcDto dcDto) {
		if(dcDto == null) {
			return null;
		}
		Dc dc = mapper.map(dcDto, Dc.class);
		if (dcDto.getDr() != null) {
			dc.setDr(drMapper.dtoToDto(dcDto.getDr()));
		}
		return dc;
	}
	public List<DcDto> entityListToDtoList(List<Dc> dcList) {
	    if (dcList == null) {
	        return null;
	    }

	    List<DcDto> dcDtoList = new ArrayList<>();
	    for (Dc dc :dcList) {
	        dcDtoList.add(entityToDto(dc));
	    }

	    return dcDtoList;
	}
}
