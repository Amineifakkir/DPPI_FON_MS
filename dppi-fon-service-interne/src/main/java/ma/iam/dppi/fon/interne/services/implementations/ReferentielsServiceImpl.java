package ma.iam.dppi.fon.interne.services.implementations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import ma.iam.dppi.fon.communs.domain.Commune;
import ma.iam.dppi.fon.communs.domain.Dc;
import ma.iam.dppi.fon.communs.domain.Dr;
import ma.iam.dppi.fon.communs.domain.Entite;
import ma.iam.dppi.fon.communs.domain.Operateur;
import ma.iam.dppi.fon.communs.repository.CommuneRepository;
import ma.iam.dppi.fon.communs.repository.DcRepository;
import ma.iam.dppi.fon.communs.repository.DrRepository;
import ma.iam.dppi.fon.communs.repository.EntiteRepository;
import ma.iam.dppi.fon.communs.repository.OperateurRepository;
import ma.iam.dppi.fon.domain.EtatDemande;
import ma.iam.dppi.fon.domain.EtatDemandeSousLiaison;
import ma.iam.dppi.fon.domain.EtatLiaison;
import ma.iam.dppi.fon.domain.Liaison;
import ma.iam.dppi.fon.domain.RaisonInfaisabilite;
import ma.iam.dppi.fon.domain.Site;
import ma.iam.dppi.fon.domain.TypeDemande;
import ma.iam.dppi.fon.interne.dtos.SiteDto;
import ma.iam.dppi.fon.interne.dtos.CommandeListDto;
import ma.iam.dppi.fon.interne.dtos.DirectionDto;
import ma.iam.dppi.fon.interne.mappers.MapperLiaisonList;
import ma.iam.dppi.fon.interne.services.IReferentielsService;
import ma.iam.dppi.fon.interne.utils.Utils;
import ma.iam.dppi.fon.repository.EtatDemandeRepository;
import ma.iam.dppi.fon.repository.EtatDemandeSousLiaisonRepository;
import ma.iam.dppi.fon.repository.EtatLiaisonRepository;
import ma.iam.dppi.fon.repository.LiaisonRepository;
import ma.iam.dppi.fon.repository.RaisonInfaisabiliteRepository;
import ma.iam.dppi.fon.repository.SiteRepository;
import ma.iam.dppi.fon.repository.TypeDemandeRepository;

/**
 * @author Z.BELGHAOUTI
 *
 */
@Service
@Transactional
public class ReferentielsServiceImpl implements IReferentielsService {

	private final Logger logger = LoggerFactory.getLogger(ReferentielsServiceImpl.class);

	@Autowired
	private DrRepository drDaoImpl;

	@Autowired
	private DcRepository dcDaoImpl;

	@Autowired
	private CommuneRepository communeDaoImpl;

	@Autowired
	private OperateurRepository operateurDaoImpl;

	@Autowired
	private EtatLiaisonRepository etatLiaisonDaoImpl;

	@Autowired
	private EtatDemandeRepository etatDemandeDaoImpl;

	@Autowired
	private TypeDemandeRepository typeDemandeDaoImpl;

	@Autowired
	private EntiteRepository entiteRepository;

	@Autowired
	private EtatDemandeSousLiaisonRepository etatDemandeSousLiaisonRepository;

	@Autowired
	private LiaisonRepository liaisonRepository;
	
	@Autowired
	private MapperLiaisonList mapperLiaison;

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private SiteRepository siteRepository;
	
	@Autowired
	private RaisonInfaisabiliteRepository raisonInfaisabiliteRepository;

	@Value("${url.sdr.site}")
	private String url;

	static {
		javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(new javax.net.ssl.HostnameVerifier() {

			public boolean verify(String hostname, javax.net.ssl.SSLSession sslSession) {
				return true;
			}
		});
	}

	@Override
	public List<DirectionDto> getListDr() {

		List<Dr> drs = drDaoImpl.findAll();
		List<DirectionDto> dtos = new ArrayList<>();

		if (!drs.isEmpty()) {
			for (Dr dr : drs) {
				DirectionDto dto = new DirectionDto();
				dto.setIdt(dr.getIdt());
				dto.setCode(dr.getCode());
				dto.setLabel(dr.getLabel());
				dtos.add(dto);
			}
		}
		logger.info(Utils.getLogParam() + "Récupération de la liste des Drs de tailles: " + dtos.size());
		return dtos;
	}

	@Override
	public List<DirectionDto> getListSiteByDr(Long idtDr){
		List<Site> sites = siteRepository.getListSiteByDr(idtDr);
		List<DirectionDto> dtos = new ArrayList<>();
		if (!sites.isEmpty()) {
			for (Site site : sites) {
				DirectionDto dto = new DirectionDto();
				dto.setIdt(site.getIdt());
				dto.setCode(site.getCode());
				dto.setLabel(site.getNom());
				dtos.add(dto);
			}
		}
		logger.info(Utils.getLogParam() + "Récupération de la liste des Drs de tailles: " + dtos.size());
		return dtos;
	}
	@Override
	public DirectionDto getDrByIdt(Long idt) {

		Optional<Dr> optionalDr = drDaoImpl.findById(idt);
		DirectionDto dto = null;
		if (optionalDr.isPresent()) {
			Dr dr = optionalDr.get();
			if (dr != null) {
				dto = new DirectionDto();
				dto.setIdt(dr.getIdt());
				dto.setCode(dr.getCode());
				dto.setLabel(dr.getLabel());
			}
		}

		return dto;
	}

	@Transactional
	public List<DirectionDto> getListDcByDr(Long idtDr) {
		List<DirectionDto> dtos = new ArrayList<>();
		Optional<Dr> optionalDr = drDaoImpl.findById(idtDr);
		if (optionalDr.isPresent()) {
			Dr dr = optionalDr.get();
			List<Dc> dcs = dcDaoImpl.findByDr(dr);
			if (dcs != null && !dcs.isEmpty()) {
				for (Dc dc : dcs) {
					DirectionDto dto = new DirectionDto();
					dto.setIdt(dc.getIdt());
					dto.setCode(dc.getCode());
					dto.setLabel(dc.getLabel());
					dtos.add(dto);
				}
			}
		}
		logger.info(Utils.getLogParam() + "Récupération de la liste des Dcs de tailles: " + dtos.size());
		return dtos;
	}

	@Override
	public DirectionDto getDcByIdt(Long idt) {
		Optional<Dc> optionalDc = dcDaoImpl.findById(idt);
		DirectionDto dto = null;
		if (optionalDc.isPresent()) {
			Dc dc = optionalDc.get();
			if (dc != null) {
				dto = new DirectionDto();
				dto.setIdt(dc.getIdt());
				dto.setCode(dc.getCode());
				dto.setLabel(dc.getLabel());
			}
		}
		return dto;
	}

	@Override
	public List<DirectionDto> getListCommuneByDc(Long idtDc) {
		List<DirectionDto> dtos = new ArrayList<>();
		Optional<Dc> optionalDc = dcDaoImpl.findById(idtDc);
		if (optionalDc.isPresent()) {
			Dc dc = optionalDc.get();
			List<Commune> communes = communeDaoImpl.findByDcOrderByLabelAsc(dc);
			if (communes != null && !communes.isEmpty()) {
				for (Commune c : communes) {
					DirectionDto dto = new DirectionDto();
					dto.setIdt(c.getIdt());
					dto.setCode(c.getCode());
					dto.setLabel(c.getLabel());
					dtos.add(dto);
				}
			}
		}
		logger.info(Utils.getLogParam() + "Récupération de la liste des communes de tailles: " + dtos.size());
		return dtos;
	}

	@Override
	public DirectionDto getCommuneByIdt(Long idt) {
		Optional<Commune> optionalCommune = communeDaoImpl.findById(idt);
		DirectionDto dto = null;
		if (optionalCommune.isPresent()) {
			Commune commune = optionalCommune.get();
			if (commune != null) {
				dto = new DirectionDto();
				dto.setIdt(commune.getIdt());
				dto.setCode(commune.getCode());
				dto.setLabel(commune.getLabel());
			}
		}
		return dto;
	}

	@Override
	public List<DirectionDto> getListOperateur() {

		List<Operateur> operateurs = operateurDaoImpl.findAll();
		List<DirectionDto> dtos = new ArrayList<>();

		if (!operateurs.isEmpty()) {
			for (Operateur dr : operateurs) {
				DirectionDto dto = new DirectionDto();
				dto.setIdt(dr.getIdt());
				dto.setCode(dr.getCode());
				dto.setLabel(dr.getLabel());
				dtos.add(dto);
			}
		}
		logger.info(Utils.getLogParam() + "Récupération de la liste des opérateurs de tailles: " + dtos.size());
		return dtos;
	}

	@Override
	public DirectionDto getOperateurByIdt(Long idt) {
		Optional<Operateur> optionalOperateur = operateurDaoImpl.findById(idt);
		DirectionDto dto = null;
		if (optionalOperateur.isPresent()) {
			Operateur operateur = optionalOperateur.get();
			if (operateur != null) {
				dto = new DirectionDto();
				dto.setIdt(operateur.getIdt());
				dto.setCode(operateur.getCode());
				dto.setLabel(operateur.getLabel());
			}
		}
		return dto;
	}

	@Override
	public List<DirectionDto> getListEtatLiaison() {

		List<EtatLiaison> etatLiaisons = etatLiaisonDaoImpl.findAll();
		List<DirectionDto> dtos = new ArrayList<>();

		if (!etatLiaisons.isEmpty()) {
			for (EtatLiaison dr : etatLiaisons) {
				DirectionDto dto = new DirectionDto();
				dto.setIdt(dr.getIdt());
				dto.setCode(dr.getCode());
				dto.setLabel(dr.getLabel());
				dtos.add(dto);
			}
		}
		logger.info(Utils.getLogParam() + "Récupération de la liste des états liaison de tailles: " + dtos.size());
		return dtos;
	}

	@Override
	public List<DirectionDto> getListEtatDemande() {

		List<EtatDemande> etatDemandes = etatDemandeDaoImpl.findAll();
		List<DirectionDto> dtos = new ArrayList<>();

		if (!etatDemandes.isEmpty()) {
			for (EtatDemande dr : etatDemandes) {
				DirectionDto dto = new DirectionDto();
				dto.setIdt(dr.getIdt());
				dto.setCode(dr.getCode());
				dto.setLabel(dr.getLabel());
				dtos.add(dto);
			}
		}
		logger.info(Utils.getLogParam() + "Récupération de la liste des états demande de tailles: " + dtos.size());
		return dtos;
	}

	@Override
	public List<DirectionDto> getListTypeDemande() {

		List<TypeDemande> typeDemandes = typeDemandeDaoImpl.findAll();
		List<DirectionDto> dtos = new ArrayList<>();

		if (!typeDemandes.isEmpty()) {
			for (TypeDemande dr : typeDemandes) {
				DirectionDto dto = new DirectionDto();
				dto.setIdt(dr.getIdt());
				dto.setCode(dr.getCode());
				dto.setLabel(dr.getDesignation());
				dtos.add(dto);
			}
		}
		logger.info(Utils.getLogParam() + "Récupération de la liste des types demande de tailles: " + dtos.size());
		return dtos;
	}

	@Override
	public List<DirectionDto> getListEtatDemandeForDON() {

		List<EtatDemande> etatDemandes = etatDemandeDaoImpl.getAllEtatForDon();
		List<DirectionDto> dtos = new ArrayList<>();

		if (etatDemandes != null && !etatDemandes.isEmpty()) {
			for (EtatDemande dr : etatDemandes) {
				DirectionDto dto = new DirectionDto();
				dto.setIdt(dr.getIdt());
				dto.setCode(dr.getCode());
				dto.setLabel(dr.getLabel());
				dtos.add(dto);
			}
		}
		logger.info(
				Utils.getLogParam() + "Récupération de la liste des états demande pour DON de tailles: " + dtos.size());
		return dtos;
	}

	@Override
	public List<DirectionDto> getListEtatDemandeForDEF() {

		List<EtatDemande> etatDemandes = etatDemandeDaoImpl.getAllEtatForDef();
		List<DirectionDto> dtos = new ArrayList<>();

		if (etatDemandes != null && !etatDemandes.isEmpty()) {
			for (EtatDemande dr : etatDemandes) {
				DirectionDto dto = new DirectionDto();
				dto.setIdt(dr.getIdt());
				dto.setCode(dr.getCode());
				dto.setLabel(dr.getLabel());
				dtos.add(dto);
			}
		}
		logger.info(
				Utils.getLogParam() + "Récupération de la liste des états demande pour DEF de tailles: " + dtos.size());
		return dtos;
	}

	@Override
	public List<SiteDto> getSitesFromUrlExterne(String idtDr) {

		List<SiteDto> data = null;
		if (url != null) {
			String urlStr = url.replace("$1", idtDr);
	
			try {
				ResponseEntity<List<SiteDto>> response = restTemplate.exchange(urlStr, HttpMethod.GET, null,
						new ParameterizedTypeReference<List<SiteDto>>() {
						});
				data = response.getBody();
			} catch (HttpClientErrorException e) {
				String errorpayload = e.getResponseBodyAsString();
				logger.info("getSitesFromUrlExterne: " + errorpayload);

			} catch (HttpStatusCodeException e) {
				String errorpayload = e.getResponseBodyAsString();
				logger.info("getDataFromUrlExterneError: " + errorpayload);
			} catch (Exception e) {
				String errorpayload = e.toString();
				logger.info("getSitesFromUrlExterne: " + errorpayload);
			}
		}
		return data;
	}

	@Override
	public List<DirectionDto> getListEntite() {

		List<Entite> entites = entiteRepository.findAll();
		List<DirectionDto> dtos = new ArrayList<>();

		if (!entites.isEmpty()) {
			for (Entite dr : entites) {
				DirectionDto dto = new DirectionDto();
				dto.setIdt(dr.getIdt());
				dto.setCode(dr.getCode());
				dto.setLabel(dr.getLabel());
				dtos.add(dto);
			}
		}
		logger.info(Utils.getLogParam() + "Récupération de la liste des Entités de tailles: " + dtos.size());
		return dtos;
	}

	@Override
	public List<DirectionDto> getListEtatDemandeTroncon() {

		List<EtatDemandeSousLiaison> etatDemandes = etatDemandeSousLiaisonRepository.getListEtatDemandeSousLiaison();
		List<DirectionDto> dtos = new ArrayList<>();

		if (etatDemandes != null && !etatDemandes.isEmpty()) {
			for (EtatDemandeSousLiaison dr : etatDemandes) {
				DirectionDto dto = new DirectionDto();
				dto.setIdt(dr.getIdt());
				dto.setCode(dr.getCode());
				dto.setLabel(dr.getLabel());
				dtos.add(dto);
			}
		}
		logger.info(
				Utils.getLogParam() + "Récupération de la liste des états demande Sous Liaison de tailles: " + dtos.size());
		return dtos;
	}

	@Override
	public List<DirectionDto> getListEtatDemandeConsolidation() {

		List<EtatDemande> etatDemandes = etatDemandeDaoImpl.getAllEtatForConsolidation();
		List<DirectionDto> dtos = new ArrayList<>();

		if (etatDemandes != null && !etatDemandes.isEmpty()) {
			for (EtatDemande dr : etatDemandes) {
				DirectionDto dto = new DirectionDto();
				dto.setIdt(dr.getIdt());
				dto.setCode(dr.getCode());
				dto.setLabel(dr.getLabel());
				dtos.add(dto);
			}
		}
		logger.info(Utils.getLogParam() + "Récupération de la liste des états demande de tailles: " + dtos.size());
		return dtos;
	}

	@Override
	public List<CommandeListDto> getListLiaisonTypePartage() {
		EtatLiaison etatLiaison = etatLiaisonDaoImpl.getByCodeEtatLiaison("PARTAGE");
		List<Liaison> listLiaison = liaisonRepository.findByEtatLiaison(etatLiaison);
		List<CommandeListDto> dtos= mapperLiaison.toDtos(listLiaison);
		return dtos;
}

	@Override
	public List<DirectionDto> getListRaisonInfaisabilite() {
		List<RaisonInfaisabilite> raisonInfaisabilites = raisonInfaisabiliteRepository.findAll();
		List<DirectionDto> dtos = new ArrayList<>();

		if (!raisonInfaisabilites.isEmpty()) {
			for (RaisonInfaisabilite dr : raisonInfaisabilites) {
				DirectionDto dto = new DirectionDto();
				dto.setIdt(dr.getIdt());
				dto.setCode(dr.getCode());
				dto.setLabel(dr.getLabel());
				dtos.add(dto);
			}
		}
		logger.info(Utils.getLogParam() + "Récupération de la liste des opérateurs de tailles: " + dtos.size());
		return dtos;
	}
	
	



}