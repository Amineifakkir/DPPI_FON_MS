package ma.iam.dppi.fon.interne.mappers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.iam.dppi.fon.communs.domain.Commune;
import ma.iam.dppi.fon.communs.domain.Operateur;
import ma.iam.dppi.fon.communs.repository.CommuneRepository;
import ma.iam.dppi.fon.communs.repository.OperateurRepository;
import ma.iam.dppi.fon.domain.Liaison;
import ma.iam.dppi.fon.interne.dtos.LiaisonDto;

/**
 * 
 * @author Z.BELGHAOUTI
 * 
 */
@Service
public class MapperLiaison {

	@Autowired
	private CommuneRepository communeRepository;

	@Autowired
	private OperateurRepository operateurRepository;

//	@Autowired
//	private OperationLiaisonRepository operationLiaisonRepository;

	public LiaisonDto toDtoLiaison(Liaison l) {
		LiaisonDto dto = null;
		if (l != null) {
			dto = new LiaisonDto();

			dto.setIdt(l.getIdt());
			dto.setReference(l.getReference());
			if (l.getIdtCommune() != null) {
				Optional<Commune> optionalCommune = communeRepository.findById(l.getIdtCommune());
				if (optionalCommune.isPresent()) {
					Commune commune = optionalCommune.get();
					if (commune != null) {
						dto.setCommuneIdt(commune.getIdt());
						dto.setCommune(commune.getLabel());
						if (commune.getDc() != null) {
							dto.setDcIdt(commune.getDc().getIdt());
							dto.setDc(commune.getDc().getLabel());
							if (commune.getDc().getDr() != null) {
								dto.setDrIdt(commune.getDc().getDr().getIdt());
								dto.setDr(commune.getDc().getDr().getLabel());
							}
						}
					}
				}
			}

			if (l.getEtatLiaison() != null) {
				dto.setEtatLiaisonIdt(l.getEtatLiaison().getIdt());
				dto.setEtatLiaisonCode(l.getEtatLiaison().getCode());
				dto.setEtatLiaisonLabel(l.getEtatLiaison().getLabel());
			}

			if (l.getIdtOperateur() != null) {
				Optional<Operateur> optionalOperateur = operateurRepository.findById(l.getIdtOperateur());
				if (optionalOperateur.isPresent()) {
					Operateur operateur = optionalOperateur.get();
					if (operateur != null) {
						dto.setOperateurIdt(operateur.getIdt());
						dto.setOperateurCode(operateur.getCode());
						dto.setOperateurLabel(operateur.getLabel());
					}
				}

			}

//			if(l.getOperationLiason()!=null) {
//				Optional<OperationLiason> optional = operationLiaisonRepository.findById(l.getOperationLiason().getIdt());
//				if(optional.isPresent()) {
//					OperationLiason operationLiason= optional.get();
//					
//					dto.setCommenatire(operationLiason.getCommentaire());
//					dto.getOperationLiaisonDto().setIdt(operationLiason.getIdt());
//					dto.getOperationLiaisonDto().setIdtDemandeur(operationLiason.getIdtDemandeur());
//					dto.getOperationLiaisonDto().setNomDemandeur(operationLiason.getNomDemandeur());
//					dto.getOperationLiaisonDto().setPrenomDemandeur(operationLiason.getPrenomDemandeur());
//					dto.getOperationLiaisonDto().setLoginDemandeur(operationLiason.getLoginDemandeur());
//					dto.getOperationLiaisonDto().setDate(operationLiason.getDate());
//					dto.getOperationLiaisonDto().setEtatLiaisonIdt(operationLiason.getEtatLiaison().getIdt());
//					dto.getOperationLiaisonDto().setEtatLiaisonLabel(operationLiason.getEtatLiaison().getLabel());
//					dto.getOperationLiaisonDto().setEtatLiaisonCode(operationLiason.getEtatLiaison().getCode());
//				}
//			}

//			if (l.getTypeLiaison().getIdt() != null) {
//				System.out.println(l.getTypeLiaison().getIdt());
//				Optional<TypeLiaison> optionalTypeLiaison = liaisonRepository.findById(l.getTypeLiaison().getIdt());
//				if (optionalTypeLiaison.isPresent()) {
//					TypeLiaison typeLiaison = optionalTypeLiaison.get();
//					if (typeLiaison != null) {
//						dto.setTypeLiaisonCode(typeLiaison.getCode());
//					
//					}
//				}
//				
//			}
			dto.setCodeLiaisonErpt(l.getCodeSiteErpt());
			dto.setxGpsDepart(l.getxGpsDepart());
			dto.setyGpsArrivee(l.getyGpsArrivee());
			dto.setxGpsArrivee(l.getxGpsArrivee());
			dto.setyGpsDepart(l.getyGpsDepart());
			dto.setCodeSiteA(l.getCodeSiteA());
			dto.setCodeSiteB(l.getCodeSiteB());
			dto.setDistanceDisponible(l.getDistanceDisponible());
			dto.setDistanceSature(l.getDistanceSature());

		}
		return dto;
	}

}
