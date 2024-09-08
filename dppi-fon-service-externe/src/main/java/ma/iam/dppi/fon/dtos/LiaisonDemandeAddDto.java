package ma.iam.dppi.fon.dtos;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LiaisonDemandeAddDto {

	private Long idt;

	private String reference;

	private String codeSiteErpt;

	private Long idtOperateur;

	private String operateur;

	private Long idtCommune;

	private String communeLabel;

	private Long idtDr;

	private String drLabel;

	private Long idtDc;

	private String dcLabel;

	@JsonProperty("xGpsDepart")
	private Double xGpsDepart;

	@JsonProperty("xGpsArrivee")
	private Double xgpsArrivee;

	@JsonProperty("yGpsDepart")
	private Double ygpsDepart;

	@JsonProperty("yGpsArrivee")
	private Double ygpsArrivee;
	
	private String commentaire;

	private Long idtEtatLiaison;
	
	private Long idtEtatDemande;
	
	private Long idtType;
	
	private Boolean demandeAnnuler;
	
	private Boolean archive;
	
	private Long idtPieceJoint;
	
	private Long idtDemande;
	
	private Long idtTypeLiaison;
	
	
	
	private String contactDemandeur;

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

	public Double getXgpsArrivee() {
		return xgpsArrivee;
	}

	public void setXgpsArrivee(Double xgpsArrivee) {
		this.xgpsArrivee = xgpsArrivee;
	}

	public Double getYgpsDepart() {
		return ygpsDepart;
	}

	public void setYgpsDepart(Double ygpsDepart) {
		this.ygpsDepart = ygpsDepart;
	}

	public Double getYgpsArrivee() {
		return ygpsArrivee;
	}

	public void setYgpsArrivee(Double ygpsArrivee) {
		this.ygpsArrivee = ygpsArrivee;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public Long getIdtEtatLiaison() {
		return idtEtatLiaison;
	}

	public void setIdtEtatLiaison(Long idtEtatLiaison) {
		this.idtEtatLiaison = idtEtatLiaison;
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

	public Long getIdtPieceJoint() {
		return idtPieceJoint;
	}

	public void setIdtPieceJoint(Long idtPieceJoint) {
		this.idtPieceJoint = idtPieceJoint;
	}

	public Long getIdtDemande() {
		return idtDemande;
	}

	public void setIdtDemande(Long idtDemande) {
		this.idtDemande = idtDemande;
	}

	public Long getIdtTypeLiaison() {
		return idtTypeLiaison;
	}

	public void setIdtTypeLiaison(Long idtTypeLiaison) {
		this.idtTypeLiaison = idtTypeLiaison;
	}

	

	public String getContactDemandeur() {
		return contactDemandeur;
	}

	public void setContactDemandeur(String contactDemandeur) {
		this.contactDemandeur = contactDemandeur;
	}

	public String getCodeSiteErpt() {
		return codeSiteErpt;
	}

	public void setCodeSiteErpt(String codeSiteErpt) {
		this.codeSiteErpt = codeSiteErpt;
	}
	
	
}
