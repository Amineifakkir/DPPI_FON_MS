package ma.iam.dppi.fon.mappers;

import java.util.ArrayList;
import java.util.List;

import ma.iam.dppi.fon.communs.domain.Operateur;
import ma.iam.dppi.fon.dtos.LibelleDto;
import ma.iam.dppi.fon.dtos.OperateurDto;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @author Z.BELGHAOUTI
 *
 */
@Service
public class MapperOperateur {

	@Autowired
	private DozerBeanMapper mapper;
	
	
	public OperateurDto entityToDto(Operateur operateur) {
		
		
		return mapper.map(operateur, OperateurDto.class);
	}
	
	
	public Operateur DtoToEntity(OperateurDto operateurDto) {
		
		
		return mapper.map(operateurDto, Operateur.class);
	}
	
	private MapperOperateur() {
	}

	public static List<LibelleDto> convertToDto(List<Operateur> list) {
		List<LibelleDto> listDto = null;
		if (list == null) {
			return listDto;
		}
		listDto = new ArrayList<>();
		for (Operateur in : list) {
			LibelleDto out = new LibelleDto();
			BeanUtils.copyProperties(in, out);
			listDto.add(out);
		}
		return listDto;
	}
}
