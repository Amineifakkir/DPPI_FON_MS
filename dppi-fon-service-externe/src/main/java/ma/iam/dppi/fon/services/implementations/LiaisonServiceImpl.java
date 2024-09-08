package ma.iam.dppi.fon.services.implementations;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ma.iam.dppi.fon.communs.domain.Parametrage;
import ma.iam.dppi.fon.communs.domain.Utilisateur;
import ma.iam.dppi.fon.communs.repository.ParametrageRepository;
import ma.iam.dppi.fon.communs.repository.UtilisateurRepository;
import ma.iam.dppi.fon.constants.ActionCode;
import ma.iam.dppi.fon.domain.Demande;
import ma.iam.dppi.fon.domain.Email;
import ma.iam.dppi.fon.domain.Escalade;
import ma.iam.dppi.fon.domain.EtatDemande;
import ma.iam.dppi.fon.domain.EtatLiaison;
import ma.iam.dppi.fon.domain.Liaison;
import ma.iam.dppi.fon.domain.PieceJointe;
import ma.iam.dppi.fon.domain.TypeDemande;
import ma.iam.dppi.fon.dtos.AddDemandeDevisDto;
import ma.iam.dppi.fon.dtos.DemandeAccesCriteresDto;
import ma.iam.dppi.fon.dtos.DemandeGcCriteresDto;
import ma.iam.dppi.fon.dtos.DemandeOperationCriteresDto;
import ma.iam.dppi.fon.dtos.EscaladeDto;
import ma.iam.dppi.fon.dtos.LiaisonDemandeAddDto;
import ma.iam.dppi.fon.dtos.LiaisonDemandeUppdateDto;
import ma.iam.dppi.fon.dtos.LiaisonDto;
import ma.iam.dppi.fon.mappers.DemandeUpdateMapper;
import ma.iam.dppi.fon.mappers.EscaladeMapper;
import ma.iam.dppi.fon.mappers.IntervenantMapper;
import ma.iam.dppi.fon.mappers.LiaisonDemandeAddMapper;
import ma.iam.dppi.fon.mappers.LiaisonDemandeUpdateMapper;
import ma.iam.dppi.fon.mappers.MapperDemande;
import ma.iam.dppi.fon.mappers.MapperLiaison;
import ma.iam.dppi.fon.mappers.MapperTraceComentaire;
import ma.iam.dppi.fon.repository.DemandeRepository;
import ma.iam.dppi.fon.repository.EmailRepository;
import ma.iam.dppi.fon.repository.EscaladeRepository;
import ma.iam.dppi.fon.repository.EtatDemandeRepository;
import ma.iam.dppi.fon.repository.EtatLiaisonRepository;
import ma.iam.dppi.fon.repository.LiaisonRepository;
import ma.iam.dppi.fon.repository.PieceJointeRepository;
import ma.iam.dppi.fon.repository.TraceCommentaireRepository;
import ma.iam.dppi.fon.repository.TypeDemandeRepository;
import ma.iam.dppi.fon.services.ILiaisonService;
import ma.iam.dppi.fon.services.ITraceCommentaireService;
import ma.iam.dppi.fon.services.ITraceService;
import ma.iam.dppi.utils.Utils;

@Service
@RequiredArgsConstructor
public class LiaisonServiceImpl implements ILiaisonService {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	private static final String SEPARATOR = File.separator;

	private UtilisateurRepository utilisateurRepository;

	private LiaisonRepository liasionRepo;
	
	private EtatLiaisonRepository etatLiaisonRepo;

	private EtatDemandeRepository etatDemandeRepo;

	private PieceJointeRepository pieceJointeDaoImpl;

	private MapperLiaison mapperLiaison;

	private TraceCommentaireRepository traceCommentaireRepo;

	private LiaisonDemandeAddMapper liaisonAddMapper;

	private LiaisonRepository liaisonDaoImpl;

	private DemandeRepository demandeRepository;

	private TypeDemandeRepository typeDemandeRepo;

	private DemandeUpdateMapper demandeUpdateMapper;

	private MapperTraceComentaire traceCommentaireMapper;

	private LiaisonDemandeUpdateMapper liaisonDemandeUpdateMapper;

	private MapperDemande demandeMapper;

	private PieceJointeRepository jointeRepository;

	private ITraceCommentaireService traceCommentaireService;

	@Value("${outpath}")
	private String fileUploadDir;

	private ITraceService traceServiceImpl;

	private TypeDemandeRepository typeDemandeRepository;

	private IntervenantMapper intervenantMapper;

	private ParametrageRepository parametrageRepository;

	private EmailRepository emailRepository;

	private EscaladeRepository escaladeRepository;

	private EscaladeMapper escaladeMapper;

	private LiaisonDemandeAddMapper liaisonDemandeAddMapper;

	@Value("${application.externe.url}")
	private String pathInterneProject;

	@Override
	public List<LiaisonDto> getListDemandeGcByParams(DemandeGcCriteresDto critDto) throws ParseException {

		List<LiaisonDto> dtos = new ArrayList<>();

		Sort sort = Sort.by(Sort.Direction.DESC, "idt");
		Pageable pageable = PageRequest.of(critDto.getStart(), critDto.getEnd(), sort);
		List<Liaison> liaisons = null;
		List<Long> etats = null;
		if (critDto.getIdEtatDemande() != null && critDto.getIdEtatDemande() != 0) {
			if (critDto.getIdEtatDemande() == 1) {
				etats = new ArrayList<>();
				etats.add(1L);
				etats.add(2L);
				etats.add(3L);
			} else {
//				Long etatStr = critDto.getIdEtatDemande();
				etats = new ArrayList<>();
				etats.add(critDto.getIdEtatDemande());
			}
		}

		if (critDto.getDateDebut() != null && !"".equals(critDto.getDateDebut()) && critDto.getDateFin() != null
				&& !"".equals(critDto.getDateFin())) {

			String dDebut = critDto.getDateDebut() + " 00:00:00";
			String dFin = critDto.getDateFin() + " 23:59:00";
			SimpleDateFormat frenchFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			Date date1 = frenchFormat.parse(dDebut);
			Date date2 = frenchFormat.parse(dFin);

			liaisons = liaisonDaoImpl.findDemandeGcByParamsDates(date1, date2, critDto.getIdDr(), critDto.getIdDc(),
					critDto.getIdCommune(), critDto.getCodeTypeDemande(),
					critDto.getCodeEtatLiaison(), critDto.getEtatDemandeCode(), critDto.getIdsEtatLiaison(),
					critDto.getIdOperateur(), critDto.getReference(), etats, pageable);

		} else {
			liaisons = liaisonDaoImpl.findDemandeGcByParams(critDto.getIdDr(), critDto.getIdDc(),
					critDto.getIdCommune(), critDto.getCodeTypeDemande(),
					critDto.getCodeEtatLiaison(), critDto.getEtatDemandeCode(), critDto.getIdsEtatLiaison(),
					critDto.getIdOperateur(), critDto.getReference(), etats, pageable);
		}

		if (liaisons != null && !liaisons.isEmpty()) {
			dtos = mapperLiaison.entityListToDtoList(liaisons);
		}

		traceServiceImpl.traceOperation(ActionCode.CONSULTATION, "Récupérer la liste des liaisons de taille: "
				+ dtos.size() + " par Opérateur ayant IDT: " + critDto.getIdOperateur() + ", par date début: "
				+ critDto.getDateDebut() + ", par date fin: " + critDto.getDateFin() + ", par DR ayant IDT: "
				+ critDto.getIdDr() + ", par DC ayant IDT: " + critDto.getIdDc() + ", par Commune ayant IDT: "
				+ critDto.getIdCommune() + ", par Type Demande ayant IDT: " + critDto.getIdTypeDemande()
				+ ", par Etat Demande ayant IDT: " + critDto.getIdEtatDemande() + ", par Etat Liaison ayant IDT: "
				+ critDto.getIdEtatLiaison() + " et par Référence: " + critDto.getReference(),
				"Récupérer la liste des liaisons de taille: " + dtos.size() + " par Opérateur ayant IDT: "
						+ critDto.getIdOperateur() + ", par date début: " + critDto.getDateDebut() + ", par date fin: "
						+ critDto.getDateFin() + ", par DR ayant IDT: " + critDto.getIdDr() + ", par DC ayant IDT: "
						+ critDto.getIdDc() + ", par Commune ayant IDT: " + critDto.getIdCommune()
						+ ", par Type Demande ayant IDT: " + critDto.getIdTypeDemande()
						+ ", par Etat Demande ayant IDT: " + critDto.getIdEtatDemande()
						+ ", par Etat Liaison ayant IDT: " + critDto.getIdEtatLiaison() + " et par Référence: "
						+ critDto.getReference(),
				"CONSULTATION", "Commande", null, null);

		logger.info(Utils.getLogParam() + "Récupérer la liste des liaisons de taille: " + dtos.size()
				+ " par Opérateur ayant IDT: " + critDto.getIdOperateur() + ", par date début: "
				+ critDto.getDateDebut() + ", par date fin: " + critDto.getDateFin() + ", par DR ayant IDT: "
				+ critDto.getIdDr() + ", par DC ayant IDT: " + critDto.getIdDc() + ", par Commune ayant IDT: "
				+ critDto.getIdCommune() + ", par Type Demande ayant IDT: " + critDto.getIdTypeDemande()
				+ ", par Etat Demande ayant IDT: " + critDto.getIdEtatDemande() + ", par Etat Liaison ayant IDT: "
				+ critDto.getIdEtatLiaison() + " et par Référence: " + critDto.getReference());
		return dtos;

	}

	@Override
	public Long getCountDemandeGcByParams(DemandeGcCriteresDto critDto) throws ParseException {
		Long total = 0L;
		String login = SecurityContextHolder.getContext().getAuthentication().getName();
		Utilisateur utilisateur = utilisateurRepository.findByLogin(login);

		if (utilisateur != null && utilisateur.getOperateur() != null) {
			List<Long> etats = null;
			if (critDto.getIdEtatDemande() != null && critDto.getIdEtatDemande() != 0) {
				if (critDto.getIdEtatDemande() == 1) {
					etats = new ArrayList<>();
					etats.add(1L);
					etats.add(2L);
					etats.add(3L);
				} else {
//					Long etatStr = critDto.getIdEtatDemande();
					etats = new ArrayList<>();
					etats.add(critDto.getIdEtatDemande());
				}
			}

			if (critDto.getDateDebut() != null && !"".equals(critDto.getDateDebut()) && critDto.getDateFin() != null
					&& !"".equals(critDto.getDateFin())) {

				String dDebut = critDto.getDateDebut() + " 00:00:00";
				String dFin = critDto.getDateFin() + " 23:59:00";
				SimpleDateFormat frenchFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				Date date1 = frenchFormat.parse(dDebut);
				Date date2 = frenchFormat.parse(dFin);

				total = liaisonDaoImpl.countDemandeGcByParamsDates(date1, date2, critDto.getIdDr(), critDto.getIdDc(),
						critDto.getIdCommune(), critDto.getCodeTypeDemande(), 
						critDto.getCodeEtatLiaison(), critDto.getEtatDemandeCode(), critDto.getIdsEtatLiaison(),
						utilisateur.getOperateur().getIdt(), critDto.getReference(), etats);

			} else {
				total = liaisonDaoImpl.countDemandeGcByParams(critDto.getIdDr(), critDto.getIdDc(),
						critDto.getIdCommune(), critDto.getCodeTypeDemande(),
						critDto.getCodeEtatLiaison(), critDto.getEtatDemandeCode(), critDto.getIdsEtatLiaison(),
						utilisateur.getOperateur().getIdt(), critDto.getReference(), etats);
			}
		}

		return total;
	}

	@Override
	public Long saveDemande(LiaisonDemandeAddDto demandeGcDto) throws ParseException {
		Liaison liaison = mapperLiaison.dtoToEntity(demandeGcDto);
		Demande demande = liaisonAddMapper.dtoToEntityDemande(demandeGcDto);
		Liaison saveLiaison = liaisonDaoImpl.save(liaison);
		demande.setLiaison(saveLiaison);
		demandeRepository.save(demande);
		return 1L;

	}

	@Override
	public Long suppDemande(Long idt) {
		Liaison liaison = null;

		Optional<Liaison> demandeOptional = liaisonDaoImpl.findById(idt);
		if (demandeOptional.isPresent()) {
			liaison = demandeOptional.get();
			liaison.setDemandeAnnuler(true);
			EtatLiaison etatOptional = etatLiaisonRepo.findByCode("CANCEL");
			liaison.setEtatLiaison(etatOptional);

			liaisonDaoImpl.save(liaison);
		}

		return idt;
	}

	@Override
	public Long archiveDemande(Long idt) {
		Liaison liaison ;
		Optional<Liaison> demandeOptional = liaisonDaoImpl.findById(idt);
		if (demandeOptional.isPresent()) {
			liaison = demandeOptional.get();
			liaison.setArchive(true);
			EtatLiaison etatOptional = etatLiaisonRepo.findByCode("ARCHIVE");
			liaison.setEtatLiaison(etatOptional);
			liaisonDaoImpl.save(liaison);
		}

		return idt;
	}

	@Override
	public LiaisonDemandeUppdateDto update(LiaisonDemandeUppdateDto demandeGcDto) throws ParseException {

		LiaisonDemandeUppdateDto demandeGc = null;

		Liaison liaison = liaisonDemandeUpdateMapper.dtoToEntity(demandeGcDto);
		demandeGc = liaisonDemandeUpdateMapper.entityToDto(liaison);

		Demande demande = demandeUpdateMapper.dtoToEntity(demandeGcDto);
		liaison = liaisonDaoImpl.save(liaison);
		demande = demandeRepository.save(demande);
		demandeGc = demandeUpdateMapper.entityToDto(demande);
		traceCommentaireRepo.save(traceCommentaireMapper.demandeToTrace(demande));

		String link = "";
		if (pathInterneProject != null && !pathInterneProject.equals("") && demande.getTypeDemande().getIdt() != null) {
			Optional<TypeDemande> optionalType = typeDemandeRepository.findById(demande.getTypeDemande().getIdt());
			TypeDemande typeDemande = null;
			if (optionalType.isPresent()) {
				typeDemande = optionalType.get();
			}
			if (typeDemande != null) {
				if ("FAISABILITE".equalsIgnoreCase(typeDemande.getCode())) {
					link = pathInterneProject + "/demande-faisabilite/list?idt=" + demande.getIdt();
				} else if ("DESATURATION".equalsIgnoreCase(typeDemande.getCode())) {
					link = pathInterneProject + "/demande-desaturation/list?idt=" + demande.getIdt();
				} else if ("ACCES".equalsIgnoreCase(typeDemande.getCode())) {
					link = pathInterneProject + "/demande-acces/list?idt=" + demande.getIdt();
				} else if ("SIGNALISATION".equalsIgnoreCase(typeDemande.getCode())) {
					link = pathInterneProject + "/demande-signalisation/list?idt=" + demande.getIdt();
				} else if ("PARTAGE".equalsIgnoreCase(typeDemande.getCode())) {
					link = pathInterneProject + "/demande-partage/list?idt=" + demande.getIdt();
				} else if ("SOUS_TUBAGE".equalsIgnoreCase(typeDemande.getCode())) {
					link = pathInterneProject + "/demande-sous-tubage/list?idt=" + demande.getIdt();
				} else if ("DEVIS".equalsIgnoreCase(typeDemande.getCode())) {
					link = pathInterneProject + "/demande-devis/list?idt=" + demande.getIdt();
				} else if ("TRAVAUX_PROG".equalsIgnoreCase(typeDemande.getCode())) {
					link = pathInterneProject + "/demande-travaux-prog/list?idt=" + demande.getIdt();
				}
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

			String login = SecurityContextHolder.getContext().getAuthentication().getName();
			Utilisateur utilisateur = utilisateurRepository.findByLogin(login);
			String entite = "";
			Long idtUser = null;
			if (utilisateur != null) {
				idtUser = utilisateur.getIdt();
				if (utilisateur.getEntite() != null) {
					entite = utilisateur.getEntite().getCode();
				}
			}

			List<String> entities = new ArrayList<>();
			entities.add("DON");
			entities.add("DEMT");
			entities.add("DR");

			for (String entit : entities) {
				Email email = new Email(false, now, link, "update-demande", "EXTERNE", liaison, demande, null, idtUser,
						entit, "ERPT", null);

				emailRepository.save(email);
			}

		}

		return demandeGc;
	}

//	@Override
//	public void updateDemande(DemandeUpdateDto demandeUpdate) throws ParseException {
//		Optional<Demande> demandeOptional= demandeRepository.findById(demandeUpdate.getIdtDemande());
//		if(demandeOptional.isPresent()) {
//			Demande demande = demandeOptional.get();
//			demande.setCommentaire(demandeUpdate.getCommentaire());
//			demande.setDateReception(DateUtils.stringToDate(demandeUpdate.getDateReception()));
//			Demande savedDemande=demandeRepository.save(demande);
//			
//			traceCommentaireRepo.save(traceCommentaireMapper.demandeToTrace(savedDemande));
//			
//			
//			// email
//			
//			String link = "";
//			if (pathInterneProject!= null && !pathInterneProject.equals("") && demandeUpdate.getIdtTypeDemande() != null) {
//				Optional<TypeDemande> optionalType = typeDemandeRepository.findById(demandeUpdate.getIdtTypeDemande());
//				TypeDemande typeDemande = null;
//				if (optionalType.isPresent()) {
//					typeDemande = optionalType.get();
//				}
//				if (typeDemande != null) {
//					if ("FAISABILITE".equalsIgnoreCase(typeDemande.getCode())) {
//						link = pathInterneProject + "/demande-faisabilite/list?idt=" + demande.getIdt();
//					} else if ("DESATURATION".equalsIgnoreCase(typeDemande.getCode())) {
//						link = pathInterneProject + "/demande-desaturation/list?idt=" + demande.getIdt();
//					} else if ("ACCES".equalsIgnoreCase(typeDemande.getCode())) {
//						link = pathInterneProject + "/demande-acces/list?idt=" + demande.getIdt();
//					} else if ("SIGNALISATION".equalsIgnoreCase(typeDemande.getCode())) {
//						link = pathInterneProject + "/demande-signalisation/list?idt=" + demande.getIdt();
//					} else if ("PARTAGE".equalsIgnoreCase(typeDemande.getCode())) {
//						link = pathInterneProject + "/demande-partage/list?idt=" + demande.getIdt();
//					} else if ("SOUS_TUBAGE".equalsIgnoreCase(typeDemande.getCode())) {
//						link = pathInterneProject + "/demande-sous-tubage/list?idt=" + demande.getIdt();
//					} else if ("DEVIS".equalsIgnoreCase(typeDemande.getCode())) {
//						link = pathInterneProject + "/demande-devis/list?idt=" + demande.getIdt();
//					} else if ("TRAVAUX_PROG".equalsIgnoreCase(typeDemande.getCode())) {
//						link = pathInterneProject + "/demande-travaux-prog/list?idt=" + demande.getIdt();
//					}
//				}
//			}
//			Liaison liaison = new Liaison();
//			Optional<Liaison> liaisonOptional= liaisonDaoImpl.findById(demandeUpdate.getIdtLiaison());
//			if(liaisonOptional.isPresent()) {
//				liaison=liaisonOptional.get();
//			}
//
//
//			Parametrage parametrageGMT = parametrageRepository.findByCode("VGMT");
//			Date now = null;
//			if (parametrageGMT != null) {
//				if ("0".equalsIgnoreCase(parametrageGMT.getValeur())) {
//					now = Utils.getCurrentDateGMT();
//				} else {
//					now = Utils.getCurrentDateGMTPlus1();
//				}
//			} else {
//				now = new Date();
//			}
//
//			String login = SecurityContextHolder.getContext().getAuthentication().getName();
//			Utilisateur utilisateur = utilisateurRepository.findByLogin(login);
//			String entite = "";
//			Long idtUser = null;
//			if (utilisateur != null) {
//				idtUser = utilisateur.getIdt();
//				if (utilisateur.getEntite() != null) {
//					entite = utilisateur.getEntite().getCode();
//				}
//			}
//			
//			Email email = new Email(false, now, link, "validation-demande", "INTERNE", liaison, demande, null, idtUser,
//					"ERPT", "ERPT", null);
//			emailRepository.save(email);
//		}
//				
//	}

	@Override
	public Long saveDemandeByType(DemandeOperationCriteresDto demandeDto) throws ParseException {
		Demande demande = demandeMapper.dtoToEntityy(demandeDto);
		Liaison liaison = null;
		List<String> notification = new ArrayList<>();
		Optional<Liaison> liaOptional = liasionRepo.findById(demandeDto.getIdtLiaison());
		if (liaOptional.isPresent()) {
			liaison = liaOptional.get();

			// REALISATION
			if (liaison.getEtatLiaison().getCode().equals("FAISABLE")
					&& demandeDto.getTypeComponent().equals("PARTAGE")) {
				EtatLiaison etatLiaOptional = etatLiaisonRepo.getByCodeEtatLiaison("PARTAGE_PENDING");
				TypeDemande typeDemande = typeDemandeRepo.findByCode("PARTAGE");
				demande.setTypeDemande(typeDemande);
				liaison.setEtatLiaison(etatLiaOptional);
				liasionRepo.save(liaison);
			} else
			// FAISABILITE POINT REGENERATION
			if ((liaison.getEtatLiaison().getCode().equals("FAISABLE")
					|| liaison.getEtatLiaison().getCode().equals("PARTAGE"))
					&& demandeDto.getTypeComponent().equals("FAISABLE_PR")) {
				EtatLiaison etatLiaOptional = etatLiaisonRepo.getByCodeEtatLiaison("PR_PENDING");
				TypeDemande typeDemande = typeDemandeRepo.findByCode("PR");
				demande.setTypeDemande(typeDemande);
				liaison.setEtatLiaison(etatLiaOptional);
				liasionRepo.save(liaison);
				notification.add("DON");
				notification.add("DEMT");
			} else
			// REALISATION POINT REGENERATION
			if ((liaison.getEtatLiaison().getCode().equals("FAISABLE")
					|| liaison.getEtatLiaison().getCode().equals("DEVIS_ACCEPTE"))
					&& demandeDto.getTypeComponent().equals("REALISATION_PR")) {
				EtatLiaison etatLiaOptional = etatLiaisonRepo.getByCodeEtatLiaison("PENDING");
				TypeDemande typeDemande = typeDemandeRepo.findByCode("PR");
				demande.setTypeDemande(typeDemande);
				liaison.setEtatLiaison(etatLiaOptional);
				liasionRepo.save(liaison);
			}

			// DESATURATION
			if (liaison.getEtatLiaison().getCode().equals("P_FAISABLE") && demandeDto.getTypeComponent().equals("DESATURATION")) {
				EtatLiaison etatLiaOptional = etatLiaisonRepo.getByCodeEtatLiaison("DESATURATION_PENDING");
				TypeDemande typeDemande = typeDemandeRepo.findByCode("DESATURATION");
				demande.setTypeDemande(typeDemande);
				liaison.setEtatLiaison(etatLiaOptional);
				liasionRepo.save(liaison);
			}
			// NORMALISATION_OBSTACLE
			if (liaison.getEtatLiaison().getCode().equals("P_FAISABLE") && demandeDto.getTypeComponent().equals("NORMALISATION")) {
				EtatLiaison etatLiaOptional = etatLiaisonRepo.getByCodeEtatLiaison("NORMALISATION_PENDING");
				TypeDemande typeDemande = typeDemandeRepo.findByCode("NORMALISATION_OBSTACLE");
				demande.setTypeDemande(typeDemande);
				liaison.setEtatLiaison(etatLiaOptional);
				liasionRepo.save(liaison);
			}
			// Devis
			if (liaison.getEtatLiaison().getCode().equals("FAISABLE")&& demandeDto.getTypeComponent().equals("DEVIS")) {
				EtatLiaison etatLiaOptional = etatLiaisonRepo.getByCodeEtatLiaison("DEVIS_PENDING");
				TypeDemande typeDemande = typeDemandeRepo.findByCode("DEVIS");
				demande.setTypeDemande(typeDemande);
				liaison.setEtatLiaison(etatLiaOptional);
				liasionRepo.save(liaison);
			}


		//sous tubage
			if (liaison.getEtatLiaison().getCode().equals("PARTAGE_VALIDER")&& demandeDto.getTypeComponent().equals("SOUS-TUBAGE")) {
				EtatLiaison etatLiaOptional = etatLiaisonRepo.getByCodeEtatLiaison("PARTAGE_PENDING");
				TypeDemande typeDemande = typeDemandeRepo.findByCode("VALIDATION_SOUS_TUBAGE");
				demande.setTypeDemande(typeDemande);
				liaison.setEtatLiaison(etatLiaOptional);
				liasionRepo.save(liaison);
			}
			// Instalation
			if (liaison.getEtatLiaison().getCode().equals("P_FAISABLE")&& demandeDto.getTypeComponent().equals("INSTALLATION")) {
				EtatLiaison etatLiaOptional = etatLiaisonRepo.getByCodeEtatLiaison("INSTALLATION_PENDING");
				TypeDemande typeDemande = typeDemandeRepo.findByCode("INSTALLATION");
				demande.setTypeDemande(typeDemande);
				liaison.setEtatLiaison(etatLiaOptional);
				liasionRepo.save(liaison);
			}

		}
		EtatDemande etatDemande = etatDemandeRepo.findByCode("PENDING");
		demande.setEtatDemande(etatDemande);
		Demande savedDemande = demandeRepository.save(demande);
		if (demandeDto.getMultipartFiles() != null) {
			for (MultipartFile file : demandeDto.getMultipartFiles()) {
				Long idPieceJoindre = uploadFile(file);
				PieceJointe jointe = jointeRepository.findById(idPieceJoindre).get();
				jointe.setDemande(savedDemande);
			}

		}

		traceCommentaireRepo.save(traceCommentaireMapper.demandeToTrace(demande));
//		intervenantMapper.dtoToEntity(demandeDto);

		addEmail(demande, notification, liaison);

		return 1L;

	}

	@Override
	public Demande saveOperationDemande(DemandeOperationCriteresDto criteresDto) throws ParseException {
		Demande demande = demandeMapper.dtoOperationToEntity(criteresDto);
		Optional<Liaison> liaOptional = liasionRepo.findById(criteresDto.getIdtLiaison());
		if (liaOptional.isPresent()) {
			Liaison liaison = liaOptional.get();
			if ("P_FAISABLE".equals(liaison.getEtatLiaison().getCode()) 
					&&   "DESATURATION".equals(demande.getTypeDemande().getCode())) {
				EtatLiaison etatLiaOptional = etatLiaisonRepo.getByCodeEtatLiaison("DESATURATION_PENDING");
				liaison.setEtatLiaison(etatLiaOptional);
				liasionRepo.save(liaison);
			}
			demande.setLiaison(liaison);

		}
		return demandeRepository.save(demande);
	}

	@Override
	public Long uploadFile(MultipartFile file) {
		PieceJointe attachment = null;
		String fileName;
		try {
			fileName = uploadFileString(file);
			traceServiceImpl.traceOperation(ActionCode.UPLOAD,
					"Chargement du fichier " + file.getOriginalFilename() + " avec succès",
					"Chargement du fichier " + file.getOriginalFilename() + " avec succès", "CHARGEMENT",
					"INTERVENTION", null, null);
			logger.info(Utils.getLogParam() , "Chargement du fichier " , file.getOriginalFilename() , " avec succès");
		} catch (IOException ex) {
			logger.error("Impossible de créer le dossier où staocker les pièces jointes !.", ex);
			return null;
		}

		attachment = createAttachment(file.getOriginalFilename(), fileName);
		return attachment == null ? null : attachment.getIdt();
	}

	private PieceJointe createAttachment(String originalFilename, String fileName) {
		PieceJointe attachment = new PieceJointe();
		attachment.setOriginFileName(originalFilename);
		attachment.setFileName(fileName);
		return pieceJointeDaoImpl.save(attachment);
	}

	private String uploadFileString(MultipartFile file) throws IOException {

		String fileName = "" + System.currentTimeMillis() + "." + getFileExtension(file.getOriginalFilename());
		InputStream stream = null;
		try {
			File targetFile = new File(fileUploadDir + SEPARATOR + fileName);
			stream = file.getInputStream();
			Files.copy(stream, targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

			return fileName;
		} catch (IOException ex) {
			logger.error(Utils.formatMessage("Impossible de sauvegarder le fichier " + fileName), ex);
			traceServiceImpl.traceOperation(ActionCode.UPLOAD,
					"Erreur de chargement du fichier CSV : " + file.getOriginalFilename(),
					"Erreur de chargement du fichier CSV : " + file.getOriginalFilename(), "CHARGEMENT", "INTERVENTION",
					null, null);
			throw new IOException("Impossible de sauvegarder le fichier " + fileName + ". Merci de réessayer!", ex);
		} finally {
			closeInputStreamSafely(stream);
		}
	}

	private String getFileExtension(String fileName) throws IOException {
		if (fileName == null) {
			throw new IOException("File name is Null");
		}
		int i = fileName.lastIndexOf('.');
		if (i > 0) {
			return fileName.substring(i + 1);
		}
		throw new IOException("Invalid file name");
	}

	private void closeInputStreamSafely(InputStream stream) {
		if (stream != null) {
			try {
				stream.close();
			} catch (IOException e) {
				logger.error("Impossible de copier le fichier. Merci de réessayer!", e);
			}
		}
	}

	@Override
	public Long saveDemandeAcces(DemandeAccesCriteresDto demandeDto) throws ParseException {
		Demande demande = demandeMapper.dtoAccesToEntity(demandeDto);
		demandeRepository.save(demande);

		traceCommentaireRepo.save(traceCommentaireMapper.demandeToTrace(demande));
		intervenantMapper.dtoToEntity(demandeDto);
		return 1L;
	}

//	
//	public void saveCommandeSmMassif(ExcelParserInfo parserInfo,LiaisonDto dto) {
//		Utilisateur utilisateur = utilisateurRepository.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
//		if(utilisateur == null || utilisateur.getOperateur() == null)
//			return ;
//		Demande commande = demandeMapper.toCommandeSmMassifDomain(dto, utilisateur);
//		saveEmailCommandeSM(commande, utilisateur);
//	}

	public void enregistereDemande(DemandeAccesCriteresDto demandeUpdateDto) throws ParseException {
		Demande demande = new Demande();
		Demande savedDemande = null;
		Boolean inDemande = false;
		List<String> notification = new ArrayList<>();
//		demande = demandeMapper.dtoAccesToEntity(demandeUpdateDto);
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
		Optional<Demande> demandeOptional = demandeRepository.findById(demandeUpdateDto.getIdtDemande());
		if (demandeOptional.isPresent()) {
			demande = demandeOptional.get();
			// injection pv
			if (demande.getEtatDemande().getCode().equals("PV_PENDING")
					&& demande.getTypeDemande().getCode().equals("RECEPTION")) {
				EtatDemande etatDemande = etatDemandeRepo.findByCode("INSTALLATION_PENDING");
				demande.setEtatDemande(etatDemande);
				inDemande = true;
				savedDemande = demandeRepository.save(demande);
				notification.add("DEMT");
				notification.add("DR");
			}

		}
		Liaison liaison = null;
		if (!inDemande) {
			Optional<Liaison> LiaOptional = liasionRepo.findById(demandeUpdateDto.getIdtLiaison());
			if (LiaOptional.isPresent()) {
				liaison = LiaOptional.get();
				// Injection trace
				if (liaison.getEtatLiaison().getCode().equals("TRACE_PENDING")) {
					EtatLiaison etatLiaOptional = etatLiaisonRepo.getByCodeEtatLiaison("ACCEPTATION_LIAISON_PENDING");
					liaison.setEtatLiaison(etatLiaOptional);
					liasionRepo.save(liaison);

					notification.add("DEMT");
					notification.add("DON");
					notification.add("ERPT");
				}
				// instalation
				else if (liaison.getEtatLiaison().getCode().equals("INSTALLATION_PENDING")) {

					Optional<Demande> demandeOp = demandeRepository.findById(demandeUpdateDto.getIdtDemande());
					if (demandeOp.isPresent()) {
						demande = demandeOp.get();
						EtatDemande etatDemande = etatDemandeRepo.findByCode("ATTENTE_JARRETIRAGE");
						demande.setEtatDemande(etatDemande);
						savedDemande = demandeRepository.save(demande);
						notification.add("DEMT");
						notification.add("DON");
						notification.add("DR");
					}

				}

				// jerratierage
				else if (liaison.getEtatLiaison().getCode().equals("ATTENTE_JARRETIERAGE")) {
					Demande enregistereDemande = new Demande();

					Optional<Demande> demandeOp = demandeRepository.findById(demandeUpdateDto.getIdtDemande());
					if (demandeOp.isPresent()) {
						EtatDemande etatDemande = etatDemandeRepo.findByCode("JARRETIERAGE_PENDING");
						demande.setEtatDemande(etatDemande);
						savedDemande = demandeRepository.save(demande);

						// save new demande
						TypeDemande typeDemande = typeDemandeRepo.findByCode("JERRETIERAGE");
						enregistereDemande.setTypeDemande(typeDemande);
						enregistereDemande.setDateDemande(now);
						enregistereDemande.setDateReception(now);
						enregistereDemande.setLiaison(demande.getLiaison());
						EtatDemande newDemadeEtat = etatDemandeRepo.findByCode("PENDING");
						enregistereDemande.setEtatDemande(newDemadeEtat);
						demandeRepository.save(enregistereDemande);
						notification.add("DEMT");
						notification.add("DON");
						notification.add("DR");
					}
				}

				// QUALIFICATION_JARRETIERAGE_PENDING
				else if (liaison.getEtatLiaison().getCode().equals("QUALIFICATION_JARRETIERAGE_PENDING")) {

					Optional<Demande> demandeOp = demandeRepository.findById(demandeUpdateDto.getIdtDemande());
					if (demandeOp.isPresent()) {
						demande = demandeOp.get();
						if (demandeUpdateDto.getIsValid()) {
							EtatDemande etatDemande = etatDemandeRepo.findByCode("PARTAGE");
							demande.setEtatDemande(etatDemande);

						} else {
							EtatDemande etatDemande = etatDemandeRepo.findByCode("JARRETIERAGE_PENDING");
							demande.setEtatDemande(etatDemande);
						}

						savedDemande = demandeRepository.save(demande);
						notification.add("DEMT");
						notification.add("DON");
						notification.add("DR");
					}

				}

				else if (demande.getEtatDemande().getCode().equals("FAISABLE")
						&& demande.getTypeDemande().getCode().equals("FAISABILITE_PR")) {
					if (!demandeUpdateDto.getIsDevisExist()) {
						demande.setDateDebut(now);
						demande.setDateReception(now);
						demande.setCommentaire(demandeUpdateDto.getCommentaire());
						EtatDemande etatDemande = etatDemandeRepo.findByCode("PENDING");
						demande.setEtatDemande(etatDemande);
						TypeDemande typeDemande = typeDemandeRepo.findByCode("DEVIS_PR");
						demande.setTypeDemande(typeDemande);

					} else {
						if (demandeUpdateDto.getEtatDevis()) {
							EtatDemande etatDemande = etatDemandeRepo.findByCode("DEVIS_ACCEPTED");
							demande.setEtatDemande(etatDemande);

							EtatLiaison etatLiaison = etatLiaisonRepo.findByCode("DEVIS_ACCEPTED");
							liaison.setEtatLiaison(etatLiaison);
							liaisonDaoImpl.save(liaison);
						} else {
							EtatDemande etatDemande = etatDemandeRepo.findByCode("DEVIS_DENIED");
							demande.setEtatDemande(etatDemande);

							EtatLiaison etatLiaison = etatLiaisonRepo.findByCode("DEVIS_DENIED");
							liaison.setEtatLiaison(etatLiaison);
							liaisonDaoImpl.save(liaison);
						}

					}

					notification.add("DON");
					savedDemande = demandeRepository.save(demande);

				}

				// ACCEES
//				else if ((demande.getTypeDemande().getCode().equals("PARTAGE")
//						|| demande.getTypeDemande().getCode().equals("COMMANDEE"))
//						&& demandeUpdateDto.getTypeComponent().equals("ACCEES")) {
//					Demande newDemande = new Demande();
//					TypeDemande typeDemande = typeDemandeRepo.findByCode("ACCES");
//					newDemande.setTypeDemande(typeDemande);
//					EtatDemande etatDemande = etatDemandeRepo.findByCode("PENDING");
//					newDemande.setEtatDemande(etatDemande);
//					newDemande.setLiaison(liaison);
//					newDemande.setDateReception(now);
//					newDemande.setDateFin(demandeUpdateDto.getDateFin());
//					newDemande.setDateDebut(demandeUpdateDto.getDateDebut());
//					demandeRepository.save(newDemande);
//
//					intervenantMapper.dtoToEntity(demandeUpdateDto);
//				}

				// SIGNALISATION
				else if (liaison.getEtatLiaison().getCode().equals("PARTAGE")
						&& demandeUpdateDto.getTypeComponent().equals("SIGNALISATION")) {
					Demande newDemande = new Demande();
					TypeDemande typeDemande = typeDemandeRepo.findByCode("SIGNALISATION");
					newDemande.setTypeDemande(typeDemande);
					EtatDemande etatDemande = etatDemandeRepo.findByCode("PENDING");
					newDemande.setEtatDemande(etatDemande);
					newDemande.setLiaison(liaison);
					newDemande.getLiaison().setCodeSiteErpt(demandeUpdateDto.getCodeChambreIAM());
					newDemande.getLiaison().setxGpsDepart(demandeUpdateDto.getxGpsChambre());
					newDemande.getLiaison().setxGpsArrivee(demandeUpdateDto.getYGpsChambre());
					newDemande.setDescriptionIncident(demandeUpdateDto.getDescriptionIncident());
					newDemande.setLocalisationIncident(demandeUpdateDto.getLocalisationIncident());
					newDemande.setInterlocuteur(demandeUpdateDto.getInterlocuteur());
					newDemande.setDistanceMesureLiaisonFon(demandeUpdateDto.getDistanceIncident());
					newDemande.setCommentaire(demandeUpdateDto.getCommentaire());
					newDemande.setDateDebut(now);
					newDemande.setDateDemande(now);
					newDemande.setDateReception(now);
					savedDemande = demandeRepository.save(newDemande);

					EscaladeDto escaladeDto = new EscaladeDto();
					Escalade savedEscalad = new Escalade();

					escaladeDto.setDateEscalade(now);
					escaladeDto.setIdtDemande(savedDemande.getIdt());
					Escalade escalade = escaladeMapper.dtoToEntity(escaladeDto);

					savedEscalad = escaladeRepository.save(escalade);
					Long countDemande = escaladeRepository.countDemandeInEscalade(savedEscalad.getDemande().getIdt());
					if (countDemande < 3) {
						escalade.setNumOderEscalade(countDemande + 1);
						Escalade save = escaladeRepository.save(escalade);
						Date date = save.getDateEscalade();
						DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

						save.setNumTicket(
								liaison.getReference() + "-" + df.format(date) + "-" + save.getNumOderEscalade());
						escaladeRepository.save(save);
					}

					notification.add("DEMT");
					notification.add("DR");
					notification.add("DON");

				}

				// DEVIS

				else if (demande.getTypeDemande().getCode().equals("DEVIS")
						&& demandeUpdateDto.getTypeComponent().equals("DEVIS")
						&& (demandeUpdateDto.getIsAddDemande() == null || !demandeUpdateDto.getIsAddDemande() )) {
					if (demandeUpdateDto.getDevisValid()) {
						EtatLiaison etatLiaison = etatLiaisonRepo.findByCode("DEVIS_ACCEPTE");
						demande.getLiaison().setEtatLiaison(etatLiaison);
						EtatDemande etatDemande = etatDemandeRepo.findByCode("DEVIS_ACCEPTED");
						demande.setEtatDemande(etatDemande);
						demande.setCommentaire(demandeUpdateDto.getCommentaire());
						demande.setContactDemandeur(demandeUpdateDto.getContactDemandeur());

					} else {
						EtatLiaison etatLiaison = etatLiaisonRepo.findByCode("DEVIS_REFUSE");
						liaison.setEtatLiaison(etatLiaison);
						EtatDemande etatDemande = etatDemandeRepo.findByCode("DEVIS_DENIED");
						demande.setEtatDemande(etatDemande);
						demande.setCommentaire(demandeUpdateDto.getCommentaire());
						demande.setContactDemandeur(demandeUpdateDto.getContactDemandeur());

					}
					savedDemande = demandeRepository.save(demande);

				} else if (demande.getTypeDemande().getCode().equals("DEVIS")
						&& demandeUpdateDto.getTypeComponent().equals("DEVIS") && demandeUpdateDto.getIsAddDemande()) {

					Demande newDemande = new Demande();
					newDemande.setLiaison(liaison);
					TypeDemande typeDemande = typeDemandeRepo.findByCode("DEVIS");
					newDemande.setTypeDemande(typeDemande);
					EtatDemande etatDemande = etatDemandeRepo.findByCode("PENDING");
					EtatLiaison etatLiaison = etatLiaisonRepo.findByCode("DEVIS_PENDING");
					newDemande.getLiaison().setEtatLiaison(etatLiaison);
					newDemande.setDateDebut(now);
					newDemande.setDateDemande(now);
					newDemande.setDateReception(now);
					newDemande.setCommentaire(demandeUpdateDto.getCommentaire());
					newDemande.setContactDemandeur(demandeUpdateDto.getContactDemandeur());

					savedDemande = demandeRepository.save(newDemande);
					notification.add("DON");

				}

			}
		}
//		 savedDemande = demandeRepository.save(demande);
		if (demandeUpdateDto.getMultipartFiles() != null)

		{
			for (MultipartFile file : demandeUpdateDto.getMultipartFiles()) {
				Long idPieceJoindre = uploadFile(file);
				PieceJointe jointe = jointeRepository.findById(idPieceJoindre).get();
				jointe.setDemande(savedDemande);
			}

		}

		traceCommentaireRepo.save(traceCommentaireMapper.demandeToTrace(savedDemande));

		addEmail(demande, notification, liaison);
	}


	@Override
	public void addDemandeDevis(AddDemandeDevisDto demandeUpdateDto) throws ParseException {
		Liaison liaison=null;
		List<String> notification = new ArrayList<>();
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
		Optional<Liaison> LiaOptional = liasionRepo.findById(demandeUpdateDto.getIdtLiaison());
		if (LiaOptional.isPresent()) {
			liaison = LiaOptional.get();
		}

		Demande newDemande = new Demande();

		newDemande.setLiaison(liaison);
		TypeDemande typeDemande = typeDemandeRepo.findByCode("DEVIS");
		newDemande.setTypeDemande(typeDemande);
		EtatDemande etatDemande = etatDemandeRepo.findByCode("PENDING");
		newDemande.setEtatDemande(etatDemande);
		EtatLiaison etatLiaison = etatLiaisonRepo.findByCode("DEVIS_PENDING");
		newDemande.getLiaison().setEtatLiaison(etatLiaison);
		newDemande.setDateDebut(now);
		newDemande.setDateDemande(now);
		newDemande.setDateReception(now);
		newDemande.setCommentaire(demandeUpdateDto.getCommentaire());
		newDemande.setContactDemandeur(demandeUpdateDto.getContact());

		String login = SecurityContextHolder.getContext().getAuthentication().getName();
		Utilisateur utilisateur = utilisateurRepository.findByLogin(login);
		if (utilisateur != null) {
			newDemande.setIdtDemandeur(utilisateur.getIdt());

			newDemande.setNomDemandeur(utilisateur.getNom());
			newDemande.setPrenomDemandeur(utilisateur.getPrenom());
			newDemande.setLoginDemandeur(utilisateur.getLogin());
		}
		
		Demande savedDemande = demandeRepository.save(newDemande);
		notification.add("DON");
		traceCommentaireRepo.save(traceCommentaireMapper.demandeToTrace(savedDemande));

		addEmail(savedDemande, notification, liaison);

	}
	@Override
	public List<LiaisonDto> getListLiaison() {
		String login = SecurityContextHolder.getContext().getAuthentication().getName();
		Utilisateur utilisateur = utilisateurRepository.findByLogin(login);
		String entite = "";
		Long idtUser = null;
		if (utilisateur != null) {
			idtUser = utilisateur.getIdt();
			if (utilisateur.getEntite() != null) {
				entite = utilisateur.getOperateur().getLabel();
			}
		}

		EtatLiaison etatLiaison = etatLiaisonRepo.findByCode("PARTAGE");
		List<LiaisonDto> liaisonDtos = mapperLiaison
				.entityListToDtoList(liaisonDaoImpl.findByEtatLiaisonAndOperateur(etatLiaison, entite));
		return liaisonDtos;
	}

	@Override
	public List<LiaisonDto> getListLiaisonFaisable() {
		String login = SecurityContextHolder.getContext().getAuthentication().getName();
		Utilisateur utilisateur = utilisateurRepository.findByLogin(login);
		String entite = "";
		Long idtUser = null;
		if (utilisateur != null) {
			idtUser = utilisateur.getIdt();
			if (utilisateur.getEntite() != null) {
				entite = utilisateur.getOperateur().getLabel();
			}
		}

		EtatLiaison etatLiaison = etatLiaisonRepo.findByCode("FAISABLE");
		List<LiaisonDto> liaisonDtos = mapperLiaison
				.entityListToDtoList(liaisonDaoImpl.findByEtatLiaisonAndOperateur(etatLiaison, entite));
		return liaisonDtos;
	}
	@Override
	public void saveDemandeWithIntervenant(DemandeAccesCriteresDto demandeDto) throws ParseException {
		Liaison liaison = null;
		Demande demande = new Demande();
		Demande savedDemande = null;
		List<String> notification = new ArrayList<>();
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
		demande = demandeMapper.dtoAccesToEntity(demandeDto);
		Optional<Liaison> LiaOptional = liasionRepo.findById(demandeDto.getIdtLiaison());
		if (LiaOptional.isPresent()) {

			liaison = LiaOptional.get();
			if (liaison.getEtatLiaison().getCode().equals("REALISE")
					&& demandeDto.getTypeComponent().equals("RECEPTION")) {
				Optional<Demande> demandeOp = demandeRepository.findById(demandeDto.getIdtDemande());
				if (demandeOp.isPresent()) {

					EtatDemande etatDemande = etatDemandeRepo.findByCode("PLANNING_PENDING");
					demande.setEtatDemande(etatDemande);

					TypeDemande typeDemande = typeDemandeRepo.findByCode("RECEPTION_FON");
					demande.setTypeDemande(typeDemande);

					EtatLiaison etatLiaison = etatLiaisonRepo.getByCodeEtatLiaison("RECEPTION_PRNDING");
					liaison.setEtatLiaison(etatLiaison);
					liasionRepo.save(liaison);

					savedDemande = demandeRepository.save(demande);
					traceCommentaireRepo.save(traceCommentaireMapper.demandeToTrace(demande));
					intervenantMapper.dtoToEntity(demandeDto);
					notification.add("DEMT");
					notification.add("DON");
					notification.add("DR");
				}

			}
			// ACCES
			else if ((liaison.getEtatLiaison().getCode().equals("PARTAGE")
					|| liaison.getEtatLiaison().getCode().equals("COMMANDEE"))
					&& demandeDto.getTypeComponent().equals("ACCEES")) {
				savedDemande = new Demande();
				TypeDemande typeDemande = typeDemandeRepo.findByCode("ACCES");
				demande.setTypeDemande(typeDemande);
				EtatDemande etatDemande = etatDemandeRepo.findByCode("PENDING");
				demande.setEtatDemande(etatDemande);
				demande.setLiaison(liaison);
				demande.setDateReception(now);
				demande.setDateFin(demandeDto.getDateFin());
				demande.setDateDebut(demandeDto.getDateDebut());
				demande.setCommentaire(demandeDto.getCommentaire());
				demande.setDescriptionAcces(demandeDto.getDescriptionAcces());
				demande.setTypeIntervention(demandeDto.getTypeIntervention());
				savedDemande = demandeRepository.save(demande);

				intervenantMapper.dtoToEntity(demandeDto);

				notification.add("DEMT");
				notification.add("DON");
				notification.add("DR");
			}

			else
				traceCommentaireRepo.save(traceCommentaireMapper.demandeToTrace(savedDemande));

			addEmail(savedDemande, notification, liaison);

		}

	}

	@Override
	public void addDemandeSignalisation(DemandeAccesCriteresDto demandeDto) throws ParseException {
		Demande newDemande = new Demande();
		Demande savedDemande = new Demande();
		List<String> notification = new ArrayList<>();
	
//		demande = demandeMapper.dtoAccesToEntity(demandeDto);
		Liaison liaison = liasionRepo.findByReference(demandeDto.getReference());
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
		TypeDemande typeDemande = typeDemandeRepo.findByCode("SIGNALISATION");
		newDemande.setTypeDemande(typeDemande);
		EtatDemande etatDemande = etatDemandeRepo.findByCode("PENDING");
		newDemande.setEtatDemande(etatDemande);

		newDemande.setLiaison(liaison);
		newDemande.getLiaison().setCodeSiteErpt(demandeDto.getCodeChambreIAM());
		newDemande.getLiaison().setxGpsDepart(demandeDto.getxGpsChambre());
		newDemande.getLiaison().setxGpsArrivee(demandeDto.getYGpsChambre());
		newDemande.setDescriptionIncident(demandeDto.getDescriptionIncident());
		newDemande.setLocalisationIncident(demandeDto.getLocalisationIncident());
		newDemande.setInterlocuteur(demandeDto.getInterlocuteur());
		newDemande.setDistanceMesureLiaisonFon(demandeDto.getDistanceIncident());
		newDemande.setCommentaire(demandeDto.getCommentaire());
		newDemande.setDateDebut(now);
		newDemande.setDateDemande(now);
		newDemande.setDateReception(now);
		String login = SecurityContextHolder.getContext().getAuthentication().getName();
		Utilisateur utilisateur = utilisateurRepository.findByLogin(login);
		if (utilisateur != null) {
			newDemande.setIdtDemandeur(utilisateur.getIdt());

			newDemande.setNomDemandeur(utilisateur.getNom());
			newDemande.setPrenomDemandeur(utilisateur.getPrenom());
			newDemande.setLoginDemandeur(utilisateur.getLogin());
		}
		savedDemande = demandeRepository.save(newDemande);

//		EscaladeDto escaladeDto = new EscaladeDto();
//		Escalade savedEscalad = new Escalade();
//
//		escaladeDto.setDateEscalade(now);
//		escaladeDto.setIdtDemande(savedDemande.getIdt());
//		Escalade escalade = escaladeMapper.dtoToEntity(escaladeDto);
//
//		savedEscalad = escaladeRepository.save(escalade);
//		Long countDemande = escaladeRepository.countDemandeInEscalade(savedEscalad.getDemande().getIdt());
//		if (countDemande < 3) {
//			escalade.setNumOderEscalade(countDemande + 1);
//			Escalade save = escaladeRepository.save(escalade);
//			Date date = save.getDateEscalade();
//			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//
//			save.setNumTicket(liaison.getReference() + "-" + df.format(date) + "-" + save.getNumOderEscalade());
//			escaladeRepository.save(save);
//		}

		notification.add("DEMT");
		notification.add("DR");
		notification.add("DON");

		addEmail(newDemande,notification,liaison);

	}

	public void addEmail(Demande demande, List<String> notification, Liaison liaison) throws ParseException {
		String link = "";
		if (pathInterneProject != null && !pathInterneProject.equals("") && demande.getTypeDemande().getIdt() != null) {
			Optional<TypeDemande> optionalType = typeDemandeRepository.findById(demande.getTypeDemande().getIdt());
			TypeDemande typeDemande = null;
			if (optionalType.isPresent()) {
				typeDemande = optionalType.get();
			}
			if (typeDemande != null) {
				if ("FAISABILITE".equalsIgnoreCase(typeDemande.getCode())) {
					link = pathInterneProject + "/demande-faisabilite/list?idt=" + demande.getIdt();
				} else if ("DESATURATION".equalsIgnoreCase(typeDemande.getCode())) {
					link = pathInterneProject + "/demande-desaturation/list?idt=" + demande.getIdt();
				} else if ("ACCES".equalsIgnoreCase(typeDemande.getCode())) {
					link = pathInterneProject + "/demande-acces/list?idt=" + demande.getIdt();
				} else if ("SIGNALISATION".equalsIgnoreCase(typeDemande.getCode())) {
					link = pathInterneProject + "/demande-signalisation/list?idt=" + demande.getIdt();
				} else if ("PARTAGE".equalsIgnoreCase(typeDemande.getCode())) {
					link = pathInterneProject + "/demande-partage/list?idt=" + demande.getIdt();
				} else if ("SOUS_TUBAGE".equalsIgnoreCase(typeDemande.getCode())) {
					link = pathInterneProject + "/demande-sous-tubage/list?idt=" + demande.getIdt();
				} else if ("DEVIS".equalsIgnoreCase(typeDemande.getCode())) {
					link = pathInterneProject + "/demande-devis/list?idt=" + demande.getIdt();
				} else if ("TRAVAUX_PROG".equalsIgnoreCase(typeDemande.getCode())) {
					link = pathInterneProject + "/demande-travaux-prog/list?idt=" + demande.getIdt();
				}
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

			String login = SecurityContextHolder.getContext().getAuthentication().getName();
			Utilisateur utilisateur = utilisateurRepository.findByLogin(login);
			String entite = "";
			Long idtUser = null;
			if (utilisateur != null) {
				idtUser = utilisateur.getIdt();
				if (utilisateur.getEntite() != null) {
					entite = utilisateur.getEntite().getCode();
				}
			}

			for (String notif : notification) {
				Email email = new Email(false, now, link, "update-demande", "EXTERNE", liaison, demande, null, idtUser,
						notif, "ERPT", null);

				emailRepository.save(email);
			}

		}

	}
	
	

}
