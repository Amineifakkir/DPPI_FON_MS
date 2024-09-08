package ma.iam.dppi.fon.mappers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ma.iam.dppi.fon.domain.Demande;
import ma.iam.dppi.fon.domain.Devis;
import ma.iam.dppi.fon.dtos.DevisDto;
import ma.iam.dppi.fon.repository.DemandeRepository;

@Component
public class DevisMapper {

	@Autowired
	private DemandeRepository demandeRepository;

	public DevisDto entityToDto(Devis devis) {
		if (devis == null) {
			return null;
		}

		DevisDto dto = new DevisDto();
		dto.setIdt(devis.getIdt());
		dto.setDateDevis(devis.getDateDevis());
		dto.setDateRefus(devis.getDateRefus());
		dto.setDistance(devis.getDistance());
		dto.setPrix(devis.getPrix());
		dto.setPrixTotal(devis.getPrixTotal());

		Demande demande = demandeRepository.getLastDemandeByIdtLiaison(devis.getIdt());
		if (demande != null) {
			dto.setIdtDemande(devis.getDemande().getIdt());
		}

		return dto;
	}
	
	public List<DevisDto> entitiesToDtos(List<Devis> devisList) {
	    if (devisList == null) {
	        return null;
	    }

	    List<DevisDto> dtoList = new ArrayList<>();

	    for (Devis devis : devisList) {
	        DevisDto dto = entityToDto(devis);
	        if (dto != null) {
	            dtoList.add(dto);
	        }
	    }

	    return dtoList;
	}


	public Devis dtoToEntity(DevisDto dto) {
		if (dto == null) {
			return null;
		}

		Devis devis = new Devis();
		devis.setIdt(dto.getIdt());
		devis.setDateDevis(dto.getDateDevis());
		devis.setDateRefus(dto.getDateRefus());
		devis.setDistance(dto.getDistance());
		devis.setPrix(dto.getPrix());
		devis.setPrixTotal(dto.getPrixTotal());
		Optional<Demande> deviss = demandeRepository.findById(dto.getIdtDemande());

		if (deviss.isPresent()) {
			devis.setDemande(deviss.get());

		}
		return devis;
	}

	public List<DevisDto> listEntityToListDto(List<Devis> devisList) {

		if (CollectionUtils.isEmpty(devisList))
			return Collections.emptyList();

		List<DevisDto> devisDtos = new ArrayList<DevisDto>();

		for (Devis devis : devisList) {
			devisDtos.add(entityToDto(devis));

		}

		return devisDtos;
	}
}
