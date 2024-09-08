package ma.iam.dppi.fon.dtos;

import java.util.Date;

public class DevisDto {

	private Long idt;

	private Date dateDevis;

	private Date dateRefus;

	private Long distance;

	private Double prix;

	private Double prixTotal;

	private Long idtDemande;

	private Boolean devisValide;
	
	private Boolean withDevis;

	public Boolean getDevisValide() {
		return devisValide;
	}

	public void setDevisValide(Boolean devisValide) {
		this.devisValide = devisValide;
	}

	public Long getIdt() {
		return idt;
	}

	public void setIdt(Long idt) {
		this.idt = idt;
	}

	public Date getDateDevis() {
		return dateDevis;
	}

	public void setDateDevis(Date dateDevis) {
		this.dateDevis = dateDevis;
	}

	public Date getDateRefus() {
		return dateRefus;
	}

	public void setDateRefus(Date dateRefus) {
		this.dateRefus = dateRefus;
	}

	public Long getDistance() {
		return distance;
	}

	public void setDistance(Long distance) {
		this.distance = distance;
	}

	public Double getPrix() {
		return prix;
	}

	public void setPrix(Double prix) {
		this.prix = prix;
	}

	public Double getPrixTotal() {
		return prixTotal;
	}

	public void setPrixTotal(Double prixTotal) {
		this.prixTotal = prixTotal;
	}

	public Long getIdtDemande() {
		return idtDemande;
	}

	public void setIdtDemande(Long idtDemande) {
		this.idtDemande = idtDemande;
	}

	public Boolean getWithDevis() {
		return withDevis;
	}

	public void setWithDevis(Boolean withDevis) {
		this.withDevis = withDevis;
	}

	

}
