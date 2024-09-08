package ma.iam.dppi.fon.interne.services.implementations;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManagerFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import ma.iam.dppi.fon.communs.domain.Dr;
import ma.iam.dppi.fon.communs.domain.Parametrage;
import ma.iam.dppi.fon.communs.domain.Utilisateur;
import ma.iam.dppi.fon.communs.repository.DrRepository;
import ma.iam.dppi.fon.communs.repository.ParametrageRepository;
import ma.iam.dppi.fon.communs.repository.UtilisateurRepository;
import ma.iam.dppi.fon.constants.ActionCode;
import ma.iam.dppi.fon.domain.Demande;
import ma.iam.dppi.fon.domain.DemandeTravauxProgramme;
import ma.iam.dppi.fon.domain.Devis;
import ma.iam.dppi.fon.domain.Email;
import ma.iam.dppi.fon.domain.EtatDemande;
import ma.iam.dppi.fon.domain.EtatDemandeSousLiaison;
import ma.iam.dppi.fon.domain.EtatLiaison;
import ma.iam.dppi.fon.domain.Interaction;
import ma.iam.dppi.fon.domain.Liaison;
import ma.iam.dppi.fon.domain.OperationSousLiaison;
import ma.iam.dppi.fon.domain.PieceJointe;
import ma.iam.dppi.fon.domain.RaisonInfaisabilite;
import ma.iam.dppi.fon.domain.Site;
import ma.iam.dppi.fon.domain.SousLiaison;
import ma.iam.dppi.fon.domain.TraceCommentaire;
import ma.iam.dppi.fon.domain.TraceCommentaireSousLiaison;
import ma.iam.dppi.fon.domain.TraceEtatDemande;
import ma.iam.dppi.fon.domain.TraceEtatDemandeSousLiaison;
import ma.iam.dppi.fon.domain.TraceEtatLiaison;
import ma.iam.dppi.fon.domain.TypeDemande;
import ma.iam.dppi.fon.interne.dtos.ConsolidationDto;
import ma.iam.dppi.fon.interne.dtos.CriteriaCommandeDto;
import ma.iam.dppi.fon.interne.dtos.DemandeDto;
import ma.iam.dppi.fon.interne.dtos.DemandeListDto;
import ma.iam.dppi.fon.interne.dtos.InteractionDto;
import ma.iam.dppi.fon.interne.dtos.SousLiaisonDto;
import ma.iam.dppi.fon.interne.dtos.TraceCommentaireDto;
import ma.iam.dppi.fon.interne.dtos.ValidateDemandeDto;
import ma.iam.dppi.fon.interne.mappers.MapperDemande;
import ma.iam.dppi.fon.interne.mappers.MapperInteraction;
import ma.iam.dppi.fon.interne.mappers.MapperOperationSousLiaison;
import ma.iam.dppi.fon.interne.mappers.MapperSousLiaison;
import ma.iam.dppi.fon.interne.mappers.MapperTraceCommentaire;
import ma.iam.dppi.fon.interne.security.SecurityContextHelper;
import ma.iam.dppi.fon.interne.services.IDemandeService;
import ma.iam.dppi.fon.interne.services.ITraceService;
import ma.iam.dppi.fon.interne.utils.DateUtils;
import ma.iam.dppi.fon.interne.utils.Utils;
import ma.iam.dppi.fon.repository.DemandeRepository;
import ma.iam.dppi.fon.repository.DemandeTravauxProgrammeRepository;
import ma.iam.dppi.fon.repository.DevisRepository;
import ma.iam.dppi.fon.repository.EmailRepository;
import ma.iam.dppi.fon.repository.EtatDemandeRepository;
import ma.iam.dppi.fon.repository.EtatDemandeSousLiaisonRepository;
import ma.iam.dppi.fon.repository.EtatLiaisonRepository;
import ma.iam.dppi.fon.repository.InteractionRepository;
import ma.iam.dppi.fon.repository.LiaisonRepository;
import ma.iam.dppi.fon.repository.OperationSousLiaisonRepository;
import ma.iam.dppi.fon.repository.PieceJointeRepository;
import ma.iam.dppi.fon.repository.RaisonInfaisabiliteRepository;
import ma.iam.dppi.fon.repository.SiteRepository;
import ma.iam.dppi.fon.repository.SousLiaisonRepository;
import ma.iam.dppi.fon.repository.TraceCommentaireRepository;
import ma.iam.dppi.fon.repository.TraceCommentaireSousLiaisonRepository;
import ma.iam.dppi.fon.repository.TraceEtatDemandeRepository;
import ma.iam.dppi.fon.repository.TraceEtatDemandeSousLiaisonRepository;
import ma.iam.dppi.fon.repository.TraceEtatLiaisonRepository;
import ma.iam.dppi.fon.repository.TypeDemandeRepository;

/**
 * 
 * @author Z.BELGHAOUTI
 *
 */
@Service
@Transactional
public class DemandeServiceImpl implements IDemandeService {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	@Qualifier("dppiFonEntityManager") // Use the correct qualifier
	private EntityManagerFactory entityManagerFactory;

	@Autowired
	private PieceJointeRepository jointeRepository;

	@Autowired
	private ITraceService traceServiceImpl;

	@Autowired
	private EtatDemandeRepository etatDemandeRepository;

	@Autowired
	private DemandeRepository demandeRepository;

	@Autowired
	private DevisRepository devisRepository;

	@Autowired
	private LiaisonRepository liaisonRepository;

	@Autowired
	private ParametrageRepository parametrageRepository;

	@Autowired
	private EtatLiaisonRepository etatLiaisonRepository;

	@Autowired
	private UtilisateurRepository utilisateurRepository;

	@Autowired
	private TraceCommentaireRepository traceCommentaireRepository;

	@Autowired
	private TraceEtatDemandeRepository traceEtatDemandeRepository;

	@Autowired
	private TraceEtatLiaisonRepository traceEtatLiaisonRepository;

	@Autowired
	private EmailRepository emailRepository;

	@Autowired
	private TypeDemandeRepository typeDemandeRepository;

	@Autowired
	private InteractionRepository interactionRepository;

	@Autowired
	private SousLiaisonRepository sousLiaisonRepository;

	@Autowired
	private OperationSousLiaisonRepository operationSousLiaisonRepository;

	@Autowired
	private TraceEtatDemandeSousLiaisonRepository traceEtatDemandeSousLiaisonRepository;

	@Autowired
	private TraceCommentaireSousLiaisonRepository traceCommentaireSousLiaisonRepository;

	@Autowired
	private EtatDemandeSousLiaisonRepository etatDemandeSousLiaisonRepository;

	@Autowired
	private MapperDemande mapperDemande;

	@Autowired
	private MapperInteraction mapperInteraction;

	@Autowired
	private MapperSousLiaison mapperSousLiaison;

	@Autowired
	private MapperOperationSousLiaison mapperOperationSousLiaison;

	@Autowired
	private MapperTraceCommentaire mapperTraceCommentaire;

	@Autowired
	private RaisonInfaisabiliteRepository raisonInfaisabiliteRepository;

	@Autowired
	private SiteRepository siteRepository;

	@Autowired
	private SousLiaisonRepository souLiaisonRepository;

	@Autowired
	private DrRepository drRepository;
	
	@Autowired
	private DemandeTravauxProgrammeRepository demandeTravauxProgrammeRepository;

	@Value("${application.interne.url}")
	private String pathInterneProject;

	private static final String SEPARATOR = File.separator;

	@Value("${file.upload.dir}")
	private String fileUploadDir;
	
	@Value("${application.externe.url}")
	private String pathExterneProject;

	/**
	 * GET ALL LIAISONS BY CRITERES
	 * 
	 * @throws ParseException
	 */
	public List<DemandeListDto> getAllDemandesByCriteres(CriteriaCommandeDto critDto) throws ParseException {

		List<DemandeListDto> dtos = new ArrayList<>();

		Sort sort = Sort.by(Sort.Direction.DESC, "idt");
		Pageable pageable = PageRequest.of(critDto.getStart(), critDto.getEnd(), sort);
		List<Demande> demandes = null;

		List<Long> idsEtatDemande = null;

		if (critDto.getDateDebut() != null && !"".equals(critDto.getDateDebut()) && critDto.getDateFin() != null
				&& !"".equals(critDto.getDateFin())) {

			String dDebut = critDto.getDateDebut() + " 00:00:00";
			String dFin = critDto.getDateFin() + " 23:59:00";
			SimpleDateFormat frenchFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			Date date1 = frenchFormat.parse(dDebut);
			Date date2 = frenchFormat.parse(dFin);

			demandes = demandeRepository.findDemandesByParamsDates(date1, date2, critDto.getIdDr(), critDto.getIdDc(),
					critDto.getIdCommune(), critDto.getCodeTypeDemande(), critDto.getIdEtatLiaison(),
					critDto.getIdEtatDemande(), critDto.getIdOperateur(), critDto.getReference(), idsEtatDemande,
					pageable);

		} else {
			demandes = demandeRepository.findDemandesByParams(critDto.getIdDr(), critDto.getIdDc(),
					critDto.getIdCommune(), critDto.getIdTypeDemande(), critDto.getIdEtatLiaison(),
					critDto.getIdEtatDemande(), critDto.getIdOperateur(), critDto.getReference(), idsEtatDemande,
					pageable);
		}

//		downloadPdf(demandes)

		if (demandes != null && !demandes.isEmpty()) {
			dtos = mapperDemande.toDtosDemandes(demandes);
		}

		traceServiceImpl.traceOperation(ActionCode.CONSULTATION, "Récupérer la liste des demandes de taille: "
				+ dtos.size() + " par Opérateur ayant IDT: " + critDto.getIdOperateur() + ", par date début: "
				+ critDto.getDateDebut() + ", par date fin: " + critDto.getDateFin() + ", par DR ayant IDT: "
				+ critDto.getIdDr() + ", par DC ayant IDT: " + critDto.getIdDc() + ", par Commune ayant IDT: "
				+ critDto.getIdCommune() + ", par Type Demande ayant IDT: " + critDto.getIdTypeDemande()
				+ ", par Etat Demande ayant IDT: " + critDto.getIdEtatDemande() + ", par Etat Liaison ayant IDT: "
				+ critDto.getIdEtatLiaison() + " et par Référence: " + critDto.getReference(),
				"Récupérer la liste des demandes de taille: " + dtos.size() + " par Opérateur ayant IDT: "
						+ critDto.getIdOperateur() + ", par date début: " + critDto.getDateDebut() + ", par date fin: "
						+ critDto.getDateFin() + ", par DR ayant IDT: " + critDto.getIdDr() + ", par DC ayant IDT: "
						+ critDto.getIdDc() + ", par Commune ayant IDT: " + critDto.getIdCommune()
						+ ", par Type Demande ayant IDT: " + critDto.getIdTypeDemande()
						+ ", par Etat Demande ayant IDT: " + critDto.getIdEtatDemande()
						+ ", par Etat Liaison ayant IDT: " + critDto.getIdEtatLiaison() + " et par Référence: "
						+ critDto.getReference(),
				"CONSULTATION", "Commande", null, null);

		logger.info(Utils.getLogParam() + "Récupérer la liste des demandes de taille: " + dtos.size()
				+ " par Opérateur ayant IDT: " + critDto.getIdOperateur() + ", par date début: "
				+ critDto.getDateDebut() + ", par date fin: " + critDto.getDateFin() + ", par DR ayant IDT: "
				+ critDto.getIdDr() + ", par DC ayant IDT: " + critDto.getIdDc() + ", par Commune ayant IDT: "
				+ critDto.getIdCommune() + ", par Type Demande ayant IDT: " + critDto.getIdTypeDemande()
				+ ", par Etat Demande ayant IDT: " + critDto.getIdEtatDemande() + ", par Etat Liaison ayant IDT: "
				+ critDto.getIdEtatLiaison() + " et par Référence: " + critDto.getReference());
		return dtos;
	}

	public File downloadFile(String fileName) throws IOException {
		File file = new File(this.fileUploadDir + fileName);
		if (!file.exists()) {
			throw new IOException();
		}

		return file;
	}

	@Override
	public void getJointByDemande(Long idtDemande, MultipartFile multipartFile) {
		Optional<Demande> demandeOptional = demandeRepository.findById(idtDemande);
		if (demandeOptional.isPresent()) {
			Demande demande = demandeOptional.get();

			if (multipartFile != null) {
				Long idPieceJoindre = uploadFile(multipartFile);
				PieceJointe jointe = jointeRepository.findById(idPieceJoindre).get();
				jointe.setDemande(demande);

			}
		}
//		return pieceJointe;

	}

	public Long uploadFile(MultipartFile file) {
		PieceJointe attachment = null;
		String fileName;
		try {
			fileName = uploadFileString(file);
			traceServiceImpl.traceOperation(ActionCode.UPLOAD,
					"Chargement du fichier " + file.getOriginalFilename() + " avec succès",
					"Chargement du fichier " + file.getOriginalFilename() + " avec succès", "CHARGEMENT",
					"INTERVENTION", null, null);
			logger.info(Utils.getLogParam() + "Chargement du fichier " + file.getOriginalFilename() + " avec succès");
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
		return jointeRepository.save(attachment);
	}

	/**
	 * Save a compte.
	 *
	 * @param file the entity to save.
	 * @return the persisted entity.
	 */
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

	private void closeInputStreamSafely(InputStream stream) {
		if (stream != null) {
			try {
				stream.close();
			} catch (IOException e) {
				logger.error("Impossible de copier le fichier. Merci de réessayer!", e);
			}
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

	/**
	 * COUNT LIAISONS BY CRITERES
	 * 
	 * @throws ParseException
	 */
	public Long getTotalDemandesByCriteres(CriteriaCommandeDto critDto) throws ParseException {

		Long total = null;

		List<Long> idsEtatDemande = null;

		if (critDto.getDateDebut() != null && !"".equals(critDto.getDateDebut()) && critDto.getDateFin() != null
				&& !"".equals(critDto.getDateFin())) {

			String dDebut = critDto.getDateDebut() + " 00:00:00";
			String dFin = critDto.getDateFin() + " 23:59:00";
			SimpleDateFormat frenchFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			Date date1 = frenchFormat.parse(dDebut);
			Date date2 = frenchFormat.parse(dFin);

			total = demandeRepository.countDemandesByParamsDates(date1, date2, critDto.getIdDr(), critDto.getIdDc(),
					critDto.getIdCommune(), critDto.getIdTypeDemande(), critDto.getIdEtatLiaison(),
					critDto.getIdEtatDemande(), critDto.getIdOperateur(), critDto.getReference(), idsEtatDemande);

		} else {
			total = demandeRepository.countDemandesByParams(critDto.getIdDr(), critDto.getIdDc(),
					critDto.getIdCommune(), critDto.getIdTypeDemande(), critDto.getIdEtatLiaison(),
					critDto.getIdEtatDemande(), critDto.getIdOperateur(), critDto.getReference(), idsEtatDemande);
		}

		traceServiceImpl.traceOperation(ActionCode.CONSULTATION, "Récupération le total des Demandes : " + total
				+ " par Opérateur ayant IDT: " + critDto.getIdOperateur() + ", par date début: "
				+ critDto.getDateDebut() + ", par date fin: " + critDto.getDateFin() + ", par DR ayant IDT: "
				+ critDto.getIdDr() + ", par DC ayant IDT: " + critDto.getIdDc() + ", par Commune ayant IDT: "
				+ critDto.getIdCommune() + ", par Type Demande ayant IDT: " + critDto.getIdTypeDemande()
				+ ", par Etat Demande ayant IDT: " + critDto.getIdEtatDemande() + ", par Etat Liaison ayant IDT: "
				+ critDto.getIdEtatLiaison() + " et par Référence: " + critDto.getReference(),
				"Récupération le total des Demandes : " + total + " par Opérateur ayant IDT: "
						+ critDto.getIdOperateur() + ", par date début: " + critDto.getDateDebut() + ", par date fin: "
						+ critDto.getDateFin() + ", par DR ayant IDT: " + critDto.getIdDr() + ", par DC ayant IDT: "
						+ critDto.getIdDc() + ", par Commune ayant IDT: " + critDto.getIdCommune()
						+ ", par Type Demande ayant IDT: " + critDto.getIdTypeDemande()
						+ ", par Etat Demande ayant IDT: " + critDto.getIdEtatDemande()
						+ ", par Etat Liaison ayant IDT: " + critDto.getIdEtatLiaison() + " et par Référence: "
						+ critDto.getReference(),
				"CONSULTATION", "Commande", null, null);

		logger.info(Utils.getLogParam() + "Récupération le total des Demandes : " + total + " par Opérateur ayant IDT: "
				+ critDto.getIdOperateur() + ", par date début: " + critDto.getDateDebut() + ", par date fin: "
				+ critDto.getDateFin() + ", par DR ayant IDT: " + critDto.getIdDr() + ", par DC ayant IDT: "
				+ critDto.getIdDc() + ", par Commune ayant IDT: " + critDto.getIdCommune()
				+ ", par Type Demande ayant IDT: " + critDto.getIdTypeDemande() + ", par Etat Demande ayant IDT: "
				+ critDto.getIdEtatDemande() + ", par Etat Liaison ayant IDT: " + critDto.getIdEtatLiaison()
				+ " et par Référence: " + critDto.getReference());
		return total;
	}

	public void ajouterSousLiaison(SousLiaisonDto sousLiaisonDto) {
		SousLiaison sousLiaison = new SousLiaison();
		Optional<Site> siteAOptional = siteRepository.findById(sousLiaisonDto.getSite1Idt());
		if (siteAOptional.isPresent()) {
			Site siteA = siteAOptional.get();
			sousLiaison.setSiteA(siteA);
		}
		Optional<Site> siteBOptional = siteRepository.findById(sousLiaisonDto.getSite2Idt());
		if (siteBOptional.isPresent()) {
			Site siteB = siteBOptional.get();
			sousLiaison.setSiteB(siteB);
		}

		Optional<Dr> drOptional = drRepository.findById(sousLiaisonDto.getDrIdt());
		if (drOptional.isPresent()) {
			Dr dr = drOptional.get();
			sousLiaison.setIdtDr(dr.getIdt());
		}

		if (sousLiaisonDto.getLiaisonIdt() != null) {
			Optional<Liaison> liaisonOptional = liaisonRepository.findById(sousLiaisonDto.getLiaisonIdt());
			if (liaisonOptional.isPresent()) {
				Liaison liaison = liaisonOptional.get();
				sousLiaison.setLiaison(liaison);
			}
		}

		sousLiaison.setDateAffectation(new Date());
		if (sousLiaison.getDistance() != null) {
			sousLiaison.setDistance("0");
		}

		souLiaisonRepository.save(sousLiaison);

	}

//	private OperationSousLiaison saveOperationSousLiaison(Oper)

	private SousLiaison addSousLiaison(SousLiaisonDto sousLiaisonDto, Date now, Utilisateur utilisateur,
			String entite) {
		SousLiaison sousLiaison = mapperSousLiaison.toDomain(sousLiaisonDto);
		if (sousLiaison != null) {
			sousLiaison = sousLiaisonRepository.save(sousLiaison);
			OperationSousLiaison operation = mapperOperationSousLiaison.toSaveDomain(sousLiaisonDto, sousLiaison);
			if (operation != null) {
				operation = operationSousLiaisonRepository.save(operation);
				/****************** AJOUT TRACE ETAT DEMANDE TRONCON *********************/
				TraceEtatDemandeSousLiaison traceEtatDemandeSousLiaison = new TraceEtatDemandeSousLiaison();
				traceEtatDemandeSousLiaison.setSousLiaison(sousLiaison);
				traceEtatDemandeSousLiaison.setDateEtat(now);
				traceEtatDemandeSousLiaison.setEtatDemandeSousLiaison(operation.getEtatDemandeSousLiaison());
				traceEtatDemandeSousLiaisonRepository.save(traceEtatDemandeSousLiaison);

				/****************** AJOUT TRACE COMMENTAIRE TRONCON *********************/
				TraceCommentaireSousLiaison traceCommentaire = new TraceCommentaireSousLiaison();
				traceCommentaire.setCommentaire(sousLiaisonDto.getCommentaire());
				traceCommentaire.setDateCommentaire(now);
				traceCommentaire.setSousLiaison(sousLiaison);
				if (utilisateur != null) {
					traceCommentaire.setDemandeurLogin(utilisateur.getLogin());
					traceCommentaire.setDemandeurNom(utilisateur.getNom());
					traceCommentaire.setDemandeurPrenom(utilisateur.getPrenom());
				}
				traceCommentaire.setEntite(entite);
				traceCommentaireSousLiaisonRepository.save(traceCommentaire);
			}
		}
		return sousLiaison;
	}

	private void addTraceEtatDemande(Demande demande, Date now, EtatDemande etatDemande) {
		TraceEtatDemande traceEtatDemande = new TraceEtatDemande();
		traceEtatDemande.setDemande(demande);
		traceEtatDemande.setDateEtat(now);
		traceEtatDemande.setEtatDemande(etatDemande);
		traceEtatDemandeRepository.save(traceEtatDemande);
	}

	private void addTraceEtatLiaison(Liaison liaison, Date now, EtatLiaison etatLiaison) {
		TraceEtatLiaison traceEtatLiaison = new TraceEtatLiaison();
		traceEtatLiaison.setDateEtat(now);
		traceEtatLiaison.setEtatLiaison(etatLiaison);
		traceEtatLiaison.setLiaison(liaison);
		traceEtatLiaisonRepository.save(traceEtatLiaison);
	}

	private void addTraceCommentaire(String commentaire, Date now, Demande demande, Utilisateur utilisateur,
			String entite) {
		TraceCommentaire traceCommentaire = new TraceCommentaire();
		traceCommentaire.setCommentaire(commentaire);
		traceCommentaire.setDateCommentaire(now);
		traceCommentaire.setDemande(demande);

		if (utilisateur != null) {
			traceCommentaire.setDemandeurLogin(utilisateur.getLogin());
			traceCommentaire.setDemandeurNom(utilisateur.getNom());
			traceCommentaire.setDemandeurPrenom(utilisateur.getPrenom());
		}
		traceCommentaire.setEntite(entite);
		traceCommentaireRepository.save(traceCommentaire);
	}

	private void addEmailValidation(ValidateDemandeDto dto, Demande demande, Liaison liaison, Date now, Long idtUser,
			String entiteAEnvoyer) {
		String link = "";
		if (pathInterneProject != null && !pathInterneProject.equals("") && dto.getIdtTypeDemande() != null) {
			Optional<TypeDemande> optionalType = typeDemandeRepository.findById(dto.getIdtTypeDemande());
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
		}

		Email email = new Email(false, now, link, "validation-demande", "INTERNE", liaison, demande, null, idtUser,
				entiteAEnvoyer, dto.getEntite(), null);
		emailRepository.save(email);
	}

	@Override
	public Long validerDemande(ValidateDemandeDto dto) throws ParseException {
		Long idt = null;
		Devis devis = null;
		if (dto != null && dto.getIdt() != null) {
			Optional<Demande> optional = demandeRepository.findById(dto.getIdt());

			Demande demande = null;
			if (optional.isPresent()) {
				demande = optional.get();
			}

			boolean isDon = SecurityContextHelper.isDon();
			boolean isDemt = SecurityContextHelper.isDemt();
			boolean isDR = SecurityContextHelper.isDr();
			if (demande != null && demande.getLiaison() != null) {
				EtatDemande etatDemande = null;
				EtatLiaison etatLiaison = null;
				String entite = null;
				String entiteAEnvoyer = null;

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
				Long idtUser = null;
				if (utilisateur != null) {
					idtUser = utilisateur.getIdt();
				}
				if (isDon) {
					if (!demande.getTypeDemande().getCode().equals("DEVIS") &&!demande.getTypeDemande().getCode().equals("RECEPTION") ) {
						etatDemande = etatDemandeRepository.findByCode("VALIDE_DON");
						entite = "DON";
						entiteAEnvoyer = "DEMT";
					} else if (demande.getTypeDemande().getCode().equals("DEVIS")) {
						etatDemande = etatDemandeRepository.findByCode("DEVIS_SENT");
						entite = "DON";
						entiteAEnvoyer = "ERPT";
					} else if (demande.getTypeDemande().getCode().equals("RECEPTION")) {
						if (demande.getEtatDemande().getCode().equals("VALIDE_DEMT")) {
							etatDemande = etatDemandeRepository.findByCode("TRACE_PENDING");
							entite = "DON";
							entiteAEnvoyer = "ERPT";
						}

					}

				} else if (isDR) {
					if (demande.getTypeDemande().getCode().equals("RECEPTION")) {
						if (demande.getEtatDemande().getCode().equals("PLANNING_PENDING")) {
							etatDemande = etatDemandeRepository.findByCode("VALIDE_DR");
							entite = "DR";
							entiteAEnvoyer = "DEMT";
						} else if (demande.getEtatDemande().getCode().equals("TRACE_PENDING")) {
							etatDemande = etatDemandeRepository.findByCode("ACCEPTATION_PENDING");
							entite = "DR";
							entiteAEnvoyer = "DEMT";
						} else if (demande.getEtatDemande().getCode().equals("PV_PENDING")) {
							etatDemande = etatDemandeRepository.findByCode("INSTALLATION_PENDING");
							entite = "DR";
							entiteAEnvoyer = "DEMT";
						} else if (demande.getEtatDemande().getCode().equals("JARRETIERAGE_PENDING")
								|| demande.getEtatDemande().getCode().equals("JARRETIERAGE_DENIED")) {
							etatDemande = etatDemandeRepository.findByCode("QUALIFICATION_JARRETIERAGE_PENDING");
							entite = "DR";
							entiteAEnvoyer = "DEMT";
						}

					} else if (demande.getTypeDemande().getCode().equals("SIGNALISATION")) {
						demande.setActionsRecommandees(dto.getActionRecommandees());
						demande.setCommentaire(dto.getCommentaire());
						demande.setIdtDrReponse(dto.getSelectedDr());
						demande.setDiagnostic(dto.getDiagnostic());
						demande.setIntervenant(dto.getIntervenant());
						demande.setRepondeur(utilisateur.getLogin());
						etatDemande = etatDemandeRepository.findByCode("CLOTURE_IAM");

						entite = "DR";
						entiteAEnvoyer = "DEMT";
					}

				} else if (isDemt) {
					if (demande.getEtatDemande().getCode().equals("VALIDE_DR")) {
						etatDemande = etatDemandeRepository.findByCode("VALIDE_DEMT");
						entite = "DEMT";
						entiteAEnvoyer = "DON";
					} else if (demande.getEtatDemande().getCode().equals("VALIDE_DON")) {
							if(demande.getTypeDemande().getCode().equals("ACCES")) {
								etatDemande = etatDemandeRepository.findByCode("VALIDE_DEMT");
								entite = "DEMT";
								entiteAEnvoyer = "DR";
							}else {
								etatLiaison = etatLiaisonRepository.findByCode("ETUDE_PENDING");
								etatDemande = etatDemandeRepository.findByCode("ETUDE_PENDING");
								entite = "DEMT";
								entiteAEnvoyer = "DR";
							}
						

						/****************** AJOUT DES TRONCONS *********************/
						if (dto.getListSousLiaison() != null && !dto.getListSousLiaison().isEmpty()) {
							for (SousLiaisonDto sousLiaisonDto : dto.getListSousLiaison()) {
								if (sousLiaisonDto.getIdt() == null || sousLiaisonDto.getIdt() <= 0L) {
									addSousLiaison(sousLiaisonDto, now, utilisateur, entite);
								}
							}
						}
					}

				}

				if (etatDemande != null) {
					if (isDon && etatDemande.getCode().equals("DEVIS_SENT")) {
						devis = new Devis();
						devis.setDemande(demande);
						devis.setDateDevis(now);
						devis.setPrixTotal(dto.getPrixTotal());
						devisRepository.save(devis);
					}
					if (isDR) {
						demande.setDateReception(DateUtils.stringToDate(dto.getDateReception()));
					}
					demande.setCommentaire(dto.getCommentaire());
					demande.setEtatDemande(etatDemande);
					demandeRepository.save(demande);
					/****************** AJOUT TRACE ETAT DEMANDE *********************/
					addTraceEtatDemande(demande, now, etatDemande);
				}

				Liaison liaison = demande.getLiaison();
				if (etatLiaison != null) {
					liaison.setEtatLiaison(etatLiaison);
					liaisonRepository.save(liaison);
					/****************** AJOUT TRACE ETAT LIAISON *********************/
					addTraceEtatLiaison(liaison, now, etatLiaison);
				}

				/****************** AJOUT TRACE COMMENTAIRE *********************/
				if (etatDemande != null && !"ETUDE_PENDING".equalsIgnoreCase(etatDemande.getCode())) {
					addTraceCommentaire(dto.getCommentaire(), now, demande, utilisateur, entite);
				}

				/****************** AJOUT EMAIL *********************/
				addEmailValidation(dto, demande, liaison, now, idtUser, entiteAEnvoyer);

				idt = demande.getIdt();

				logger.info(Utils.getLogParam() + "Validation de la demande ayant IDT: " + demande.getIdt());

				traceServiceImpl.traceOperation(ActionCode.MODIF,
						"Validation de la demande ayant IDT: " + demande.getIdt() + " par l'entité " + dto.getEntite(),
						"Validation de la demande ayant IDT: " + demande.getIdt() + " par l'entité " + dto.getEntite(),
						"MODIFICATION", "Commande", null, null);
			}
		}
		return idt;
	}

	@Override
	public InteractionDto addReponseInteraction(InteractionDto dto) throws ParseException {
		InteractionDto interactionDto = null;
		if (dto != null && dto.getIdt() != null) {
			Optional<Interaction> optional = interactionRepository.findById(dto.getIdt());
			Interaction interaction = null;
			if (optional.isPresent()) {
				interaction = optional.get();
			}
			if (interaction != null && interaction.getDemande() != null
					&& interaction.getDemande().getLiaison() != null) {
				Demande demande = interaction.getDemande();
				Liaison liaison = interaction.getDemande().getLiaison();
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

				interaction.setDateReponse(now);
				interaction.setReponse(dto.getReponse());

				String login = SecurityContextHolder.getContext().getAuthentication().getName();
				Utilisateur utilisateur = utilisateurRepository.findByLogin(login);
				Long idtUser = null;
				if (utilisateur != null) {
					interaction.setRepondeurLogin(utilisateur.getLogin());
					interaction.setRepondeurNom(utilisateur.getNom());
					interaction.setRepondeurPrenom(utilisateur.getPrenom());
					idtUser = utilisateur.getIdt();
				}
				interactionRepository.save(interaction);

				/****************** AJOUT EMAIL *********************/
				String link = "";
				if (pathInterneProject != null && !pathInterneProject.equals("")) {
					TypeDemande typeDemande = interaction.getDemande().getTypeDemande();

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
				}

				Email email = new Email(false, now, link, "reponse-interaction", "INTERNE", liaison, demande,
						interaction, idtUser, dto.getEntiteSource(), dto.getEntiteCible(), null);

				emailRepository.save(email);

				interactionDto = mapperInteraction.toDto(interaction);

				logger.info(Utils.getLogParam() + "Ajout une réponse à l'interaction ayant IDT: " + interaction.getIdt()
						+ " par l'entité " + dto.getEntiteCible());

				traceServiceImpl.traceOperation(ActionCode.MODIF,
						"Ajout une réponse à l'interaction ayant IDT: " + interaction.getIdt() + " par l'entité "
								+ dto.getEntiteCible(),
						"Ajout une réponse à l'interaction ayant IDT: " + interaction.getIdt() + " par l'entité "
								+ dto.getEntiteCible(),
						"MODIFICATION", "Interaction", null, null);
			}
		}
		return interactionDto;
	}

	@Override
	public InteractionDto addInteraction(InteractionDto dto) throws ParseException {
		InteractionDto interactionDto = null;
		if (dto != null && dto.getDemandeIdt() != null) {

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

			Optional<Demande> optional = demandeRepository.findById(dto.getDemandeIdt());
			Demande demande = null;
			if (optional.isPresent()) {
				demande = optional.get();
			}
			Interaction interactionSave = null;
			Long idtUser = null;
			if (demande != null) {
				Interaction interaction = new Interaction();
				interaction.setDateInteraction(now);
				interaction.setDemande(demande);
				interaction.setEntiteCible(dto.getEntiteCible());
				interaction.setEntiteSource(dto.getEntiteSource());
				interaction.setInteractionLabel(dto.getInteractionLabel());

				String login = SecurityContextHolder.getContext().getAuthentication().getName();
				Utilisateur utilisateur = utilisateurRepository.findByLogin(login);

				if (utilisateur != null) {
					interaction.setDemandeurLogin(utilisateur.getLogin());
					interaction.setDemandeurNom(utilisateur.getNom());
					interaction.setDemandeurPrenom(utilisateur.getPrenom());
					idtUser = utilisateur.getIdt();
				}
				interactionSave = interactionRepository.save(interaction);
			}

			/****************** AJOUT EMAIL *********************/
			if (interactionSave != null && interactionSave.getDemande() != null
					&& interactionSave.getDemande().getLiaison() != null) {
				String link = "";
				Liaison liaison = interactionSave.getDemande().getLiaison();
				if (pathInterneProject != null && !pathInterneProject.equals("")) {
					TypeDemande typeDemande = interactionSave.getDemande().getTypeDemande();
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
				}

				Email email = new Email(false, now, link, "add-interaction", "INTERNE", liaison, demande,
						interactionSave, idtUser, dto.getEntiteCible(), dto.getEntiteSource(), null);

				emailRepository.save(email);

				interactionDto = mapperInteraction.toDto(interactionSave);

				logger.info(
						Utils.getLogParam() + "Ajout d'une interaction à la demande ayant IDT: " + demande.getIdt());

				traceServiceImpl.traceOperation(ActionCode.ADD,
						"Ajout d'une interaction à la demande ayant IDT: " + demande.getIdt() + " par l'entité "
								+ dto.getEntiteSource(),
						"Ajout d'une interaction à la demande ayant IDT: " + demande.getIdt() + " par l'entité "
								+ dto.getEntiteSource(),
						"AJOUT", "Interaction", null, null);
			}

		}
		return interactionDto;
	}

	@Override
	public SousLiaisonDto addReponseSousLiaison(SousLiaisonDto dto) throws ParseException {
		SousLiaisonDto sousLiaisonDto = null;
		if (dto != null && dto.getIdt() != null) {
			Optional<SousLiaison> optional = sousLiaisonRepository.findById(dto.getIdt());
			SousLiaison sousLiaison = null;
			if (optional.isPresent()) {
				sousLiaison = optional.get();
			}
			if (sousLiaison != null) {
				OperationSousLiaison operationSousLiaison = operationSousLiaisonRepository
						.getOperationBySousLiaisonIdt(sousLiaison.getIdt());
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

				if (operationSousLiaison == null || operationSousLiaison.getDemande() == null) {
					return sousLiaisonDto;
				}
				Demande demande = operationSousLiaison.getDemande();
				String etatDemandeStr = "";
				if (dto.getEtatDemandeSousLiaisonCode() != null && !"".equals(dto.getEtatDemandeSousLiaisonCode())) {
					EtatDemandeSousLiaison etat = etatDemandeSousLiaisonRepository
							.findByCode(dto.getEtatDemandeSousLiaisonCode());
					if (etat != null) {
						etatDemandeStr = etat.getLabel();
						operationSousLiaison.setEtatDemandeSousLiaison(etat);
					}
				}
				operationSousLiaison.setDateReponse(now);
				String login = SecurityContextHolder.getContext().getAuthentication().getName();
				Utilisateur utilisateur = utilisateurRepository.findByLogin(login);
				Long idtUser = null;
				if (utilisateur != null) {
					operationSousLiaison.setRepondeurLogin(utilisateur.getLogin());
					operationSousLiaison.setRepondeurNom(utilisateur.getNom());
					operationSousLiaison.setRepondeurPrenom(utilisateur.getPrenom());
					idtUser = utilisateur.getIdt();
				}
				operationSousLiaison.setDescriptionInfaisabilite(dto.getDescriptionInfaisabilite());

				RaisonInfaisabilite raisonInfaisabilite = raisonInfaisabiliteRepository
						.findByCode(dto.getRaisonInfaisabiliteCode());

				if (raisonInfaisabilite != null) {
					operationSousLiaison.setRaisonInfaisabilite(raisonInfaisabilite);

				}

				operationSousLiaison.setSolutionAlternative(dto.getSolutionAlternative());

				operationSousLiaisonRepository.save(operationSousLiaison);

				sousLiaison.setDistanceDisponible(dto.getDistanceDisponible());
				sousLiaison.setDistanceSature(dto.getDistanceSature());
				sousLiaison.setCableFoPropose(dto.getCableFoPropose());
				sousLiaison.setBilanObtiqueEstimatif(dto.getBilanObtiqueEstimatif());
				sousLiaison.setDelaisRealisation(dto.getDelaisRealisation());
				sousLiaison.setDistanceEstimative(dto.getDistanceEstimative());
				sousLiaison.setEtat(etatDemandeStr);
				sousLiaison.setContactDr(dto.getContactDr());
				sousLiaison.setInterlocuteurDr(dto.getInterlocuteurDr());

				boolean isDR = SecurityContextHelper.isDr();
				if (isDR) {
					sousLiaison.setDateReponse(new Date());
				}
				sousLiaisonRepository.save(sousLiaison);

				/****************** AJOUT TRACE ETAT DEMANDE TRONCON *********************/
				TraceEtatDemandeSousLiaison traceEtatDemandeSousLiaison = new TraceEtatDemandeSousLiaison();
				traceEtatDemandeSousLiaison.setSousLiaison(sousLiaison);
				traceEtatDemandeSousLiaison.setDateEtat(now);
				traceEtatDemandeSousLiaison.setEtatDemandeSousLiaison(operationSousLiaison.getEtatDemandeSousLiaison());
				traceEtatDemandeSousLiaisonRepository.save(traceEtatDemandeSousLiaison);

				/****************** AJOUT EMAIL *********************/
				String link = "";
				if (pathInterneProject != null && !pathInterneProject.equals("")) {
					TypeDemande typeDemande = demande.getTypeDemande();

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
				}

				Email email = new Email(false, now, link, "reponse-troncon", "INTERNE", sousLiaison.getLiaison(),
						demande, null, idtUser, "DR", "DR", sousLiaison);
				emailRepository.save(email);

				sousLiaisonDto = mapperSousLiaison.toDto(sousLiaison);

				logger.info(Utils.getLogParam() + "Ajout une réponse au tronçon ayant IDT: " + sousLiaison.getIdt()
						+ " par l'entité DR ");

				traceServiceImpl.traceOperation(ActionCode.MODIF,
						"Ajout une réponse au tronçon ayant IDT: " + sousLiaison.getIdt() + " par l'entité DR ",
						"Ajout une réponse au tronçon ayant IDT: " + sousLiaison.getIdt() + " par l'entité DR ",
						"MODIFICATION", "Affectation tronçon", null, null);
			}
		}
		return sousLiaisonDto;
	}

	@Override
	public TraceCommentaireDto addComment(TraceCommentaireDto dto) throws ParseException {
		TraceCommentaireDto traceCommentaireDto = null;

		if (dto != null && dto.getDemandeIdt() != null) {

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

			Optional<Demande> optional = demandeRepository.findById(dto.getDemandeIdt());
			Demande demande = null;
			if (optional.isPresent()) {
				demande = optional.get();
			}
			TraceCommentaire traceCommentaire = null;
			if (demande != null) {
				traceCommentaire = new TraceCommentaire();
				traceCommentaire.setCommentaire(dto.getCommentaire());
				traceCommentaire.setDateCommentaire(now);
				traceCommentaire.setDemande(demande);
				traceCommentaire.setEntite(dto.getEntite());

				String login = SecurityContextHolder.getContext().getAuthentication().getName();
				Utilisateur utilisateur = utilisateurRepository.findByLogin(login);

				if (utilisateur != null) {
					traceCommentaire.setDemandeurLogin(utilisateur.getLogin());
					traceCommentaire.setDemandeurNom(utilisateur.getNom());
					traceCommentaire.setDemandeurPrenom(utilisateur.getPrenom());
				}
				traceCommentaireRepository.save(traceCommentaire);

				logger.info(Utils.getLogParam() + "Ajout d'un commentaire à la demande ayant IDT: " + demande.getIdt()
						+ " par l'entité : " + dto.getEntite());

				traceServiceImpl.traceOperation(ActionCode.ADD,
						"Ajout d'un commentaire à la demande ayant IDT: " + demande.getIdt() + " par l'entité : "
								+ dto.getEntite(),
						"Ajout d'un commentaire à la demande ayant IDT: " + demande.getIdt() + " par l'entité : "
								+ dto.getEntite(),
						"AJOUT", "Demande", null, null);
			}

			traceCommentaireDto = mapperTraceCommentaire.toDto(traceCommentaire);

		}
		return traceCommentaireDto;
	}

	@Override
	public SousLiaisonDto addCommentSousLiaison(SousLiaisonDto dto) throws ParseException {
		SousLiaisonDto sousLiaisonDto = null;
		if (dto != null && dto.getIdt() != null) {
			Optional<SousLiaison> optional = sousLiaisonRepository.findById(dto.getIdt());
			SousLiaison sousLiaison = null;
			if (optional.isPresent()) {
				sousLiaison = optional.get();
			}
			if (sousLiaison != null) {
				OperationSousLiaison operationSousLiaison = operationSousLiaisonRepository
						.getOperationBySousLiaisonIdt(sousLiaison.getIdt());

				if (operationSousLiaison == null) {
					return sousLiaisonDto;
				}
				operationSousLiaison.setCommentaire(dto.getCommentaire());
				operationSousLiaisonRepository.save(operationSousLiaison);

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

				/****************** AJOUT TRACE COMMENTAIRE TRONCON *********************/
				TraceCommentaireSousLiaison traceCommentaire = new TraceCommentaireSousLiaison();
				traceCommentaire.setCommentaire(dto.getCommentaire());
				traceCommentaire.setDateCommentaire(now);
				traceCommentaire.setSousLiaison(sousLiaison);
				String login = SecurityContextHolder.getContext().getAuthentication().getName();
				Utilisateur utilisateur = utilisateurRepository.findByLogin(login);
				String entite = "";
				if (utilisateur != null) {
					traceCommentaire.setDemandeurLogin(utilisateur.getLogin());
					traceCommentaire.setDemandeurNom(utilisateur.getNom());
					traceCommentaire.setDemandeurPrenom(utilisateur.getPrenom());
					if (utilisateur.getEntite() != null) {
						traceCommentaire.setEntite(utilisateur.getEntite().getCode());
						entite = utilisateur.getEntite().getCode();
					}
				}

				traceCommentaireSousLiaisonRepository.save(traceCommentaire);
				sousLiaisonDto = mapperSousLiaison.toDto(sousLiaison);

				logger.info(Utils.getLogParam() + "Ajout d'un commentaire au tronçon ayant IDT: " + sousLiaison.getIdt()
						+ " par l'entité : " + entite);

				traceServiceImpl.traceOperation(ActionCode.MODIF,
						"Ajout d'un commentaire au tronçon ayant IDT: " + sousLiaison.getIdt() + " par l'entité : "
								+ entite,
						"Ajout d'un commentaire au tronçon ayant IDT: " + sousLiaison.getIdt() + " par l'entité : "
								+ entite,
						"MODIFICATION", "Tronçon", null, null);
			}
		}
		return sousLiaisonDto;
	}

	@Override
	public SousLiaisonDto devalidationSousLiaison(SousLiaisonDto dto) throws ParseException {
		SousLiaisonDto sousLiasonDto = null;
		if (dto != null && dto.getIdt() != null) {
			Optional<SousLiaison> optional = sousLiaisonRepository.findById(dto.getIdt());
			SousLiaison sousLiaison = null;
			if (optional.isPresent()) {
				sousLiaison = optional.get();
			}
			if (sousLiaison != null) {

				OperationSousLiaison operationSousLiaison = operationSousLiaisonRepository
						.getOperationBySousLiaisonIdt(sousLiaison.getIdt());

				if (operationSousLiaison == null || operationSousLiaison.getDemande() == null) {
					return sousLiasonDto;
				}
				operationSousLiaison.setCommentaire(dto.getCommentaire());
				EtatDemandeSousLiaison etat = etatDemandeSousLiaisonRepository.findByCode("PENDING");
				operationSousLiaison.setEtatDemandeSousLiaison(etat);
				operationSousLiaison.setDateReponse(null);
				operationSousLiaison.setRepondeurLogin(null);
				operationSousLiaison.setRepondeurNom(null);
				operationSousLiaison.setRepondeurPrenom(null);
				operationSousLiaison.setDescriptionInfaisabilite(null);
				operationSousLiaison.setRaisonInfaisabilite(null);
				operationSousLiaison.setSolutionAlternative(null);
				operationSousLiaisonRepository.save(operationSousLiaison);

				if (etat != null) {
					sousLiaison.setEtat(etat.getLabel());
				}
				sousLiaison.setDistanceDisponible(null);
				sousLiaison.setDistanceSature(null);
				sousLiaison.setCableFoPropose(null);
				sousLiaison.setBilanObtiqueEstimatif(null);
				sousLiaison.setDelaisRealisation(null);
				sousLiaison.setDistanceEstimative(null);
				sousLiaisonRepository.save(sousLiaison);

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

				/****************** AJOUT TRACE COMMENTAIRE TRONCON *********************/
				TraceCommentaireSousLiaison traceCommentaire = new TraceCommentaireSousLiaison();
				traceCommentaire.setCommentaire(dto.getCommentaire());
				traceCommentaire.setDateCommentaire(now);
				traceCommentaire.setSousLiaison(sousLiaison);
				String login = SecurityContextHolder.getContext().getAuthentication().getName();
				Utilisateur utilisateur = utilisateurRepository.findByLogin(login);
				String entite = "";
				Long idtUser = null;
				if (utilisateur != null) {
					traceCommentaire.setDemandeurLogin(utilisateur.getLogin());
					traceCommentaire.setDemandeurNom(utilisateur.getNom());
					traceCommentaire.setDemandeurPrenom(utilisateur.getPrenom());
					idtUser = utilisateur.getIdt();
					if (utilisateur.getEntite() != null) {
						traceCommentaire.setEntite(utilisateur.getEntite().getCode());
						entite = utilisateur.getEntite().getCode();
					}
				}
				traceCommentaireSousLiaisonRepository.save(traceCommentaire);
				/****************** AJOUT TRACE ETAT DEMANDE TRONCON *********************/
				TraceEtatDemandeSousLiaison traceEtatDemandeSousLiaison = new TraceEtatDemandeSousLiaison();
				traceEtatDemandeSousLiaison.setSousLiaison(sousLiaison);
				traceEtatDemandeSousLiaison.setDateEtat(now);
				traceEtatDemandeSousLiaison.setEtatDemandeSousLiaison(etat);
				traceEtatDemandeSousLiaisonRepository.save(traceEtatDemandeSousLiaison);

				/****************** AJOUT EMAIL *********************/
				String link = "";
				Demande demande = operationSousLiaison.getDemande();
				if (pathInterneProject != null && !pathInterneProject.equals("")) {
					TypeDemande typeDemande = demande.getTypeDemande();

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
				}

				Email email = new Email(false, now, link, "devalidation-troncon", "INTERNE", sousLiaison.getLiaison(),
						demande, null, idtUser, "DR", "DR", sousLiaison);
				emailRepository.save(email);

				sousLiasonDto = mapperSousLiaison.toDto(sousLiaison);

				logger.info(Utils.getLogParam() + "Dévalidation du tronçon ayant IDT: " + sousLiaison.getIdt()
						+ " par l'entité : " + entite);

				traceServiceImpl.traceOperation(ActionCode.MODIF,
						"Dévalidation du tronçon ayant IDT: " + sousLiaison.getIdt() + " par l'entité : " + entite,
						"Dévalidation du tronçon ayant IDT: " + sousLiaison.getIdt() + " par l'entité : " + entite,
						"MODIFICATION", "Tronçon", null, null);
			}
		}
		return sousLiasonDto;
	}

	@Override
	public SousLiaisonDto clotureSousLiaison(SousLiaisonDto dto) throws ParseException {
		SousLiaisonDto sousLiaisonDto = null;
		if (dto != null && dto.getIdt() != null) {
			Optional<SousLiaison> optional = sousLiaisonRepository.findById(dto.getIdt());
			SousLiaison sousLiaison = null;
			if (optional.isPresent()) {
				sousLiaison = optional.get();
			}
			if (sousLiaison != null) {

				OperationSousLiaison operationSousLiaison = operationSousLiaisonRepository
						.getOperationBySousLiaisonIdt(sousLiaison.getIdt());

				if (operationSousLiaison == null || operationSousLiaison.getDemande() == null) {
					return sousLiaisonDto;
				}
				EtatDemandeSousLiaison etat = etatDemandeSousLiaisonRepository.findByCode("CLOTURE");
				operationSousLiaison.setEtatDemandeSousLiaison(etat);
				operationSousLiaisonRepository.save(operationSousLiaison);

				if (etat != null) {
					sousLiaison.setEtat(etat.getLabel());
				}
				sousLiaisonRepository.save(sousLiaison);

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
				/****************** AJOUT TRACE ETAT DEMANDE TRONCON *********************/
				TraceEtatDemandeSousLiaison traceEtatDemandeSousLiaison = new TraceEtatDemandeSousLiaison();
				traceEtatDemandeSousLiaison.setSousLiaison(sousLiaison);
				traceEtatDemandeSousLiaison.setDateEtat(now);
				traceEtatDemandeSousLiaison.setEtatDemandeSousLiaison(etat);
				traceEtatDemandeSousLiaisonRepository.save(traceEtatDemandeSousLiaison);

				/****************** AJOUT EMAIL *********************/
				String link = "";
				Demande demande = operationSousLiaison.getDemande();
				if (pathInterneProject != null && !pathInterneProject.equals("")) {
					TypeDemande typeDemande = demande.getTypeDemande();

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
				}

				Email email = new Email(false, now, link, "cloture-troncon", "INTERNE", sousLiaison.getLiaison(),
						demande, null, idtUser, "DR", "DR", sousLiaison);
				emailRepository.save(email);

				sousLiaisonDto = mapperSousLiaison.toDto(sousLiaison);

				logger.info(Utils.getLogParam() + "Dévalidation du tronçon ayant IDT: " + sousLiaison.getIdt()
						+ " par l'entité : " + entite);

				traceServiceImpl.traceOperation(ActionCode.MODIF,
						"Dévalidation du tronçon ayant IDT: " + sousLiaison.getIdt() + " par l'entité : " + entite,
						"Dévalidation du tronçon ayant IDT: " + sousLiaison.getIdt() + " par l'entité : " + entite,
						"MODIFICATION", "Tronçon", null, null);
			}
		}
		return sousLiaisonDto;
	}

	@Override
	public SousLiaisonDto addNewSousLiaison(SousLiaisonDto dto) throws ParseException {
		SousLiaisonDto sousLiaisonDto = null;
		if (dto != null) {
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
			if (utilisateur != null) {
				if (utilisateur.getEntite() != null) {
					entite = utilisateur.getEntite().getCode();
				}
				SousLiaison sousLiaison = addSousLiaison(dto, now, utilisateur, entite);
				sousLiaisonDto = mapperSousLiaison.toDto(sousLiaison);
			}
		}
		return sousLiaisonDto;
	}

	@Override
	public boolean removeSousLiaison(SousLiaisonDto dto) {
		if (dto != null && dto.getIdt() != null && dto.getIdt() > 0L) {
			Optional<SousLiaison> optional = sousLiaisonRepository.findById(dto.getIdt());
			if (optional.isPresent()) {
				SousLiaison sousLiaison = optional.get();
				if (sousLiaison != null) {
					List<OperationSousLiaison> operations = operationSousLiaisonRepository
							.getListOperationSousLiaisonBySousLiaison(sousLiaison.getIdt());
					if (operations != null && !operations.isEmpty()) {
						for (OperationSousLiaison o : operations) {
							operationSousLiaisonRepository.delete(o);
						}
					}

					List<TraceCommentaireSousLiaison> traceCommentaires = traceCommentaireSousLiaisonRepository
							.getTraceCommentBySousLiaison(sousLiaison.getIdt());
					if (traceCommentaires != null && !traceCommentaires.isEmpty()) {
						for (TraceCommentaireSousLiaison t : traceCommentaires) {
							traceCommentaireSousLiaisonRepository.delete(t);
						}
					}

					List<TraceEtatDemandeSousLiaison> traceEtats = traceEtatDemandeSousLiaisonRepository
							.getTraceEtatBySousLiaison(sousLiaison.getIdt());
					if (traceEtats != null && !traceEtats.isEmpty()) {
						for (TraceEtatDemandeSousLiaison t : traceEtats) {
							traceEtatDemandeSousLiaisonRepository.delete(t);
						}
					}
					sousLiaisonRepository.delete(sousLiaison);
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public void updateSousLiaison(SousLiaisonDto sousLiaisonDto) {
		SousLiaison sousLiaison = new SousLiaison();
		Optional<SousLiaison> sousLiaisonOptional = souLiaisonRepository.findById(sousLiaisonDto.getIdt());
		if (sousLiaisonOptional.isPresent()) {
			sousLiaison = sousLiaisonOptional.get();

			sousLiaison.setIdtDr(sousLiaisonDto.getDrIdt());
			sousLiaison.setDistance(sousLiaisonDto.getDistance());
			sousLiaison.setDateAffectation(DateUtils.stringToDateTime(sousLiaisonDto.getDateAffectation()));
			sousLiaison.setContactDemt(sousLiaisonDto.getContactDemt());
			sousLiaison.setInterlocuteurDemt(sousLiaisonDto.getInterlocuteurDemt());
			sousLiaison.setDistanceDisponible(sousLiaisonDto.getDistanceDisponible());
			sousLiaison.setDistanceEstimative(sousLiaisonDto.getDistanceEstimative());
			sousLiaison.setBilanObtiqueEstimatif(sousLiaisonDto.getBilanObtiqueEstimatif());
			sousLiaison.setCableFoPropose(sousLiaisonDto.getCableFoPropose());
			sousLiaison.setDelaisRealisation(sousLiaisonDto.getDelaisRealisation());

		}
		souLiaisonRepository.save(sousLiaison);
	}

	@Override
	public Long validateLiaison(ConsolidationDto dto) throws ParseException {
		Long idt = null;
		if (dto != null) {
			Optional<Demande> optional = demandeRepository.findById(dto.getIdtDemande());
			Demande demande = null;
			if (optional.isPresent()) {
				demande = optional.get();
			}
			if (demande != null && demande.getLiaison() != null) {
				Liaison liaison = demande.getLiaison();

				EtatDemande etatDraft = etatDemandeRepository.findByCode(dto.getCodeEtatDemande());
				EtatDemande etatDemande = etatDemandeRepository.findByCode("CONSOLIDATION_DON");

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
				String entiteSource = "";
				Long idtUser = null;
				if (utilisateur != null) {
					idtUser = utilisateur.getIdt();
					if (utilisateur.getEntite() != null) {
						entiteSource = utilisateur.getEntite().getCode();
					}
				}

				demande.setEtatDemande(etatDemande);
				demande.setEtatDraft(etatDraft);
				demandeRepository.save(demande);
				/****************** AJOUT TRACE ETAT DEMANDE *********************/
				addTraceEtatDemande(demande, now, etatDemande);

				liaison.setDistanceDisponible(dto.getDistanceDisponible());
				liaison.setDistanceSature(dto.getDistanceSature());

				if (demande.getTypeDemande().getCode() == "PARTAGE") {
					List<SousLiaison> sousLiaison = sousLiaisonRepository
							.getListSousLiaisonByLiaisonIdt(liaison.getIdt());
					if (sousLiaison != null && !sousLiaison.isEmpty()) {
						liaison.setCodeSiteA(sousLiaison.get(0).getSiteA().getCode());
						liaison.setCodeSiteB(sousLiaison.get(0).getSiteB().getCode());

					}
				}

				liaisonRepository.save(liaison);

				/****************** AJOUT EMAIL *********************/
				String link = "";
				if (pathInterneProject != null && !pathInterneProject.equals("") && demande.getTypeDemande() != null) {
					TypeDemande typeDemande = demande.getTypeDemande();
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
				}

				Email email = new Email(false, now, link, "validate-consolidation", "INTERNE", liaison, demande, null,
						idtUser, "DEF", entiteSource, null);
				emailRepository.save(email);

				idt = demande.getIdt();

				logger.info(Utils.getLogParam() + "Validation Consolidation demande ayant IDT: " + demande.getIdt()
						+ " par l'entité: " + entiteSource);

				traceServiceImpl.traceOperation(ActionCode.MODIF,
						"Validation Consolidation demande ayant IDT: " + demande.getIdt() + " par l'entité: "
								+ entiteSource,
						"Validation Consolidation demande ayant IDT: " + demande.getIdt() + " par l'entité: "
								+ entiteSource,
						"MODIFICATION", "Commande", null, null);
			}
		}
		return idt;
	}

	@Override
	public Long validateConsolidation(ValidateDemandeDto dto) throws ParseException {
		Long idt = null;
		if (dto != null) {
			Optional<Demande> optional = demandeRepository.findById(dto.getIdt());
			Demande demande = null;
			if (optional.isPresent()) {
				demande = optional.get();
			}
			if (demande != null && demande.getLiaison() != null) {
				Liaison liaison = demande.getLiaison();

				EtatDemande etatDemande = null;
				EtatLiaison etatLiaison = null;
				String entiteAEnvoyer = "";
				if ("DEF".equalsIgnoreCase(dto.getEntite())) {
					etatDemande = etatDemandeRepository.findByCode("CONSOLIDATION_DON");
					entiteAEnvoyer = "DON";
				} else if ("DON".equalsIgnoreCase(dto.getEntite()) && demande.getEtatDraft() != null) {
					entiteAEnvoyer = "ERPT";
					etatDemande = demande.getEtatDraft();
					etatLiaison = etatLiaisonRepository.findByCode(demande.getEtatDraft().getCode());
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

				Long idtUser = null;
				if (utilisateur != null) {
					idtUser = utilisateur.getIdt();
				}

				if (etatDemande != null) {
					demande.setEtatDemande(etatDemande);
					demandeRepository.save(demande);
					/****************** AJOUT TRACE ETAT DEMANDE *********************/
					addTraceEtatDemande(demande, now, etatDemande);
					/****************** AJOUT TRACE COMMENTAIRE DEMANDE *********************/
					addTraceCommentaire(dto.getCommentaire(), now, demande, utilisateur, dto.getEntite());
				}

				if (etatLiaison != null) {
					liaison.setEtatLiaison(etatLiaison);
					liaisonRepository.save(liaison);
					/****************** AJOUT TRACE ETAT LIAISON *********************/
					addTraceEtatLiaison(liaison, now, etatLiaison);
				}

				/****************** AJOUT EMAIL *********************/
				String link = "";
				if (pathInterneProject != null && !pathInterneProject.equals("") && demande.getTypeDemande() != null) {
					TypeDemande typeDemande = demande.getTypeDemande();
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
				}

				Email email = new Email(false, now, link, "validate-consolidation", "INTERNE", liaison, demande, null,
						idtUser, entiteAEnvoyer, dto.getEntite(), null);
				emailRepository.save(email);

				idt = demande.getIdt();

				logger.info(Utils.getLogParam() + "Validate Consolidation demande ayant IDT: " + demande.getIdt()
						+ " par l'entité : " + dto.getEntite());

				traceServiceImpl.traceOperation(ActionCode.MODIF,
						"Validate Consolidation demande ayant IDT: " + demande.getIdt() + " par l'entité : "
								+ dto.getEntite(),
						"Validate Consolidation demande ayant IDT: " + demande.getIdt() + " par l'entité : "
								+ dto.getEntite(),
						"MODIFICATION", "Commande", null, null);
			}
		}
		return idt;
	}

	@Override
	public DemandeDto addDemandeTypeTravaux(DemandeDto dto) throws ParseException {
		DemandeTravauxProgramme demandeTravauxProgrammee = null;
		Demande demande = mapperDemande.toEntity(dto);
		Demande saveDemande = demandeRepository.save(demande);
		if (dto.getLiaison().size() > 0) {
			for (Long entity : dto.getLiaison()) {
				demandeTravauxProgrammee =new DemandeTravauxProgramme();
				Optional<Liaison> liaisonOptional = liaisonRepository.findById(entity);
				if (liaisonOptional.isPresent()) {
					
					demandeTravauxProgrammee.setLiaison(liaisonOptional.get());
					demandeTravauxProgrammee.setDemande(saveDemande);
					demandeTravauxProgrammeRepository.save(demandeTravauxProgrammee);

				}

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

		Long idtUser = null;
		if (utilisateur != null) {
			idtUser = utilisateur.getIdt();
		}
		addEmailTravauxProg(demande, now, idtUser);
		return dto;
	}

	private void addEmailTravauxProg(Demande demande, Date now, Long idtUser) {
		String link = "";
		if (pathExterneProject != null && !pathExterneProject.equals("")) {
			TypeDemande typeDemande = demande.getTypeDemande();
			if (typeDemande != null && "TRAVAUX_PROGRAMME".equalsIgnoreCase(typeDemande.getCode())) {
				link = pathExterneProject + "/travaux-programme/list?idt=" + demande.getIdt();
			}
		}
		
		Email email = new Email(false, now, link, "validation-demande", "INTERNE", null, demande, null, idtUser,
				"ERPT", "DEMT", null);
		emailRepository.save(email);
	}
	
	@Override
	public void changeEtatDemande(Long idtDemande, String etatDemande) {
		EtatDemande etat = etatDemandeRepository.findByCode(etatDemande);
		Optional<Demande> demandeOptional = demandeRepository.findById(idtDemande);
		if (demandeOptional.isPresent()) {
			Demande demande = demandeOptional.get();
			demande.setEtatDemande(etat);
			demandeRepository.save(demande);
		}

	}

	@Override
	public List<InteractionDto> getListInteractionByEntitySource(Long idtDemande, String entitySource) {
		List<InteractionDto> interactionDto = null;
//		interactionDto = mapperInteraction.toDto(interaction);
		List<Interaction> interaction = interactionRepository.getListInteractionByEnitySource(idtDemande, entitySource);
		if (interaction != null) {
			interactionDto = mapperInteraction.toDtos(interaction);
		}

		return interactionDto;
	}

	@Override
	public void saveDemandeWithFile(DemandeDto demandeDto) {
		if (demandeDto != null) {
			Demande demande = demandeRepository.save(mapperDemande.toEntity(demandeDto));
			if (demande != null) {
				if (demandeDto.getFile() != null) {
					Long idPieceJoindre = uploadFile(demandeDto.getFile());
					PieceJointe jointe = jointeRepository.findById(idPieceJoindre).get();
					jointe.setDemande(demande);
				}
			}
		}
	}
	
	@Override
	public void changeEtatLiaison(Long idtLiaison, String codeEtatLiaison) {
		Optional<Liaison> liaisonOptional= liaisonRepository.findById(idtLiaison);
		if(liaisonOptional.isPresent()) {
			Liaison  liaison= liaisonOptional .get();
			EtatLiaison etatLiaison = etatLiaisonRepository.findByCode(codeEtatLiaison);
			liaison.setEtatLiaison(etatLiaison);
			liaisonRepository.save(liaison);
		}
	}
	
	@Override
	public List<DemandeListDto> getAllDemandesTravauxProgrammeeByCriteres(CriteriaCommandeDto critDto)
			throws ParseException {

		List<DemandeListDto> dtos = new ArrayList<>();

		Sort sort = Sort.by(Sort.Direction.DESC, "idt");
		Pageable pageable = PageRequest.of(critDto.getStart(), critDto.getEnd(), sort);
		List<Demande> demandes = null;

		List<Long> idsEtatDemande = null;

		String typeDemande = "".equals(critDto.getCodeTypeDemande()) ? null : critDto.getCodeTypeDemande();
		String etatDemande = "".equals(critDto.getCodeEtatDemande()) ? null : critDto.getCodeEtatDemande();

		if (critDto.getDateDebut() != null && !"".equals(critDto.getDateDebut()) && critDto.getDateFin() != null
				&& !"".equals(critDto.getDateFin())) {

			String dDebut = critDto.getDateDebut() + " 00:00:00";
			String dFin = critDto.getDateFin() + " 23:59:00";
			SimpleDateFormat frenchFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			Date date1 = frenchFormat.parse(dDebut);
			Date date2 = frenchFormat.parse(dFin);

			demandes = demandeRepository.findDemandesTravauxProgrammeeByParamsDates(date1, date2, critDto.getIdDr(),
					critDto.getIdDc(), critDto.getIdCommune(), typeDemande, critDto.getIdEtatLiaison(), etatDemande,
					critDto.getIdOperateur(), critDto.getReference(), idsEtatDemande, pageable);

		} else {
			demandes = demandeRepository.findDemandesTravauxProgrammeeByParams(critDto.getIdDr(), critDto.getIdDc(),
					critDto.getIdCommune(), typeDemande, critDto.getIdEtatLiaison(), etatDemande,
					critDto.getIdOperateur(), critDto.getReference(), idsEtatDemande, pageable);
		}

		if (demandes != null && !demandes.isEmpty()) {
			dtos = mapperDemande.toDtosDemandes(demandes);
		}

		traceServiceImpl.traceOperation(ActionCode.CONSULTATION,
				"Récupérer la liste des demandes de taille: " + dtos.size() + " par Opérateur ayant IDT: "
						+ critDto.getIdOperateur() + ", par date début: " + critDto.getDateDebut() + ", par date fin: "
						+ critDto.getDateFin() + ", par DR ayant IDT: " + critDto.getIdDr() + ", par DC ayant IDT: "
						+ critDto.getIdDc() + ", par Commune ayant IDT: " + critDto.getIdCommune()
						+ ", par Type Demande : " + critDto.getCodeTypeDemande() + ", par Etat Demande ayant IDT: "
						+ critDto.getCodeEtatDemande() + ", par Etat Liaison ayant IDT: " + critDto.getIdEtatLiaison()
						+ " et par Référence: " + critDto.getReference(),
				"Récupérer la liste des demandes de taille: " + dtos.size() + " par Opérateur ayant IDT: "
						+ critDto.getIdOperateur() + ", par date début: " + critDto.getDateDebut() + ", par date fin: "
						+ critDto.getDateFin() + ", par DR ayant IDT: " + critDto.getIdDr() + ", par DC ayant IDT: "
						+ critDto.getIdDc() + ", par Commune ayant IDT: " + critDto.getIdCommune()
						+ ", par Type Demande : " + critDto.getCodeTypeDemande() + ", par Etat Demande ayant IDT: "
						+ critDto.getCodeEtatDemande() + ", par Etat Liaison ayant IDT: " + critDto.getIdEtatLiaison()
						+ " et par Référence: " + critDto.getReference(),
				"CONSULTATION", "Commande", null, null);

		logger.info(Utils.getLogParam() + "Récupérer la liste des demandes de taille: " + dtos.size()
				+ " par Opérateur ayant IDT: " + critDto.getIdOperateur() + ", par date début: "
				+ critDto.getDateDebut() + ", par date fin: " + critDto.getDateFin() + ", par DR ayant IDT: "
				+ critDto.getIdDr() + ", par DC ayant IDT: " + critDto.getIdDc() + ", par Commune ayant IDT: "
				+ critDto.getIdCommune() + ", par Type Demande : " + critDto.getCodeTypeDemande()
				+ ", par Etat Demande ayant IDT: " + critDto.getCodeEtatDemande() + ", par Etat Liaison ayant IDT: "
				+ critDto.getIdEtatLiaison() + " et par Référence: " + critDto.getReference());
		return dtos;
	}

	@Override
	public Long getTotalDemandesTravauxProgrammeByCriteres(CriteriaCommandeDto critDto) throws ParseException {

		Long total = null;

		List<Long> idsEtatDemande = null;

		String typeDemande = "".equals(critDto.getCodeTypeDemande()) ? null : critDto.getCodeTypeDemande();
		String etatDemande = "".equals(critDto.getCodeEtatDemande()) ? null : critDto.getCodeEtatDemande();

		if (critDto.getDateDebut() != null && !"".equals(critDto.getDateDebut()) && critDto.getDateFin() != null
				&& !"".equals(critDto.getDateFin())) {

			String dDebut = critDto.getDateDebut() + " 00:00:00";
			String dFin = critDto.getDateFin() + " 23:59:00";
			SimpleDateFormat frenchFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			Date date1 = frenchFormat.parse(dDebut);
			Date date2 = frenchFormat.parse(dFin);

			total = demandeRepository.countDemandesTravauxProgrammeByParamsDates(date1, date2, critDto.getIdDr(),
					critDto.getIdDc(), critDto.getIdCommune(), typeDemande, critDto.getIdEtatLiaison(), etatDemande,
					critDto.getIdOperateur(), critDto.getReference(), idsEtatDemande);

		} else {
			total = demandeRepository.countDemandesTravauxProgrammeByParams(critDto.getIdDr(), critDto.getIdDc(),
					critDto.getIdCommune(), typeDemande, critDto.getIdEtatLiaison(), etatDemande,
					critDto.getIdOperateur(), critDto.getReference(), idsEtatDemande);
		}

		traceServiceImpl.traceOperation(ActionCode.CONSULTATION,
				"Récupération le total des Demandes : " + total + " par Opérateur ayant IDT: "
						+ critDto.getIdOperateur() + ", par date début: " + critDto.getDateDebut() + ", par date fin: "
						+ critDto.getDateFin() + ", par DR ayant IDT: " + critDto.getIdDr() + ", par DC ayant IDT: "
						+ critDto.getIdDc() + ", par Commune ayant IDT: " + critDto.getIdCommune()
						+ ", par Type Demande : " + critDto.getCodeTypeDemande() + ", par Etat Demande ayant IDT: "
						+ critDto.getCodeEtatDemande() + ", par Etat Liaison ayant IDT: " + critDto.getIdEtatLiaison()
						+ " et par Référence: " + critDto.getReference(),
				"Récupération le total des Demandes : " + total + " par Opérateur ayant IDT: "
						+ critDto.getIdOperateur() + ", par date début: " + critDto.getDateDebut() + ", par date fin: "
						+ critDto.getDateFin() + ", par DR ayant IDT: " + critDto.getIdDr() + ", par DC ayant IDT: "
						+ critDto.getIdDc() + ", par Commune ayant IDT: " + critDto.getIdCommune()
						+ ", par Type Demande : " + critDto.getCodeTypeDemande() + ", par Etat Demande ayant IDT: "
						+ critDto.getCodeEtatDemande() + ", par Etat Liaison ayant IDT: " + critDto.getIdEtatLiaison()
						+ " et par Référence: " + critDto.getReference(),
				"CONSULTATION", "Commande", null, null);

		logger.info(Utils.getLogParam() + "Récupération le total des Demandes : " + total + " par Opérateur ayant IDT: "
				+ critDto.getIdOperateur() + ", par date début: " + critDto.getDateDebut() + ", par date fin: "
				+ critDto.getDateFin() + ", par DR ayant IDT: " + critDto.getIdDr() + ", par DC ayant IDT: "
				+ critDto.getIdDc() + ", par Commune ayant IDT: " + critDto.getIdCommune() + ", par Type Demande : "
				+ critDto.getCodeTypeDemande() + ", par Etat Demande ayant IDT: " + critDto.getCodeEtatDemande()
				+ ", par Etat Liaison ayant IDT: " + critDto.getIdEtatLiaison() + " et par Référence: "
				+ critDto.getReference());
		return total;
	}
}
