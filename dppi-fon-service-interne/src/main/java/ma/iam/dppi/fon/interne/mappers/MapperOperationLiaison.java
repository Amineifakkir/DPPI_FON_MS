package ma.iam.dppi.fon.interne.mappers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.iam.dppi.fon.communs.domain.Utilisateur;
import ma.iam.dppi.fon.communs.repository.UtilisateurRepository;
import ma.iam.dppi.fon.domain.Demande;
import ma.iam.dppi.fon.domain.EtatLiaison;
import ma.iam.dppi.fon.domain.Liaison;
import ma.iam.dppi.fon.domain.OperationLiason;
import ma.iam.dppi.fon.interne.dtos.OperationLiaisonDto;
import ma.iam.dppi.fon.repository.DemandeRepository;
import ma.iam.dppi.fon.repository.EtatLiaisonRepository;
import ma.iam.dppi.fon.repository.LiaisonRepository;

@Service
public class MapperOperationLiaison {

	@Autowired
	private EtatLiaisonRepository etatLiaisonRepository;

	@Autowired
	private UtilisateurRepository utilisateurRepository;

	@Autowired
	private LiaisonRepository liaisonRepository;

	@Autowired
	private DemandeRepository demandeRepository;

	public OperationLiaisonDto toDto(OperationLiason operationLiason) {
		OperationLiaisonDto operationLiaisonDto = null;
		if (operationLiason != null) {
			operationLiaisonDto = new OperationLiaisonDto();

			operationLiaisonDto.setIdt(operationLiason.getIdt());
			operationLiaisonDto.setCommentaire(operationLiason.getCommentaire());

			if (operationLiason.getDate() != null) {
				operationLiaisonDto.setDate(operationLiason.getDate());
			}

			if (operationLiason.getIdtDemandeur() != null) {
				Optional<Utilisateur> utilisateurOptional = utilisateurRepository
						.findById(operationLiason.getIdtDemandeur());
				if (utilisateurOptional.isPresent()) {
					Utilisateur utilisateur = utilisateurOptional.get();
					operationLiaisonDto.setIdtDemandeur(utilisateur.getIdt());
					operationLiaisonDto.setNomDemandeur(utilisateur.getNom());
					operationLiaisonDto.setPrenomDemandeur(utilisateur.getPrenom());
					operationLiaisonDto.setLoginDemandeur(utilisateur.getLogin());
				}

			}
			EtatLiaison etatLiason = etatLiaisonRepository.findByCode("PENDING");
			if (etatLiason != null) {
				operationLiaisonDto.setEtatLiaisonIdt(etatLiason.getIdt());
				operationLiaisonDto.setEtatLiaisonCode(etatLiason.getCode());
				operationLiaisonDto.setEtatLiaisonLabel(etatLiason.getLabel());
			}

		}
		return operationLiaisonDto;
	}

	public OperationLiason toDomain(OperationLiaisonDto dto) {
		OperationLiason operationLiason = null;
		if (dto != null) {
			operationLiason = new OperationLiason();

			operationLiason.setIdt(dto.getIdt());
			operationLiason.setCommentaire(dto.getCommentaire());

			if (dto.getDate() != null) {
				operationLiason.setDate(dto.getDate());
			}
			if (dto.getIdtDemandeur() != null) {
				Optional<Utilisateur> utilisateurOptional = utilisateurRepository.findById(dto.getIdtDemandeur());
				if (utilisateurOptional.isPresent()) {
					Utilisateur utilisateur = utilisateurOptional.get();
					operationLiason.setIdtDemandeur(utilisateur.getIdt());
					operationLiason.setNomDemandeur(utilisateur.getNom());
					operationLiason.setPrenomDemandeur(utilisateur.getPrenom());
					operationLiason.setLoginDemandeur(utilisateur.getLogin());
				}
			}
			EtatLiaison EtatLiasonOptional = etatLiaisonRepository.findByCode("PENDING");
			if (EtatLiasonOptional != null) {
				operationLiason.setEtatLiaison(EtatLiasonOptional);
			}
			if (dto.getIdtLiaison() != null) {
				Optional<Liaison> liaisonOptional = liaisonRepository.findById(dto.getIdtLiaison());
				if (liaisonOptional.isPresent()) {
					Liaison liaison = liaisonOptional.get();
					operationLiason.setLiaison(liaison);
				}
			}

			if (dto.getIdtDemande() != null) {
				Optional<Demande> demandeOptional = demandeRepository.findById(dto.getIdtDemande());
				if (demandeOptional.isPresent()) {
					Demande demande = demandeOptional.get();
					operationLiason.setDemande(demande);
				}
			}

		}
		return operationLiason;
	}
}
