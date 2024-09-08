package ma.iam.dppi.fon.interne.dtos;

import java.util.Date;

public class OperationLiaisonDto {

	private Long idt;
	private Long idtDemandeur;
	private String nomDemandeur;
	private String prenomDemandeur;
	private String loginDemandeur;
	private Date date;
	private Long etatLiaisonIdt;
	private String etatLiaisonCode;
	private String etatLiaisonLabel;
	private String commentaire;
	private Long idtLiaison;
	private Long idtDemande;


	public Long getIdt() {
		return idt;
	}

	public void setIdt(Long idt) {
		this.idt = idt;
	}

	public Long getIdtDemandeur() {
		return idtDemandeur;
	}

	public void setIdtDemandeur(Long idtDemandeur) {
		this.idtDemandeur = idtDemandeur;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Long getEtatLiaisonIdt() {
		return etatLiaisonIdt;
	}

	public void setEtatLiaisonIdt(Long etatLiaisonIdt) {
		this.etatLiaisonIdt = etatLiaisonIdt;
	}

	public String getEtatLiaisonCode() {
		return etatLiaisonCode;
	}

	public void setEtatLiaisonCode(String etatLiaisonCode) {
		this.etatLiaisonCode = etatLiaisonCode;
	}

	public String getEtatLiaisonLabel() {
		return etatLiaisonLabel;
	}

	public void setEtatLiaisonLabel(String etatLiaisonLabel) {
		this.etatLiaisonLabel = etatLiaisonLabel;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
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

	public Long getIdtLiaison() {
		return idtLiaison;
	}

	public void setIdtLiaison(Long idtLiaison) {
		this.idtLiaison = idtLiaison;
	}

	public Long getIdtDemande() {
		return idtDemande;
	}

	public void setIdtDemande(Long idtDemande) {
		this.idtDemande = idtDemande;
	}

	
}
