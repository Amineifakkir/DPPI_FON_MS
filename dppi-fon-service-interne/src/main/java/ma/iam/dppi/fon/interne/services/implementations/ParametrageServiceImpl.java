package ma.iam.dppi.fon.interne.services.implementations;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ma.iam.dppi.fon.communs.domain.Parametrage;
import ma.iam.dppi.fon.communs.repository.ParametrageRepository;
import ma.iam.dppi.fon.constants.ActionCode;
import ma.iam.dppi.fon.interne.constants.Constants;
import ma.iam.dppi.fon.interne.dtos.ParametreBoDto;
import ma.iam.dppi.fon.interne.dtos.ParametresDto;
import ma.iam.dppi.fon.interne.dtos.ParametresStrDto;
import ma.iam.dppi.fon.interne.services.IParametrageService;
import ma.iam.dppi.fon.interne.services.ITraceService;
import ma.iam.dppi.fon.interne.utils.Utils;

/**
 * 
 * @author Z.BELGHAOUTI
 *
 */
@Service
@Transactional
public class ParametrageServiceImpl implements IParametrageService {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ITraceService traceServiceImpl;

//	@Autowired
//	private UtilisateurRepository utilisateurDaoImpl;

	@Autowired
	private ParametrageRepository parametrageBODaoImpl;

	@Override
	public ParametresDto getAllParameters() {
		ParametresDto dto = new ParametresDto();
		ParametreBoDto valueGMT = toDto(parametrageBODaoImpl.findByCode("VGMT"));
		if (valueGMT != null) {
			traceServiceImpl.traceOperation(ActionCode.CONSULTATION,
					"Récupération du paramètre: " + valueGMT.getLabel() + " de code: " + valueGMT.getCode()
							+ " et de valeur: " + valueGMT.getValeur(),
					"Récupération du paramètre: " + valueGMT.getLabel() + " de code: " + valueGMT.getCode()
							+ " et de valeur: " + valueGMT.getValeur(),
					"CONSULTATION", "Paramétrage", null, null);
		}
		dto.setValueGMT(valueGMT);

		return dto;
	}

	@Override
	public List<ParametreBoDto> getParameterByCode(String code) {
		List<ParametreBoDto> dtos = new ArrayList<>();
		List<Parametrage> domains = parametrageBODaoImpl.findAll();
		if (Utils.isValid(domains)) {
			dtos = toDtos(domains);
		}
		traceServiceImpl.traceOperation(ActionCode.CONSULTATION,
				"Récupération de la liste des paramètres de tailles: " + dtos.size() + " par code: " + code,
				"Récupération de la liste des paramètres de tailles: " + dtos.size() + " par code: " + code,
				"CONSULTATION", "Paramétrage", null, null);
		return dtos;
	}

	@Override
	public Long changeParameter(ParametreBoDto parametreBoDto) {
		if (parametreBoDto != null) {
			Parametrage domain = parametrageBODaoImpl.findByCode(parametreBoDto.getCode());
			if (domain != null && Utils.isValid(parametreBoDto.getValeur())) {
				String oldValue = "Paramètre : " + domain.getLabel() + " Valeur : " + domain.getValeur();
				domain.setValeur(parametreBoDto.getValeur());
				parametrageBODaoImpl.save(domain);
				String newValue = "Paramètre : " + domain.getLabel() + " Valeur : " + domain.getValeur();
				traceServiceImpl.traceOperation(ActionCode.MODIF,
						"Changer la valeur du paramètre: " + domain.getLabel() + " de code: " + domain.getCode()
								+ " par: " + domain.getValeur(),
						"Changer la valeur du paramètre: " + domain.getLabel() + " de code: " + domain.getCode()
								+ " par: " + domain.getValeur(),
						"MODIFICATION", "Paramétrage", oldValue, newValue);
				return 1L;
			} else {
				return 0L;
			}
		}
		return 0L;
	}

	@Override
	public Long changeParameters(ParametresStrDto parameters) {

		if (Utils.isValid(parameters.getValueGMT())) {
			Parametrage domain = parametrageBODaoImpl.findByCode(Constants.VGMT);
			if (domain != null) {
				String oldValue = "Paramètre : " + domain.getLabel() + " Valeur : " + domain.getValeur();
				domain.setValeur(parameters.getValueGMT());
				parametrageBODaoImpl.save(domain);
				String newValue = "Paramètre : " + domain.getLabel() + " Valeur : " + domain.getValeur();
				traceServiceImpl.traceOperation(ActionCode.MODIF,
						"Changer la valeur du paramètre: " + domain.getLabel() + " de code: " + domain.getCode()
								+ " par: " + domain.getValeur(),
						"Changer la valeur du paramètre: " + domain.getLabel() + " de code: " + domain.getCode()
								+ " par: " + domain.getValeur(),
						"MODIFICATION", "Paramétrage", oldValue, newValue);
			}
		}

		return 1L;
	}

	@Override
	public Long resetToDefaultSettings() {
		logger.info(Utils.getLogParam() + "Reset to default parametres... ");
		ParametresStrDto dto = new ParametresStrDto();
		dto.setValueGMT(Constants.DEFAULT_VGMT);
		traceServiceImpl.traceOperation(ActionCode.MODIF, "Réinitialiser toutes les valeurs des paramètres",
				"Réinitialiser toutes les valeurs des paramètres", "MODIFICATION", "Paramétrage", null, null);
		return changeParameters(dto);
	}

	private List<ParametreBoDto> toDtos(List<Parametrage> domains) {
		List<ParametreBoDto> dtos = new ArrayList<>();
		if (domains != null && !domains.isEmpty()) {
			dtos = new ArrayList<>();
			for (Parametrage parametreBo : domains) {
				ParametreBoDto dto = toDto(parametreBo);
				if (dto != null) {
					dtos.add(dto);
				}
			}
		}
		return dtos;
	}

	private ParametreBoDto toDto(Parametrage domain) {
		ParametreBoDto dto = null;
		if (domain != null) {
			dto = new ParametreBoDto();
			dto.setCode(domain.getCode());
			dto.setDescription(domain.getDescription());
			dto.setLabel(domain.getLabel());
			dto.setValeur(domain.getValeur());
		}
		return dto;
	}

}
