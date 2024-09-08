package ma.iam.dppi.fon.mappers;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import ma.iam.dppi.fon.communs.domain.Parametrage;
import ma.iam.dppi.fon.communs.domain.Utilisateur;
import ma.iam.dppi.fon.communs.repository.ParametrageRepository;
import ma.iam.dppi.fon.communs.repository.UtilisateurRepository;
import ma.iam.dppi.fon.domain.Demande;
import ma.iam.dppi.fon.domain.Devis;
import ma.iam.dppi.fon.domain.Escalade;
import ma.iam.dppi.fon.domain.EtatDemande;
import ma.iam.dppi.fon.domain.Liaison;
import ma.iam.dppi.fon.domain.TypeDemande;
import ma.iam.dppi.fon.domain.TypeDesaturation;
import ma.iam.dppi.fon.dtos.DemandeAccesCriteresDto;
import ma.iam.dppi.fon.dtos.DemandeDto;
import ma.iam.dppi.fon.dtos.DemandeOperationCriteresDto;
import ma.iam.dppi.fon.dtos.DevisDto;
import ma.iam.dppi.fon.dtos.TraceCommentaireDto;
import ma.iam.dppi.fon.repository.DevisRepository;
import ma.iam.dppi.fon.repository.EscaladeRepository;
import ma.iam.dppi.fon.repository.EtatDemandeRepository;
import ma.iam.dppi.fon.repository.LiaisonRepository;
import ma.iam.dppi.fon.repository.TypeDemandeRepository;
import ma.iam.dppi.fon.utils.DateUtils;
import ma.iam.dppi.fon.utils.Utils;

@Component
public class MapperDemande {

	@Autowired
	private EtatDemandeRepository etatDemandeRepo;
	@Autowired
	private LiaisonRepository liasionRepo;
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	@Autowired
	private ParametrageRepository parametrageRepository;
	@Autowired
	private TypeDemandeRepository typeDemandeRepository;

	@Autowired
	private DevisMapper devisMapper;
	@Autowired
	private DevisRepository devisRepository;
	@Autowired
	private EscaladeRepository escaladeRepository;

	public DemandeDto entityToDto(Demande demande) {
		if (demande == null) {
			return null;
		}
		DemandeDto dto = new DemandeDto();
		dto.setIdt(demande.getIdt());
		dto.setCommentaire(demande.getCommentaire());
		Escalade escalade = escaladeRepository.getLastEscaladeByIdtDemande(demande.getIdt());
		if (escalade != null) {
			dto.setNumOderEscalade(escalade.getNumOderEscalade());
		}

		if (demande.getDateDemande() != null) {
			dto.setDateDemande(DateUtils.toDateTime(demande.getDateDemande()));
		}

		if (demande.getEtatDemande() != null) {
			dto.setEtatDemandeCode(demande.getEtatDemande().getCode());
			dto.setEtatDemandeLabel(demande.getEtatDemande().getLabel());
			dto.setIdtEtatDemande(demande.getEtatDemande().getIdt());
		}

		if (demande.getLiaison() != null) {
			dto.setIdtLiaison(demande.getLiaison().getIdt());
			dto.setLiaisonRef(demande.getLiaison().getReference());
//			dto.setLiaisonCodeLiaisonErpt(demande.getLiaison().getCodeLiaisonErpt());
			//todo salman
		}
		if (demande.getTypeDemande() != null) {
			dto.setTypeCode(demande.getTypeDemande().getCode());
			dto.setTypeDesignation(demande.getTypeDemande().getDesignation());
			dto.setIdtType(demande.getTypeDemande().getIdt());
		}
		if (demande.getTypeDesaturation() != null) {
			dto.setTypeDesaturationCode(demande.getTypeDesaturation().getCode());
			dto.setTypeDesaturationLabel(demande.getTypeDesaturation().getLabel());
			dto.setIdtTypeDesaturationDemande(demande.getTypeDesaturation().getIdt());
		}
		String login = SecurityContextHolder.getContext().getAuthentication().getName();

		Utilisateur utilisateur = utilisateurRepository.findByLogin(login);
		if (utilisateur != null) {
			dto.setIdtDemandeur(utilisateur.getIdt());
			dto.setNomDemandeur(utilisateur.getNom());
			dto.setPrenomDemandeur(utilisateur.getPrenom());
			dto.setLoginDemandeur(utilisateur.getLogin());
		}

//	    List<Interaction> interactions = interactionRepository.getListInteractionByDemandeIdt(demande.getIdt());
//		List<InteractionDto> interactionDtos = mapperInteraction.listEntityToListDto(interactions);
//		dto.setInteractions(interactionDtos);

		List<Devis> devis = devisRepository.getListByDevisIdt(demande.getIdt());
		List<DevisDto> devisDtos = devisMapper.listEntityToListDto(devis);
		dto.setDevis(devisDtos);

		return dto;
	}

	public TraceCommentaireDto entityTraceToDto(Demande demande) {
		if (demande == null) {
			return null;
		}
		TraceCommentaireDto trace = new TraceCommentaireDto();
		trace.setCommentaire(demande.getCommentaire());
		return trace;
	}

	public Demande dtoToEnityTrace(TraceCommentaireDto trace) {
		if (trace == null) {
			return null;
		}
		Demande demande = new Demande();
		demande.setCommentaire(trace.getCommentaire());
		return demande;
	}

//	public Demande dtoToEntity(DemandeDto demandeDto) {
//	    if (demandeDto == null) {
//	        return null;
//	    }
//	    Demande demande = new Demande();
//	    Date now = new Date();
//	    demande.setIdt(22L);
//	    demande.setDateDemande(now);
//	    
////	    demande.setReference("REF_23" + (demande.getIdt() + 1));
//	    String login = SecurityContextHolder.getContext().getAuthentication().getName();
//	    Utilisateur utilisateur = utilisateurRepository.findByLogin(login);
//	    demande.setIdtDemandeur(utilisateur.getIdt());
//	    demande.setNomDemandeur(login);
//	    demande.setPrenomDemandeur(utilisateur.getPrenom());
//	    demande.setLoginDemandeur(login);
//	   
//	    
//	
//	    Optional<EtatDemande>  etatDemandeOptional = etatRepository.findById(1L);
//		   if (etatDemandeOptional.isPresent()) {
//			   EtatDemande etatDemande = etatDemandeOptional.get();
//			   System.out.println(etatDemande.getIdt());
//			   demande.setEtatDemande(etatDemande);
//		   }
//	    demande.setCommentaire(demandeDto.getCommentaire());
//
//	   
//
//	    if (demandeDto.getIdtEtatDemande() != null) {
//	        demande.getEtatDemande().setCode(demandeDto.getEtatDemandeCode());
//	        demande.getEtatDemande().setLabel(demandeDto.getEtatDemandeLabel());
//	        demande.getEtatDemande().setIdt(demandeDto.getIdtEtatDemande());
//	    }
//	    
//
//	    if(demandeDto.getIdtLiaison() != null) {
//	    	Liaison liaison =new Liaison();
//			Optional<Liaison> liaisonOptional = liaisonRepository.findById(demandeDto.getIdt());
//			if(liaisonOptional.isPresent()) {
//				liaison =liaisonOptional.get();
//			}
//	    	demande.setLiaison(liaison);
//			demande.getLiaison().setReference(demandeDto.getLiaisonRef());
//			demande.getLiaison().setCodeLiaisonErpt(demandeDto.getLiaisonCodeLiaisonErpt());
//			demande.getLiaison().setXGpsDepart(demandeDto.getXGpsDepart());
//			demande.getLiaison().setYGpsDepart(demandeDto.getYGpsDepart());
//			demande.getLiaison().setXGpsArrivee(demandeDto.getXGpsArrivee());
//			demande.getLiaison().setYGpsArrivee(demandeDto.getYGpsArrivee());
//		}
//		if(demandeDto.getIdtType() != null) {
//			TypeDemande type = new TypeDemande();
//			 Optional<TypeDemande>   typeOptional = typeRepository.findById(1L);
//			   if (typeOptional.isPresent()) {
//				  type=typeOptional.get();
//				   demande.setTypeDemande(type);
//			   }
//			   demande.setTypeDemande(type);
//			demande.getTypeDemande().setCode(demandeDto.getTypeCode());
//			demande.getTypeDemande().setDesignation(demandeDto.getTypeDesignation());
//			demande.getTypeDemande().setIdt(demandeDto.getIdtType());
//		}
//		if(demande.getTypeDesaturation() != null) {
//			demande.getTypeDesaturation().setCode(demandeDto.getTypeDesaturationCode());
//			demande.getTypeDesaturation().setLabel(demandeDto.getTypeDesaturationLabel());
//			demande.getTypeDesaturation().setIdt(demandeDto.getIdtTypeDesaturationDemande());
//		}
//		if(demandeDto.getDateDemande()!= null) {
//			demandeDto.setDateDemande(DateUtils.toDateTime(demande.getDateDemande()));
//		}
//	
//	    return demande;
//	}

	public Demande dtoToEntity(DemandeDto dto) throws ParseException {
		if (dto == null) {
			return null;
		}
		Demande demande = new Demande();
		// Liaison

		System.out.println("========" + dto.getIdtLiaison() + "=======" + dto.getCommentaire());
		Optional<Liaison> LiaOptional = liasionRepo.findById(dto.getIdtLiaison());
		if (LiaOptional.isPresent()) {
			demande.setLiaison(LiaOptional.get());

		}

		// date
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

		demande.setDateDemande(now);
		demande.setCommentaire(dto.getCommentaire());

		if (dto.getIdtEtatDemande() != null) {
			Long idEtatDemande = dto.getIdtEtatDemande();
			Optional<EtatDemande> etatDemandeOptional = etatDemandeRepo.findById(idEtatDemande);
			if (etatDemandeOptional.isPresent()) {
				EtatDemande etatDemande = etatDemandeOptional.get();
				demande.setEtatDemande(etatDemande);
			}

		}

		if (dto.getIdtType() != null) {
			Long idtTypeDemande = dto.getIdtType() + 1L;
			Optional<TypeDemande> typeDemandeOptional = typeDemandeRepository.findById(idtTypeDemande);
			if (typeDemandeOptional.isPresent()) {
				demande.setTypeDemande(typeDemandeOptional.get());

			}
		}

		if (dto.getTypeDesaturationCode() != null && dto.getTypeDesaturationLabel() != null
				&& dto.getIdtTypeDesaturationDemande() != null) {
			TypeDesaturation typeDesaturation = new TypeDesaturation();
			typeDesaturation.setCode(dto.getTypeDesaturationCode());
			typeDesaturation.setLabel(dto.getTypeDesaturationLabel());
			typeDesaturation.setIdt(dto.getIdtTypeDesaturationDemande());
			demande.setTypeDesaturation(typeDesaturation);
		}

		String login = SecurityContextHolder.getContext().getAuthentication().getName();
		Utilisateur utilisateur = utilisateurRepository.findByLogin(login);
		if (utilisateur != null) {
			demande.setIdtDemandeur(utilisateur.getIdt());

			demande.setNomDemandeur(utilisateur.getNom());
			demande.setPrenomDemandeur(utilisateur.getPrenom());
			demande.setLoginDemandeur(utilisateur.getLogin());
		}
		return demande;
	}

	public Demande dtoToEntityy(DemandeOperationCriteresDto dto) throws ParseException {
		if (dto == null) {
			return null;
		}
		Demande demande = new Demande();
		// Liaison

		Optional<Liaison> LiaOptional = liasionRepo.findById(dto.getIdtLiaison());
		if (LiaOptional.isPresent()) {
			demande.setLiaison(LiaOptional.get());

		}
		// date
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

		demande.setDateDemande(now);
		demande.setDateReception(now);
		demande.setCommentaire(dto.getCommentaire());
		demande.setContactDemandeur(dto.getContactDemandeur());
		demande.setPrGpsX(dto.getxGpsPointRegeneration());
		demande.setPrGpsY(dto.getyGpsPointRegeneration());
		demande.setRefRegenerationErpt(dto.getReferanceRegenerationErpt());
		demande.setDescriptionAcces(dto.getDescriptionIncident());
		demande.setDescriptionIncident(dto.getDescriptionIncident());
		demande.setDescriptionObstacle(dto.getDescriptionObstacle());
		demande.setPointObstacleGPSN(dto.getPointObstacleGPSN());
		demande.setPointObstacleGPSW(dto.getPointObstacleGPSW());
		demande.setDateDebut(dto.getDateDebut());
		demande.setDateFin(dto.getDateFin());
		demande.setDescriptionAcces(dto.getDescriptionAcces());
		demande.setTypeIntervention(dto.getTypeIntervention());
		demande.setPrGpsX(dto.getxGpsPointRegeneration());
		demande.setPrGpsY(dto.getyGpsPointRegeneration());
		demande.setRefRegenerationErpt(dto.getReferanceRegenerationErpt());
		if (dto.getIdtEtatDemande() != null) {
//			Long idEtatDemande = dto.getIdtEtatDemande();
			EtatDemande etatDemande = etatDemandeRepo.findByCode("PENDING");
			if (etatDemande != null) {
//				EtatDemande etatDemande = etatDemandeOptional;
				demande.setEtatDemande(etatDemande);
			}

		}

		if (dto.getIdtType() != null) {
			Long idtTypeDemande = dto.getIdtType();
			Optional<TypeDemande> typeDemandeOptional = typeDemandeRepository.findById(idtTypeDemande);
			if (typeDemandeOptional.isPresent()) {
				demande.setTypeDemande(typeDemandeOptional.get());

			}
		}

//	    if (dto.getTypeDesaturationCode() != null && dto.getTypeDesaturationLabel() != null && dto.getIdtTypeDesaturationDemande() != null) {
//	        TypeDesaturation typeDesaturation = new TypeDesaturation();
//	        typeDesaturation.setCode(dto.getTypeDesaturationCode());
//	        typeDesaturation.setLabel(dto.getTypeDesaturationLabel());
//	        typeDesaturation.setIdt(dto.getIdtTypeDesaturationDemande());
//	        demande.setTypeDesaturation(typeDesaturation);
//	    }

		String login = SecurityContextHolder.getContext().getAuthentication().getName();
		Utilisateur utilisateur = utilisateurRepository.findByLogin(login);
		if (utilisateur != null) {
			demande.setIdtDemandeur(utilisateur.getIdt());

			demande.setNomDemandeur(utilisateur.getNom());
			demande.setPrenomDemandeur(utilisateur.getPrenom());
			demande.setLoginDemandeur(utilisateur.getLogin());
		}

		return demande;
	}

	public List<DemandeDto> entityListToDtoList(List<Demande> demandeList) {
		if (demandeList == null || demandeList.isEmpty()) {
			return null;
		}

		List<DemandeDto> demandeDtoList = new ArrayList<>();
		for (Demande demande : demandeList) {
			demandeDtoList.add(entityToDto(demande));
		}

		return demandeDtoList;
	}

	// Operation
	public Demande dtoOperationToEntity(DemandeOperationCriteresDto dto) throws ParseException {

//	    if (dto.getDateDemande() != null) {
//	    	SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
//	    	String date = dto.getDateDemande();
//	    	Date datenow =  formatter.parse(date);
//	    	demande.setDateDemande(DateUtils.toDateTime(datenow));
//	        
//	    }
		if (dto == null) {
			return null;
		}
		Demande demande = new Demande();
		Optional<Liaison> LiaOptional = liasionRepo.findById(dto.getIdtLiaison());
		if (LiaOptional.isPresent()) {
			demande.setLiaison(LiaOptional.get());

		}
		demande.setCommentaire(dto.getCommentaire());

		if (dto.getIdtEtatDemande() != null) {
			Optional<EtatDemande> etatDemandeOptional = etatDemandeRepo.findById(dto.getIdtEtatDemande());
			if (etatDemandeOptional.isPresent()) {
				EtatDemande etatDemande = etatDemandeOptional.get();
				demande.setEtatDemande(etatDemande);
			}

		}

		if (dto.getIdtLiaison() != null) {
			Optional<Liaison> liaisonOptional = liasionRepo.findById(dto.getIdtLiaison());
			if (liaisonOptional.isPresent()) {
				Liaison liaison = liaisonOptional.get();
				demande.setLiaison(liaison);
			}

		}

//	    if (dto.getTypeCode() != null && dto.getTypeDesignation() != null && dto.getIdtType() != null) {
//	        TypeDemande typeDemande = new TypeDemande();
//	        typeDemande.setCode(dto.getTypeCode());
//	        typeDemande.setDesignation(dto.getTypeDesignation());
//	        typeDemande.setIdt(dto.getIdtType());
//	        demande.setTypeDemande(typeDemande);
//	    }
//
//	    if (dto.getTypeDesaturationCode() != null && dto.getTypeDesaturationLabel() != null && dto.getIdtTypeDesaturationDemande() != null) {
//	        TypeDesaturation typeDesaturation = new TypeDesaturation();
//	        typeDesaturation.setCode(dto.getTypeDesaturationCode());
//	        typeDesaturation.setLabel(dto.getTypeDesaturationLabel());
//	        typeDesaturation.setIdt(dto.getIdtTypeDesaturationDemande());
//	        demande.setTypeDesaturation(typeDesaturation);
//	    }

		String login = SecurityContextHolder.getContext().getAuthentication().getName();
		Utilisateur utilisateur = utilisateurRepository.findByLogin(login);
		if (utilisateur != null) {
			demande.setIdtDemandeur(utilisateur.getIdt());
			demande.setNomDemandeur(utilisateur.getNom());
			demande.setPrenomDemandeur(utilisateur.getPrenom());
			demande.setLoginDemandeur(utilisateur.getLogin());
		}

		return demande;
	}

	public Demande dtoAccesToEntity(DemandeAccesCriteresDto dto) throws ParseException {
		if (dto == null) {
			return null;
		}
		Demande demande = new Demande();
		// Liaison

		Optional<Liaison> LiaOptional = liasionRepo.findById(dto.getIdtLiaison());
		if (LiaOptional.isPresent()) {
			demande.setLiaison(LiaOptional.get());
			
		}
		// date
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

		demande.setDateDemande(now);
		demande.setCommentaire(dto.getCommentaire());
		demande.setDescriptionAcces(dto.getDescriptionIncident());
		demande.setDescriptionIncident(dto.getDescriptionIncident());
		demande.setDescriptionObstacle(dto.getDescriptionObstacle());
		demande.setPointObstacleGPSN(dto.getPointObstacleGPSN());
		demande.setPointObstacleGPSW(dto.getPointObstacleGPSW());
		demande.setDateDebut(dto.getDateDebut());
		demande.setDateFin(dto.getDateFin());
		demande.setDescriptionAcces(dto.getDescriptionAcces());
		demande.setTypeIntervention(dto.getTypeIntervention());
		
		demande.setCommentaire(dto.getCommentaire());
		demande.setContactDemandeur(dto.getContactDemandeur());
		demande.setDateReception(DateUtils.stringToDate(dto.getDateDebutReception()));
		demande.setDateFinReception(DateUtils.stringToDate(dto.getDateFinReception()));
		
		if (dto.getIdtEtatDemande() != null) {
			Long idEtatDemande = dto.getIdtEtatDemande();
			Optional<EtatDemande> etatDemandeOptional = etatDemandeRepo.findById(idEtatDemande);
			if (etatDemandeOptional.isPresent()) {
				EtatDemande etatDemande = etatDemandeOptional.get();
				demande.setEtatDemande(etatDemande);
			}

		}

		if (dto.getIdtType() != null) {
			Long idtTypeDemande = dto.getIdtType();
			Optional<TypeDemande> typeDemandeOptional = typeDemandeRepository.findById(idtTypeDemande);
			if (typeDemandeOptional.isPresent()) {
				demande.setTypeDemande(typeDemandeOptional.get());

			}
		}

//	    if (dto.getTypeDesaturationCode() != null && dto.getTypeDesaturationLabel() != null && dto.getIdtTypeDesaturationDemande() != null) {
//	        TypeDesaturation typeDesaturation = new TypeDesaturation();
//	        typeDesaturation.setCode(dto.getTypeDesaturationCode());
//	        typeDesaturation.setLabel(dto.getTypeDesaturationLabel());
//	        typeDesaturation.setIdt(dto.getIdtTypeDesaturationDemande());
//	        demande.setTypeDesaturation(typeDesaturation);
//	    }

		String login = SecurityContextHolder.getContext().getAuthentication().getName();
		Utilisateur utilisateur = utilisateurRepository.findByLogin(login);
		if (utilisateur != null) {
			demande.setIdtDemandeur(utilisateur.getIdt());

			demande.setNomDemandeur(utilisateur.getNom());
			demande.setPrenomDemandeur(utilisateur.getPrenom());
			demande.setLoginDemandeur(utilisateur.getLogin());
		}

		return demande;
	}
	//////////////////////////////

}
