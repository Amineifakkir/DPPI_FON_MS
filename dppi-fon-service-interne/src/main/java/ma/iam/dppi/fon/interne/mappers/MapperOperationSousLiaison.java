package ma.iam.dppi.fon.interne.mappers;

import java.util.Optional;

import ma.iam.dppi.fon.communs.domain.Utilisateur;
import ma.iam.dppi.fon.communs.repository.UtilisateurRepository;
import ma.iam.dppi.fon.domain.Demande;
import ma.iam.dppi.fon.domain.EtatDemandeSousLiaison;
import ma.iam.dppi.fon.domain.OperationSousLiaison;
import ma.iam.dppi.fon.domain.SousLiaison;
import ma.iam.dppi.fon.interne.dtos.SousLiaisonDto;
import ma.iam.dppi.fon.interne.utils.DateUtils;
import ma.iam.dppi.fon.repository.DemandeRepository;
import ma.iam.dppi.fon.repository.EtatDemandeSousLiaisonRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * 
 * @author Z.BELGHAOUTI
 * 
 */
@Service
public class MapperOperationSousLiaison {
	
	@Autowired
	private EtatDemandeSousLiaisonRepository etatDemandeSousLiaisonRepository;
	
	@Autowired
	private DemandeRepository demandeRepository;
	
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	
	public OperationSousLiaison toSaveDomain(SousLiaisonDto dto, SousLiaison troncon) {
		OperationSousLiaison t = null;
		if (dto != null) {
			t = new OperationSousLiaison();
			t.setCommentaire(dto.getCommentaire());
			if (dto.getDateOperation() != null) {
				t.setDateAffectation(DateUtils.dateFromDateString(dto.getDateOperation()));
			}
			if (dto.getDemandeIdt() != null) {
				Optional<Demande> optional = demandeRepository.findById(dto.getDemandeIdt());
				if (optional.isPresent()) {
					t.setDemande(optional.get());
				}
			}
			String login = SecurityContextHolder.getContext().getAuthentication().getName();
			Utilisateur utilisateur = utilisateurRepository.findByLogin(login);
			if (utilisateur != null) {
				t.setDemandeurLogin(utilisateur.getLogin());
				t.setDemandeurNom(utilisateur.getNom());
				t.setDemandeurPrenom(utilisateur.getPrenom());
			}
			EtatDemandeSousLiaison etat = etatDemandeSousLiaisonRepository.findByCode("PENDING");
			if (etat != null) {
				t.setEtatDemandeSousLiaison(etat);
			}
			t.setSousLiaison(troncon);
		}
		return t;
	}
}
