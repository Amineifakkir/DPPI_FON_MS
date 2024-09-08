package ma.iam.dppi.fon.mappers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ma.iam.dppi.fon.domain.Demande;
import ma.iam.dppi.fon.dtos.LiaisonDemandeUppdateDto;
import ma.iam.dppi.fon.repository.DemandeRepository;
import ma.iam.dppi.fon.utils.DateUtils;

@Component
public class DemandeUpdateMapper {

	@Autowired
	private DemandeRepository demandeRepo;

	public LiaisonDemandeUppdateDto entityToDto(Demande liaison) {

		if (liaison == null) {
			return null;
		}
		LiaisonDemandeUppdateDto dto = new LiaisonDemandeUppdateDto();
		dto.setIdt(liaison.getIdt());

		Demande demande = demandeRepo.findById(liaison.getIdt()).get();
		if (demande.getIdt() != null) {
			dto.setCommentaire(demande.getCommentaire());
			dto.setDateReception(DateUtils.dateToString(demande.getDateReception()));
		}
		return dto;
	}

	public Demande dtoToEntity(LiaisonDemandeUppdateDto dto) {

		if (dto == null) {
			return null;
		}
		Demande demande = new Demande();
		Optional<Demande> demandeOptional = demandeRepo.findById(dto.getIdtDemande());
		if (demandeOptional.isPresent()) {
			demande = demandeOptional.get();
			demande.setCommentaire(dto.getCommentaire());
			demande.setDateReception(DateUtils.stringToDate(dto.getDateReception()));
			
		}
		return demande;

	}

}
