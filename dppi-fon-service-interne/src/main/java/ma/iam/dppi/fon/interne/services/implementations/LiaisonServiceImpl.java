package ma.iam.dppi.fon.interne.services.implementations;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ma.iam.dppi.fon.constants.ActionCode;
import ma.iam.dppi.fon.domain.Demande;
import ma.iam.dppi.fon.domain.Interaction;
import ma.iam.dppi.fon.domain.Liaison;
import ma.iam.dppi.fon.domain.SousLiaison;
import ma.iam.dppi.fon.domain.TraceCommentaire;
import ma.iam.dppi.fon.domain.TraceCommentaireSousLiaison;
import ma.iam.dppi.fon.domain.TraceEtatDemande;
import ma.iam.dppi.fon.domain.TraceEtatDemandeSousLiaison;
import ma.iam.dppi.fon.interne.dtos.CommandeListDto;
import ma.iam.dppi.fon.interne.dtos.CriteriaCommandeDto;
import ma.iam.dppi.fon.interne.dtos.HistoriqueDemandeDto;
import ma.iam.dppi.fon.interne.dtos.HistoriqueTronconDto;
import ma.iam.dppi.fon.interne.dtos.InteractionDto;
import ma.iam.dppi.fon.interne.dtos.LiaisonDto;
import ma.iam.dppi.fon.interne.dtos.TraceCommentaireDto;
import ma.iam.dppi.fon.interne.dtos.TraceCommentaireSousLiaisonDto;
import ma.iam.dppi.fon.interne.dtos.TraceEtatDto;
import ma.iam.dppi.fon.interne.dtos.TraceEtatSousLiaisonDto;
import ma.iam.dppi.fon.interne.mappers.MapperInteraction;
import ma.iam.dppi.fon.interne.mappers.MapperLiaison;
import ma.iam.dppi.fon.interne.mappers.MapperLiaisonList;
import ma.iam.dppi.fon.interne.mappers.MapperTraceCommentaire;
import ma.iam.dppi.fon.interne.mappers.MapperTraceCommentaireSousLiaison;
import ma.iam.dppi.fon.interne.mappers.MapperTraceEtat;
import ma.iam.dppi.fon.interne.mappers.MapperTraceEtatTroncon;
import ma.iam.dppi.fon.interne.services.ILiaisonService;
import ma.iam.dppi.fon.interne.services.ITraceService;
import ma.iam.dppi.fon.interne.utils.Utils;
import ma.iam.dppi.fon.repository.DemandeRepository;
import ma.iam.dppi.fon.repository.InteractionRepository;
import ma.iam.dppi.fon.repository.LiaisonRepository;
import ma.iam.dppi.fon.repository.SousLiaisonRepository;
import ma.iam.dppi.fon.repository.TraceCommentaireRepository;
import ma.iam.dppi.fon.repository.TraceCommentaireSousLiaisonRepository;
import ma.iam.dppi.fon.repository.TraceEtatDemandeRepository;
import ma.iam.dppi.fon.repository.TraceEtatDemandeSousLiaisonRepository;

/**
 * 
 * @author Z.BELGHAOUTI
 *
 */
@Service
@Transactional
public class LiaisonServiceImpl implements ILiaisonService {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private LiaisonRepository liaisonDaoImpl;
	
	@Autowired
	private MapperLiaison liaisonMapper;
	
	@Autowired
	private ITraceService traceServiceImpl;

	@Autowired
	private DemandeRepository demandeRepository;
	
	@Autowired
	private TraceCommentaireRepository traceCommentaireRepository;
	
	@Autowired
	private TraceEtatDemandeRepository traceEtatDemandeRepository;
	
	@Autowired
	private TraceCommentaireSousLiaisonRepository traceCommentaireSousLiaisonRepository;
	
	@Autowired
	private TraceEtatDemandeSousLiaisonRepository traceEtatDemandeSousLiaisonRepository;
	
	@Autowired
	private InteractionRepository interactionRepository;
	
	@Autowired
	private SousLiaisonRepository sousLiaisonRepository;
	
	@Autowired
	private MapperLiaisonList mapperLiaison;
	
	@Autowired
	private MapperInteraction mapperInteraction;
	
	@Autowired
	private MapperTraceCommentaire mapperTraceCommentaire;
	
	@Autowired
	private MapperTraceEtat mapperTraceEtat;
	
	@Autowired
	private MapperTraceCommentaireSousLiaison mapperTraceCommentaireSousLiaison;
	
	@Autowired
	private MapperTraceEtatTroncon mapperTraceEtatTroncon;
	
	
	/**
	 * GET ALL LIAISONS BY CRITERES
	 * @throws ParseException 
	 */
	public List<CommandeListDto> getAllCommandesByCriteres(CriteriaCommandeDto critDto) throws ParseException{
		
		List<CommandeListDto> dtos = new ArrayList<>();
		
		Sort sort = Sort.by(Sort.Direction.DESC, "idt");
		Pageable pageable = PageRequest.of(critDto.getStart(), critDto.getEnd(), sort);
		List<Liaison> liaisons = null;
		
		List<Long> idsEtatDemande = null;
		if (critDto.getDateDebut() != null && !"".equals(critDto.getDateDebut()) 
				&& critDto.getDateFin() != null && !"".equals(critDto.getDateFin())) {
			
			String dDebut = critDto.getDateDebut() + " 00:00:00";
			String dFin = critDto.getDateFin() + " 23:59:00";
			SimpleDateFormat frenchFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			Date date1 = frenchFormat.parse(dDebut);
			Date date2 = frenchFormat.parse(dFin);
			
			liaisons = liaisonDaoImpl.findLiaisonInterneByParamsDates(date1, date2, critDto.getIdDr(), 
					critDto.getIdDc(), critDto.getIdCommune(), critDto.getIdTypeDemande(), 
					critDto.getIdEtatLiaison(), critDto.getIdEtatDemande(), critDto.getIdOperateur(), 
					critDto.getReference(), idsEtatDemande,critDto.getListIdEtatDemandes() ,pageable);
			
		} else {
			liaisons = liaisonDaoImpl.findLiaisonInterneByParams(critDto.getIdDr(), 
					critDto.getIdDc(), critDto.getIdCommune(), critDto.getIdTypeDemande(), 
					critDto.getIdEtatLiaison(), critDto.getIdEtatDemande(), critDto.getIdOperateur(), 
					critDto.getReference(), idsEtatDemande,critDto.getListIdEtatDemandes(), pageable);
		}
			
		if(liaisons != null && !liaisons.isEmpty()) {
			dtos = mapperLiaison.toDtos(liaisons);
		}
		
		traceServiceImpl.traceOperation(ActionCode.CONSULTATION, 
				"Récupérer la liste des liaisons de taille: " 
						+ dtos.size() + " par Opérateur ayant IDT: " + critDto.getIdOperateur()
						+ ", par date début: " + critDto.getDateDebut()
						+ ", par date fin: " + critDto.getDateFin()
						+ ", par DR ayant IDT: " + critDto.getIdDr()
						+ ", par DC ayant IDT: " + critDto.getIdDc()
						+ ", par Commune ayant IDT: " + critDto.getIdCommune()
						+ ", par Type Demande ayant IDT: " + critDto.getIdTypeDemande()
						+ ", par Etat Demande ayant IDT: " + critDto.getIdEtatDemande()
						+ ", par Etat Liaison ayant IDT: " + critDto.getIdEtatLiaison()
						+ " et par Référence: " + critDto.getReference(), 
				"Récupérer la liste des liaisons de taille: " 
						+ dtos.size() + " par Opérateur ayant IDT: " + critDto.getIdOperateur()
						+ ", par date début: " + critDto.getDateDebut()
						+ ", par date fin: " + critDto.getDateFin()
						+ ", par DR ayant IDT: " + critDto.getIdDr()
						+ ", par DC ayant IDT: " + critDto.getIdDc()
						+ ", par Commune ayant IDT: " + critDto.getIdCommune()
						+ ", par Type Demande ayant IDT: " + critDto.getIdTypeDemande()
						+ ", par Etat Demande ayant IDT: " + critDto.getIdEtatDemande()
						+ ", par Etat Liaison ayant IDT: " + critDto.getIdEtatLiaison()
						+ " et par Référence: " + critDto.getReference(),
				"CONSULTATION", "Commande", null, null);
		
		logger.info(Utils.getLogParam()
				+ "Récupérer la liste des liaisons de taille: " + dtos.size() 
				+ " par Opérateur ayant IDT: " + critDto.getIdOperateur()
				+ ", par date début: " + critDto.getDateDebut()
				+ ", par date fin: " + critDto.getDateFin()
				+ ", par DR ayant IDT: " + critDto.getIdDr()
				+ ", par DC ayant IDT: " + critDto.getIdDc()
				+ ", par Commune ayant IDT: " + critDto.getIdCommune()
				+ ", par Type Demande ayant IDT: " + critDto.getIdTypeDemande()
				+ ", par Etat Demande ayant IDT: " + critDto.getIdEtatDemande()
				+ ", par Etat Liaison ayant IDT: " + critDto.getIdEtatLiaison()
				+ " et par Référence: " + critDto.getReference());
		return dtos;
	}
	
	/**
	 * COUNT LIAISONS BY CRITERES
	 * @throws ParseException 
	 */
	public Long getTotalCommandesByCriteres(CriteriaCommandeDto critDto) throws ParseException {
		
		Long total = null;
		
		List<Long> idsEtatDemande = null;
			
		if (critDto.getDateDebut() != null && !"".equals(critDto.getDateDebut()) 
				&& critDto.getDateFin() != null && !"".equals(critDto.getDateFin())) {
			
			String dDebut = critDto.getDateDebut() + " 00:00:00";
			String dFin = critDto.getDateFin() + " 23:59:00";
			SimpleDateFormat frenchFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			Date date1 = frenchFormat.parse(dDebut);
			Date date2 = frenchFormat.parse(dFin);
			
			total = liaisonDaoImpl.countLiaisonInterneByParamsDates(date1, date2, critDto.getIdDr(), 
					critDto.getIdDc(), critDto.getIdCommune(), critDto.getIdTypeDemande(), 
					critDto.getIdEtatLiaison(), critDto.getIdEtatDemande(), critDto.getIdOperateur(), 
					critDto.getReference(), idsEtatDemande);
			
		} else {
			total = liaisonDaoImpl.countLiaisonInterneByParams(critDto.getIdDr(), 
					critDto.getIdDc(), critDto.getIdCommune(), critDto.getIdTypeDemande(), 
					critDto.getIdEtatLiaison(), critDto.getIdEtatDemande(), critDto.getIdOperateur(), 
					critDto.getReference(), idsEtatDemande);
		}
			
		
		traceServiceImpl.traceOperation(ActionCode.CONSULTATION, 
				"Récupération le total des Liaisons : " + total
				+ " par Opérateur ayant IDT: " + critDto.getIdOperateur()
				+ ", par date début: " + critDto.getDateDebut()
				+ ", par date fin: " + critDto.getDateFin()
				+ ", par DR ayant IDT: " + critDto.getIdDr()
				+ ", par DC ayant IDT: " + critDto.getIdDc()
				+ ", par Commune ayant IDT: " + critDto.getIdCommune()
				+ ", par Type Demande ayant IDT: " + critDto.getIdTypeDemande()
				+ ", par Etat Demande ayant IDT: " + critDto.getIdEtatDemande()
				+ ", par Etat Liaison ayant IDT: " + critDto.getIdEtatLiaison()
				+ " et par Référence: " + critDto.getReference(), 
				"Récupération le total des Liaisons : " + total
				+ " par Opérateur ayant IDT: " + critDto.getIdOperateur()
				+ ", par date début: " + critDto.getDateDebut()
				+ ", par date fin: " + critDto.getDateFin()
				+ ", par DR ayant IDT: " + critDto.getIdDr()
				+ ", par DC ayant IDT: " + critDto.getIdDc()
				+ ", par Commune ayant IDT: " + critDto.getIdCommune()
				+ ", par Type Demande ayant IDT: " + critDto.getIdTypeDemande()
				+ ", par Etat Demande ayant IDT: " + critDto.getIdEtatDemande()
				+ ", par Etat Liaison ayant IDT: " + critDto.getIdEtatLiaison()
				+ " et par Référence: " + critDto.getReference(),
				"CONSULTATION", "Commande", null, null);
		
		logger.info(Utils.getLogParam()
				+ "Récupération le total des interventions : " + total
				+ " par Opérateur ayant IDT: " + critDto.getIdOperateur()
				+ ", par date début: " + critDto.getDateDebut()
				+ ", par date fin: " + critDto.getDateFin()
				+ ", par DR ayant IDT: " + critDto.getIdDr()
				+ ", par DC ayant IDT: " + critDto.getIdDc()
				+ ", par Commune ayant IDT: " + critDto.getIdCommune()
				+ ", par Type Demande ayant IDT: " + critDto.getIdTypeDemande()
				+ ", par Etat Demande ayant IDT: " + critDto.getIdEtatDemande()
				+ ", par Etat Liaison ayant IDT: " + critDto.getIdEtatLiaison()
				+ " et par Référence: " + critDto.getReference());	
		return total;
	}
	
	@Override
	public HistoriqueDemandeDto getHistoriquesByDemande(Long idtDemande) {
		HistoriqueDemandeDto dto = null;
		if (idtDemande != null) {
			Demande demande = null;
			Optional<Demande> optional = demandeRepository.findById(idtDemande);
			if (optional.isPresent()) {
				demande = optional.get();
			}
			if (demande == null) {
				return dto;
			}
			dto = new HistoriqueDemandeDto();
			
			List<TraceCommentaire> commentaires = traceCommentaireRepository.getListTraceCommentaireByDemandeIdt(demande.getIdt());
			List<TraceCommentaireDto> commentaireDtos = mapperTraceCommentaire.toDtos(commentaires);
			dto.setListTraceCommentaire(commentaireDtos);
			
			List<TraceEtatDemande> etats = traceEtatDemandeRepository.getListTraceEtatByDemandeIdt(demande.getIdt());
			List<TraceEtatDto> etatDtos = mapperTraceEtat.toDtos(etats);
			dto.setListTraceEtat(etatDtos);
			
			List<Interaction> interactions = interactionRepository.getListInteractionByDemandeIdt(demande.getIdt(), null);
			List<InteractionDto> interactionDtos = mapperInteraction.toDtos(interactions);
			dto.setListInteraction(interactionDtos);
		}
		return dto;
	}
	
	@Override
	public HistoriqueTronconDto getHistoriquesByTroncon(Long idtTroncon) {
		HistoriqueTronconDto dto = null;
		if (idtTroncon != null) {
			SousLiaison sousLiaison = null;
			Optional<SousLiaison> optional = sousLiaisonRepository.findById(idtTroncon);
			if (optional.isPresent()) {
				sousLiaison = optional.get();
			}
			if (sousLiaison == null) {
				return dto;
			}
			dto = new HistoriqueTronconDto();
			
			List<TraceCommentaireSousLiaison> commentaires = traceCommentaireSousLiaisonRepository.getTraceCommentBySousLiaison(sousLiaison.getIdt());
			List<TraceCommentaireSousLiaisonDto> commentaireDtos = mapperTraceCommentaireSousLiaison.toDtos(commentaires);
			dto.setListTraceCommentaire(commentaireDtos);
			
			List<TraceEtatDemandeSousLiaison> etats = traceEtatDemandeSousLiaisonRepository.getTraceEtatBySousLiaison(sousLiaison.getIdt());
			List<TraceEtatSousLiaisonDto> etatDtos = mapperTraceEtatTroncon.toDtos(etats);
			dto.setListTraceEtat(etatDtos);
			
		}
		return dto;
	}

	@Override
	public LiaisonDto updateLiaison(LiaisonDto liaisonDto) {
		Liaison savedLiaison = null;
		Liaison liaison = null;
		if(liaisonDto != null) {
			
			Optional<Liaison> liaisonOptional = liaisonDaoImpl.findById(liaisonDto.getIdt());
			if(liaisonOptional.isPresent()) {
				liaison = liaisonOptional.get();
				liaison.setCodeSiteA(liaisonDto.getCodeSiteA());
				liaison.setCodeSiteB(liaisonDto.getCodeSiteB());
				liaison.setxGpsArrivee(liaisonDto.getxGpsArrivee());
				liaison.setyGpsArrivee(liaisonDto.getyGpsArrivee());
				liaison.setxGpsDepart(liaisonDto.getxGpsDepart());
				liaison.setyGpsDepart(liaisonDto.getxGpsDepart());
			}
			savedLiaison = liaisonDaoImpl.save(liaison);
			
		}
		return liaisonMapper.toDtoLiaison(savedLiaison);
	}
}
