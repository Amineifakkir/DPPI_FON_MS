package ma.iam.dppi.fon.interne.dtos;

import java.util.List;

/**
 * 
 * @author Z.BELGHAOUTI
 *
 */
public class ValidateDemandeDto {

	private Long idt;
	private String commentaire;
	private String entite;
	private Long idtTypeDemande;
	private List<SousLiaisonDto> listSousLiaison;
	private String typeDemande;
	private Double prixTotal;
	private String dateReception;
	private String intervenant;
	private String diagnostic;
	private String actionRecommandees;
	private Long selectedDr;

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

	public String getEntite() {
		return entite;
	}

	public List<SousLiaisonDto> getListSousLiaison() {
		return listSousLiaison;
	}

	public void setListSousLiaison(List<SousLiaisonDto> listSousLiaison) {
		this.listSousLiaison = listSousLiaison;
	}

	public void setEntite(String entite) {
		this.entite = entite;
	}

	public Long getIdtTypeDemande() {
		return idtTypeDemande;
	}

	public void setIdtTypeDemande(Long idtTypeDemande) {
		this.idtTypeDemande = idtTypeDemande;
	}

	public String getTypeDemande() {
		return typeDemande;
	}

	public void setTypeDemande(String typeDemande) {
		this.typeDemande = typeDemande;
	}

	public Double getPrixTotal() {
		return prixTotal;
	}

	public void setPrixTotal(Double prixTotal) {
		this.prixTotal = prixTotal;
	}

	public String getDateReception() {
		return dateReception;
	}

	public void setDateReception(String dateReception) {
		this.dateReception = dateReception;
	}

	public String getIntervenant() {
		return intervenant;
	}

	public void setIntervenant(String intervenant) {
		this.intervenant = intervenant;
	}

	public String getDiagnostic() {
		return diagnostic;
	}

	public void setDiagnostic(String diagnostic) {
		this.diagnostic = diagnostic;
	}

	public String getActionRecommandees() {
		return actionRecommandees;
	}

	public void setActionRecommandees(String actionRecommandees) {
		this.actionRecommandees = actionRecommandees;
	}

	public Long getSelectedDr() {
		return selectedDr;
	}

	public void setSelectedDr(Long selectedDr) {
		this.selectedDr = selectedDr;
	}

}
