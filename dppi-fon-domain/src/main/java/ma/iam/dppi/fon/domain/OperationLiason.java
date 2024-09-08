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

@Entity
@Table(name = "dppi_operation_liaison")
public class OperationLiason {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_operation_liaison")
	@SequenceGenerator(name = "seq_operation_liaison", sequenceName = "seq_operation_liaison", allocationSize = 1)
	@Column(name = "IDT", nullable = false)
	private Long idt;

	@Column(name = "IDT_DEMANDEUR")
	private Long idtDemandeur;

	@Column(name = "NOM_DEMANDEUR")
	private String nomDemandeur;

	@Column(name = "PRENOM_DEMANDEUR")
	private String prenomDemandeur;

	@Column(name = "LOGIN_DEMANDEUR")
	private String loginDemandeur;

	@Column(name = "DATE")
	private Date date;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "IDT_ETAT_LIAISON")
	private EtatLiaison etatLiaison;

	@Column(name = "COMMENTAIRE")
	private String commentaire;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "LIAISON")
	private Liaison liaison;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "DEMANDE")
	private Demande demande;

	public Long getIdt() {
		return idt;
	}

	public void setIdt(Long idt) {
		this.idt = idt;
	}

	public Long getIdtDemandeur() {
		return idtDemandeur;
	}

	public void setIdtDemandeur(Long idtDemandeur) {
		this.idtDemandeur = idtDemandeur;
	}

	public String getNomDemandeur() {
		return nomDemandeur;
	}

	public void setNomDemandeur(String nomDemandeur) {
		this.nomDemandeur = nomDemandeur;
	}

	public String getPrenomDemandeur() {
		return prenomDemandeur;
	}

	public void setPrenomDemandeur(String prenomDemandeur) {
		this.prenomDemandeur = prenomDemandeur;
	}

	public String getLoginDemandeur() {
		return loginDemandeur;
	}

	public void setLoginDemandeur(String loginDemandeur) {
		this.loginDemandeur = loginDemandeur;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public EtatLiaison getEtatLiaison() {
		return etatLiaison;
	}

	public void setEtatLiaison(EtatLiaison etatLiaison) {
		this.etatLiaison = etatLiaison;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public Liaison getLiaison() {
		return liaison;
	}

	public void setLiaison(Liaison liaison) {
		this.liaison = liaison;
	}

	public Demande getDemande() {
		return demande;
	}

	public void setDemande(Demande demande) {
		this.demande = demande;
	}
	

}
