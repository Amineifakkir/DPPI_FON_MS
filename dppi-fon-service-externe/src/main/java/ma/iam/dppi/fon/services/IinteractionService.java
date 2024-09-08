package ma.iam.dppi.fon.services;

import java.util.List;

import ma.iam.dppi.fon.dtos.InteractionDto;

/**
 * 
 * @author Z.BELGHAOUTI
 *
 */
public interface IinteractionService {
	
	 InteractionDto saveInteraction(InteractionDto interactionDto);
	
	 InteractionDto updateInteraction(InteractionDto interactionDto);

	 List<InteractionDto> getAllInteractions();
}

