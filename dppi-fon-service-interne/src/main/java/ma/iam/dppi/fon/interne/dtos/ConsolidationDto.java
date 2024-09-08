package ma.iam.dppi.fon.interne.dtos;

/**
 *
 * @author Z.BELGHAOUTI
 */
public class ConsolidationDto {

	private String codeEtatDemande;
	private String distanceDisponible;
	private String distanceSature;
	private Long idtDemande;
	private Long idtLiaison;

	public String getCodeEtatDemande() {
		return codeEtatDemande;
	}

	public void setCodeEtatDemande(String codeEtatDemande) {
		this.codeEtatDemande = codeEtatDemande;
	}

	public String getDistanceDisponible() {
		return distanceDisponible;
	}

	public void setDistanceDisponible(String distanceDisponible) {
		this.distanceDisponible = distanceDisponible;
	}

	public String getDistanceSature() {
		return distanceSature;
	}

	public void setDistanceSature(String distanceSature) {
		this.distanceSature = distanceSature;
	}

	public Long getIdtDemande() {
		return idtDemande;
	}

	public void setIdtDemande(Long idtDemande) {
		this.idtDemande = idtDemande;
	}

	public Long getIdtLiaison() {
		return idtLiaison;
	}

	public void setIdtLiaison(Long idtLiaison) {
		this.idtLiaison = idtLiaison;
	}

}
