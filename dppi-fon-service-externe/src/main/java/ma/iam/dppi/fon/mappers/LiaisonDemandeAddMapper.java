package ma.iam.dppi.fon.mappers;

import java.text.ParseException;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import ma.iam.dppi.fon.communs.domain.Commune;
import ma.iam.dppi.fon.communs.domain.Parametrage;
import ma.iam.dppi.fon.communs.domain.Utilisateur;
import ma.iam.dppi.fon.communs.repository.CommuneRepository;
import ma.iam.dppi.fon.communs.repository.ParametrageRepository;
import ma.iam.dppi.fon.communs.repository.UtilisateurRepository;
import ma.iam.dppi.fon.domain.Demande;
import ma.iam.dppi.fon.domain.EtatDemande;
import ma.iam.dppi.fon.domain.Liaison;
import ma.iam.dppi.fon.domain.TypeDemande;
import ma.iam.dppi.fon.dtos.LiaisonDemandeAddDto;
import ma.iam.dppi.fon.repository.EtatDemandeRepository;
import ma.iam.dppi.fon.repository.TypeDemandeRepository;
import ma.iam.dppi.fon.utils.Utils;

@Component
public class LiaisonDemandeAddMapper {

	@Autowired
	private CommuneRepository communeRepository;

	@Autowired
	private UtilisateurRepository utilisateurRepository;

	@Autowired
	private EtatDemandeRepository etatDemandeRepository;

	@Autowired
	private TypeDemandeRepository typeRepository;

	@Autowired
	private ParametrageRepository parametrageRepository;

	public LiaisonDemandeAddDto entityToDto(Liaison demande) {
		if (demande == null) {
			return null;
		}
		LiaisonDemandeAddDto dto = new LiaisonDemandeAddDto();
		dto.setIdt(demande.getIdt());
		//todo salman
//		dto.setCodeLiaisonErpt(demande.getCodeLiaisonErpt());

		if (demande.getIdtCommune() != null) {
			Optional<Commune> optionalCommune = communeRepository.findById(demande.getIdtCommune());
			if (optionalCommune.isPresent()) {
				Commune commune = optionalCommune.get();

				dto.setCommuneLabel(commune.getLabel());
				dto.setIdtCommune(commune.getIdt());
				if (commune.getDc() != null) {
					dto.setIdtDc(commune.getDc().getIdt());
					dto.setDcLabel(commune.getDc().getLabel());
					if (commune.getDc().getDr() != null) {
						dto.setIdtDr(commune.getDc().getDr().getIdt());
						dto.setDrLabel(commune.getDc().getDr().getLabel());

					}
				}
			}
		}

		dto.setIdtOperateur(demande.getIdtOperateur());
		dto.setOperateur(demande.getOperateur());

		//todo salman
//		dto.setXGpsDepart(demande.getXGpsDepart());
//		dto.setXGpsArrivee(demande.getXGpsArrivee());
//		dto.setYGpsDepart(demande.getYGpsDepart());
//		dto.setYGpsArrivee(demande.getYGpsArrivee());
//		dto.setXGpsDepart(demande.getXGpsDepart());

		dto.setReference(demande.getReference());

		return dto;
	}

	public LiaisonDemandeAddDto entityDemandeToDto(Demande demande) {
		if (demande == null) {
			return null;
		}
		LiaisonDemandeAddDto dto = new LiaisonDemandeAddDto();
		dto.setIdt(demande.getIdt());
		dto.setCommentaire(demande.getCommentaire());

		return dto;
	}

	public Liaison dtoToEntity(LiaisonDemandeAddDto demandeDto) {
		if (demandeDto == null) {
			return null;
		}
		Liaison demande = new Liaison();

		String login = SecurityContextHolder.getContext().getAuthentication().getName();
		Utilisateur utilisateur = utilisateurRepository.findByLogin(login);
		// demande.setIdtDemandeur(utilisateur.getIdt());
		// demande.setNomDemandeur(login);
		// demande.setPrenomDemandeur(utilisateur.getPrenom());
		// demande.setLoginDemandeur(login);
		demande.setOperateur(utilisateur.getOperateur().getLabel());
		demande.setIdtOperateur(utilisateur.getOperateur().getIdt());
		demande.setReference(demandeDto.getReference());
//		demande.setCodeLiaisonErpt(demandeDto.getCodeLiaisonErpt());
		//todo salman

		if (demandeDto.getIdtCommune() != null) {
			Optional<Commune> optionalCommune = communeRepository.findById(demandeDto.getIdtCommune());
			if (optionalCommune.isPresent()) {
				Commune commune = optionalCommune.get();

				System.out.println("commune: " + commune);
				demande.setIdtCommune(commune.getIdt());
				if (commune.getDc() != null) {
					commune.getDc().setIdt(demandeDto.getIdtDc());
					commune.getDc().setLabel(demandeDto.getDcLabel());

					if (commune.getDc().getDr() != null) {
						commune.getDc().getDr().setIdt(demandeDto.getIdtDr());
						commune.getDc().getDr().setLabel(demandeDto.getDrLabel());
					}
				}
			} else {
				System.out.println("commune is null");
			}
		}

		//todo salman
//		demande.setXGpsDepart(demandeDto.getXGpsDepart());
//		demande.setXGpsArrivee(demandeDto.getXGpsArrivee());
//		demande.setYGpsDepart(demandeDto.getYGpsDepart());
//		demande.setYGpsArrivee(demandeDto.getYGpsArrivee());


		return demande;
	}

	public Demande dtoToEntityDemande(LiaisonDemandeAddDto demandeDto) throws ParseException {
		if (demandeDto == null) {
			return null;
		}
		Demande demande = new Demande();
		String login = SecurityContextHolder.getContext().getAuthentication().getName();
		Utilisateur utilisateur = utilisateurRepository.findByLogin(login);
		if (utilisateur != null) {
			demande.setCommentaire(demandeDto.getCommentaire());
			Parametrage parametrageGMT = parametrageRepository.findByCode("VGMT");
			Date now = null;
			if (parametrageGMT != null) {
				if ("0".equalsIgnoreCase(parametrageGMT.getValeur())) {
					now = Utils.getCurrentDateGMT();
				} else {
					now = Utils.getCurrentDateGMTPlus1();
				}
			} else {
				now = new Date();
			}
			demande.setDateDemande(now);
			demande.setIdtDemandeur(utilisateur.getIdt());
			demande.setLoginDemandeur(utilisateur.getLogin());
			demande.setNomDemandeur(utilisateur.getNom());
			demande.setPrenomDemandeur(utilisateur.getPrenom());
			demande.setContactDemandeur(demandeDto.getContactDemandeur());
			demande.setDateReception(now);
			Optional<EtatDemande> etatDemandeOptional = etatDemandeRepository.findById(1L);
			if (etatDemandeOptional.isPresent()) {
				EtatDemande etatDemande = etatDemandeOptional.get();
				demande.setEtatDemande(etatDemande);
			}

			Optional<TypeDemande> typeOpt = typeRepository.findById(1L);
			if (typeOpt.isPresent()) {
				TypeDemande type = typeOpt.get();
				demande.setTypeDemande(type);
			}
		}

		return demande;
	}

}
