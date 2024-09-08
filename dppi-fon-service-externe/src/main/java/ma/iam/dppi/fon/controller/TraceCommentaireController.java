package ma.iam.dppi.fon.controller;//package ma.iam.dppi.fon.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ma.iam.dppi.fon.dtos.TraceCommentaireDto;
import ma.iam.dppi.fon.services.ITraceCommentaireService;

@RequestMapping("/services/traceCommentaire")
@RestController
public class TraceCommentaireController {

	@Autowired 
    private ITraceCommentaireService service;
	
	@PostMapping("/add")
	public TraceCommentaireDto saveTraceCommentaire(@RequestBody TraceCommentaireDto traceCommentaireDto)throws ParseException {
		return service.save(traceCommentaireDto);
	}
	
	@GetMapping("/getListCommentaireByDemande/{idtDemande}")
	public List<TraceCommentaireDto> getListCommentaireByDemande(@PathVariable Long idtDemande) throws ParseException{
		return service.getCommentaireByDemande(idtDemande);
	}
}
