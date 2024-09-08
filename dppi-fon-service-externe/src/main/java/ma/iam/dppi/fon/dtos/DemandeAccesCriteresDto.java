package ma.iam.dppi.fon.dtos;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * @author Z.BELGHAOUTI
 *
 */
public class DemandeAccesCriteresDto {

	// demande
	private Long idt;
	private Long idtLiaison;
	private String dateDemande;
	private Long idtDemandeur;
	private String loginDemandeur;
	private String commentaire;
	private Long idtEtatDemande;
	private Long idtType;
	private String codeType;
	private List<IntervenantDto> intervenants;
	private String descriptionIncident;
	private String localisationIncident;
    private Long pointObstacleGPSN;
	private Long pointObstacleGPSW;
	private String descriptionObstacle;
	private String typeComponent;
	private Date dateDebut;

	
	private Long idtTypeDesaturaion;
	private Date dateFin;
	private String descriptionAcces;
	private Boolean typeIntervention;
	private String erpt;
	private Long idtOperateur;
	private String operateurCode;
	
	private Long idtDemande;
	private String contactDemandeur;
	private String dateDebutReception;
	private String dateFinReception;
	
	private Boolean isValid;
	private Boolean isDevisExist;
	private Boolean etatDevis;
	
	private String interlocuteur;
	
	private List<MultipartFile> multipartFiles;
	
	private String codeChambreIAM;
	private Double xGpsChambre;
	private Double YGpsChambre;
	private String distanceIncident;
	private String criticite;
	
	private Boolean devisValid;
	
	private Boolean isAddDemande;
	
	private String reference;
	
	private String telephone;
	// intervenent

	public String getErpt() {
		return erpt;
	}

	public void setErpt(String erpt) {
		this.erpt = erpt;
	}

	public Long getIdtOperateur() {
		return idtOperateur;
	}

	public void setIdtOperateur(Long idtOperateur) {
		this.idtOperateur = idtOperateur;
	}

	public String getOperateurCode() {
		return operateurCode;
	}

	public void setOperateurCode(String operateurCode) {
		this.operateurCode = operateurCode;
	}

	public String getDescriptionIncident() {
		return descriptionIncident;
	}

	public void setDescriptionIncident(String descriptionIncident) {
		this.descriptionIncident = descriptionIncident;
	}

	public String getLocalisationIncident() {
		return localisationIncident;
	}

	public void setLocalisationIncident(String localisationIncident) {
		this.localisationIncident = localisationIncident;
	}

	public Long getPointObstacleGPSN() {
		return pointObstacleGPSN;
	}

	public void setPointObstacleGPSN(Long pointObstacleGPSN) {
		this.pointObstacleGPSN = pointObstacleGPSN;
	}

	public Long getPointObstacleGPSW() {
		return pointObstacleGPSW;
	}

	public void setPointObstacleGPSW(Long pointObstacleGPSW) {
		this.pointObstacleGPSW = pointObstacleGPSW;
	}

	public String getDescriptionObstacle() {
		return descriptionObstacle;
	}

	public void setDescriptionObstacle(String descriptionObstacle) {
		this.descriptionObstacle = descriptionObstacle;
	}


	public Long getIdt() {
		return idt;
	}

	public String getCodeType() {
		return codeType;
	}

	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}

	public void setIdt(Long idt) {
		this.idt = idt;
	}

	public Long getIdtLiaison() {
		return idtLiaison;
	}

	public void setIdtLiaison(Long idtLiaison) {
		this.idtLiaison = idtLiaison;
	}

	public String getDateDemande() {
		return dateDemande;
	}

	public void setDateDemande(String dateDemande) {
		this.dateDemande = dateDemande;
	}

	public Long getIdtDemandeur() {
		return idtDemandeur;
	}

	public void setIdtDemandeur(Long idtDemandeur) {
		this.idtDemandeur = idtDemandeur;
	}

	public String getLoginDemandeur() {
		return loginDemandeur;
	}

	public void setLoginDemandeur(String loginDemandeur) {
		this.loginDemandeur = loginDemandeur;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public Long getIdtEtatDemande() {
		return idtEtatDemande;
	}

	public void setIdtEtatDemande(Long idtEtatDemande) {
		this.idtEtatDemande = idtEtatDemande;
	}

	public Long getIdtType() {
		return idtType;
	}

	public void setIdtType(Long idtType) {
		this.idtType = idtType;
	}

	public List<IntervenantDto> getIntervenants() {
		return intervenants;
	}

	public void setIntervenants(List<IntervenantDto> intervenants) {
		this.intervenants = intervenants;
	}

	public Long getIdtTypeDesaturaion() {
		return idtTypeDesaturaion;
	}

	public void setIdtTypeDesaturaion(Long idtTypeDesaturaion) {
		this.idtTypeDesaturaion = idtTypeDesaturaion;
	}

	


	public String getTypeComponent() {
		return typeComponent;
	}

	public void setTypeComponent(String typeComponent) {
		this.typeComponent = typeComponent;
	}

	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public String getDescriptionAcces() {
		return descriptionAcces;
	}

	public void setDescriptionAcces(String descriptionAcces) {
		this.descriptionAcces = descriptionAcces;
	}

	public Boolean getTypeIntervention() {
		return typeIntervention;
	}

	public void setTypeIntervention(Boolean typeIntervention) {
		this.typeIntervention = typeIntervention;
	}

	public Long getIdtDemande() {
		return idtDemande;
	}

	public void setIdtDemande(Long idtDemande) {
		this.idtDemande = idtDemande;
	}

	public String getContactDemandeur() {
		return contactDemandeur;
	}

	public void setContactDemandeur(String contactDemandeur) {
		this.contactDemandeur = contactDemandeur;
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

	public List<MultipartFile> getMultipartFiles() {
		return multipartFiles;
	}

	public void setMultipartFiles(List<MultipartFile> multipartFiles) {
		this.multipartFiles = multipartFiles;
	}

	public Boolean getEtatDevis() {
		return etatDevis;
	}

	public void setEtatDevis(Boolean etatDevis) {
		this.etatDevis = etatDevis;
	}

	public String getInterlocuteur() {
		return interlocuteur;
	}

	public void setInterlocuteur(String interlocuteur) {
		this.interlocuteur = interlocuteur;
	}

	public String getCodeChambreIAM() {
		return codeChambreIAM;
	}

	public void setCodeChambreIAM(String codeChambreIAM) {
		this.codeChambreIAM = codeChambreIAM;
	}

	public Double getxGpsChambre() {
		return xGpsChambre;
	}

	public void setxGpsChambre(Double xGpsChambre) {
		this.xGpsChambre = xGpsChambre;
	}

	public Double getYGpsChambre() {
		return YGpsChambre;
	}

	public void setYGpsChambre(Double yGpsChambre) {
		YGpsChambre = yGpsChambre;
	}

	public String getDistanceIncident() {
		return distanceIncident;
	}

	public void setDistanceIncident(String distanceIncident) {
		this.distanceIncident = distanceIncident;
	}

	public String getCriticite() {
		return criticite;
	}

	public void setCriticite(String criticite) {
		this.criticite = criticite;
	}

	public Boolean getDevisValid() {
		return devisValid;
	}

	public void setDevisValid(Boolean devisValid) {
		this.devisValid = devisValid;
	}

	public Boolean getIsAddDemande() {
		return isAddDemande;
	}

	public void setIsAddDemande(Boolean isAddDemande) {
		this.isAddDemande = isAddDemande;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	

	

}
