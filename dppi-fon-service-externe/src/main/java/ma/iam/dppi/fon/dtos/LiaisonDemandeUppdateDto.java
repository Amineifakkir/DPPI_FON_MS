package ma.iam.dppi.fon.dtos;

import java.util.Date;


//@JsonIgnoreProperties(ignoreUnknown = true)
public class LiaisonDemandeUppdateDto {

	private Long idt;

	private String codeLiaisonErpt;
	
	private String commentaire;
	
	private Long idtDemande;

	private Double xGpsDepart;

	private Double xGpsArrivee;

	private Double yGpsDepart;

	private Double yGpsArrivee;

	private Date dateCommentaire;
	
	private LiaisonDto demandeGc;

	private DemandeDto demandeDto;
	
	private String source;
	
	private String dateReception;
	

	public Date getDateCommentaire() {
		return dateCommentaire;
	}

	public void setDateCommentaire(Date dateCommentaire) {
		this.dateCommentaire = dateCommentaire;
	}

	public LiaisonDto getDemandeGc() {
		return demandeGc;
	}

	public void setDemandeGc(LiaisonDto demandeGc) {
		this.demandeGc = demandeGc;
	}

	public DemandeDto getDemandeDto() {
		return demandeDto;
	}

	public void setDemandeDto(DemandeDto demandeDto) {
		this.demandeDto = demandeDto;
	}

	

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Long getIdtDemande() {
		return idtDemande;
	}

	public void setIdtDemande(Long idtDemande) {
		this.idtDemande = idtDemande;
	}

	public Long getIdt() {
		return idt;
	}

	public void setIdt(Long idt) {
		this.idt = idt;
	}


	public String getCodeLiaisonErpt() {
		return codeLiaisonErpt;
	}

	public void setCodeLiaisonErpt(String codeLiaisonErpt) {
		this.codeLiaisonErpt = codeLiaisonErpt;
	}

	
	

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
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

	public Double getxGpsDepart() {
		return xGpsDepart;
	}

	public void setxGpsDepart(Double xGpsDepart) {
		this.xGpsDepart = xGpsDepart;
	}

	public Double getxGpsArrivee() {
		return xGpsArrivee;
	}

	public void setxGpsArrivee(Double xGpsArrivee) {
		this.xGpsArrivee = xGpsArrivee;
	}

	public Double getyGpsDepart() {
		return yGpsDepart;
	}

	public void setyGpsDepart(Double yGpsDepart) {
		this.yGpsDepart = yGpsDepart;
	}

	public Double getyGpsArrivee() {
		return yGpsArrivee;
	}

	public void setyGpsArrivee(Double yGpsArrivee) {
		this.yGpsArrivee = yGpsArrivee;
	}

	public String getDateReception() {
		return dateReception;
	}

	public void setDateReception(String dateReception) {
		this.dateReception = dateReception;
	}

	
	

	
	
	

	

	
	


	
	
	
}
