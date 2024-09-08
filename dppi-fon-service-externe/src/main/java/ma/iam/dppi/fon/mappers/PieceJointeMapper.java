package ma.iam.dppi.fon.mappers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ma.iam.dppi.fon.domain.Demande;
import ma.iam.dppi.fon.domain.PieceJointe;
import ma.iam.dppi.fon.dtos.DemandeDto;
import ma.iam.dppi.fon.dtos.PieceJointeDto;

@Component
public class PieceJointeMapper {

	@Autowired
	private DozerBeanMapper mapper;

	public PieceJointeDto entityToDto(PieceJointe pj) {
		if (pj == null) {
			return null;
		}
		PieceJointeDto dto = mapper.map(pj, PieceJointeDto.class);
		if (pj.getDemande() != null) {
			dto.setDemandeDto(mapper.map(pj.getDemande(), DemandeDto.class));
		}
		return dto;

	}

	public PieceJointe dtoToEntity(PieceJointeDto dto) {
		if (dto == null) {
			return null;
		}
		PieceJointe pj = mapper.map(dto, PieceJointe.class);
		if (dto.getDemandeDto() != null) {
			pj.setDemande(mapper.map(dto.getDemandeDto(), Demande.class));
		}
		return pj;
	}

	public List<PieceJointeDto> listEntityToListDto(List<PieceJointe> pjs) {

		if (CollectionUtils.isEmpty(pjs))
			return Collections.emptyList();

		List<PieceJointeDto> dtos = new ArrayList<PieceJointeDto>();

		for (PieceJointe pj : pjs) {
			dtos.add(entityToDto(pj));

		}

		return dtos;
	}

}
