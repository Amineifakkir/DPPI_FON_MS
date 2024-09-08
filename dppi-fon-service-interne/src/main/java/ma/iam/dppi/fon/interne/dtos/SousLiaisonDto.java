package ma.iam.dppi.fon.interne.dtos;

public class SousLiaisonDto {

	private Long idt;
	private String code;
	private Long drIdt;
	private String dr;
	private Long dcIdt;
	private String dc;
	private Long communeIdt;
	private String commune;
	private Long site1Idt;
	private String site1Label;
	private Double site1N;
	private Double site1W;
	private Long site2Idt;
	private String site2Label;
	private Double site2N;
	private Double site2W;
	private Long liaisonIdt;
	private String distanceDisponible;
	private String distanceSature;
	private String distance;
	private Integer number;

	private String commentaire;
	private String dateOperation;
	private String dateReponse;
	private Long demandeIdt;
	private Long etatDemandeSousLiaisonIdt;
	private String etatDemandeSousLiaisonCode;
	private String etatDemandeSousLiaisonLabel;
	private String demandeurLogin;
	private String demandeurNom;
	private String demandeurPrenom;
	private String repondeurLogin;
	private String repondeurNom;
	private String repondeurPrenom;

	private Long raisonInfaisabiliteIdt;
	private String raisonInfaisabiliteCode;
	private String raisonInfaisabiliteLabel;

	private String descriptionInfaisabilite;
	private String solutionAlternative;

	private SiteDto site1;
	private SiteDto site2;

	private String cableFoPropose;
	private String bilanObtiqueEstimatif;
	private String delaisRealisation;
	private String distanceEstimative;

	private String contactDemt;
	private String interlocuteurDemt;
	private String contactDr;
	private String interlocuteurDr;
	private String dateAffectation;
	
	private String designationSousLiaison;

	private String designationTronconSousLiaison;

	public Long getIdt() {
		return idt;
	}

	public void setIdt(Long idt) {
		this.idt = idt;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public Long getSite1Idt() {
		return site1Idt;
	}

	public void setSite1Idt(Long site1Idt) {
		this.site1Idt = site1Idt;
	}

	public String getSite1Label() {
		return site1Label;
	}

	public void setSite1Label(String site1Label) {
		this.site1Label = site1Label;
	}

	public Double getSite1N() {
		return site1N;
	}

	public void setSite1N(Double site1n) {
		site1N = site1n;
	}

	public Double getSite1W() {
		return site1W;
	}

	public void setSite1W(Double site1w) {
		site1W = site1w;
	}

	public Long getSite2Idt() {
		return site2Idt;
	}

	public void setSite2Idt(Long site2Idt) {
		this.site2Idt = site2Idt;
	}

	public String getSite2Label() {
		return site2Label;
	}

	public void setSite2Label(String site2Label) {
		this.site2Label = site2Label;
	}

	public Double getSite2N() {
		return site2N;
	}

	public void setSite2N(Double site2n) {
		site2N = site2n;
	}

	public Double getSite2W() {
		return site2W;
	}

	public void setSite2W(Double site2w) {
		site2W = site2w;
	}

	public Long getLiaisonIdt() {
		return liaisonIdt;
	}

	public void setLiaisonIdt(Long liaisonIdt) {
		this.liaisonIdt = liaisonIdt;
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

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public String getDateOperation() {
		return dateOperation;
	}

	public void setDateOperation(String dateOperation) {
		this.dateOperation = dateOperation;
	}

	public String getDateReponse() {
		return dateReponse;
	}

	public void setDateReponse(String dateReponse) {
		this.dateReponse = dateReponse;
	}

	public Long getDemandeIdt() {
		return demandeIdt;
	}

	public void setDemandeIdt(Long demandeIdt) {
		this.demandeIdt = demandeIdt;
	}

	public Long getEtatDemandeSousLiaisonIdt() {
		return etatDemandeSousLiaisonIdt;
	}

	public void setEtatDemandeSousLiaisonIdt(Long etatDemandeSousLiaisonIdt) {
		this.etatDemandeSousLiaisonIdt = etatDemandeSousLiaisonIdt;
	}

	public String getEtatDemandeSousLiaisonCode() {
		return etatDemandeSousLiaisonCode;
	}

	public void setEtatDemandeSousLiaisonCode(String etatDemandeSousLiaisonCode) {
		this.etatDemandeSousLiaisonCode = etatDemandeSousLiaisonCode;
	}

	public String getEtatDemandeSousLiaisonLabel() {
		return etatDemandeSousLiaisonLabel;
	}

	public void setEtatDemandeSousLiaisonLabel(String etatDemandeSousLiaisonLabel) {
		this.etatDemandeSousLiaisonLabel = etatDemandeSousLiaisonLabel;
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

	public String getRepondeurLogin() {
		return repondeurLogin;
	}

	public void setRepondeurLogin(String repondeurLogin) {
		this.repondeurLogin = repondeurLogin;
	}

	public String getRepondeurNom() {
		return repondeurNom;
	}

	public void setRepondeurNom(String repondeurNom) {
		this.repondeurNom = repondeurNom;
	}

	public String getRepondeurPrenom() {
		return repondeurPrenom;
	}

	public void setRepondeurPrenom(String repondeurPrenom) {
		this.repondeurPrenom = repondeurPrenom;
	}

	public Long getRaisonInfaisabiliteIdt() {
		return raisonInfaisabiliteIdt;
	}

	public void setRaisonInfaisabiliteIdt(Long raisonInfaisabiliteIdt) {
		this.raisonInfaisabiliteIdt = raisonInfaisabiliteIdt;
	}

	public String getRaisonInfaisabiliteCode() {
		return raisonInfaisabiliteCode;
	}

	public void setRaisonInfaisabiliteCode(String raisonInfaisabiliteCode) {
		this.raisonInfaisabiliteCode = raisonInfaisabiliteCode;
	}

	public String getRaisonInfaisabiliteLabel() {
		return raisonInfaisabiliteLabel;
	}

	public void setRaisonInfaisabiliteLabel(String raisonInfaisabiliteLabel) {
		this.raisonInfaisabiliteLabel = raisonInfaisabiliteLabel;
	}

	public String getDescriptionInfaisabilite() {
		return descriptionInfaisabilite;
	}

	public void setDescriptionInfaisabilite(String descriptionInfaisabilite) {
		this.descriptionInfaisabilite = descriptionInfaisabilite;
	}

	public String getSolutionAlternative() {
		return solutionAlternative;
	}

	public void setSolutionAlternative(String solutionAlternative) {
		this.solutionAlternative = solutionAlternative;
	}

	public SiteDto getSite1() {
		return site1;
	}

	public void setSite1(SiteDto site1) {
		this.site1 = site1;
	}

	public SiteDto getSite2() {
		return site2;
	}

	public void setSite2(SiteDto site2) {
		this.site2 = site2;
	}

	public String getCableFoPropose() {
		return cableFoPropose;
	}

	public void setCableFoPropose(String cableFoPropose) {
		this.cableFoPropose = cableFoPropose;
	}

	public String getBilanObtiqueEstimatif() {
		return bilanObtiqueEstimatif;
	}

	public void setBilanObtiqueEstimatif(String bilanObtiqueEstimatif) {
		this.bilanObtiqueEstimatif = bilanObtiqueEstimatif;
	}

	public String getDelaisRealisation() {
		return delaisRealisation;
	}

	public void setDelaisRealisation(String delaisRealisation) {
		this.delaisRealisation = delaisRealisation;
	}

	public String getDistanceEstimative() {
		return distanceEstimative;
	}

	public void setDistanceEstimative(String distanceEstimative) {
		this.distanceEstimative = distanceEstimative;
	}

	public String getContactDemt() {
		return contactDemt;
	}

	public void setContactDemt(String contactDemt) {
		this.contactDemt = contactDemt;
	}

	public String getInterlocuteurDemt() {
		return interlocuteurDemt;
	}

	public void setInterlocuteurDemt(String interlocuteurDemt) {
		this.interlocuteurDemt = interlocuteurDemt;
	}

	public String getContactDr() {
		return contactDr;
	}

	public void setContactDr(String contactDr) {
		this.contactDr = contactDr;
	}

	public String getInterlocuteurDr() {
		return interlocuteurDr;
	}

	public void setInterlocuteurDr(String interlocuteurDr) {
		this.interlocuteurDr = interlocuteurDr;
	}

	public String getDateAffectation() {
		return dateAffectation;
	}

	public void setDateAffectation(String dateAffectation) {
		this.dateAffectation = dateAffectation;
	}

	public String getDesignationSousLiaison() {
		return designationSousLiaison;
	}

	public void setDesignationSousLiaison(String designationSousLiaison) {
		this.designationSousLiaison = designationSousLiaison;
	}

	public String getDesignationTronconSousLiaison() {
		return designationTronconSousLiaison;
	}

	public void setDesignationTronconSousLiaison(String designationTronconSousLiaison) {
		this.designationTronconSousLiaison = designationTronconSousLiaison;
	}

	
}
