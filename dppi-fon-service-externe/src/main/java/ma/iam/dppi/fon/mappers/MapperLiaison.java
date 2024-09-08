package ma.iam.dppi.fon.mappers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import ma.iam.dppi.fon.communs.domain.Commune;
import ma.iam.dppi.fon.communs.domain.Operateur;
import ma.iam.dppi.fon.communs.domain.Utilisateur;
import ma.iam.dppi.fon.communs.repository.CommuneRepository;
import ma.iam.dppi.fon.communs.repository.UtilisateurRepository;
import ma.iam.dppi.fon.domain.Demande;
import ma.iam.dppi.fon.domain.EtatLiaison;
import ma.iam.dppi.fon.domain.Interaction;
import ma.iam.dppi.fon.domain.Liaison;
//import ma.iam.dppi.fon.domain.Troncon;
import ma.iam.dppi.fon.dtos.DemandeDto;
import ma.iam.dppi.fon.dtos.InteractionDto;
import ma.iam.dppi.fon.dtos.LiaisonDemandeAddDto;
import ma.iam.dppi.fon.dtos.LiaisonDto;
import ma.iam.dppi.fon.repository.DemandeRepository;
import ma.iam.dppi.fon.repository.EtatLiaisonRepository;
import ma.iam.dppi.fon.repository.InteractionRepository;
//import ma.iam.dppi.fon.repository.TronconRepository;
import ma.iam.dppi.fon.services.IOperateurService;
import ma.iam.dppi.fon.utils.DateUtils;

@Component
public class MapperLiaison {

	@Autowired
	private CommuneRepository communeRepository;

	@Autowired
	private EtatLiaisonRepository etatRepository;

	@Autowired
	private UtilisateurRepository utilisateurRepository;

	@Autowired
	private DemandeRepository demandeRepository;
	@Autowired
	private MapperDemande mapperDemande;

	@Autowired
	private IOperateurService operateurService;

	@Autowired
	private InteractionRepository interactionRepository;
	
	
	@Autowired
	private DevisMapper devisMapper;
	

	@Autowired
	private InteractionMapper interactionMapper;

	public LiaisonDto toDto(Liaison liaison) {
		LiaisonDto dto = null;
		if (liaison != null) {
			dto = new LiaisonDto();

			dto.setIdt(liaison.getIdt());
			dto.setReference(liaison.getReference());
			dto.setCodeSiteErpt(liaison.getCodeSiteErpt());
			

			if (liaison.getIdtCommune() != null) {
				Optional<Commune> optionalCommune = communeRepository.findById(liaison.getIdtCommune());
				if (optionalCommune.isPresent()) {
					Commune commune = optionalCommune.get();
					if (commune != null) {
						dto.setIdtCommune(commune.getIdt());
						dto.setCommuneLabel(commune.getLabel());
						if (commune.getDc() != null) {
							dto.setIdtDc(commune.getDc().getIdt());
							dto.setDcLabel(commune.getDc().getLabel());
							if (commune.getDc().getDr() != null) {
								dto.setIdtDr(commune.getDc().getDr().getIdt());
								dto.setDrLabel(commune.getDc().getDr().getLabel());
							}
						}
					}
				}
			}

			if (liaison.getEtatLiaison() != null) {
				dto.setIdtEtatLiaison(liaison.getEtatLiaison().getIdt());
				dto.setCodeEtatLiaison(liaison.getEtatLiaison().getCode());
				dto.setLabelEtat(liaison.getEtatLiaison().getLabel());
			}

			if (liaison.getIdtOperateur() != null) {
				Operateur operateur = operateurService.getOperateur(liaison.getIdtOperateur());
				if (operateur != null) {
					dto.setIdtOperateur(operateur.getIdt());
					dto.setOperateurCode(operateur.getCode());
					dto.setOperateurLabel(operateur.getLabel());
				}

			}
			if (liaison.getTypeLiaison() != null) {
				dto.setIdtTypeLiaison(liaison.getTypeLiaison().getIdt());
				dto.setCodeTypeLiaison(liaison.getTypeLiaison().getCode());
			}

			dto.setxGpsDepart(liaison.getxGpsDepart());
			dto.setyGpsDepart(liaison.getyGpsDepart());
			dto.setxGpsArrivee(liaison.getxGpsArrivee());
			dto.setyGpsArrivee(liaison.getyGpsArrivee());
			dto.setDistanceDisponible(liaison.getDistanceDisponible());
			dto.setDistanceSature(liaison.getDistanceSature());

			Demande demande = demandeRepository.getLastDemandeByIdtLiaison(liaison.getIdt());
			
			DemandeDto lastDemande = mapperDemande.entityToDto(demande);
			dto.setDemandes(lastDemande);
			
			if (demande != null) {
				dto.setIdtDemande(demande.getIdt());
				if (demande.getEtatDemande() != null) {
					dto.setIdtEtatDemande(demande.getEtatDemande().getIdt());
					dto.setEtatDemandeCode(demande.getEtatDemande().getCode());
					dto.setEtatDemandeLabel(demande.getEtatDemande().getLabel());
					if(demande.getDateReception()!=null) {
						dto.setDateReception(DateUtils.toDateTime(demande.getDateReception()));
					}
				}

				if (demande.getTypeDemande() != null) {
					dto.setIdtTypeDemande(demande.getTypeDemande().getIdt());
					dto.setCodeTypeDemande(demande.getTypeDemande().getCode());
					dto.setLabelTypeDemande(demande.getTypeDemande().getDesignation());
				}
				dto.setCommentaire(demande.getCommentaire());
				dto.setxGpsPr(demande.getPrGpsX());
				dto.setDemandeurLogin(demande.getLoginDemandeur());
				dto.setNomDemandeur(demande.getNomDemandeur());
				dto.setPrenomDemandeur(demande.getPrenomDemandeur());
				dto.setDateDebut(demande.getDateDebut());
				dto.setDateFin(demande.getDateFin());
				dto.setDescriptionIncident(demande.getDescriptionIncident());
				dto.setLocalisationIncident(demande.getLocalisationIncident());
				dto.setDescriptionObstacle(demande.getDescriptionObstacle());
				dto.setPointObstacleGPSN(demande.getPointObstacleGPSN());
				dto.setPointObstacleGPSW(demande.getPointObstacleGPSW());
				dto.setContactDemandeur(demande.getContactDemandeur());
			
				dto.setDevis(devisMapper.entitiesToDtos(demande.getDevis()));
				if (demande.getDateDemande() != null) {
					dto.setDateDemande(DateUtils.toDateTime(demande.getDateDemande()));
				}

			}

			List<Demande> demandes = demandeRepository.getListByDemandeIdt(liaison.getIdt());
			List<DemandeDto> demandeDtos = mapperDemande.entityListToDtoList(demandes);
			dto.setListDemande(demandeDtos);

			List<Interaction> interactions = interactionRepository.getListInteractionErptByLiaisonIdt(liaison.getIdt(),
					"ERPT");
			List<InteractionDto> interactionDtos = interactionMapper.listEntityToListDto(interactions);
			dto.setInteractions(interactionDtos);

		}
		return dto;
	}

	public List<LiaisonDto> entityListToDtoList(List<Liaison> demandeList) {
		List<LiaisonDto> demandeGcDtoList = null;
		if (demandeList == null || demandeList.isEmpty()) {
			return demandeGcDtoList;
		}

		demandeGcDtoList = new ArrayList<>();
		for (Liaison demandeGc : demandeList) {
			demandeGcDtoList.add(toDto(demandeGc));
		}

		return demandeGcDtoList;
	}

	public Liaison dtoToEntity(LiaisonDemandeAddDto demandeDto) {
		if (demandeDto == null) {
			return null;
		}
		String login = SecurityContextHolder.getContext().getAuthentication().getName();
		Utilisateur utilisateur = utilisateurRepository.findByLogin(login);
		Liaison liaison = new Liaison();
		if (utilisateur != null) {

			if (demandeDto.getIdtCommune() != null) {
				Optional<Commune> optionalCommune = communeRepository.findById(demandeDto.getIdtCommune());
				if (optionalCommune.isPresent()) {
					Commune commune = optionalCommune.get();
					if (commune != null) {
						liaison.setIdtCommune(commune.getIdt());

						if (commune.getDc() != null) {
							liaison.setIdtDc(commune.getDc().getIdt());

							if (commune.getDc().getDr() != null) {
								liaison.setIdtDr(commune.getDc().getDr().getIdt());
						
							}
						}
					}

				}
			}
			LocalDate date = LocalDate.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			String formattedDate = date.format(formatter);

			liaison.setReference("DL-FON-"+utilisateur.getOperateur().getCode()+" - "+formattedDate + "-" + System.currentTimeMillis());
			liaison.setCodeSiteErpt(demandeDto.getCodeSiteErpt());
			if (utilisateur.getOperateur() != null) {
				liaison.setOperateur(utilisateur.getOperateur().getLabel());
				liaison.setIdtOperateur(utilisateur.getOperateur().getIdt());
			}

			liaison.setxGpsDepart(demandeDto.getxGpsDepart());
			liaison.setxGpsArrivee(demandeDto.getXgpsArrivee());
			liaison.setyGpsDepart(demandeDto.getYgpsDepart());
			liaison.setyGpsArrivee(demandeDto.getYgpsArrivee());

			Optional<EtatLiaison> etatLiaisonOptional = etatRepository.findById(1L);
			if (etatLiaisonOptional.isPresent()) {
				liaison.setEtatLiaison(etatLiaisonOptional.get());
			}

//			Optional<TypeLiaison> typeLiaisonOptional = typeLiaisonRepository.findById(demandeDto.getIdtTypeLiaison());
//			if (typeLiaisonOptional.isPresent()) {
//				TypeLiaison typeLiaison = typeLiaisonOptional.get();
//				if(typeLiaison.getCode().equals("Site")) {
//					liaison.setTypeLiaison(typeLiaison);
//				} else if (typeLiaison.getCode().equals("FTTH")) {
//					liaison.setTypeLiaison(typeLiaison);
////					liaison.setCodeSr(demandeDto.getCodeSr());
//					//todo salman we have to delete typeLiaison
//				}
//
//
//			}

			liaison.setDemandeAnnuler(false);
			liaison.setArchive(false);

		}
		return liaison;
	}
}
