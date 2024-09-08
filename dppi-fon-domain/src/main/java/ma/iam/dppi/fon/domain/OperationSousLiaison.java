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
@Table(name = "dppi_operation_sous_liaison")
public class OperationSousLiaison {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_operation_sous_liaison")
	@SequenceGenerator(name = "seq_operation_sous_liaison", sequenceName = "seq_operation_sous_liaison", allocationSize = 1)
	@Column(name = "IDT", nullable = false)
	private Long idt;

	@Column(name = "DATE_AFFECTATION")
	private Date dateAffectation;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "IDT_DEMANDE")
	private Demande demande;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "IDT_SOUS_LIAISON")
	private SousLiaison sousLiaison;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "IDT_ETAT_DEMANDE_SOUS_LIAISON")
	private EtatDemandeSousLiaison etatDemandeSousLiaison;

	@Column(name = "DATE_REPONSE")
	private Date dateReponse;

	@Column(name = "DEMANDEUR_LOGIN")
	private String demandeurLogin;

	@Column(name = "DEMANDEUR_NOM")
	private String demandeurNom;

	@Column(name = "DEMANDEUR_PRENOM")
	private String demandeurPrenom;

	@Column(name = "REPONDEUR_LOGIN")
	private String repondeurLogin;

	@Column(name = "REPONDEUR_NOM")
	private String repondeurNom;

	@Column(name = "REPONDEUR_PRENOM")
	private String repondeurPrenom;

	@Column(name = "COMMENTAIRE", columnDefinition = "TEXT")
	private String commentaire;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "IDT_RAISON_INFAISABILITE")
	private RaisonInfaisabilite raisonInfaisabilite;

	@Column(name = "DESCRIPTION_INFAISABILITE", columnDefinition = "TEXT")
	private String descriptionInfaisabilite;

	@Column(name = "SOLUTION_ALTERNATIVE", columnDefinition = "TEXT")
	private String solutionAlternative;

	public Long getIdt() {
		return idt;
	}

	public void setIdt(Long idt) {
		this.idt = idt;
	}

	

	public Date getDateAffectation() {
		return dateAffectation;
	}

	public void setDateAffectation(Date dateAffectation) {
		this.dateAffectation = dateAffectation;
	}

	public Demande getDemande() {
		return demande;
	}

	public void setDemande(Demande demande) {
		this.demande = demande;
	}

	public SousLiaison getSousLiaison() {
		return sousLiaison;
	}

	public void setSousLiaison(SousLiaison sousLiaison) {
		this.sousLiaison = sousLiaison;
	}

	public EtatDemandeSousLiaison getEtatDemandeSousLiaison() {
		return etatDemandeSousLiaison;
	}

	public void setEtatDemandeSousLiaison(EtatDemandeSousLiaison etatDemandeSousLiaison) {
		this.etatDemandeSousLiaison = etatDemandeSousLiaison;
	}

	public Date getDateReponse() {
		return dateReponse;
	}

	public void setDateReponse(Date dateReponse) {
		this.dateReponse = dateReponse;
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

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}


	public RaisonInfaisabilite getRaisonInfaisabilite() {
		return raisonInfaisabilite;
	}

	public void setRaisonInfaisabilite(RaisonInfaisabilite raisonInfaisabilite) {
		this.raisonInfaisabilite = raisonInfaisabilite;
	}

	public String getDescriptionInfaisabilite() {
		return descriptionInfaisabilite;
	}

	public void setDescriptionInfaisabilite(String descriptionInfaisabilite) {
		this.descriptionInfaisabilite = descriptionInfaisabilite;
	}

	public String getSolutionAlternative() {
		return solutionAlternative;
	}

	public void setSolutionAlternative(String solutionAlternative) {
		this.solutionAlternative = solutionAlternative;
	}

	
}
