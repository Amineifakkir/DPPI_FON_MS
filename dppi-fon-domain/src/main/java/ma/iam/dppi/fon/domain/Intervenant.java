package ma.iam.dppi.fon.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 
 * @author Z.BELGHAOUTI
 *
 */
@Entity
@Table(name = "dppi_intervenant")
public class Intervenant {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_intervenant")
	@SequenceGenerator(name = "seq_intervenant", sequenceName = "seq_intervenant", allocationSize = 1)
	@Column(name = "IDT", nullable = false)
	private Long idt;

	@Column(name = "NOM")
	private String nom;

	@Column(name = "PRENOM")
	private String prenom;

	@Column(name = "CIN", unique = true)
	private String cin;

	@Column(name = "ERPT")
	private String erpt;

	@Column(name = "SOUS_TRAITANT")
	private String sousTraitant;

	@Column(name = "IDT_OPERATEUR")
	private Long idtOperateur;

	@Column(name = "OPERATEUR")
	private String operateur;
	
	@Column(name = "TELEPHONE")
	private String telephone;
	
	public Long getIdt() {
		return idt;
	}

	public void setIdt(Long idt) {
		this.idt = idt;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getCin() {
		return cin;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}

	public String getErpt() {
		return erpt;
	}

	public void setErpt(String erpt) {
		this.erpt = erpt;
	}

	public String getSousTraitant() {
		return sousTraitant;
	}

	public void setSousTraitant(String sousTraitant) {
		this.sousTraitant = sousTraitant;
	}

	public Long getIdtOperateur() {
		return idtOperateur;
	}

	public void setIdtOperateur(Long idtOperateur) {
		this.idtOperateur = idtOperateur;
	}

	public String getOperateur() {
		return operateur;
	}

	public void setOperateur(String operateur) {
		this.operateur = operateur;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	

}
