package ma.iam.dppi.fon.services.implementations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import ma.iam.dppi.fon.communs.domain.Commune;
import ma.iam.dppi.fon.communs.domain.Dc;
import ma.iam.dppi.fon.communs.domain.Dr;
import ma.iam.dppi.fon.communs.repository.CommuneRepository;
import ma.iam.dppi.fon.communs.repository.DcRepository;
import ma.iam.dppi.fon.communs.repository.DrRepository;
import ma.iam.dppi.fon.constants.ReferentielConstants;
import ma.iam.dppi.fon.domain.EtatDemande;
import ma.iam.dppi.fon.domain.Site;
import ma.iam.dppi.fon.domain.TypeDemande;
import ma.iam.dppi.fon.domain.TypeLiaison;
import ma.iam.dppi.fon.dtos.ChambreAggcDto;
import ma.iam.dppi.fon.dtos.LabelDto;
import ma.iam.dppi.fon.dtos.RepartiteurDto;
import ma.iam.dppi.fon.repository.EtatDemandeRepository;
import ma.iam.dppi.fon.repository.SiteRepository;
import ma.iam.dppi.fon.repository.TypeDemandeRepository;
import ma.iam.dppi.fon.repository.TypeLiaisonRepository;
import ma.iam.dppi.fon.services.IReferentielService;
import ma.iam.dppi.utils.Utils;

@Service
@Transactional
public class ReferentielServiceImpl implements IReferentielService {

	private final Logger logger = LoggerFactory.getLogger(ReferentielServiceImpl.class);
	@Autowired
	private DrRepository repositoryDr;

	@Autowired
	private DcRepository repositoryDc;

	@Autowired
	private CommuneRepository communeRepository;

	@Autowired
	private EtatDemandeRepository etatDemandeRepository;

	@Autowired
	private SiteRepository siteRepository;

	@Autowired
	private TypeDemandeRepository typeDemandeRepository;

	@Autowired
	private TypeLiaisonRepository typeLiaisonRepository;

	private final RestTemplate restTemplate;

	@Autowired
	public ReferentielServiceImpl(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Value("${url.aggc.rep}")
	private String urlRep;

	@Value("${url.aggc.sr}")
	private String urlSr;

	@Value("${url.aggc.sr.chambre}")
	private String urlChambre;

	@Override
	public List<LabelDto> getAllEtat() {
		List<EtatDemande> listEtat = etatDemandeRepository.getAllEtatExterne();
		List<LabelDto> dtos = new ArrayList<>();
		if (listEtat != null && !listEtat.isEmpty()) {
			for (EtatDemande dr : listEtat) {
				LabelDto dto = new LabelDto();
				dto.setIdt(dr.getIdt());
				dto.setCode(dr.getCode());
				dto.setLabel(dr.getLabel());
				dtos.add(dto);
			}
		}
		return dtos;
	}

	@Override
	public List<LabelDto> getAllTypeDemande() {
		List<TypeDemande> listTypeDemande = typeDemandeRepository.findAll();
		List<LabelDto> dtos = new ArrayList<>();
		if (listTypeDemande != null && !listTypeDemande.isEmpty()) {
			for (TypeDemande dr : listTypeDemande) {
				LabelDto dto = new LabelDto();
				dto.setIdt(dr.getIdt());
				dto.setCode(dr.getCode());
				dto.setLabel(dr.getDesignation());
				dtos.add(dto);
			}
		}
		return dtos;
	}

	@Override
	public List<LabelDto> getAllDr() {
		List<Dr> listDr = repositoryDr.findAll();
		List<LabelDto> dtos = new ArrayList<>();
		if (!listDr.isEmpty()) {
			logger.info(Utils.formatMessage(ReferentielConstants.MESSAGE_CONSULTER_DR) + listDr.size());
			for (Dr dr : listDr) {
				LabelDto dto = new LabelDto();
				dto.setIdt(dr.getIdt());
				dto.setCode(dr.getCode());
				dto.setLabel(dr.getLabel());
				dtos.add(dto);
			}
		}
		return dtos;
	}

	@Override
	public LabelDto getOneDR(Long id) {
		Dr dr = null;
		Optional<Dr> optional = repositoryDr.findById(id);
		if (optional.isPresent()) {
			dr = optional.get();
		}
		LabelDto dto = new LabelDto();
		if (dr != null) {
			logger.info(Utils.formatMessage(ReferentielConstants.MESSAGE_CONSULTER_DR_AVEC_ID + id));
			dto.setIdt(dr.getIdt());
			dto.setCode(dr.getCode());
			dto.setLabel(dr.getLabel());
		}
		return dto;
	}

	@Override
	public List<LabelDto> getListDcByIdDr(Long id) {
		Dr dr = null;
		Optional<Dr> optionalDr = repositoryDr.findById(id);
		if (optionalDr.isPresent()) {
			dr = optionalDr.get();
		}
		List<LabelDto> dtos = new ArrayList<>();

		if (dr == null) {
			return new ArrayList<>();
		}
		List<Dc> listDc = repositoryDc.findByDr(dr);
		if (listDc != null && !listDc.isEmpty()) {

			logger.info(Utils.formatMessage(ReferentielConstants.MESSAGE_CONSULTER_DC_AVEC_DR + id));
			logger.info(Utils.formatMessage(ReferentielConstants.MESSAGE_CONSULTER_DC + listDc.size()));
			for (Dc dc : listDc) {
				dtos = getListCommuneByIdDc(dc.getIdt());
//				LabelDto dto = new LabelDto();
//				dto.setIdt(dc.getIdt());
//				dto.setCode(dc.getCode());
//				dto.setLabel(dc.getLabel());
//				dtos.add(dto);
			}
		}
		return dtos;
	}

	@Override
	public List<LabelDto> getListCommuneByIdDc(Long id) {
		Dc dc = null;
		Optional<Dc> optionalDc = repositoryDc.findById(id);
		if (optionalDc.isPresent()) {
			dc = optionalDc.get();
		}
		List<LabelDto> dtos = new ArrayList<>();

		if (dc == null) {
			return new ArrayList<>();
		}

		List<Commune> listCommune = communeRepository.findByDcOrderByLabelAsc(dc);
		if (listCommune != null && !listCommune.isEmpty()) {
			logger.info(Utils.formatMessage(ReferentielConstants.MESSAGE_CONSULTER_COMMUNE_AVEC_DC + id));
			logger.info(Utils.formatMessage(ReferentielConstants.MESSAGE_CONSULTER_COMMUNE + listCommune.size()));
			for (Commune commune : listCommune) {
				LabelDto dto = new LabelDto();
				dto.setIdt(commune.getIdt());
				dto.setCode(commune.getCode());
				dto.setLabel(commune.getLabel());
				dtos.add(dto);
			}
		}
		return dtos;
	}

	@Override
	public List<LabelDto> getAllTypeLiaison() {
		List<TypeLiaison> listTypeLiaison = typeLiaisonRepository.findAll();
		List<LabelDto> dtos = new ArrayList<>();
		if (listTypeLiaison != null && !listTypeLiaison.isEmpty()) {
			for (TypeLiaison dr : listTypeLiaison) {
				LabelDto dto = new LabelDto();
				dto.setIdt(dr.getIdt());
				dto.setCode(dr.getCode());
				dto.setLabel(dr.getDesignation());
				dtos.add(dto);
			}
		}
		return dtos;
	}

	@Override
	public List<RepartiteurDto> getRepartiteurByCommuneExterne(String codeCommune) {

		List<RepartiteurDto> data = null;
		if (urlRep != null) {
			String urlStr = urlRep.replace("$1", codeCommune);
			System.out.println(urlStr);

			try {
				ResponseEntity<List<RepartiteurDto>> response = restTemplate.exchange(urlStr, HttpMethod.GET, null,
						new ParameterizedTypeReference<List<RepartiteurDto>>() {
						});
				data = response.getBody();

			} catch (HttpClientErrorException e) {
				String errorpayload = e.getResponseBodyAsString();
				logger.info("getChambresFromUrlExterne: " + errorpayload);

			} catch (HttpStatusCodeException e) {
				String errorpayload = e.getResponseBodyAsString();
				logger.info("getDataFromUrlExterneError: " + errorpayload);
			} catch (Exception e) {
				String errorpayload = e.toString();
				logger.info("getChambresFromUrlExterne: " + errorpayload);
			}
		}

		return data;
	}

	@Override
	public List<RepartiteurDto> getSousRepartiteurByRepartiteurExterne(String codeRepartiteur) {

		List<RepartiteurDto> data = null;
		if (urlSr != null) {
			String urlStr = urlSr.replace("$1", codeRepartiteur);

			try {
				ResponseEntity<List<RepartiteurDto>> response = restTemplate.exchange(urlStr, HttpMethod.GET, null,
						new ParameterizedTypeReference<List<RepartiteurDto>>() {
						});
				data = response.getBody();
			} catch (HttpClientErrorException e) {
				String errorpayload = e.getResponseBodyAsString();
				logger.info("getChambresFromUrlExterne: " + errorpayload);

			} catch (HttpStatusCodeException e) {
				String errorpayload = e.getResponseBodyAsString();
				logger.info("getDataFromUrlExterneError: " + errorpayload);
			} catch (Exception e) {
				String errorpayload = e.toString();
				logger.info("getChambresFromUrlExterne: " + errorpayload);
			}
		}
		return data;
	}

	@Override
	public List<ChambreAggcDto> getChambrerBySousRepartiteurExterne(String codeSr) {

		List<ChambreAggcDto> data = null;
		if (urlChambre != null) {
			String urlStr = urlChambre.replace("$1", codeSr);
			System.out.println(urlStr);

			try {
				ResponseEntity<List<ChambreAggcDto>> response = restTemplate.exchange(urlStr, HttpMethod.GET, null,
						new ParameterizedTypeReference<List<ChambreAggcDto>>() {
						});
				data = response.getBody();
			} catch (HttpClientErrorException e) {
				String errorpayload = e.getResponseBodyAsString();
				logger.info("getChambresFromUrlExterne: " + errorpayload);

			} catch (HttpStatusCodeException e) {
				String errorpayload = e.getResponseBodyAsString();
				logger.info("getDataFromUrlExterneError: " + errorpayload);
			} catch (Exception e) {
				String errorpayload = e.toString();
				logger.info("getChambresFromUrlExterne: " + errorpayload);
			}
		}
		return data;
	}

	@Override
	public List<Site> getAllSites() {
		List<Site> sites = siteRepository.findAll();

		return sites;
	}
	
	@Override
	public void addEtatDemande(EtatDemande demandes) {
		EtatDemande demande = etatDemandeRepository.save(demandes);
	}

}
