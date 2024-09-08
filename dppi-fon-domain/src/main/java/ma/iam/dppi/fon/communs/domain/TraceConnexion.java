package ma.iam.dppi.fon.communs.domain;

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
@Table(name = "dppi_trace_connexion")
public class TraceConnexion {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_tr_connexion")
	@SequenceGenerator(name = "seq_tr_connexion", sequenceName = "seq_tr_connexion", allocationSize = 1)
	@Column(name = "IDT", nullable = false)
	private Long idt;

	@Column(name = "CODE")
	private String code;

	@Column(name = "DESCRIPTION", columnDefinition = "LONGTEXT")
	private String description;

	@Column(name = "LOGIN")
	private String login;

	@Column(name = "NOM")
	private String nom;

	@Column(name = "PRENOM")
	private String prenom;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IDT_UTILISATEUR")
	private Utilisateur utilisateur;

	@Column(name = "DATE_OPERATION")
	private Date dateOperation;

	@Column(name = "TYPE_CONNEXION")
	private String typeConnexion;

	@Column(name = "PROFIL", columnDefinition = "LONGTEXT")
	private String profil;

	@Column(name = "APPLICATION")
	private String application;

	@Column(name = "ENTITE", columnDefinition = "LONGTEXT")
	private String entite;

	@Column(name = "OPERATEUR")
	private String operateur;

	@Column(name = "MODULE")
	private String module;

	public TraceConnexion() {

	}

	public TraceConnexion(String code, String description, String login,
			String nom, String prenom, Utilisateur utilisateur,
			Date dateOperation, String typeConnexion, String profil,
			String application, String entite, String operateur, String module) {
		super();
		this.code = code;
		this.description = description;
		this.login = login;
		this.nom = nom;
		this.prenom = prenom;
		this.utilisateur = utilisateur;
		this.dateOperation = dateOperation;
		this.typeConnexion = typeConnexion;
		this.profil = profil;
		this.application = application;
		this.entite = entite;
		this.operateur = operateur;
		this.module = module;
	}

	public Long getIdt() {
		return idt;
	}

	public void setIdt(Long idt) {
		this.idt = idt;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getDateOperation() {
		return dateOperation;
	}

	public void setDateOperation(Date dateOperation) {
		this.dateOperation = dateOperation;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getProfil() {
		return profil;
	}

	public void setProfil(String profil) {
		this.profil = profil;
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

	public String getTypeConnexion() {
		return typeConnexion;
	}

	public void setTypeConnexion(String typeConnexion) {
		this.typeConnexion = typeConnexion;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public String getApplication() {
		return application;
	}

	public void setApplication(String application) {
		this.application = application;
	}

	public String getEntite() {
		return entite;
	}

	public void setEntite(String entite) {
		this.entite = entite;
	}

	public String getOperateur() {
		return operateur;
	}

	public void setOperateur(String operateur) {
		this.operateur = operateur;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((dateOperation == null) ? 0 : dateOperation.hashCode());
		result = prime * result
				+ ((operateur == null) ? 0 : operateur.hashCode());
		result = prime * result
				+ ((utilisateur == null) ? 0 : utilisateur.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		TraceConnexion other = (TraceConnexion) obj;
		if (dateOperation == null) {
			if (other.dateOperation != null)
				return false;
		} else if (!dateOperation.equals(other.dateOperation))
			return false;
		if (operateur == null) {
			if (other.operateur != null)
				return false;
		} else if (!operateur.equals(other.operateur))
			return false;
		if (utilisateur == null) {
			if (other.utilisateur != null)
				return false;
		} else if (!utilisateur.equals(other.utilisateur))
			return false;
		return true;
	}

}
