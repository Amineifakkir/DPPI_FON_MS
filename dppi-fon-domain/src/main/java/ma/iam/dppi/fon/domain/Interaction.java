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
@Table(name = "dppi_interaction")
public class Interaction {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_interaction")
	@SequenceGenerator(name = "seq_interaction", sequenceName = "seq_interaction", allocationSize = 1)
	@Column(name = "IDT", nullable = false)
	private Long idt;

	@Column(name = "ENTITE_SOURCE")
	private String entiteSource;

	@Column(name = "ENTITE_CIBLE")
	private String entiteCible;

	@Column(name = "DATE_INTERACTION")
	private Date dateInteraction;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "IDT_DEMANDE")
	private Demande demande;

	@Column(name = "DATE_REPONSE")
	private Date dateReponse;

	@Column(name = "INTERACTION")
	private String interactionLabel;

	@Column(name = "REPONSE", columnDefinition = "TEXT")
	private String reponse;

	@Column(name = "REPONDEUR_LOGIN")
	private String repondeurLogin;

	@Column(name = "REPONDEUR_NOM")
	private String repondeurNom;

	@Column(name = "REPONDEUR_PRENOM")
	private String repondeurPrenom;

	@Column(name = "DEMANDEUR_LOGIN")
	private String demandeurLogin;

	@Column(name = "DEMANDEUR_NOM")
	private String demandeurNom;

	@Column(name = "DEMANDEUR_PRENOM")
	private String demandeurPrenom;

	public Long getIdt() {
		return idt;
	}

	public void setIdt(Long idt) {
		this.idt = idt;
	}

	public String getEntiteSource() {
		return entiteSource;
	}

	public void setEntiteSource(String entiteSource) {
		this.entiteSource = entiteSource;
	}

	public String getEntiteCible() {
		return entiteCible;
	}

	public void setEntiteCible(String entiteCible) {
		this.entiteCible = entiteCible;
	}

	public Date getDateInteraction() {
		return dateInteraction;
	}

	public void setDateInteraction(Date dateInteraction) {
		this.dateInteraction = dateInteraction;
	}

	public Demande getDemande() {
		return demande;
	}

	public void setDemande(Demande demande) {
		this.demande = demande;
	}

	public Date getDateReponse() {
		return dateReponse;
	}

	public void setDateReponse(Date dateReponse) {
		this.dateReponse = dateReponse;
	}

	public String getInteractionLabel() {
		return interactionLabel;
	}

	public void setInteractionLabel(String interactionLabel) {
		this.interactionLabel = interactionLabel;
	}

	public String getReponse() {
		return reponse;
	}

	public void setReponse(String reponse) {
		this.reponse = reponse;
	}

	public String getRepondeurLogin() {
		return repondeurLogin;
	}

	public void setRepondeurLogin(String repondeurLogin) {
		this.repondeurLogin = repondeurLogin;
	}

	public String getRepondeurNom() {
		return repondeurNom;
	}

	public void setRepondeurNom(String repondeurNom) {
		this.repondeurNom = repondeurNom;
	}

	public String getRepondeurPrenom() {
		return repondeurPrenom;
	}

	public void setRepondeurPrenom(String repondeurPrenom) {
		this.repondeurPrenom = repondeurPrenom;
	}

	public String getDemandeurLogin() {
		return demandeurLogin;
	}

	public void setDemandeurLogin(String demandeurLogin) {
		this.demandeurLogin = demandeurLogin;
	}

	public String getDemandeurNom() {
		return demandeurNom;
	}

	public void setDemandeurNom(String demandeurNom) {
		this.demandeurNom = demandeurNom;
	}

	public String getDemandeurPrenom() {
		return demandeurPrenom;
	}

	public void setDemandeurPrenom(String demandeurPrenom) {
		this.demandeurPrenom = demandeurPrenom;
	}

}
