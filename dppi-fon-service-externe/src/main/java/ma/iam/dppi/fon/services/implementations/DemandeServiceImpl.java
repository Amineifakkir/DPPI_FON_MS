/**
 * 
 */
package ma.iam.dppi.fon.services.implementations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ma.iam.dppi.fon.services.IDemandeService;

@Service
@Transactional
public class DemandeServiceImpl implements IDemandeService {

	Logger logger = LoggerFactory.getLogger(this.getClass());

//	@Override
//	public Demande saveDemande(DemandeDto demandeDto) throws ParseException {
//		return demandeMapper.entityToDto(demandeRepository.save(demandeDto));
//	}

}
