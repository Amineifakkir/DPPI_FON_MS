/**
 * 
 */
package ma.iam.dppi.fon.services.implementations;

import java.util.List;

import ma.iam.dppi.fon.dtos.InteractionDto;
import ma.iam.dppi.fon.mappers.InteractionMapper;
import ma.iam.dppi.fon.repository.InteractionRepository;
import ma.iam.dppi.fon.services.IinteractionService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class InteractionServiceImpl implements IinteractionService {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private InteractionRepository interactionRepository;

	@Autowired
	private InteractionMapper interactionMapper;

	
	@Override
	public InteractionDto saveInteraction(InteractionDto interactionDto) {
		return interactionMapper.entityToDto(interactionRepository.save(interactionMapper.dtoToEntity(interactionDto)));
	}


	@Override
	public List<InteractionDto> getAllInteractions() {
		return interactionMapper.listEntityToListDto(interactionRepository.findAll());	
		}


	@Override
	public InteractionDto updateInteraction(InteractionDto interactionDto) {
		return interactionMapper.entityToDto(interactionRepository.save(interactionMapper.dtoToEntity(interactionDto)));	}



}
