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
@Table(name = "dppi_trace_operation")
public class TraceOperation {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_tr_operation")
	@SequenceGenerator(name = "seq_tr_operation", sequenceName = "seq_tr_operation", allocationSize = 1)
	@Column(name = "IDT", nullable = false)
	private Long idt;

	@Column(name = "CODE")
	private String code;

	@Column(name = "DESCRIPTION", columnDefinition = "LONGTEXT")
	private String description;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IDT_UTILISATEUR")
	private Utilisateur utilisateur;

	@Column(name = "DATE_OPERATION")
	private Date dateOperation;

	@Column(name = "OPERATION", columnDefinition = "LONGTEXT")
	private String operation;

	@Column(name = "TYPE_OPERATION")
	private String typeOperation;

	@Column(name = "PROFIL", columnDefinition = "LONGTEXT")
	private String profil;

	@Column(name = "MODULE")
	private String module;

	@Column(name = "OBJET_MODIFIE")
	private String objetModifie;

	@Column(name = "OLD_VALUE", columnDefinition = "LONGTEXT")
	private String oldValue;

	@Column(name = "NEW_VALUE", columnDefinition = "LONGTEXT")
	private String newValue;

	@Column(name = "APPLICATION")
	private String application;

	@Column(name = "LOGIN")
	private String login;

	@Column(name = "NOM")
	private String nom;

	@Column(name = "PRENOM")
	private String prenom;

	@Column(name = "ENTITE", columnDefinition = "LONGTEXT")
	private String entite;

	@Column(name = "OPERATEUR")
	private String operateur;

	public TraceOperation() {
	}

	/**
	 * @author Z.BELGHAOUTI
	 * 
	 */
	public TraceOperation(String code, String description,
			Utilisateur utilisateur, Date dateOperation, String operation,
			String typeOperation, String profil, String module,
			String objetModifie, String oldValue, String newValue,
			String application, String login, String nom, String prenom,
			String entite, String operateur) {

		super();
		this.code = code;
		this.description = description;
		this.utilisateur = utilisateur;
		this.dateOperation = dateOperation;
		this.operation = operation;
		this.typeOperation = typeOperation;
		this.profil = profil;
		this.module = module;
		this.objetModifie = objetModifie;
		this.oldValue = oldValue;
		this.newValue = newValue;
		this.application = application;
		this.login = login;
		this.nom = nom;
		this.prenom = prenom;
		this.entite = entite;
		this.operateur = operateur;
	}

	public TraceOperation(String code, String description, Date dateOperation,
			Utilisateur utilisateur, String application, String module,
			String profil, String typeOperation, String objetModifie,
			String oldValue, String newValue) {
		super();
		this.code = code;
		this.description = description;
		this.dateOperation = dateOperation;
		this.utilisateur = utilisateur;
		this.application = application;
		this.module = module;
		this.profil = profil;
		this.typeOperation = typeOperation;
		this.objetModifie = objetModifie;
		this.oldValue = oldValue;
		this.newValue = newValue;
	}

	public TraceOperation(String code, String description, Date dateOperation,
			Utilisateur utilisateur) {
		super();
		this.code = code;
		this.description = description;
		this.dateOperation = dateOperation;
		this.utilisateur = utilisateur;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDateOperation() {
		return dateOperation;
	}

	public void setDateOperation(Date dateOperation) {
		this.dateOperation = dateOperation;
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

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getProfil() {
		return profil;
	}

	public void setProfil(String profil) {
		this.profil = profil;
	}

	public String getTypeOperation() {
		return typeOperation;
	}

	public void setTypeOperation(String typeOperation) {
		this.typeOperation = typeOperation;
	}

	public String getObjetModifie() {
		return objetModifie;
	}

	public void setObjetModifie(String objetModifie) {
		this.objetModifie = objetModifie;
	}

	public String getOldValue() {
		return oldValue;
	}

	public void setOldValue(String oldValue) {
		this.oldValue = oldValue;
	}

	public String getNewValue() {
		return newValue;
	}

	public void setNewValue(String newValue) {
		this.newValue = newValue;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((dateOperation == null) ? 0 : dateOperation.hashCode());
		result = prime * result
				+ ((typeOperation == null) ? 0 : typeOperation.hashCode());
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
		TraceOperation other = (TraceOperation) obj;
		if (dateOperation == null) {
			if (other.dateOperation != null)
				return false;
		} else if (!dateOperation.equals(other.dateOperation))
			return false;
		if (typeOperation == null) {
			if (other.typeOperation != null)
				return false;
		} else if (!typeOperation.equals(other.typeOperation))
			return false;
		if (utilisateur == null) {
			if (other.utilisateur != null)
				return false;
		} else if (!utilisateur.equals(other.utilisateur))
			return false;
		return true;
	}

}
