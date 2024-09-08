package ma.iam.dppi.fon.mappers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import ma.iam.dppi.fon.domain.Demande;
import ma.iam.dppi.fon.domain.Interaction;
import ma.iam.dppi.fon.dtos.InteractionDto;
import ma.iam.dppi.fon.repository.DemandeRepository;

import org.apache.commons.collections.CollectionUtils;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InteractionMapper {

	@Autowired
	private DozerBeanMapper mapper;
	
	@Autowired
	private DemandeRepository demandeRep;
	
	public InteractionDto entityToDto(Interaction interaction) {
		if(interaction == null) {
			return null;
		}
		InteractionDto interactionDto = mapper.map(interaction, InteractionDto.class);
		

//		if (interaction.getDemande() != null) {
//				interactionDto.setIdtDemande(interaction.getDemande().getIdt());
//		}
	
		return interactionDto;

	}
	
	public Interaction dtoToEntity(InteractionDto interactionDto) {
		if(interactionDto == null) {
			return null;
		}
		Interaction interaction = mapper.map(interactionDto, Interaction.class);
		
		Optional<Demande> LiaOptional = demandeRep.findById(interactionDto.getIdtDemande());
		if (LiaOptional.isPresent()) {
			interaction.setDemande(LiaOptional.get());
		}

		return interaction;
	}
	
	public List<InteractionDto> listEntityToListDto(List<Interaction> interactions) {

		if (CollectionUtils.isEmpty(interactions))
			return Collections.emptyList();

		List<InteractionDto> interactionDtos = new ArrayList<InteractionDto>();

		for (Interaction interaction : interactions) {
			interactionDtos.add(entityToDto(interaction));
			
		}

		return interactionDtos;
	}

}
