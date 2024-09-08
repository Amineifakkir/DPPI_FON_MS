package ma.iam.dppi.fon.interne.dtos;

/**
 * 
 * @author Z.BELGHAOUTI
 *
 */
public class TraceCommentaireSousLiaisonDto {

	private Long idt;
	private String commentaire;
	private String dateCommentaire;
	private String demandeurLogin;
	private String demandeurNom;
	private String demandeurPrenom;
	private String entite;
	private Long tronconIdt;

	public Long getIdt() {
		return idt;
	}

	public void setIdt(Long idt) {
		this.idt = idt;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public String getDateCommentaire() {
		return dateCommentaire;
	}

	public void setDateCommentaire(String dateCommentaire) {
		this.dateCommentaire = dateCommentaire;
	}

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

	public Long getTronconIdt() {
		return tronconIdt;
	}

	public void setTronconIdt(Long tronconIdt) {
		this.tronconIdt = tronconIdt;
	}

}
