package ma.iam.dppi.fon.interne.mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.iam.dppi.fon.domain.Demande;
import ma.iam.dppi.fon.domain.DemandeTravauxProgramme;
import ma.iam.dppi.fon.domain.EtatDemande;
import ma.iam.dppi.fon.domain.Interaction;
import ma.iam.dppi.fon.domain.Liaison;
import ma.iam.dppi.fon.domain.TraceCommentaire;
import ma.iam.dppi.fon.domain.SousLiaison;
import ma.iam.dppi.fon.domain.TypeDemande;
import ma.iam.dppi.fon.interne.dtos.DemandeDto;
import ma.iam.dppi.fon.interne.dtos.DemandeListDto;
import ma.iam.dppi.fon.interne.dtos.DemandeTravauxProgrammeDto;
import ma.iam.dppi.fon.interne.dtos.InteractionDto;
import ma.iam.dppi.fon.interne.dtos.LiaisonDto;
import ma.iam.dppi.fon.interne.dtos.SousLiaisonDto;
import ma.iam.dppi.fon.interne.dtos.TraceCommentaireDto;
import ma.iam.dppi.fon.interne.security.SecurityContextHelper;
import ma.iam.dppi.fon.interne.utils.DateUtils;
import ma.iam.dppi.fon.repository.DemandeRepository;
import ma.iam.dppi.fon.repository.DemandeTravauxProgrammeRepository;
import ma.iam.dppi.fon.repository.EtatDemandeRepository;
import ma.iam.dppi.fon.repository.InteractionRepository;
import ma.iam.dppi.fon.repository.LiaisonRepository;
import ma.iam.dppi.fon.repository.TraceCommentaireRepository;
import ma.iam.dppi.fon.repository.SousLiaisonRepository;
import ma.iam.dppi.fon.repository.TypeDemandeRepository;

/**
 * 
 * @author Z.BELGHAOUTI
 * 
 */
@Service
public class MapperDemande {

	@Autowired
	private MapperLiaison mapperLiaison;

	@Autowired
	private MapperInteraction mapperInteraction;

	@Autowired
	private MapperSousLiaison mapperSousLiaison;

	@Autowired
	private InteractionRepository interactionRepository;
	@Autowired
	private LiaisonRepository liaisonRepository;

	@Autowired
	private DemandeRepository demandeRepository;

	@Autowired
	private EtatDemandeRepository etatDemandeRepo;

	@Autowired
	private TypeDemandeRepository typeDemandeRepository;

	@Autowired
	private SousLiaisonRepository sousLiaisonRepository;

	@Autowired
	private TraceCommentaireRepository traceCommentaireRepository;

	@Autowired
	private MapperTraceCommentaire mapperTraceCommentaire;
	
	@Autowired
	private DemandeTravauxProgrammeRepository demandeTravauxProgrammeRepository;
	
	@Autowired
	private MapperDemandeTravauxProgramme mapperDemandeTravauxProgramme;

	public DemandeDto toDto(Demande d) {
		DemandeDto dto = null;
		if (d != null) {
			dto = new DemandeDto();

			dto.setIdt(d.getIdt());
	
			dto.setIntervenant(d.getIntervenant());
			dto.setDiagnostic(d.getDiagnostic());
			dto.setActionsRecommandees(d.getActionsRecommandees());
			dto.setRepondeur(d.getRepondeur());
			dto.setIdtDrReponse(d.getIdtDrReponse());
			dto.setDistanceMesureLiaisonFon(d.getDistanceMesureLiaisonFon());
			dto.setAxeGlobalMesure(d.getAxeGlobalMesure());
			dto.setBilanOptiqueDb(d.getBilanOptiqueDb());
			dto.setContactEmail(d.getContactEmail());
			dto.setTelephoneDr(d.getTelephoneDr());
			dto.setNomInterlocuteurDr(d.getNomInterlocuteurDr());
			dto.setPrenomInterlocuteurDr(d.getPrenomInterlocuteurDr());
			if (d.getDateDemande() != null) {
				dto.setDateDemande(DateUtils.toDateTime(d.getDateDemande()));
			}
			dto.setCommentaire(d.getCommentaire());
			if (d.getEtatDemande() != null) {
				dto.setEtatDemandeIdt(d.getEtatDemande().getIdt());
				dto.setEtatDemandeCode(d.getEtatDemande().getCode());
				dto.setEtatDemandeLabel(d.getEtatDemande().getLabel());
			}

			if (d.getIdtDemandeur() != null) {
				dto.setIdtDemandeur(d.getIdtDemandeur());
				dto.setLoginDemandeur(d.getLoginDemandeur());
				dto.setNomDemandeur(d.getNomDemandeur());
				dto.setPrenomDemandeur(d.getPrenomDemandeur());
			}

			if (d.getTypeDemande() != null) {
				dto.setTypeDemandeIdt(d.getTypeDemande().getIdt());
				dto.setTypeDemandeCode(d.getTypeDemande().getCode());
				dto.setTypeDemandeLabel(d.getTypeDemande().getDesignation());
			}

			if (d.getTypeDesaturation() != null) {
				dto.setTypeSaturationIdt(d.getTypeDesaturation().getIdt());
				dto.setTypeSaturationCode(d.getTypeDesaturation().getCode());
				dto.setTypeSaturationLabel(d.getTypeDesaturation().getLabel());
			}
		}
		return dto;
	}

	public List<DemandeDto> toDtos(List<Demande> entities) {
		List<DemandeDto> dtos = new ArrayList<>();
		if (entities != null && !entities.isEmpty()) {
			for (Demande entity : entities) {
				dtos.add(toDto(entity));
			}
		}
		return dtos;
	}

	public DemandeListDto toDtoDemande(Demande d) {
		DemandeListDto dto = null;
		if (d != null) {
			dto = new DemandeListDto();

			dto.setIdt(d.getIdt());
			dto.setIntervenant(d.getIntervenant());
			dto.setDiagnostic(d.getDiagnostic());
			dto.setActionsRecommandees(d.getActionsRecommandees());
			dto.setRepondeur(d.getRepondeur());
			dto.setIdtDrReponse(d.getIdtDrReponse());

			dto.setDistanceMesureLiaisonFon(d.getDistanceMesureLiaisonFon());
			dto.setAxeGlobalMesure(d.getAxeGlobalMesure());
			dto.setBilanOptiqueDb(d.getBilanOptiqueDb());
			dto.setContactEmail(d.getContactEmail());
			dto.setTelephoneDr(d.getTelephoneDr());
			dto.setNomInterlocuteurDr(d.getNomInterlocuteurDr());
			dto.setPrenomInterlocuteurDr(d.getPrenomInterlocuteurDr());
			if (d.getDateDemande() != null) {
				dto.setDateDemande(DateUtils.toDateTime(d.getDateDemande()));
			}
			dto.setCommentaire(d.getCommentaire());
			if (d.getEtatDemande() != null) {
				dto.setEtatDemandeIdt(d.getEtatDemande().getIdt());
				dto.setEtatDemandeCode(d.getEtatDemande().getCode());
				dto.setEtatDemandeLabel(d.getEtatDemande().getLabel());
			}

			if (d.getEtatDraft() != null) {
				dto.setEtatDemandeDraftIdt(d.getEtatDraft().getIdt());
				dto.setEtatDemandeDraftCode(d.getEtatDraft().getCode());
				dto.setEtatDemandeDraftLabel(d.getEtatDraft().getLabel());
			}

			if (d.getIdtDemandeur() != null) {
				dto.setIdtDemandeur(d.getIdtDemandeur());
				dto.setLoginDemandeur(d.getLoginDemandeur());
				dto.setNomDemandeur(d.getNomDemandeur());
				dto.setPrenomDemandeur(d.getPrenomDemandeur());
			}

			if (d.getTypeDemande() != null) {
				dto.setTypeDemandeIdt(d.getTypeDemande().getIdt());
				dto.setTypeDemandeCode(d.getTypeDemande().getCode());
				dto.setTypeDemandeLabel(d.getTypeDemande().getDesignation());
			}

			if (d.getTypeDesaturation() != null) {
				dto.setTypeSaturationIdt(d.getTypeDesaturation().getIdt());
				dto.setTypeSaturationCode(d.getTypeDesaturation().getCode());
				dto.setTypeSaturationLabel(d.getTypeDesaturation().getLabel());
			}

			if (d.getLiaison() != null) {
				LiaisonDto liaisonDto = mapperLiaison.toDtoLiaison(d.getLiaison());
				dto.setLiaisonDto(liaisonDto);
			}

			List<String> entities = null;
			if (SecurityContextHelper.isDon()) {
				if (SecurityContextHelper.isDef()) {
					if (SecurityContextHelper.isDr()) {
						entities = new ArrayList<>();
						entities.add("DON");
						entities.add("DEF");
						entities.add("DR");
					} else {
						entities = new ArrayList<>();
						entities.add("DON");
						entities.add("DEF");
					}
				} else {
					if (SecurityContextHelper.isDr()) {
						entities = new ArrayList<>();
						entities.add("DON");
						entities.add("DR");
					} else {
						entities = new ArrayList<>();
						entities.add("DON");
					}
				}
			} else {
				if (SecurityContextHelper.isDef()) {
					if (SecurityContextHelper.isDr()) {
						entities = new ArrayList<>();
						entities.add("DEF");
						entities.add("DR");
					} else {
						entities = new ArrayList<>();
						entities.add("DEF");
					}
				} else {
					if (SecurityContextHelper.isDr()) {
						entities = new ArrayList<>();
						entities.add("DR");
					}
				}
			}

			List<Interaction> interactions = interactionRepository.getListInteractionByDemandeIdt(d.getIdt(), entities);
			List<InteractionDto> interactionDtos = mapperInteraction.toDtos(interactions);
			dto.setListInteraction(interactionDtos);

			List<DemandeTravauxProgramme> demandeTp = demandeTravauxProgrammeRepository
					.getListDemandeTravauxProgrammeeByDemandeIdt(d.getIdt());
			List<DemandeTravauxProgrammeDto> travauxProgrammeDtos = mapperDemandeTravauxProgramme.toDtos(demandeTp);

//			Optional<Liaison> liaisonTpOptional = liaisonRepository.findById(trava)
			dto.setDemandeTp(travauxProgrammeDtos);
			
			if (d.getLiaison() != null) {
				List<SousLiaison> troncons = sousLiaisonRepository
						.getListSousLiaisonByLiaisonIdt(d.getLiaison().getIdt());
				List<SousLiaisonDto> sousLiaisonDtos = mapperSousLiaison.toDtos(troncons);
				dto.setListSousLiaison(sousLiaisonDtos);
			}

			List<TraceCommentaire> commentaires = traceCommentaireRepository
					.getListTraceCommentaireByDemandeIdt(d.getIdt());
			List<TraceCommentaireDto> commentaireDtos = mapperTraceCommentaire.toDtos(commentaires);
			dto.setListTraceCommentaire(commentaireDtos);
		}
		return dto;
	}

	public List<DemandeListDto> toDtosDemandes(List<Demande> entities) {
		List<DemandeListDto> dtos = new ArrayList<>();
		if (entities != null && !entities.isEmpty()) {
			for (Demande entity : entities) {
				dtos.add(toDtoDemande(entity));
			}
		}
		return dtos;
	}

	public Demande toEntity(DemandeDto demandeDto) {
		Demande demandeEntity = null;
		if (demandeDto != null) {
			demandeEntity = new Demande();
			demandeEntity.setCommentaire(demandeDto.getCommentaire());
			demandeEntity.setDateDebut(DateUtils.stringToDate(demandeDto.getDateDebut()));
			demandeEntity.setDateFin(DateUtils.stringToDate(demandeDto.getDateFin()));
			demandeEntity.setDateReception(DateUtils.stringToDate(demandeDto.getDateReception()));
			demandeEntity.setMotif(demandeDto.getMotif());
			demandeEntity.setIntervenant(demandeDto.getIntervenant());
			demandeEntity.setDiagnostic(demandeDto.getDiagnostic());
			demandeEntity.setActionsRecommandees(demandeDto.getActionsRecommandees());
			demandeEntity.setRepondeur(demandeDto.getRepondeur());
			demandeEntity.setIdtDrReponse(demandeDto.getIdtDrReponse());
			demandeEntity.setDistanceMesureLiaisonFon(demandeDto.getDistanceMesureLiaisonFon());
			demandeEntity.setAxeGlobalMesure(demandeDto.getAxeGlobalMesure());
			demandeEntity.setBilanOptiqueDb(demandeDto.getBilanOptiqueDb());
			demandeEntity.setContactEmail(demandeDto.getContactEmail());
			demandeEntity.setTelephoneDr(demandeDto.getTelephoneDr());
			demandeEntity.setNomInterlocuteurDr(demandeDto.getNomInterlocuteurDr());
			demandeEntity.setPrenomInterlocuteurDr(demandeDto.getPrenomInterlocuteurDr());
			EtatDemande etatDemande = etatDemandeRepo.findByCode("PENDING");
			if (etatDemande != null) {
				demandeEntity.setEtatDemande(etatDemande);
			}
			TypeDemande typeDemande = typeDemandeRepository.findByCode("TRAVAUX_PROGRAMME");
			if (typeDemande != null) {
				demandeEntity.setTypeDemande(typeDemande);
			}
			if (demandeDto.getLiaison() != null) {
				for (Long entity : demandeDto.getLiaison()) {
					Optional<Liaison> liaisonOptional = liaisonRepository.findById(entity);
					if (liaisonOptional.isPresent()) {
						demandeEntity.setLiaison(liaisonOptional.get());
					}

					demandeRepository.save(demandeEntity);

				}
			}
		}
		return demandeEntity;

	}

}
