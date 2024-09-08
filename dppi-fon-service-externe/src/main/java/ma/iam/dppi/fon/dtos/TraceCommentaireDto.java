package ma.iam.dppi.fon.dtos;

import java.util.Date;



public class TraceCommentaireDto {

	private Long idt;
	
	private Date dateCommentaire;

	private LiaisonDto liaisonDto;

	private DemandeDto demandeDto;
	
	private String commentaire;

	private String source;
	
	private String demandeurLogin;
	
	private String demandeurNom;
	
	private String demandeurPrenom;
	
	private String entite;
	
	private Long idtDemande;

	
	
	public DemandeDto getDemandeDto() {
		return demandeDto;
	}

	public void setDemandeDto(DemandeDto demandeDto) {
		this.demandeDto = demandeDto;
	}

	public Long getIdt() {
		return idt;
	}

	public void setIdt(Long idt) {
		this.idt = idt;
	}

	public Date getDateCommentaire() {
		return dateCommentaire;
	}

	public void setDateCommentaire(Date dateCommentaire) {
		this.dateCommentaire = dateCommentaire;
	}

	public LiaisonDto getliaisonDto() {
		return liaisonDto;
	}

	public void setliaisonDto(LiaisonDto liaisonDto) {
		this.liaisonDto = liaisonDto;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}



	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

//	public LiaisonDto getLiaisonDto() {
//		return liaisonDto;
//	}
//
//	public void setLiaisonDto(LiaisonDto liaisonDto) {
//		this.liaisonDto = liaisonDto;
//	}

	public String getDemandeurLogin() {
		return demandeurLogin;
	}

	public void setDemandeurLogin(String demandeurLogin) {
		this.demandeurLogin = demandeurLogin;
	}

	public String getDemandeurNom() {
		return demandeurNom;
	}

	public void setDemandeurNom(String demandeurNom) {
		this.demandeurNom = demandeurNom;
	}

	public String getDemandeurPrenom() {
		return demandeurPrenom;
	}

	public void setDemandeurPrenom(String demandeurPrenom) {
		this.demandeurPrenom = demandeurPrenom;
	}

	public String getEntite() {
		return entite;
	}

	public void setEntite(String entite) {
		this.entite = entite;
	}

	public Long getIdtDemande() {
		return idtDemande;
	}

	public void setIdtDemande(Long idtDemande) {
		this.idtDemande = idtDemande;
	}
	
	
	
	
}
