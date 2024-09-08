package ma.iam.dppi.fon.interne.dtos;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class DemandeDto {

	private Long idt;
	private String CodeFonErpt;

	private String dateDemande;
	private String dateDebut;
	private String dateReception;

	private String dateFin;
	private String motif;

	private String commentaire;

	private List<Long> liaison;

	private Long etatDemandeIdt;
	private String etatDemandeLabel;
	private String etatDemandeCode;

	private Long idtDemandeur;
	private String nomDemandeur;
	private String prenomDemandeur;
	private String loginDemandeur;

	private Long typeDemandeIdt;
	private String typeDemandeLabel;
	private String typeDemandeCode;

	private Long typeSaturationIdt;
	private String typeSaturationLabel;
	private String typeSaturationCode;

	private String intervenant;
	private String diagnostic;
	private String actionsRecommandees;
	private String repondeur;
	private Long idtDrReponse;

	private String distanceMesureLiaisonFon;
	private String axeGlobalMesure;
	private String bilanOptiqueDb;
	private String contactEmail;
	private String telephoneDr;
	private String nomInterlocuteurDr;
	private String prenomInterlocuteurDr;

	private MultipartFile file;
	public Long getIdt() {
		return idt;
	}

	public void setIdt(Long idt) {
		this.idt = idt;
	}

	public String getDateDemande() {
		return dateDemande;
	}

	public void setDateDemande(String dateDemande) {
		this.dateDemande = dateDemande;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public Long getEtatDemandeIdt() {
		return etatDemandeIdt;
	}

	public void setEtatDemandeIdt(Long etatDemandeIdt) {
		this.etatDemandeIdt = etatDemandeIdt;
	}

	public String getEtatDemandeLabel() {
		return etatDemandeLabel;
	}

	public void setEtatDemandeLabel(String etatDemandeLabel) {
		this.etatDemandeLabel = etatDemandeLabel;
	}

	public String getEtatDemandeCode() {
		return etatDemandeCode;
	}

	public void setEtatDemandeCode(String etatDemandeCode) {
		this.etatDemandeCode = etatDemandeCode;
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

	public Long getTypeDemandeIdt() {
		return typeDemandeIdt;
	}

	public void setTypeDemandeIdt(Long typeDemandeIdt) {
		this.typeDemandeIdt = typeDemandeIdt;
	}

	public String getTypeDemandeLabel() {
		return typeDemandeLabel;
	}

	public void setTypeDemandeLabel(String typeDemandeLabel) {
		this.typeDemandeLabel = typeDemandeLabel;
	}

	public String getTypeDemandeCode() {
		return typeDemandeCode;
	}

	public void setTypeDemandeCode(String typeDemandeCode) {
		this.typeDemandeCode = typeDemandeCode;
	}

	public Long getTypeSaturationIdt() {
		return typeSaturationIdt;
	}

	public void setTypeSaturationIdt(Long typeSaturationIdt) {
		this.typeSaturationIdt = typeSaturationIdt;
	}

	public String getTypeSaturationLabel() {
		return typeSaturationLabel;
	}

	public void setTypeSaturationLabel(String typeSaturationLabel) {
		this.typeSaturationLabel = typeSaturationLabel;
	}

	public String getTypeSaturationCode() {
		return typeSaturationCode;
	}

	public void setTypeSaturationCode(String typeSaturationCode) {
		this.typeSaturationCode = typeSaturationCode;
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

	public String getMotif() {
		return motif;
	}

	public void setMotif(String motif) {
		this.motif = motif;
	}

	public String getCodeFonErpt() {
		return CodeFonErpt;
	}

	public void setCodeFonErpt(String codeFonErpt) {
		CodeFonErpt = codeFonErpt;
	}

	public List<Long> getLiaison() {
		return liaison;
	}

	public void setLiaison(List<Long> liaison) {
		this.liaison = liaison;
	}

	public String getDateReception() {
		return dateReception;
	}

	public void setDateReception(String dateReception) {
		this.dateReception = dateReception;
	}

	public String getIntervenant() {
		return intervenant;
	}

	public void setIntervenant(String intervenant) {
		this.intervenant = intervenant;
	}

	public String getActionsRecommandees() {
		return actionsRecommandees;
	}

	public void setActionsRecommandees(String actionsRecommandees) {
		this.actionsRecommandees = actionsRecommandees;
	}

	public String getRepondeur() {
		return repondeur;
	}

	public void setRepondeur(String repondeur) {
		this.repondeur = repondeur;
	}

	public Long getIdtDrReponse() {
		return idtDrReponse;
	}

	public void setIdtDrReponse(Long idtDrReponse) {
		this.idtDrReponse = idtDrReponse;
	}

	public String getDiagnostic() {
		return diagnostic;
	}

	public void setDiagnostic(String diagnostic) {
		this.diagnostic = diagnostic;
	}

	public String getDistanceMesureLiaisonFon() {
		return distanceMesureLiaisonFon;
	}

	public void setDistanceMesureLiaisonFon(String distanceMesureLiaisonFon) {
		this.distanceMesureLiaisonFon = distanceMesureLiaisonFon;
	}

	public String getAxeGlobalMesure() {
		return axeGlobalMesure;
	}

	public void setAxeGlobalMesure(String axeGlobalMesure) {
		this.axeGlobalMesure = axeGlobalMesure;
	}

	public String getBilanOptiqueDb() {
		return bilanOptiqueDb;
	}

	public void setBilanOptiqueDb(String bilanOptiqueDb) {
		this.bilanOptiqueDb = bilanOptiqueDb;
	}

	public String getNomInterlocuteurDr() {
		return nomInterlocuteurDr;
	}

	public void setNomInterlocuteurDr(String nomInterlocuteurDr) {
		this.nomInterlocuteurDr = nomInterlocuteurDr;
	}

	public String getPrenomInterlocuteurDr() {
		return prenomInterlocuteurDr;
	}

	public void setPrenomInterlocuteurDr(String prenomInterlocuteurDr) {
		this.prenomInterlocuteurDr = prenomInterlocuteurDr;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public String getTelephoneDr() {
		return telephoneDr;
	}

	public void setTelephoneDr(String telephoneDr) {
		this.telephoneDr = telephoneDr;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}
	
	

}
