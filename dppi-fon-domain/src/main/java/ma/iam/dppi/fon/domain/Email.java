package ma.iam.dppi.fon.domain;

import java.util.Date;

import javax.persistence.CascadeType;
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
@Table(name = "dppi_email")
public class Email {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_email")
	@SequenceGenerator(name = "seq_email", sequenceName = "seq_email", allocationSize = 1)
	@Column(name = "IDT", nullable = false)
	private Long idt;

	@Column(name = "DATE_ENVOI")
	private Date dateEnvoi;

	@Column(name = "ENVOI")
	private Boolean envoi;

	@Column(name = "DATE_CREATION")
	private Date dateCreation;

	@Column(name = "LINK")
	private String link;

	@Column(name = "TEMPLATE")
	private String template;

	@Column(name = "PROJET")
	private String projet;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IDT_LIAISON")
	private Liaison liaison;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IDT_DEMANDE")
	private Demande demande;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "IDT_INTERACTION")
	private Interaction interaction;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "IDT_TRONCON")
	private SousLiaison sousLiaison;

	@Column(name = "IDT_UTILISATEUR")
	private Long idtUtilisateur;

	@Column(name = "ENTITE_A_ENVOYER")
	private String entiteAEnvoyer;

	@Column(name = "SOURCE")
	private String source;

	public Email() {

	}

	public Email(Boolean envoi, Date dateCreation, String link,
			String template, String projet, Liaison liaison, Demande demande,
			Interaction interaction, Long idtUtilisateur,
			String entiteAEnvoyer, String source, SousLiaison sousLiaison) {
		this.envoi = envoi;
		this.dateCreation = dateCreation;
		this.link = link;
		this.template = template;
		this.projet = projet;
		this.liaison = liaison;
		this.demande = demande;
		this.interaction = interaction;
		this.idtUtilisateur = idtUtilisateur;
		this.entiteAEnvoyer = entiteAEnvoyer;
		this.source = source;
		this.sousLiaison = sousLiaison;
	}

	public Long getIdt() {
		return idt;
	}

	public void setIdt(Long idt) {
		this.idt = idt;
	}

	public Date getDateEnvoi() {
		return dateEnvoi;
	}

	public void setDateEnvoi(Date dateEnvoi) {
		this.dateEnvoi = dateEnvoi;
	}

	public Boolean getEnvoi() {
		return envoi;
	}

	public void setEnvoi(Boolean envoi) {
		this.envoi = envoi;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public String getProjet() {
		return projet;
	}

	public void setProjet(String projet) {
		this.projet = projet;
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

	public Interaction getInteraction() {
		return interaction;
	}

	public void setInteraction(Interaction interaction) {
		this.interaction = interaction;
	}

	public SousLiaison getTroncon() {
		return sousLiaison;
	}

	public void setTroncon(SousLiaison sousLiaison) {
		this.sousLiaison = sousLiaison;
	}

	public String getEntiteAEnvoyer() {
		return entiteAEnvoyer;
	}

	public void setEntiteAEnvoyer(String entiteAEnvoyer) {
		this.entiteAEnvoyer = entiteAEnvoyer;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Long getIdtUtilisateur() {
		return idtUtilisateur;
	}

	public void setIdtUtilisateur(Long idtUtilisateur) {
		this.idtUtilisateur = idtUtilisateur;
	}

}
