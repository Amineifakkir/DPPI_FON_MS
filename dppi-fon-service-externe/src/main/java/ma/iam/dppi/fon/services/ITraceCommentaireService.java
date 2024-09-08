package ma.iam.dppi.fon.services;

import java.text.ParseException;
import java.util.List;

import org.springframework.stereotype.Service;

import ma.iam.dppi.fon.dtos.TraceCommentaireDto;

@Service
public interface ITraceCommentaireService {

	TraceCommentaireDto save(TraceCommentaireDto traceCommentaireDto) throws ParseException;
	
	public List<TraceCommentaireDto> getCommentaireByDemande(Long idtDemande) throws ParseException;
}
