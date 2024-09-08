package ma.iam.dppi.fon.mappers;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import ma.iam.dppi.fon.communs.domain.Parametrage;
import ma.iam.dppi.fon.communs.repository.ParametrageRepository;
import ma.iam.dppi.fon.domain.Demande;
import ma.iam.dppi.fon.domain.TraceCommentaire;
import ma.iam.dppi.fon.dtos.LiaisonDemandeUppdateDto;
import ma.iam.dppi.fon.dtos.TraceCommentaireDto;
import ma.iam.dppi.fon.repository.DemandeRepository;
import ma.iam.dppi.fon.repository.TraceCommentaireRepository;
import ma.iam.dppi.utils.Utils;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MapperTraceComentaire {

	@Autowired
	private DozerBeanMapper mapper;

	@Autowired
	private TraceCommentaireRepository traceRepo;

	@Autowired
	MapperDemande mapperDemande;
	
	@Autowired
	ParametrageRepository parametrageRepository;

	@Autowired
	DemandeRepository demandeRepository;

	public TraceCommentaireDto entityToDto(TraceCommentaire c) throws ParseException {
		TraceCommentaireDto dto = null;
		if (c != null) {
			dto = new TraceCommentaireDto();

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
			dto.setIdt(c.getIdt());
			dto.setCommentaire(c.getCommentaire());
//			if (c.getDateCommentaire() != null) {
				dto.setDateCommentaire(now);
//			}
			dto.setDemandeurLogin(c.getDemandeurLogin());
			dto.setDemandeurNom(c.getDemandeurNom());
			dto.setDemandeurPrenom(c.getDemandeurPrenom());
			Optional<Demande> deOptional = demandeRepository.findById(c.getDemande().getIdt());
			if (deOptional.isPresent()) {
				dto.setDemandeDto(mapperDemande.entityToDto(deOptional.get()));

			}

			dto.setEntite(c.getEntite());
		}
		return dto;
	}

	public LiaisonDemandeUppdateDto entityToDtoLiaison(TraceCommentaire traceCommentaire) {

		if (traceCommentaire == null) {
			return null;
		}
		LiaisonDemandeUppdateDto dto = new LiaisonDemandeUppdateDto();
		dto.setIdt(traceCommentaire.getIdt());

		TraceCommentaire trace = new TraceCommentaire();
		if (trace.getIdt() != null) {
			dto.setIdt(trace.getIdt());
			dto.setCommentaire(trace.getCommentaire());
			dto.setDateCommentaire(trace.getDateCommentaire());
			dto.getDemandeDto().setIdt(trace.getDemande().getIdt());
		}
		return dto;

	}

	public TraceCommentaire dtoLiaisonToEntity(LiaisonDemandeUppdateDto dto) throws ParseException {

		if (dto == null) {
			return null;
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
		TraceCommentaire trace = new TraceCommentaire();
		Optional<TraceCommentaire> traceOptional = traceRepo.findById(dto.getIdtDemande());
		if (traceOptional.isPresent()) {
			trace = traceOptional.get();

			trace.setIdt(dto.getIdt());
			trace.setCommentaire(dto.getCommentaire());
			trace.setDateCommentaire(now);
			trace.getDemande().setIdt(dto.getDemandeDto().getIdt());

		}
		return trace;

	}

	public TraceCommentaire dtoToEntity(TraceCommentaireDto traceCommentaireDto) throws ParseException {
		TraceCommentaire traceCommentaire=null;
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
		if (traceCommentaireDto != null) {
			traceCommentaire = new TraceCommentaire();
			traceCommentaire.setCommentaire(traceCommentaireDto.getCommentaire());
			traceCommentaire.setDateCommentaire(now);
			Optional<Demande> demandeoOptional=demandeRepository.findById(traceCommentaireDto.getIdtDemande());
			if(demandeoOptional.isPresent()) {
				Demande demande= demandeoOptional.get();
				traceCommentaire.setDemande(demande);
			}
			traceCommentaire.setDemandeurLogin(traceCommentaireDto.getDemandeurLogin());
			traceCommentaire.setDemandeurNom(traceCommentaireDto.getDemandeurNom());
			traceCommentaire.setDemandeurPrenom(traceCommentaireDto.getDemandeurPrenom());
		}
		
		
		
//		TraceCommentaire traceCommentaire = mapper.map(traceCommentaireDto, TraceCommentaire.class);

		return traceCommentaire;
	}

	public List<TraceCommentaireDto> entityListToDtoList(List<TraceCommentaire> traceCommentaires) throws ParseException {
		if (traceCommentaires == null || traceCommentaires.isEmpty()) {
			return null;
		}

		List<TraceCommentaireDto> traceCommentaireDto = new ArrayList<>();
		for (TraceCommentaire traceCommentaire : traceCommentaires) {
			traceCommentaireDto.add(entityToDto(traceCommentaire));
		}

		return traceCommentaireDto;
	}

	public TraceCommentaire demandeToTrace(Demande dto) {
		if (dto == null) {
			return null;
		}
		TraceCommentaire traceCommentaire = new TraceCommentaire();
		traceCommentaire.setCommentaire(dto.getCommentaire());
		traceCommentaire.setDemande(dto);
		traceCommentaire.setDemandeurLogin(dto.getLoginDemandeur());
		traceCommentaire.setDateCommentaire(dto.getDateDemande());
		traceCommentaire.setDemandeurNom(dto.getNomDemandeur());
		traceCommentaire.setDemandeurPrenom(dto.getPrenomDemandeur());
		return traceCommentaire;
	}

}
