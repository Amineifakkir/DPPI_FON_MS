package ma.iam.dppi.fon.interne.mappers;

import java.util.ArrayList;
import java.util.List;

import ma.iam.dppi.fon.communs.domain.Profil;
import ma.iam.dppi.fon.interne.dtos.LibelleDto;

import org.springframework.beans.BeanUtils;

/**
 * 
 * @author Z.BELGHAOUTI
 * 
 */
public class MapperProfil {

	public static List<LibelleDto> convertToDto(List<Profil> list) {
		if (list == null) {
			return null;
		}
		List<LibelleDto> listDto = new ArrayList<>();
		for (Profil in : list) {
			LibelleDto dto = new LibelleDto();
			BeanUtils.copyProperties(in, dto);
			listDto.add(dto);
		}
		return listDto;
	}
}
