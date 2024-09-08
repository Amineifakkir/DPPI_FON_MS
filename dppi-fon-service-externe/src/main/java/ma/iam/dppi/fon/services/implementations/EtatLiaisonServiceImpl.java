package ma.iam.dppi.fon.services.implementations;

import java.util.List;

import ma.iam.dppi.fon.dtos.EtatLiaisonDto;
import ma.iam.dppi.fon.mappers.MapperEtatLiaison;
import ma.iam.dppi.fon.repository.EtatLiaisonRepository;
import ma.iam.dppi.fon.services.IEtatLiaisonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EtatLiaisonServiceImpl implements IEtatLiaisonService{

	@Autowired
	private EtatLiaisonRepository etatLiaisonRepository;
	
	@Autowired
	private MapperEtatLiaison mapperEtatLiaison;
	
	@Override
	public List<EtatLiaisonDto> getListLiaison() {
		return mapperEtatLiaison.listToListDto(etatLiaisonRepository.findAll());	
		}

}
