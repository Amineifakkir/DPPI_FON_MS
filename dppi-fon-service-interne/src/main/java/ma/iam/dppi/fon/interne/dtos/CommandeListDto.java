package ma.iam.dppi.fon.interne.dtos;

import java.util.List;

/**
 * 
 * @author Z.BELGHAOUTI
 *
 */
public class CommandeListDto {

	private Long idt;
	private Long idtDemande;
	private String reference;
	private Long drIdt;
	private String dr;
	private Long dcIdt;
	private String dc;
	private Long communeIdt;
	private String commune;
	private Long etatLiaisonIdt;
	private String etatLiaisonCode;
	private String etatLiaisonLabel;
	private Long etatDemandeIdt;
	private String etatDemandeCode;
	private String etatDemandeLabel;
	private Long operateurIdt;
	private String operateurCode;
	private String operateurLabel;
	private Long typeDemandeIdt;
	private String typeDemandeCode;
	private String typeDemandeLabel;
	private Double xGpsDepart;
	private Double yGpsArrivee;
	private Double xGpsArrivee;
	private Double yGpsDepart;
	private String codeSiteErpt;
	private String distanceDisponible;
	private String distanceSature;
	private String demandeurLogin;
	private String demandeurNom;
	private String demandeurPrenom;
	private String commentaire;
	private String dateDemande;
	private List<DemandeDto> listDemande;
	private List<InteractionDto> listInteraction;
	private List<SousLiaisonDto> listSousLiaison;
	private String codeSr ;
	private String typeLiaisonCode;

	public Long getIdt() {
		return idt;
	}

	public void setIdt(Long idt) {
		this.idt = idt;
	}

	public Long getIdtDemande() {
		return idtDemande;
	}

	public void setIdtDemande(Long idtDemande) {
		this.idtDemande = idtDemande;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public Long getDrIdt() {
		return drIdt;
	}

	public void setDrIdt(Long drIdt) {
		this.drIdt = drIdt;
	}

	public String getDr() {
		return dr;
	}

	public void setDr(String dr) {
		this.dr = dr;
	}

	public Long getDcIdt() {
		return dcIdt;
	}

	public void setDcIdt(Long dcIdt) {
		this.dcIdt = dcIdt;
	}

	public String getDc() {
		return dc;
	}

	public void setDc(String dc) {
		this.dc = dc;
	}

	public Long getCommuneIdt() {
		return communeIdt;
	}

	public void setCommuneIdt(Long communeIdt) {
		this.communeIdt = communeIdt;
	}

	public String getCommune() {
		return commune;
	}

	public void setCommune(String commune) {
		this.commune = commune;
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

	public Long getEtatDemandeIdt() {
		return etatDemandeIdt;
	}

	public void setEtatDemandeIdt(Long etatDemandeIdt) {
		this.etatDemandeIdt = etatDemandeIdt;
	}

	public String getEtatDemandeCode() {
		return etatDemandeCode;
	}

	public void setEtatDemandeCode(String etatDemandeCode) {
		this.etatDemandeCode = etatDemandeCode;
	}

	public String getEtatDemandeLabel() {
		return etatDemandeLabel;
	}

	public void setEtatDemandeLabel(String etatDemandeLabel) {
		this.etatDemandeLabel = etatDemandeLabel;
	}

	public Long getOperateurIdt() {
		return operateurIdt;
	}

	public void setOperateurIdt(Long operateurIdt) {
		this.operateurIdt = operateurIdt;
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

	public Long getTypeDemandeIdt() {
		return typeDemandeIdt;
	}

	public void setTypeDemandeIdt(Long typeDemandeIdt) {
		this.typeDemandeIdt = typeDemandeIdt;
	}

	public String getTypeDemandeCode() {
		return typeDemandeCode;
	}

	public void setTypeDemandeCode(String typeDemandeCode) {
		this.typeDemandeCode = typeDemandeCode;
	}

	public String getTypeDemandeLabel() {
		return typeDemandeLabel;
	}

	public void setTypeDemandeLabel(String typeDemandeLabel) {
		this.typeDemandeLabel = typeDemandeLabel;
	}

	
	

	public Double getxGpsDepart() {
		return xGpsDepart;
	}

	public void setxGpsDepart(Double xGpsDepart) {
		this.xGpsDepart = xGpsDepart;
	}

	public Double getyGpsArrivee() {
		return yGpsArrivee;
	}

	public void setyGpsArrivee(Double yGpsArrivee) {
		this.yGpsArrivee = yGpsArrivee;
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

	public String getCodeSiteErpt() {
		return codeSiteErpt;
	}

	public void setCodeSiteErpt(String codeSiteErpt) {
		this.codeSiteErpt = codeSiteErpt;
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

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public String getDateDemande() {
		return dateDemande;
	}

	public void setDateDemande(String dateDemande) {
		this.dateDemande = dateDemande;
	}

	public List<DemandeDto> getListDemande() {
		return listDemande;
	}

	public void setListDemande(List<DemandeDto> listDemande) {
		this.listDemande = listDemande;
	}

	public List<InteractionDto> getListInteraction() {
		return listInteraction;
	}

	public void setListInteraction(List<InteractionDto> listInteraction) {
		this.listInteraction = listInteraction;
	}

	

	public List<SousLiaisonDto> getListSousLiaison() {
		return listSousLiaison;
	}

	public void setListSousLiaison(List<SousLiaisonDto> listSousLiaison) {
		this.listSousLiaison = listSousLiaison;
	}

	public String getCodeSr() {
		return codeSr;
	}

	public void setCodeSr(String codeSr) {
		this.codeSr = codeSr;
	}

	public String getTypeLiaisonCode() {
		return typeLiaisonCode;
	}

	public void setTypeLiaisonCode(String typeLiaisonCode) {
		this.typeLiaisonCode = typeLiaisonCode;
	}

}
