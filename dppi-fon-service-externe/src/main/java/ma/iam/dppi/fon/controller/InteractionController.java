package ma.iam.dppi.fon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ma.iam.dppi.fon.dtos.InteractionDto;
import ma.iam.dppi.fon.services.IinteractionService;


@RequestMapping("/services/interaction")
@RestController
public class InteractionController {

	@Autowired
	private IinteractionService interactionService;
	
	@PostMapping("/addInteraction")
	public InteractionDto saveInteraction(@RequestBody InteractionDto interactionDto) {
		return interactionService.saveInteraction(interactionDto);
	}
	
	@PutMapping("/update")
	public InteractionDto updateIteraction(@RequestBody InteractionDto interactionDto) {
		return interactionService.updateInteraction(interactionDto);
	}

	
	@GetMapping("/listInteraction")
	public List<InteractionDto> getAllInteractions() {
		return interactionService.getAllInteractions();
	}

	
}
