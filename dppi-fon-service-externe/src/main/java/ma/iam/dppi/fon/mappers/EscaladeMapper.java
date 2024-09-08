package ma.iam.dppi.fon.mappers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ma.iam.dppi.fon.domain.Demande;
import ma.iam.dppi.fon.domain.Escalade;
import ma.iam.dppi.fon.domain.EtatEscalade;
import ma.iam.dppi.fon.dtos.EscaladeDto;
import ma.iam.dppi.fon.repository.DemandeRepository;
import ma.iam.dppi.fon.repository.EtatEscaladeRepository;

@Component
public class EscaladeMapper {

	@Autowired
	private DemandeRepository demandeRepository;
	@Autowired
	private EtatEscaladeRepository etatRepository;

	public EscaladeDto entityToDto(Escalade es) {
		if (es == null) {
			return null;
		}
		EscaladeDto dto = new EscaladeDto();
		dto.setIdt(es.getIdt());
		dto.setDateEscalade(es.getDateEscalade());
		dto.setNumOderEscalade(es.getNumOderEscalade());

		dto.setIdtDemande(es.getDemande().getIdt());

		Optional<EtatEscalade> etatOptional = etatRepository.findById(1L);
		if (etatOptional.isPresent()) {
			System.out.println();
			dto.setIdtEtatEscalade(etatOptional.get().getIdt());
		}

		return dto;

	}

	public Escalade dtoToEntity(EscaladeDto dto) {
		if (dto == null) {
			return null;
		}
		Escalade es = new Escalade();
		es.setIdt(dto.getIdt());
		es.setDateEscalade(dto.getDateEscalade());
		es.setNumOderEscalade(dto.getNumOderEscalade());
		es.setNumTicket(dto.getNumTicket());
		Optional<Demande> demandeOptional = demandeRepository.findById(dto.getIdtDemande());
		if (demandeOptional.isPresent()) {
			System.out.println();
			es.setDemande(demandeOptional.get());
		}
		Optional<EtatEscalade> etatOptional = etatRepository.findById(1L);
		if (etatOptional.isPresent()) {
			System.out.println();
			es.setEtatEscalade(etatOptional.get());
		}

		return es;
	}

	public List<EscaladeDto> listEntityToListDto(List<Escalade> esd) {

		if (CollectionUtils.isEmpty(esd))
			return Collections.emptyList();

		List<EscaladeDto> dtos = new ArrayList<EscaladeDto>();

		for (Escalade es : esd) {
			dtos.add(entityToDto(es));

		}

		return dtos;
	}

}
