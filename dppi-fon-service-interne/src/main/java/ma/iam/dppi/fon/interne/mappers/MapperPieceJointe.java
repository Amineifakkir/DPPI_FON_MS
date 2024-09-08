package ma.iam.dppi.fon.interne.mappers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import ma.iam.dppi.fon.domain.PieceJointe;
import ma.iam.dppi.fon.interne.dtos.PieceJointeDto;

@Service
public class MapperPieceJointe {



	public PieceJointeDto toDtoPieceJointe(PieceJointe pieceJoint) {
		PieceJointeDto dto = null;
		if (pieceJoint != null) {
			dto = new PieceJointeDto();
			dto.setIdt(pieceJoint.getIdt());
			dto.setFileName(pieceJoint.getFileName());
			dto.setOriginFileName(pieceJoint.getOriginFileName());
			if(pieceJoint.getDemande()!=null) {
//				Demande demande = demandeRepository.findById(dto.getIdt()).get();
				dto.setIdt(pieceJoint.getIdt());
			}
		}
		return dto;
	}
	
	public List<PieceJointeDto> toDtoPieceJointe(List<PieceJointe> entities) {
		List<PieceJointeDto> dtos = new ArrayList<>();
		if(entities != null && !entities.isEmpty()) {
			for(PieceJointe entity : entities) {
				dtos.add(toDtoPieceJointe(entity));
			}			
		}
		return dtos;
	}
}
