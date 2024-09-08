package ma.iam.dppi.fon.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ma.iam.dppi.fon.dtos.EscaladeDto;
import ma.iam.dppi.fon.services.IEscaladeService;


@RequestMapping("/services/escalade")
@RestController
public class EscaladeController {

	@Autowired
	private IEscaladeService escaladeService;
	
	@PostMapping("/addEscalade")
	public EscaladeDto saveEscalade(@RequestBody EscaladeDto escaladeDto) throws ParseException{
		return escaladeService.saveEscalade(escaladeDto);
	}
	
	
	
}
