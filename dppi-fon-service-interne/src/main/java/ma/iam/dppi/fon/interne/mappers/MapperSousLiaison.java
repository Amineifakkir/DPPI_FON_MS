package ma.iam.dppi.fon.interne.mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.iam.dppi.fon.communs.domain.Commune;
import ma.iam.dppi.fon.communs.repository.CommuneRepository;
import ma.iam.dppi.fon.domain.EtatDemandeSousLiaison;
import ma.iam.dppi.fon.domain.Liaison;
import ma.iam.dppi.fon.domain.OperationSousLiaison;
import ma.iam.dppi.fon.domain.Site;
import ma.iam.dppi.fon.domain.SousLiaison;
import ma.iam.dppi.fon.interne.dtos.SousLiaisonDto;
import ma.iam.dppi.fon.interne.utils.DateUtils;
import ma.iam.dppi.fon.repository.EtatDemandeSousLiaisonRepository;
import ma.iam.dppi.fon.repository.LiaisonRepository;
import ma.iam.dppi.fon.repository.OperationSousLiaisonRepository;
import ma.iam.dppi.fon.repository.SiteRepository;

/**
 * 
 * @author Z.BELGHAOUTI
 * 
 */
@Service
public class MapperSousLiaison {

	@Autowired
	private CommuneRepository communeRepository;

	@Autowired
	private OperationSousLiaisonRepository operationSousLiaisonRepository;

	@Autowired
	private SiteRepository siteRepository;

	@Autowired
	private EtatDemandeSousLiaisonRepository etatDemandeSousLiaisonRepository;

	@Autowired
	private LiaisonRepository liaisonRepository;

	public SousLiaisonDto toDto(SousLiaison t) {
		SousLiaisonDto dto = null;
		if (t != null) {
			dto = new SousLiaisonDto();

			dto.setIdt(t.getIdt());
			if (t.getLiaison() != null) {
				dto.setLiaisonIdt(t.getLiaison().getIdt());
				if (t.getLiaison().getIdtCommune() != null) {
					Optional<Commune> optionalCommune = communeRepository.findById(t.getLiaison().getIdtCommune());
					if (optionalCommune.isPresent()) {
						Commune commune = optionalCommune.get();
						if (commune != null) {
							dto.setCommuneIdt(commune.getIdt());
							dto.setCommune(commune.getLabel());
							if (commune.getDc() != null) {
								dto.setDcIdt(commune.getDc().getIdt());
								dto.setDc(commune.getDc().getLabel());
								if (commune.getDc().getDr() != null) {
									dto.setDrIdt(commune.getDc().getDr().getIdt());
									dto.setDr(commune.getDc().getDr().getLabel());
								}
							}
						}
					}
				}
			}
			if (t.getSiteA() != null) {
				dto.setSite1Idt(t.getSiteA().getIdt());
				dto.setSite1Label(t.getSiteA().getCode());
				dto.setSite1N(t.getSiteA().getGpsN());
				dto.setSite1W(t.getSiteA().getGpsW());
//				dto.setChambre1(mapperChambre.toDto(t.getChambre1()));
			}
			if (t.getSiteB() != null) {
				dto.setSite2Idt(t.getSiteB().getIdt());
				dto.setSite2Label(t.getSiteB().getCode());
				dto.setSite2N(t.getSiteB().getGpsN());
				dto.setSite2W(t.getSiteB().getGpsW());
//				dto.setChambre1(mapperChambre.toDto(t.getChambre1()));
			}

			dto.setDistanceDisponible(t.getDistanceDisponible());
			dto.setDistanceSature(t.getDistanceSature());
			dto.setCableFoPropose(t.getCableFoPropose());
			dto.setBilanObtiqueEstimatif(t.getBilanObtiqueEstimatif());
			dto.setDelaisRealisation(t.getDelaisRealisation());
			dto.setDistanceEstimative(t.getDistanceEstimative());
			dto.setDistance(t.getDistance());
			dto.setCode(t.getCode());
			dto.setNumber(t.getNumber());
			dto.setContactDemt(t.getContactDemt());
			dto.setInterlocuteurDemt(t.getInterlocuteurDemt());
			dto.setContactDr(t.getContactDr());
			dto.setInterlocuteurDr(t.getInterlocuteurDr());
			dto.setDateAffectation(DateUtils.toDateTime(t.getDateAffectation()));
			dto.setDesignationSousLiaison(t.getDesignationSousLiaison());
			dto.setDesignationTronconSousLiaison(t.getDesignationTronconSousLiaison());
//			dto.setDateAffectation(DateUtils.toDateTime(t.getDateAffectation()));
			OperationSousLiaison operationTroncon = operationSousLiaisonRepository
					.getOperationBySousLiaisonIdt(t.getIdt());
			if (operationTroncon != null) {
				dto.setCommentaire(operationTroncon.getCommentaire());
				if (operationTroncon.getDateAffectation() != null) {
					dto.setDateOperation(DateUtils.toDateTime(operationTroncon.getDateAffectation()));
				}
				if (operationTroncon.getDateReponse() != null) {
					dto.setDateReponse(DateUtils.toDateTime(operationTroncon.getDateReponse()));
				}
				if (operationTroncon.getDemande() != null) {
					dto.setDemandeIdt(operationTroncon.getDemande().getIdt());
				}
				if (operationTroncon.getEtatDemandeSousLiaison() != null) {
					dto.setEtatDemandeSousLiaisonIdt(operationTroncon.getEtatDemandeSousLiaison().getIdt());
					dto.setEtatDemandeSousLiaisonCode(operationTroncon.getEtatDemandeSousLiaison().getCode());
					dto.setEtatDemandeSousLiaisonLabel(operationTroncon.getEtatDemandeSousLiaison().getLabel());
				}
				dto.setDemandeurLogin(operationTroncon.getDemandeurLogin());
				dto.setDemandeurNom(operationTroncon.getDemandeurNom());
				dto.setDemandeurPrenom(operationTroncon.getDemandeurPrenom());
				dto.setRepondeurLogin(operationTroncon.getRepondeurLogin());
				dto.setRepondeurNom(operationTroncon.getRepondeurNom());
				dto.setRepondeurPrenom(operationTroncon.getRepondeurPrenom());
				if (operationTroncon.getRaisonInfaisabilite() != null) {
					dto.setRaisonInfaisabiliteIdt(operationTroncon.getRaisonInfaisabilite().getIdt());
					dto.setRaisonInfaisabiliteCode(operationTroncon.getRaisonInfaisabilite().getCode());
					dto.setRaisonInfaisabiliteLabel(operationTroncon.getRaisonInfaisabilite().getLabel());
				}

				dto.setDescriptionInfaisabilite(operationTroncon.getDescriptionInfaisabilite());
				dto.setSolutionAlternative(operationTroncon.getSolutionAlternative());
			}

		}
		return dto;
	}

	public List<SousLiaisonDto> toDtos(List<SousLiaison> entities) {
		List<SousLiaisonDto> dtos = new ArrayList<>();
		if (entities != null && !entities.isEmpty()) {
			for (SousLiaison entity : entities) {
				dtos.add(toDto(entity));
			}
		}
		return dtos;
	}

	public SousLiaison toDomain(SousLiaisonDto dto) {
		SousLiaison t = null;
		if (dto != null) {
			t = new SousLiaison();
			Site site1 = siteRepository.findByCode(dto.getSite1Label());
			if (site1 == null) {
//				ch1 = mapperChambre.toDomain(dto.getChambre1());
//				chambreRepository.save(ch1);
			}
			t.setSiteA(site1);

//			t.setSite(ch2);
			t.setCode(dto.getCode());
			t.setDistance(dto.getDistance());
			t.setIdtDr(dto.getDrIdt());
			t.setNumber(dto.getNumber());
			t.setDateAffectation(DateUtils.stringToDate(dto.getDateAffectation()));
			
			EtatDemandeSousLiaison etat = etatDemandeSousLiaisonRepository.findByCode("PENDING");
			if (etat != null) {
				t.setEtat(etat.getLabel());
			}
			if (dto.getLiaisonIdt() != null) {
				Optional<Liaison> optional = liaisonRepository.findById(dto.getLiaisonIdt());
				if (optional.isPresent()) {
					t.setLiaison(optional.get());
				}
			}
		}
		return t;
	}
}
