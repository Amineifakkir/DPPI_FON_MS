package ma.iam.dppi.fon.communs.domain;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 
 * @author Z.BELGHAOUTI
 *
 */
@Entity
@Table(name = "dppi_utilisateur")
public class Utilisateur {

	public static final String USER_HASH_CODE = "$2a$10$9y5.e7vu7zp8FJDA6FpcmO";
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_utilisateur")
	@SequenceGenerator(name = "seq_utilisateur", sequenceName = "seq_utilisateur", allocationSize = 1)
	@Column(name = "IDT", nullable = false)
	private Long idt;

	@Column(name = "LOGIN", length = 255, unique = true)
	private String login;

	@Column(name = "NOM", length = 255)
	private String nom;

	@Column(name = "PRENOM", length = 255)
	private String prenom;

	@Column(name = "MAIL", length = 255)
	private String mail;

	@Column(name = "MATRICULE", length = 255)
	private String matricule;

	@Column(name = "ENABLED")
	private Boolean enabled;

	@Column(name = "DATE_LAST_CONNECTION")
	private Date dateLastConnection;

	@Column(name = "CONDITIONS_ACCEPTED")
	private Boolean conditionsAccepted;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "IDT_OPERATEUR")
	private Operateur operateur;

	@ManyToMany(targetEntity = Profil.class, fetch = FetchType.EAGER)
	@JoinTable(name = "dppi_profil_utilisateur", joinColumns = @JoinColumn(name = "IDT_UTILISATEUR"), inverseJoinColumns = @JoinColumn(name = "IDT_PROFIL"))
	private List<Profil> listProfils;

	@Column(name = "PASSWORD", length = 255)
	private String password;

	@Column(name = "FIRST_CONNEXION")
	private boolean firstConnexion;

	@Column(name = "DATE_CHANGE_PASSWORD")
	private Timestamp dateChangePassword;

	@Column(name = "TENTATIVE_CONNEXION")
	private Integer tentativeConnexion;

	@Column(name = "FORCED_CHANGE_PWD")
	private Boolean forcedChangePwd;

	@Column(name = "DATE_CREATION")
	private Date dateCreation;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "IDT_ENTITE")
	private Entite entite;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "IDT_DR")
	private Dr dr;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "IDT_DC")
	private Dc dc;

	@Column(name = "USER_ID_SESSION")
	private String userIdSession;

	public Long getIdt() {
		return idt;
	}

	public void setIdt(Long idt) {
		this.idt = idt;
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

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getMatricule() {
		return matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Date getDateLastConnection() {
		return dateLastConnection;
	}

	public void setDateLastConnection(Date dateLastConnection) {
		this.dateLastConnection = dateLastConnection;
	}

	public Boolean isConditionsAccepted() {
		return conditionsAccepted;
	}

	public void setConditionsAccepted(Boolean conditionsAccepted) {
		this.conditionsAccepted = conditionsAccepted;
	}

	public Operateur getOperateur() {
		return operateur;
	}

	public void setOperateur(Operateur operateur) {
		this.operateur = operateur;
	}

	public List<Profil> getListProfils() {
		return listProfils;
	}

	public void setListProfils(List<Profil> listProfils) {
		this.listProfils = listProfils;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isFirstConnexion() {
		return firstConnexion;
	}

	public void setFirstConnexion(boolean firstConnexion) {
		this.firstConnexion = firstConnexion;
	}

	public Timestamp getDateChangePassword() {
		return dateChangePassword;
	}

	public void setDateChangePassword(Timestamp dateChangePassword) {
		this.dateChangePassword = dateChangePassword;
	}

	public Integer getTentativeConnexion() {
		return tentativeConnexion;
	}

	public void setTentativeConnexion(Integer tentativeConnexion) {
		this.tentativeConnexion = tentativeConnexion;
	}

	public Boolean getForcedChangePwd() {
		return forcedChangePwd;
	}

	public void setForcedChangePwd(Boolean forcedChangePwd) {
		this.forcedChangePwd = forcedChangePwd;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public Entite getEntite() {
		return entite;
	}

	public void setEntite(Entite entite) {
		this.entite = entite;
	}

	public Dr getDr() {
		return dr;
	}

	public void setDr(Dr dr) {
		this.dr = dr;
	}

	public Dc getDc() {
		return dc;
	}

	public void setDc(Dc dc) {
		this.dc = dc;
	}

	public String getUserIdSession() {
		return userIdSession;
	}

	public void setUserIdSession(String userIdSession) {
		this.userIdSession = userIdSession;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result
				+ ((operateur == null) ? 0 : operateur.hashCode());
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
		Utilisateur other = (Utilisateur) obj;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (operateur == null) {
			if (other.operateur != null)
				return false;
		} else if (!operateur.equals(other.operateur))
			return false;
		return true;
	}

}
