package ma.iam.dppi.fon.interne.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ma.iam.dppi.fon.interne.dtos.ParametreBoDto;
import ma.iam.dppi.fon.interne.dtos.ParametresDto;
import ma.iam.dppi.fon.interne.dtos.ParametresStrDto;
import ma.iam.dppi.fon.interne.services.IParametrageService;

/**
 * @author Z.BELGHAOUTI
 *
 */
@RequestMapping("/services/parametrages")
@RestController
public class ParametrageController {

	@Autowired
	private IParametrageService parametrageService;
	
	@GetMapping("/getAllParameters")
	public ParametresDto getAllParameters() {
		return parametrageService.getAllParameters();
	}
	
	@GetMapping("/getParameterByCode")
	public List<ParametreBoDto> getParameterByCode(@RequestParam("code") String code) {
		return parametrageService.getParameterByCode(code);
	}
	
	@PostMapping("/changeParameter")
	public Long changeParameter(@RequestBody ParametreBoDto params) {
		return parametrageService.changeParameter(params);
	}
	
	@PostMapping("/changeParameters")
	public Long changeParameters(@RequestBody ParametresStrDto params) {
		return parametrageService.changeParameters(params);
	}
	
	@GetMapping("/resetToDefaultSettings")
	public Long resetToDefaultSettings() {
		return parametrageService.resetToDefaultSettings();
	}
	
}
