package ma.iam.dppi.fon.dtos;

import java.util.Date;

public class InteractionDto {

	private Long idt;
	
	private String entiteSource;

	private String entiteCible;

	private Date dateInteraction;

	private Date dateReponse;

	private String interactionLabel;

	private String reponse;

	private String repondeurLogin;

	private String repondeurNom;

	private String repondeurPrenom;
	
	private Long idtDemande;	
//	private List<SousLiaisonDto> sousLiaisons;

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

	public Date getDateInteraction() {
		return dateInteraction;
	}

	public void setDateInteraction(Date dateInteraction) {
		this.dateInteraction = dateInteraction;
	}

	public Date getDateReponse() {
		return dateReponse;
	}

	public void setDateReponse(Date dateReponse) {
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

	public Long getIdtDemande() {
		return idtDemande;
	}

	public void setIdtDemande(Long idtDemande) {
		this.idtDemande = idtDemande;
	}

	

	

	
	

	
	
	
}
