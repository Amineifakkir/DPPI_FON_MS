package ma.iam.dppi.fon.dtos;

import java.util.Date;
import java.util.List;

public class LiaisonDto {

	private Long idt;

	private String reference;

	// todo salmane delete
//	private String codeLiaisonErpt;

	private String codeSiteErpt;

	private Long idtOperateur;

	private String operateurCode;

	private String operateurLabel;

	private String operateur;

	private Long idtCommune;

	private String communeLabel;

	private Long idtDr;

	private String drLabel;

	private Long idtDc;

	private String dcLabel;

	private Double xGpsDepart;

	private Double yGpsDepart;

	private Double xGpsArrivee;

	private Double yGpsArrivee;

	private String distanceDisponible;

	private String distanceSature;

	private String distanceAerien;

	private String labelEtat;

	private Long idtEtatLiaison;
	private String codeEtatLiaison;
	private String contactDemandeur;
	private Boolean demandeAnnuler;
	private Double xGpsPr;

	private Boolean archive;
	private DemandeDto demandes;
	private List<DemandeDto> listDemande;
	private List<SousLiaisonDto> sousLiaisons;
	private List<InteractionDto> interactions;
	private String dateDemande;
	private String nomDemandeur;
	private String prenomDemandeur;
	private Long idtDemandeur;
	private String demandeurLogin;
	private Long idtDemande;
	private String commantaireDemande;
	private String etatDemande;
	private Long idtTypeDemande;
	private String codeTypeDemande;
	private String labelTypeDemande;
	private String commentaire;
	private String etatDemandeLabel;
	private Date dateDebut;
	private Date dateFin;
	private String descriptionIncident;
	private List<DevisDto> devis;

	private String localisationIncident;

	private Long pointObstacleGPSN;

	private Long pointObstacleGPSW;

	private String descriptionObstacle;

	private String etatDemandeCode;
	private Long idtEtatDemande;

	private Long idtSite1;
	private String nomSite1;
	private String codeSite1;
	private Long idtSite2;
	private String nomSite2;
	private String codeSite2;

	private String codeSite;
	private Long idtTypeLiaison;
	private String codeTypeLiaison;
	private String codeSr;
	
	private String dateReception;
	
	

	public Long getIdt() {
		return idt;
	}

	public void setIdt(Long idt) {
		this.idt = idt;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	

	public String getCodeSiteErpt() {
		return codeSiteErpt;
	}

	public void setCodeSiteErpt(String codeSiteErpt) {
		this.codeSiteErpt = codeSiteErpt;
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

	public String getOperateurLabel() {
		return operateurLabel;
	}

	public void setOperateurLabel(String operateurLabel) {
		this.operateurLabel = operateurLabel;
	}

	public String getOperateur() {
		return operateur;
	}

	public void setOperateur(String operateur) {
		this.operateur = operateur;
	}

	public Long getIdtCommune() {
		return idtCommune;
	}

	public void setIdtCommune(Long idtCommune) {
		this.idtCommune = idtCommune;
	}

	public String getCommuneLabel() {
		return communeLabel;
	}

	public void setCommuneLabel(String communeLabel) {
		this.communeLabel = communeLabel;
	}

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

	public Double getxGpsDepart() {
		return xGpsDepart;
	}

	public void setxGpsDepart(Double xGpsDepart) {
		this.xGpsDepart = xGpsDepart;
	}

	public Double getyGpsDepart() {
		return yGpsDepart;
	}

	public void setyGpsDepart(Double yGpsDepart) {
		this.yGpsDepart = yGpsDepart;
	}

	public Double getxGpsArrivee() {
		return xGpsArrivee;
	}

	public void setxGpsArrivee(Double xGpsArrivee) {
		this.xGpsArrivee = xGpsArrivee;
	}

	public Double getyGpsArrivee() {
		return yGpsArrivee;
	}

	public void setyGpsArrivee(Double yGpsArrivee) {
		this.yGpsArrivee = yGpsArrivee;
	}

	public String getDistanceDisponible() {
		return distanceDisponible;
	}

	public void setDistanceDisponible(String distanceDisponible) {
		this.distanceDisponible = distanceDisponible;
	}

	public String getDistanceSature() {
		return distanceSature;
	}

	public void setDistanceSature(String distanceSature) {
		this.distanceSature = distanceSature;
	}

	public String getDistanceAerien() {
		return distanceAerien;
	}

	public void setDistanceAerien(String distanceAerien) {
		this.distanceAerien = distanceAerien;
	}

	public String getLabelEtat() {
		return labelEtat;
	}

	public void setLabelEtat(String labelEtat) {
		this.labelEtat = labelEtat;
	}

	public Long getIdtEtatLiaison() {
		return idtEtatLiaison;
	}

	public void setIdtEtatLiaison(Long idtEtatLiaison) {
		this.idtEtatLiaison = idtEtatLiaison;
	}

	public String getCodeEtatLiaison() {
		return codeEtatLiaison;
	}

	public void setCodeEtatLiaison(String codeEtatLiaison) {
		this.codeEtatLiaison = codeEtatLiaison;
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

	public List<DemandeDto> getListDemande() {
		return listDemande;
	}

	public void setListDemande(List<DemandeDto> listDemande) {
		this.listDemande = listDemande;
	}



	public List<SousLiaisonDto> getSousLiaisons() {
		return sousLiaisons;
	}

	public void setSousLiaisons(List<SousLiaisonDto> sousLiaisons) {
		this.sousLiaisons = sousLiaisons;
	}

	public List<InteractionDto> getInteractions() {
		return interactions;
	}

	public void setInteractions(List<InteractionDto> interactions) {
		this.interactions = interactions;
	}

	public String getDateDemande() {
		return dateDemande;
	}

	public void setDateDemande(String dateDemande) {
		this.dateDemande = dateDemande;
	}

	public String getNomDemandeur() {
		return nomDemandeur;
	}

	public void setNomDemandeur(String nomDemandeur) {
		this.nomDemandeur = nomDemandeur;
	}

	public Long getIdtDemandeur() {
		return idtDemandeur;
	}

	public void setIdtDemandeur(Long idtDemandeur) {
		this.idtDemandeur = idtDemandeur;
	}

	public String getDemandeurLogin() {
		return demandeurLogin;
	}

	public void setDemandeurLogin(String demandeurLogin) {
		this.demandeurLogin = demandeurLogin;
	}

	public Long getIdtDemande() {
		return idtDemande;
	}

	public void setIdtDemande(Long idtDemande) {
		this.idtDemande = idtDemande;
	}

	public String getCommantaireDemande() {
		return commantaireDemande;
	}

	public void setCommantaireDemande(String commantaireDemande) {
		this.commantaireDemande = commantaireDemande;
	}

	public String getEtatDemande() {
		return etatDemande;
	}

	public void setEtatDemande(String etatDemande) {
		this.etatDemande = etatDemande;
	}

	public Long getIdtTypeDemande() {
		return idtTypeDemande;
	}

	public void setIdtTypeDemande(Long idtTypeDemande) {
		this.idtTypeDemande = idtTypeDemande;
	}

	public String getCodeTypeDemande() {
		return codeTypeDemande;
	}

	public void setCodeTypeDemande(String codeTypeDemande) {
		this.codeTypeDemande = codeTypeDemande;
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

	public String getEtatDemandeCode() {
		return etatDemandeCode;
	}

	public void setEtatDemandeCode(String etatDemandeCode) {
		this.etatDemandeCode = etatDemandeCode;
	}

	public Long getIdtEtatDemande() {
		return idtEtatDemande;
	}

	public void setIdtEtatDemande(Long idtEtatDemande) {
		this.idtEtatDemande = idtEtatDemande;
	}

	

	public String getCodeSite() {
		return codeSite;
	}

	public void setCodeSite(String codeSite) {
		this.codeSite = codeSite;
	}

	



	public String getContactDemandeur() {
		return contactDemandeur;
	}

	public void setContactDemandeur(String contactDemandeur) {
		this.contactDemandeur = contactDemandeur;
	}

	public Long getIdtSite1() {
		return idtSite1;
	}

	public void setIdtSite1(Long idtSite1) {
		this.idtSite1 = idtSite1;
	}

	public String getNomSite1() {
		return nomSite1;
	}

	public void setNomSite1(String nomSite1) {
		this.nomSite1 = nomSite1;
	}

	public String getCodeSite1() {
		return codeSite1;
	}

	public void setCodeSite1(String codeSite1) {
		this.codeSite1 = codeSite1;
	}

	public Long getIdtSite2() {
		return idtSite2;
	}

	public void setIdtSite2(Long idtSite2) {
		this.idtSite2 = idtSite2;
	}

	public String getNomSite2() {
		return nomSite2;
	}

	public void setNomSite2(String nomSite2) {
		this.nomSite2 = nomSite2;
	}

	public String getCodeSite2() {
		return codeSite2;
	}

	public void setCodeSite2(String codeSite2) {
		this.codeSite2 = codeSite2;
	}

	public Long getIdtTypeLiaison() {
		return idtTypeLiaison;
	}

	public void setIdtTypeLiaison(Long idtTypeLiaison) {
		this.idtTypeLiaison = idtTypeLiaison;
	}

	public String getCodeTypeLiaison() {
		return codeTypeLiaison;
	}

	public void setCodeTypeLiaison(String codeTypeLiaison) {
		this.codeTypeLiaison = codeTypeLiaison;
	}

	public String getCodeSr() {
		return codeSr;
	}

	public void setCodeSr(String codeSr) {
		this.codeSr = codeSr;
	}

	public Double getxGpsPr() {
		return xGpsPr;
	}

	public void setxGpsPr(Double xGpsPr) {
		this.xGpsPr = xGpsPr;
	}

	public List<DevisDto> getDevis() {
		return devis;
	}

	public void setDevis(List<DevisDto> devis) {
		this.devis = devis;
	}

	public String getDateReception() {
		return dateReception;
	}

	public void setDateReception(String dateReception) {
		this.dateReception = dateReception;
	}

	public DemandeDto getDemandes() {
		return demandes;
	}

	public void setDemandes(DemandeDto demandes) {
		this.demandes = demandes;
	}

	public String getPrenomDemandeur() {
		return prenomDemandeur;
	}

	public void setPrenomDemandeur(String prenomDemandeur) {
		this.prenomDemandeur = prenomDemandeur;
	}

	public String getLabelTypeDemande() {
		return labelTypeDemande;
	}

	public void setLabelTypeDemande(String labelTypeDemande) {
		this.labelTypeDemande = labelTypeDemande;
	}

	

	
	
	
	

}
