package ma.iam.dppi.fon.interne.dtos;

import java.util.Date;
import java.util.List;

/**
 * 
 * @author Z.BELGHAOUTI
 *
 */
public class UtilisateurDto {

	private Long idt;

	private String login;

	private String nom;

	private String prenom;

	private String email;

	private Long idtDr;

	private String dr;

	private Long idtDc;

	private String dc;

	private Long idtEntite;

	private String entite;

	private Date derniereConnexion;

	private boolean suspended;

	private List<String> roles;

	private String role;

	private String userIdSession;

	private Boolean enable;

	private Boolean locked;

	private String dateCreation;

	private String profilsExport;

	private String statutExport;

	private Long idtOperateur;

	private String operateur;

	private String password;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDerniereConnexion() {
		return derniereConnexion;
	}

	public void setDerniereConnexion(Date derniereConnexion) {
		this.derniereConnexion = derniereConnexion;
	}

	public boolean isSuspended() {
		return suspended;
	}

	public void setSuspended(boolean suspended) {
		this.suspended = suspended;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getUserIdSession() {
		return userIdSession;
	}

	public void setUserIdSession(String userIdSession) {
		this.userIdSession = userIdSession;
	}

	public Boolean isEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	public Boolean isLocked() {
		return locked;
	}

	public void setLocked(Boolean locked) {
		this.locked = locked;
	}

	public String getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(String dateCreation) {
		this.dateCreation = dateCreation;
	}

	public String getProfilsExport() {
		return profilsExport;
	}

	public void setProfilsExport(String profilsExport) {
		this.profilsExport = profilsExport;
	}

	public String getStatutExport() {
		return statutExport;
	}

	public void setStatutExport(String statutExport) {
		this.statutExport = statutExport;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDr() {
		return dr;
	}

	public void setDr(String dr) {
		this.dr = dr;
	}

	public String getEntite() {
		return entite;
	}

	public void setEntite(String entite) {
		this.entite = entite;
	}

	public Long getIdtDr() {
		return idtDr;
	}

	public void setIdtDr(Long idtDr) {
		this.idtDr = idtDr;
	}

	public Long getIdtDc() {
		return idtDc;
	}

	public void setIdtDc(Long idtDc) {
		this.idtDc = idtDc;
	}

	public String getDc() {
		return dc;
	}

	public void setDc(String dc) {
		this.dc = dc;
	}

	public Long getIdtEntite() {
		return idtEntite;
	}

	public void setIdtEntite(Long idtEntite) {
		this.idtEntite = idtEntite;
	}

}
