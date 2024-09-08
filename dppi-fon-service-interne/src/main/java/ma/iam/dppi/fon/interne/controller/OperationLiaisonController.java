package ma.iam.dppi.fon.interne.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ma.iam.dppi.fon.interne.dtos.OperationLiaisonDto;
import ma.iam.dppi.fon.interne.services.IOperationLiaisonService;

@RestController
@RequestMapping("/services/operationLiaison")
public class OperationLiaisonController {

	@Autowired
	private IOperationLiaisonService service;
	
	@PostMapping("/saveOpertionLiaison")
	public OperationLiaisonDto saveOperationLiaison(@RequestBody OperationLiaisonDto dto) {
		return service.saveOperationLiaison(dto);
	}
}
