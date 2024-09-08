package ma.iam.dppi.fon.services.implementations;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import ma.iam.dppi.fon.communs.domain.Parametrage;
import ma.iam.dppi.fon.communs.repository.ParametrageRepository;
import ma.iam.dppi.fon.domain.Devis;
import ma.iam.dppi.fon.dtos.DevisDto;
import ma.iam.dppi.fon.mappers.DevisMapper;
import ma.iam.dppi.fon.repository.DevisRepository;
import ma.iam.dppi.fon.services.IDevisService;
import ma.iam.dppi.fon.utils.Utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DevisServiceImpl implements IDevisService {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private DevisRepository devisRepository;

	@Autowired
	private DevisMapper devisMapper;

	@Autowired
	private ParametrageRepository parametrageRepository;

	@Override
	public DevisDto saveDevis(DevisDto devisDto) throws ParseException {
		if (devisDto == null) {
			return null;
		}

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

		if (devisDto.getDevisValide() == true) {
			devisDto.setDateDevis(now);
		}

		if (devisDto.getDevisValide() == false) {
			devisDto.setDateRefus(now);
		}

		return devisMapper.entityToDto(devisRepository.save(devisMapper.dtoToEntity(devisDto)));

	}

	@Override
	public Boolean devisExistans(Long idtDemande) {

		List<DevisDto> devisDto = devisMapper.entitiesToDtos(devisRepository.getListByDevisIdt(idtDemande));
		if (devisDto.isEmpty()) {
			return false;
		} else
			return true;
	}

	@Override
	public List<DevisDto> getDevisByDemande(Long idtDemande) {
		List<DevisDto> devisDto = devisMapper.entitiesToDtos(devisRepository.getListDevisByIdtDemande(idtDemande));
		if (devisDto == null) {
			return null;
		}
		return devisDto;
	}

	@Override
	public Devis getDevisById(Long idtDevis) {
		return devisRepository.findById(idtDevis).orElse(null);
	}

}
