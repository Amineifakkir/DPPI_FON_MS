package ma.iam.dppi.fon.dtos;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class DemandeUpdateDto {

	private Long idtDemande;
	
	private Long idtLiaison;
	
	private String commentaire;
	
	private String dateDebutReception;
	
	private String dateFinReception;
	
	private String contact;
	
	private String nom;
	
	private String prenom;
	
	private String cin;
	
	private String telephone;
	
	private List<MultipartFile> multipartFiles;
	
	private Long idtTypeDemande;
	
	private Boolean isValid;
	
	private Boolean isDevisExist;

	private Boolean etatDevis;
	
	private String typeComponent;
	
	private Boolean typeIntervention;
	
	private List<IntervenantDto> intervenants;
	
	private String description;
	
	private String dateDebut;
	
	private String dateFin;

	private String interlocuteur;
	
	public Long getIdtDemande() {
		return idtDemande;
	}

	public void setIdtDemande(Long idtDemande) {
		this.idtDemande = idtDemande;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public String getDateDebutReception() {
		return dateDebutReception;
	}

	public void setDateDebutReception(String dateDebutReception) {
		this.dateDebutReception = dateDebutReception;
	}

	public String getDateFinReception() {
		return dateFinReception;
	}

	public void setDateFinReception(String dateFinReception) {
		this.dateFinReception = dateFinReception;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
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

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public List<MultipartFile> getMultipartFiles() {
		return multipartFiles;
	}

	public void setMultipartFiles(List<MultipartFile> multipartFiles) {
		this.multipartFiles = multipartFiles;
	}

	public Long getIdtLiaison() {
		return idtLiaison;
	}

	public void setIdtLiaison(Long idtLiaison) {
		this.idtLiaison = idtLiaison;
	}

	public Long getIdtTypeDemande() {
		return idtTypeDemande;
	}

	public void setIdtTypeDemande(Long idtTypeDemande) {
		this.idtTypeDemande = idtTypeDemande;
	}

	public Boolean getIsValid() {
		return isValid;
	}

	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}

	public Boolean getIsDevisExist() {
		return isDevisExist;
	}

	public void setIsDevisExist(Boolean isDevisExist) {
		this.isDevisExist = isDevisExist;
	}

	public Boolean getEtatDevis() {
		return etatDevis;
	}

	public void setEtatDevis(Boolean etatDevis) {
		this.etatDevis = etatDevis;
	}

	public String getTypeComponent() {
		return typeComponent;
	}

	public void setTypeComponent(String typeComponent) {
		this.typeComponent = typeComponent;
	}

	public Boolean getTypeIntervention() {
		return typeIntervention;
	}

	public void setTypeIntervention(Boolean typeIntervention) {
		this.typeIntervention = typeIntervention;
	}

	public List<IntervenantDto> getIntervenants() {
		return intervenants;
	}

	public void setIntervenants(List<IntervenantDto> intervenants) {
		this.intervenants = intervenants;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(String dateDebut) {
		this.dateDebut = dateDebut;
	}

	public String getDateFin() {
		return dateFin;
	}

	public void setDateFin(String dateFin) {
		this.dateFin = dateFin;
	}

	public String getInterlocuteur() {
		return interlocuteur;
	}

	public void setInterlocuteur(String interlocuteur) {
		this.interlocuteur = interlocuteur;
	}
	
	
	
	
	
}
