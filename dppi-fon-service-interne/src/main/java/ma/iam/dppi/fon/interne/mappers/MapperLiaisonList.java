package ma.iam.dppi.fon.interne.mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import ma.iam.dppi.fon.communs.domain.Commune;
import ma.iam.dppi.fon.communs.domain.Operateur;
import ma.iam.dppi.fon.communs.repository.CommuneRepository;
import ma.iam.dppi.fon.communs.repository.OperateurRepository;
import ma.iam.dppi.fon.domain.Demande;
import ma.iam.dppi.fon.domain.Interaction;
import ma.iam.dppi.fon.domain.Liaison;
import ma.iam.dppi.fon.domain.SousLiaison;
import ma.iam.dppi.fon.interne.dtos.CommandeListDto;
import ma.iam.dppi.fon.interne.dtos.DemandeDto;
import ma.iam.dppi.fon.interne.dtos.InteractionDto;
import ma.iam.dppi.fon.interne.dtos.SousLiaisonDto;
import ma.iam.dppi.fon.interne.security.SecurityContextHelper;
import ma.iam.dppi.fon.interne.utils.DateUtils;
import ma.iam.dppi.fon.repository.DemandeRepository;
import ma.iam.dppi.fon.repository.InteractionRepository;
import ma.iam.dppi.fon.repository.SousLiaisonRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @author Z.BELGHAOUTI
 * 
 */
@Service
public class MapperLiaisonList {
	
	@Autowired
	private CommuneRepository communeRepository;
	
	@Autowired
	private OperateurRepository operateurRepository;
	
	@Autowired
	private DemandeRepository demandeRepository;
	
	@Autowired
	private InteractionRepository interactionRepository;
	
	@Autowired
	private SousLiaisonRepository sousLiaisonRepository;
	
	@Autowired
	private MapperInteraction mapperInteraction;
	
	@Autowired
	private MapperDemande mapperDemande;
	
	@Autowired
	private MapperSousLiaison mapperSousLiaison;
	
	public CommandeListDto toDto(Liaison l) {
		CommandeListDto dto = null;
		if(l != null) {
			dto = new CommandeListDto();
			
			dto.setIdt(l.getIdt());
			dto.setReference(l.getReference());
			if (l.getIdtCommune() != null) {
				Optional<Commune> optionalCommune = communeRepository.findById(l.getIdtCommune());
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
			
			if (l.getEtatLiaison() != null) {
				dto.setEtatLiaisonIdt(l.getEtatLiaison().getIdt());
				dto.setEtatLiaisonCode(l.getEtatLiaison().getCode());
				dto.setEtatLiaisonLabel(l.getEtatLiaison().getLabel());
			}
			
			if (l.getIdtOperateur() != null) {
				Optional<Operateur> optionalOperateur = operateurRepository.findById(l.getIdtOperateur());
				if (optionalOperateur.isPresent()) {
					Operateur operateur = optionalOperateur.get();
					if (operateur != null) {
						dto.setOperateurIdt(operateur.getIdt());
						dto.setOperateurCode(operateur.getCode());
						dto.setOperateurLabel(operateur.getLabel());
					}
				}
				
			}
			dto.setCodeSiteErpt(l.getCodeSiteErpt());
			dto.setxGpsDepart(l.getxGpsDepart());
			dto.setyGpsArrivee(l.getyGpsArrivee());
			dto.setxGpsArrivee(l.getxGpsArrivee());
			dto.setyGpsDepart(l.getyGpsDepart());
			dto.setDistanceDisponible(l.getDistanceDisponible());
			dto.setDistanceSature(l.getDistanceSature());
		
			dto.setTypeLiaisonCode(l.getTypeLiaison().getCode());
			Demande demande = demandeRepository.getLastDemandeByIdtLiaison(l.getIdt());
			if (demande != null) {
				dto.setIdtDemande(demande.getIdt());
				if (demande.getEtatDemande() != null) {
					dto.setEtatDemandeIdt(demande.getEtatDemande().getIdt());
					dto.setEtatDemandeCode(demande.getEtatDemande().getCode());
					dto.setEtatDemandeLabel(demande.getEtatDemande().getLabel());
				}
				
				if (demande.getTypeDemande() != null) {
					dto.setTypeDemandeIdt(demande.getTypeDemande().getIdt());
					dto.setTypeDemandeCode(demande.getTypeDemande().getCode());
					dto.setTypeDemandeLabel(demande.getTypeDemande().getDesignation());
				}
				dto.setCommentaire(demande.getCommentaire());
				dto.setDemandeurLogin(demande.getLoginDemandeur());
				dto.setDemandeurNom(demande.getNomDemandeur());
				dto.setDemandeurPrenom(demande.getPrenomDemandeur());
				if (demande.getDateDemande() != null) {
					dto.setDateDemande(DateUtils.toDateTime(demande.getDateDemande()));
				}
				
			}
			
			List<Demande> demandes = demandeRepository.getListByDemandeIdt(l.getIdt());
			List<DemandeDto> demandeDtos = mapperDemande.toDtos(demandes);
			dto.setListDemande(demandeDtos);
			
			/******************************* ALL INTERACTIONS OF ALL DEMANDES LIAISON **************************************/
			List<String> entities = null;
			if (SecurityContextHelper.isDon()) {
				if (SecurityContextHelper.isDef()) {
					if (SecurityContextHelper.isDr()) {
						entities = new ArrayList<>();
						entities.add("DON");
						entities.add("DEMT");
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
				if (SecurityContextHelper.isDemt()) {
					if (SecurityContextHelper.isDr()) {
						entities = new ArrayList<>();
						entities.add("DEMT");
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
			
			List<Interaction> interactions = interactionRepository.getListInteractionByLiaisonIdt(l.getIdt(), entities);
			List<InteractionDto> interactionDtos = mapperInteraction.toDtos(interactions);
			dto.setListInteraction(interactionDtos);
			
			List<SousLiaison> troncons = sousLiaisonRepository.getListSousLiaisonByLiaisonIdt(l.getIdt());
			List<SousLiaisonDto> sousLiaisonDtos = mapperSousLiaison.toDtos(troncons);
			dto.setListSousLiaison(sousLiaisonDtos);
		}
		return dto;
	}
	
	public List<CommandeListDto> toDtos(List<Liaison> entities) {
		List<CommandeListDto> dtos = new ArrayList<>();
		if(entities != null && !entities.isEmpty()) {
			for(Liaison entity : entities) {
				dtos.add(toDto(entity));
			}			
		}
		return dtos;
	}
}
