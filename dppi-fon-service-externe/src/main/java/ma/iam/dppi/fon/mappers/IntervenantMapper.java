package ma.iam.dppi.fon.mappers;

import java.util.List;

import ma.iam.dppi.fon.communs.domain.Utilisateur;
import ma.iam.dppi.fon.communs.repository.UtilisateurRepository;
import ma.iam.dppi.fon.domain.Intervenant;
import ma.iam.dppi.fon.dtos.DemandeAccesCriteresDto;
import ma.iam.dppi.fon.dtos.IntervenantDto;
import ma.iam.dppi.fon.repository.IntervenantRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class IntervenantMapper {


	@Autowired
	private IntervenantRepository intervenantRepo;
	@Autowired 
	private UtilisateurRepository utilisateurRepository;
	
	
	public Intervenant dtoToEntity(DemandeAccesCriteresDto criteresDto) {
		if(criteresDto == null ) {
			return null;
		}
		Intervenant intervenants = new Intervenant();
		List<IntervenantDto> dto = criteresDto.getIntervenants();

		String login = SecurityContextHolder.getContext().getAuthentication().getName();
		Utilisateur utilisateur = utilisateurRepository.findByLogin(login);

			
			
		
		for (int i = 0; i < dto.size(); i++) {
		    IntervenantDto intervenantDto = dto.get(i);
			if(utilisateur != null) {
				 Intervenant intervenant = new Intervenant();
			intervenant.setOperateur(utilisateur.getOperateur().getCode());
			intervenant.setIdtOperateur(utilisateur.getOperateur().getIdt());
			intervenant.setErpt(utilisateur.getEntite().getLabel());
			intervenant.setNom(intervenantDto.getNom());
		    intervenant.setPrenom(intervenantDto.getPrenom());
		    intervenant.setCin(intervenantDto.getCin());
		    intervenant.setTelephone(intervenantDto.getTelephone());
		    intervenantRepo.save(intervenant);
//		    intervenants.add(intervenant);
		}
			}
		
		
		
		
		return intervenants;
	}
}
