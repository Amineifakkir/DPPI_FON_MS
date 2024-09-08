/**
 * 
 */
package ma.iam.dppi.fon.services.implementations;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.iam.dppi.fon.communs.domain.Parametrage;
import ma.iam.dppi.fon.communs.repository.ParametrageRepository;
import ma.iam.dppi.fon.domain.Demande;
import ma.iam.dppi.fon.domain.Escalade;
import ma.iam.dppi.fon.domain.Liaison;
import ma.iam.dppi.fon.dtos.EscaladeDto;
import ma.iam.dppi.fon.mappers.EscaladeMapper;
import ma.iam.dppi.fon.repository.DemandeRepository;
import ma.iam.dppi.fon.repository.EscaladeRepository;
import ma.iam.dppi.fon.repository.LiaisonRepository;
import ma.iam.dppi.fon.services.IEscaladeService;
import ma.iam.dppi.fon.utils.Utils;

@Service
public class EscaladeServiceImpl implements IEscaladeService {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private EscaladeRepository escaladeRepository;

	@Autowired
	private DemandeRepository demandeRepository;

	@Autowired
	private EscaladeMapper escaladeMapper;
	
	@Autowired
	private LiaisonRepository liaisonRepo;

	@Autowired
	private ParametrageRepository parametrageRepository;

	@Override
	public EscaladeDto saveEscalade(EscaladeDto escaladeDto) throws ParseException {
		Parametrage parametrageGMT = parametrageRepository.findByCode("VGMT");
		Date now = null;
		if (parametrageGMT != null) {
			if ("0".equalsIgnoreCase(parametrageGMT.getValeur())) {
				now = Utils.getCurrentDateGMT();
			} else {
				now = Utils.getCurrentDateGMTPlus1();
			}
		} else {
			now = new Date();
		}
		escaladeDto.setDateEscalade(now);
		Liaison liaison = liaisonRepo.findById(escaladeDto.getIdtLiaison()).get();

		Optional<Demande> demande = demandeRepository.findById(escaladeDto.getIdtDemande());

		EscaladeDto dto = null;
		if (demande.isPresent()) {
			escaladeDto.setIdtDemande(demande.get().getIdt());
			Long countDemande = escaladeRepository.countDemandeInEscalade(escaladeDto.getIdtDemande());
			System.out.println(countDemande);
			if (countDemande < 3) {
				escaladeDto.setNumOderEscalade(countDemande + 1);
				Escalade escalade = escaladeMapper.dtoToEntity(escaladeDto);
				Escalade save = escaladeRepository.save(escalade);
				dto = escaladeMapper.entityToDto(save);
				Date date = save.getDateEscalade();
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				save.setNumTicket(liaison.getReference() + "-" + df.format(date) + "-" + save.getNumOderEscalade());
				escaladeRepository.save(save);

			}

		} else {
			dto = null;
		}

		return dto;
	}

}
