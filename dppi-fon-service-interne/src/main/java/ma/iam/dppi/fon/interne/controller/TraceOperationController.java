package ma.iam.dppi.fon.interne.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ma.iam.dppi.fon.interne.dtos.CriteriaTraceConnexDto;
import ma.iam.dppi.fon.interne.dtos.TraceOperationDto;
import ma.iam.dppi.fon.interne.services.ITracabiliteOperationService;

/**
 * @author Z.BELGHAOUTI
 *
 */
@RequestMapping("/services/trace-operation")
@RestController
public class TraceOperationController {

	@Autowired
	private ITracabiliteOperationService tracabiliteOperationService;
	
	
	@PostMapping("/getAllTraceOperationByCriteres")
	public List<TraceOperationDto> getAllTraceOperationByCriteres(@RequestBody CriteriaTraceConnexDto params) throws ParseException {
		return tracabiliteOperationService.getAllTraceOperationByCriteres(params);
	}
	
	@PostMapping("/getTotalTraceOperationByCriteres")
	public Long getTotalTraceOperationByCriteres(@RequestBody CriteriaTraceConnexDto params) throws ParseException {
		return tracabiliteOperationService.getTotalTraceOperationByCriteres(params);
	}
}
