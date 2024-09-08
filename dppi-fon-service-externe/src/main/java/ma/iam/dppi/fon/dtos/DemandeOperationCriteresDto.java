package ma.iam.dppi.fon.dtos;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * @author Z.BELGHAOUTI
 *
 */
public class DemandeOperationCriteresDto {

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
	private byte[] fileInput;
	private String fileName;
	private List<MultipartFile> multipartFiles;
	private String descriptionIncident;
	private String localisationIncident;
    private Long pointObstacleGPSN;
	private Long pointObstacleGPSW;
	private String descriptionObstacle;
	private String typeComponent;
	private Date dateDebut;
	private Date dateFin;
	private String descriptionAcces;
	private Boolean typeIntervention;
	private String contactDemandeur;
	private Double xGpsPointRegeneration;
	private Double yGpsPointRegeneration;
	private String referanceRegenerationErpt;

	private String interlocuteur;

	// intervenent

	public List<MultipartFile> getMultipartFiles() {
		return multipartFiles;
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

	public void setMultipartFiles(List<MultipartFile> multipartFiles) {
		this.multipartFiles = multipartFiles;
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

	

	public byte[] getFileInput() {
		return fileInput;
	}

	public void setFileInput(byte[] fileInput) {
		this.fileInput = fileInput;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
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

	public String getContactDemandeur() {
		return contactDemandeur;
	}

	public void setContactDemandeur(String contactDemandeur) {
		this.contactDemandeur = contactDemandeur;
	}

	

	public Double getxGpsPointRegeneration() {
		return xGpsPointRegeneration;
	}

	public void setxGpsPointRegeneration(Double xGpsPointRegeneration) {
		this.xGpsPointRegeneration = xGpsPointRegeneration;
	}

	public Double getyGpsPointRegeneration() {
		return yGpsPointRegeneration;
	}

	public void setyGpsPointRegeneration(Double yGpsPointRegeneration) {
		this.yGpsPointRegeneration = yGpsPointRegeneration;
	}

	public String getReferanceRegenerationErpt() {
		return referanceRegenerationErpt;
	}

	public void setReferanceRegenerationErpt(String referanceRegenerationErpt) {
		this.referanceRegenerationErpt = referanceRegenerationErpt;
	}

	public String getInterlocuteur() {
		return interlocuteur;
	}

	public void setInterlocuteur(String interlocuteur) {
		this.interlocuteur = interlocuteur;
	}

	
	

	

}
