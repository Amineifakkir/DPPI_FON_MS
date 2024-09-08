package ma.iam.dppi.fon.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 
 * @author Z.BELGHAOUTI
 *
 */
@Entity
@Table(name = "dppi_escalade")
public class Escalade {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_escalade")
	@SequenceGenerator(name = "seq_escalade", sequenceName = "seq_escalade", allocationSize = 1)
	@Column(name = "IDT", nullable = false)
	private Long idt;
	
	@Column(name = "DATE_ESCALADE")
	private Date dateEscalade;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IDT_DEMANDE")
	private Demande demande;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "IDT_ETAT_ESCALADE")
	private EtatEscalade etatEscalade;

	@Column(name = "NUMERO_ORDRE_ESCALADE")
	private Long numOderEscalade;
	
	@Column(name = "NUMERO_TICKET")
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

	public Demande getDemande() {
		return demande;
	}

	public void setDemande(Demande demande) {
		this.demande = demande;
	}

	public EtatEscalade getEtatEscalade() {
		return etatEscalade;
	}

	public void setEtatEscalade(EtatEscalade etatEscalade) {
		this.etatEscalade = etatEscalade;
	}

	public String getNumTicket() {
		return numTicket;
	}

	public void setNumTicket(String numTicket) {
		this.numTicket = numTicket;
	}
	
	
}
