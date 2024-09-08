package ma.iam.dppi.fon.dtos;

import java.util.List;

public class DemandeDto {

	private Long idt;
	private String dateDemande;
	private String commentaire;
	private String etatDemandeLabel;

	private String etatDemandeCode;
	private Long idtEtatDemande;
	
	private Long idtDemandeur;

	private String nomDemandeur;
	
	private String prenomDemandeur;

	private String loginDemandeur;
	
	
	private String liaisonRef;
	private String liaisonCodeLiaisonErpt;
	private Long idtDr;
	private String drLabel;
	private Long idtDc;
	private String dcLabel;
	private Long idtLiaison;
	private LiaisonDto liaison;
	
private Boolean demandeAnnuler;
	
	private Boolean archive;
	
	private Double xGpsArrivee;

	private Double yGpsDepart;

	private Double yGpsArrivee;
	
	
	private String typeDesignation;
	private String typeCode;
	private Long idtType;
	
	private String typeDesaturationLabel;
	private String typeDesaturationCode;
	private Long idtTypeDesaturationDemande;
	
	private List<InteractionDto> interactions;
//	private List<EscaladeDto> escalades;
	private List<DevisDto> devis;
	
	private Long numOderEscalade;
	
	private String interlocuteur;

	
	
	public List<DevisDto> getDevis() {
		return devis;
	}
	public void setDevis(List<DevisDto> devis) {
		this.devis = devis;
	}
	public Long getNumOderEscalade() {
		return numOderEscalade;
	}
	public void setNumOderEscalade(Long numOderEscalade) {
		this.numOderEscalade = numOderEscalade;
	}
//	public List<EscaladeDto> getEscalades() {
//		return escalades;
//	}
//	public void setEscalades(List<EscaladeDto> escalades) {
//		this.escalades = escalades;
//	}
	public List<InteractionDto> getInteractions() {
		return interactions;
	}
	public void setInteractions(List<InteractionDto> interactions) {
		this.interactions = interactions;
	}
	public Long getIdtLiaison() {
		return idtLiaison;
	}
	public void setIdtLiaison(Long idtLiaison) {
		this.idtLiaison = idtLiaison;
	}
	public Boolean getDemandeAnnuler() {
		return demandeAnnuler;
	}
	public void setDemandeAnnuler(Boolean demandeAnnuler) {
		this.demandeAnnuler = demandeAnnuler;
	}
	public Boolean getArchive() {
		return archive;
	}
	public void setArchive(Boolean archive) {
		this.archive = archive;
	}
	public LiaisonDto getLiaison() {
		return liaison;
	}
	public void setLiaison(LiaisonDto liaison) {
		this.liaison = liaison;
	}
	private Double xGpsDepart;

	public Long getIdtDr() {
		return idtDr;
	}
	public void setIdtDr(Long idtDr) {
		this.idtDr = idtDr;
	}
	public String getDrLabel() {
		return drLabel;
	}
	public void setDrLabel(String drLabel) {
		this.drLabel = drLabel;
	}
	public Long getIdtDc() {
		return idtDc;
	}
	public void setIdtDc(Long idtDc) {
		this.idtDc = idtDc;
	}
	public String getDcLabel() {
		return dcLabel;
	}
	public void setDcLabel(String dcLabel) {
		this.dcLabel = dcLabel;
	}
	public Double getXGpsDepart() {
		return xGpsDepart;
	}
	public void setXGpsDepart(Double xGpsDepart) {
		this.xGpsDepart = xGpsDepart;
	}
	public Double getXGpsArrivee() {
		return xGpsArrivee;
	}
	public void setXGpsArrivee(Double xGpsArrivee) {
		this.xGpsArrivee = xGpsArrivee;
	}
	public Double getYGpsDepart() {
		return yGpsDepart;
	}
	public void setYGpsDepart(Double yGpsDepart) {
		this.yGpsDepart = yGpsDepart;
	}
	public Double getYGpsArrivee() {
		return yGpsArrivee;
	}
	public void setYGpsArrivee(Double yGpsArrivee) {
		this.yGpsArrivee = yGpsArrivee;
	}
	
	
	
	
	public String getLiaisonRef() {
		return liaisonRef;
	}
	public void setLiaisonRef(String liaisonRef) {
		this.liaisonRef = liaisonRef;
	}
	public String getLiaisonCodeLiaisonErpt() {
		return liaisonCodeLiaisonErpt;
	}
	public void setLiaisonCodeLiaisonErpt(String liaisonCodeLiaisonErpt) {
		this.liaisonCodeLiaisonErpt = liaisonCodeLiaisonErpt;
	}
	public String getTypeDesignation() {
		return typeDesignation;
	}
	public void setTypeDesignation(String typeDesignation) {
		this.typeDesignation = typeDesignation;
	}
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	public Long getIdtType() {
		return idtType;
	}
	public void setIdtType(Long idtType) {
		this.idtType = idtType;
	}
	public String getTypeDesaturationLabel() {
		return typeDesaturationLabel;
	}
	public void setTypeDesaturationLabel(String typeDesaturationLabel) {
		this.typeDesaturationLabel = typeDesaturationLabel;
	}
	public String getTypeDesaturationCode() {
		return typeDesaturationCode;
	}
	public void setTypeDesaturationCode(String typeDesaturationCode) {
		this.typeDesaturationCode = typeDesaturationCode;
	}
	public Long getIdtTypeDesaturationDemande() {
		return idtTypeDesaturationDemande;
	}
	public void setIdtTypeDesaturationDemande(Long idtTypeDesaturationDemande) {
		this.idtTypeDesaturationDemande = idtTypeDesaturationDemande;
	}
	public Long getIdtEtatDemande() {
		return idtEtatDemande;
	}
	public void setIdtEtatDemande(Long idtEtatDemande) {
		this.idtEtatDemande = idtEtatDemande;
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
	public String getInterlocuteur() {
		return interlocuteur;
	}
	public void setInterlocuteur(String interlocuteur) {
		this.interlocuteur = interlocuteur;
	}


	
	
}
