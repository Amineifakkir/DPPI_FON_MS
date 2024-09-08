package ma.iam.dppi.fon.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 
 * @author Z.BELGHAOUTI
 *
 */
@Entity
@Table(name = "dppi_devis")
public class Devis {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_devis")
	@SequenceGenerator(name = "seq_devis", sequenceName = "seq_devis", allocationSize = 1)
	@Column(name = "IDT", nullable = false)
	private Long idt;
	
	@Column(name = "DATE_DEVIS")
	private Date dateDevis;
	
	@Column(name = "DATE_REFUS")
	private Date dateRefus;
	
	@Column(name = "DISTANCE")
	private Long distance;
	
	@Column(name = "PRIX")
	private Double prix;
	
	@Column(name = "PRIX_TOTAL")
	private Double prixTotal;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "IDT_DEMANDE")
	private Demande demande;

	public Demande getDemande() {
		return demande;
	}

	public void setDemande(Demande demande) {
		this.demande = demande;
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
	

	
}
