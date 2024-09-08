package ma.iam.dppi.fon.dtos;

import java.util.Date;


public class EscaladeDto {

	
	private Long idt;
	
	private Date dateEscalade;
	
	
	private Long  idtDemande;
	
	private Long idtLiaison;
	
	private Long idtEtatEscalade;
	
	private Long numOderEscalade;

	private String numTicket;
	
	public Long getNumOderEscalade() {
		return numOderEscalade;
	}

	public void setNumOderEscalade(Long numOderEscalade) {
		this.numOderEscalade = numOderEscalade;
	}

	public Long getIdt() {
		return idt;
	}

	public void setIdt(Long idt) {
		this.idt = idt;
	}

	public Date getDateEscalade() {
		return dateEscalade;
	}

	public void setDateEscalade(Date dateEscalade) {
		this.dateEscalade = dateEscalade;
	}

	

	

	public Long getIdtEtatEscalade() {
		return idtEtatEscalade;
	}

	public void setIdtEtatEscalade(Long idtEtatEscalade) {
		this.idtEtatEscalade = idtEtatEscalade;
	}

	public Long getIdtDemande() {
		return idtDemande;
	}

	public void setIdtDemande(Long idtDemande) {
		this.idtDemande = idtDemande;
	}

	public String getNumTicket() {
		return numTicket;
	}

	public void setNumTicket(String numTicket) {
		this.numTicket = numTicket;
	}

	public Long getIdtLiaison() {
		return idtLiaison;
	}

	public void setIdtLiaison(Long idtLiaison) {
		this.idtLiaison = idtLiaison;
	}

	
	
	
}
