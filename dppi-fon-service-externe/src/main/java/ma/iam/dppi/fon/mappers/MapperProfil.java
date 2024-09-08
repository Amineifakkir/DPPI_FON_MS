package ma.iam.dppi.fon.mappers;

import java.util.ArrayList;
import java.util.List;

import ma.iam.dppi.fon.communs.domain.Profil;
import ma.iam.dppi.fon.dtos.LibelleDto;
import ma.iam.dppi.fon.dtos.ProfilDto;

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
public class MapperProfil {

	@Autowired
	private DozerBeanMapper mapper;

	public ProfilDto entityToDto(Profil profil) {
		if (profil == null) {
			return null;
		}
		ProfilDto profilDto = mapper.map(profil, ProfilDto.class);

		return profilDto;
	}

	public Profil dtoToEntity(ProfilDto profilDto) {
		if (profilDto == null) {
			return null;
		}
		Profil profil = mapper.map(profilDto, Profil.class);

		return profil;
	}

	private MapperProfil() {
		super();
	}

	public static List<LibelleDto> convertToDto(List<Profil> list) {
		List<LibelleDto> listDto = null;
		if (list == null) {
			return listDto;
		}
		listDto = new ArrayList<>();
		for (Profil in : list) {
			LibelleDto dto = new LibelleDto();
			BeanUtils.copyProperties(in, dto);
			listDto.add(dto);
		}
		return listDto;
	}
}
