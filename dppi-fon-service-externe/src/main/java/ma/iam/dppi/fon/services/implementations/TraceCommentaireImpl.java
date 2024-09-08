package ma.iam.dppi.fon.services.implementations;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ma.iam.dppi.fon.domain.Demande;
import ma.iam.dppi.fon.domain.TraceCommentaire;
import ma.iam.dppi.fon.dtos.TraceCommentaireDto;
import ma.iam.dppi.fon.mappers.MapperTraceComentaire;
import ma.iam.dppi.fon.repository.DemandeRepository;
import ma.iam.dppi.fon.repository.TraceCommentaireRepository;
import ma.iam.dppi.fon.services.ITraceCommentaireService;

@Service
@Transactional
public class TraceCommentaireImpl implements ITraceCommentaireService {

	@Autowired
	private MapperTraceComentaire mapper;

	@Autowired
	private TraceCommentaireRepository repo;

	@Autowired
	private DemandeRepository demandeRepo;
	


	@Override
	public TraceCommentaireDto save(TraceCommentaireDto traceCommentaireDto) throws ParseException {
//		demandeGc = liaisonAddMapper.entityDemandeToDto(demandeRepository.save(liaisonAddMapper.dtoToEntityDemande(demandeGcDto)));

//		TraceCommentaire traceCommntaire = repo.findOne(traceCommentaireDto.getDemandeDto().getIdt());
//		trace = mapperDemande.entityTraceToDto(demandeRepository.save(mapperDemande.dtoToEnityTrace(traceCommentaireDto)));
//		Optional<Demande> demandeoOptional=demandeRepo.findById(traceCommentaireDto.getIdt());
//		if(demandeoOptional.isPresent()) {
//			Demande demande= demandeoOptional.get();
//			
//		}
		return mapper.entityToDto(repo.save(mapper.dtoToEntity(traceCommentaireDto)));
	}

	@Override
	public List<TraceCommentaireDto> getCommentaireByDemande(Long idtDemande) throws ParseException {
		List<TraceCommentaireDto> commentaireDtos = null;
		if (idtDemande != null) {
			Demande demande = null;
			Optional<Demande> optional = demandeRepo.findById(idtDemande);
			if (optional.isPresent()) {
				demande = optional.get();
			}
			if (demande == null) {
				return null;
			}
			commentaireDtos= new ArrayList<>();
			List<TraceCommentaire> commentaires = repo.getListTraceCommentaireByDemandeIdt(demande.getIdt());
			 commentaireDtos = mapper.entityListToDtoList(commentaires);

			
		}
		return commentaireDtos;
	}

}
