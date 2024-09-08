package ma.iam.dppi.fon.interne.dtos;

/**
 * 
 * @author Z.BELGHAOUTI
 *
 */
public class TraceOperationDto {

	private Long idt;
	private String date;
	private String heure;
	private String login;
	private String nom;
	private String prenom;
	private String operateur;
	private String entite;
	private String typeOperation;
	private String application;
	private String module;
	private String profils;
	private String description;
	private String operation;
	private String objetModifie;
	private String valeurAvant;
	private String valeurApres;

	public Long getIdt() {
		return idt;
	}

	public void setIdt(Long idt) {
		this.idt = idt;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getHeure() {
		return heure;
	}

	public void setHeure(String heure) {
		this.heure = heure;
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

	public String getOperateur() {
		return operateur;
	}

	public void setOperateur(String operateur) {
		this.operateur = operateur;
	}

	public String getEntite() {
		return entite;
	}

	public void setEntite(String entite) {
		this.entite = entite;
	}

	public String getTypeOperation() {
		return typeOperation;
	}

	public void setTypeOperation(String typeOperation) {
		this.typeOperation = typeOperation;
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

	public String getProfils() {
		return profils;
	}

	public void setProfils(String profils) {
		this.profils = profils;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getObjetModifie() {
		return objetModifie;
	}

	public void setObjetModifie(String objetModifie) {
		this.objetModifie = objetModifie;
	}

	public String getValeurAvant() {
		return valeurAvant;
	}

	public void setValeurAvant(String valeurAvant) {
		this.valeurAvant = valeurAvant;
	}

	public String getValeurApres() {
		return valeurApres;
	}

	public void setValeurApres(String valeurApres) {
		this.valeurApres = valeurApres;
	}

}
