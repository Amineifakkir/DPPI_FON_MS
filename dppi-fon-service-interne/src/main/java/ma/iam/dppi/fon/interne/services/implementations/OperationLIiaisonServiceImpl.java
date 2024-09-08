package ma.iam.dppi.fon.interne.services.implementations;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import ma.iam.dppi.fon.communs.domain.Utilisateur;
import ma.iam.dppi.fon.communs.repository.UtilisateurRepository;
import ma.iam.dppi.fon.domain.OperationLiason;
import ma.iam.dppi.fon.interne.dtos.OperationLiaisonDto;
import ma.iam.dppi.fon.interne.mappers.MapperOperationLiaison;
import ma.iam.dppi.fon.interne.services.IOperationLiaisonService;
import ma.iam.dppi.fon.repository.OperationLiaisonRepository;

@Service
public class OperationLIiaisonServiceImpl implements IOperationLiaisonService {

	@Autowired
	private OperationLiaisonRepository operationLiaisonRepo;

	@Autowired
	private MapperOperationLiaison operationLiaisonMapper;
	
	@Autowired
	private UtilisateurRepository utilisateurRepository;

	@Override
	public OperationLiaisonDto saveOperationLiaison(OperationLiaisonDto dto) {
		Date date = new Date(); 
		dto.setDate(date);
		String login = SecurityContextHolder.getContext().getAuthentication().getName();
		Utilisateur utilisateur = utilisateurRepository.findByLogin(login);
		dto.setIdtDemandeur(utilisateur.getIdt());
		OperationLiason liason = operationLiaisonRepo.save(operationLiaisonMapper.toDomain(dto));
		OperationLiaisonDto operationLiaisonDto = operationLiaisonMapper.toDto(liason);
		return operationLiaisonDto;
	}

}
