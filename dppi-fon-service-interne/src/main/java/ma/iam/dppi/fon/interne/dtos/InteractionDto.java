package ma.iam.dppi.fon.interne.dtos;

public class InteractionDto {

	private Long idt;
	private String entiteSource;
	private String entiteCible;
	private String dateInteraction;
	private Long demandeIdt;

	private String typeDemandeLabel;
	private String typeDemandeCode;
	private Long typeDemandeIdt;

	private String etatDemandeLabel;
	private String etatDemandeCode;
	private Long etatDemandeIdt;

	private String dateReponse;
	private String interactionLabel;
	private String reponse;

	private String repondeurLogin;
	private String repondeurNom;
	private String repondeurPrenom;

	private String demandeurLogin;
	private String demandeurNom;
	private String demandeurPrenom;

	public Long getIdt() {
		return idt;
	}

	public void setIdt(Long idt) {
		this.idt = idt;
	}

	public String getEntiteSource() {
		return entiteSource;
	}

	public void setEntiteSource(String entiteSource) {
		this.entiteSource = entiteSource;
	}

	public String getEntiteCible() {
		return entiteCible;
	}

	public void setEntiteCible(String entiteCible) {
		this.entiteCible = entiteCible;
	}

	public String getDateInteraction() {
		return dateInteraction;
	}

	public void setDateInteraction(String dateInteraction) {
		this.dateInteraction = dateInteraction;
	}

	public Long getDemandeIdt() {
		return demandeIdt;
	}

	public void setDemandeIdt(Long demandeIdt) {
		this.demandeIdt = demandeIdt;
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

	public Long getTypeDemandeIdt() {
		return typeDemandeIdt;
	}

	public void setTypeDemandeIdt(Long typeDemandeIdt) {
		this.typeDemandeIdt = typeDemandeIdt;
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

	public Long getEtatDemandeIdt() {
		return etatDemandeIdt;
	}

	public void setEtatDemandeIdt(Long etatDemandeIdt) {
		this.etatDemandeIdt = etatDemandeIdt;
	}

	public String getDateReponse() {
		return dateReponse;
	}

	public void setDateReponse(String dateReponse) {
		this.dateReponse = dateReponse;
	}

	public String getInteractionLabel() {
		return interactionLabel;
	}

	public void setInteractionLabel(String interactionLabel) {
		this.interactionLabel = interactionLabel;
	}

	public String getReponse() {
		return reponse;
	}

	public void setReponse(String reponse) {
		this.reponse = reponse;
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

}
