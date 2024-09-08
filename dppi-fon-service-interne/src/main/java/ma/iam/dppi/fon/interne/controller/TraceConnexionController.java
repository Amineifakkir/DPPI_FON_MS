package ma.iam.dppi.fon.interne.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ma.iam.dppi.fon.interne.dtos.CriteriaTraceConnexDto;
import ma.iam.dppi.fon.interne.dtos.TraceConnexDto;
import ma.iam.dppi.fon.interne.services.ITracabiliteConnexionService;

/**
 * @author Z.BELGHAOUTI
 *
 */
@RequestMapping("/services/trace-connexion")
@RestController
public class TraceConnexionController {

	@Autowired
	private ITracabiliteConnexionService tracabiliteConnexionService;
	
	
	@PostMapping("/getAllTraceConnexionByCriteres")
	public List<TraceConnexDto> getAllTraceConnexionByCriteres(@RequestBody CriteriaTraceConnexDto params) throws ParseException {
		return tracabiliteConnexionService.getAllTraceConnexionByCriteres(params);
	}
	
	@PostMapping("/getTotalTraceConnexionByCriteres")
	public Long getTotalTraceConnexionByCriteres(@RequestBody CriteriaTraceConnexDto params) throws ParseException {
		return tracabiliteConnexionService.getTotalTraceConnexionByCriteres(params);
	}
}
