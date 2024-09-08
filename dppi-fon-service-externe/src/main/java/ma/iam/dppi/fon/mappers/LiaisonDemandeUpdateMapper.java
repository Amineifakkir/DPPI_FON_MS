package ma.iam.dppi.fon.mappers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ma.iam.dppi.fon.domain.Demande;
import ma.iam.dppi.fon.domain.Liaison;
import ma.iam.dppi.fon.dtos.LiaisonDemandeUppdateDto;
import ma.iam.dppi.fon.repository.LiaisonRepository;
import ma.iam.dppi.fon.utils.DateUtils;

@Component
public class LiaisonDemandeUpdateMapper {

	@Autowired
	private LiaisonRepository liaisonRepo;

	public LiaisonDemandeUppdateDto entityToDto(Liaison liaison) {

		if (liaison == null) {
			return null;
		}
		LiaisonDemandeUppdateDto dto = new LiaisonDemandeUppdateDto();
		dto.setIdt(liaison.getIdt());
		//todo salman
//		dto.setCodeLiaisonErpt(liaison.getCodeLiaisonErpt());
//		dto.setXGpsDepart(liaison.getXGpsDepart());
//		dto.setYGpsDepart(liaison.getYGpsDepart());
//		dto.setXGpsArrivee(liaison.getXGpsArrivee());
//		dto.setYGpsArrivee(liaison.getYGpsArrivee());
		Demande demande = new Demande();
		if (demande.getIdt() != null) {
			dto.setCommentaire(demande.getCommentaire());
		}
		return dto;
	}

	public Liaison dtoToEntity(LiaisonDemandeUppdateDto dto) {

		if (dto == null) {
			return null;
		}
		Liaison liaison = new Liaison();

		Optional<Liaison> liaisonOptional = liaisonRepo.findById(dto.getIdt());
		if (liaisonOptional.isPresent()) {
			liaison = liaisonOptional.get();
			liaison.setIdt(dto.getIdt());
			liaison.setCodeSiteErpt(dto.getCodeLiaisonErpt());
			liaison.setxGpsDepart(dto.getXGpsDepart());
			liaison.setyGpsDepart(dto.getYGpsDepart());
			liaison.setxGpsArrivee(dto.getXGpsArrivee());
			liaison.setyGpsArrivee(dto.getYGpsArrivee());
			liaison.setDateReception(DateUtils.stringToDate(dto.getDateReception()));
			//todo salman
		}

//		Demande demande = null;
//		Optional<Demande>  demandeOptional= demandeRepo.findById(dto.getIdtDemande());
//		if(demandeOptional.isPresent()) {
//			 demande = demandeOptional.get();
//		}
		return liaison;
	}
}
