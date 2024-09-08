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
@Table(name = "dppi_trace_etat_demande_sous_liaison")
public class TraceEtatDemandeSousLiaison {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_tr_etat_demande_sous_liaison")
	@SequenceGenerator(name = "seq_tr_etat_demande_sous_liaison", sequenceName = "seq_tr_etat_demande_sous_liaison", allocationSize = 1)
	@Column(name = "IDT", nullable = false)
	private Long idt;

	@Column(name = "DATE_ETAT")
	private Date dateEtat;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "IDT_TRONCON")
	private SousLiaison sousLiaison;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "IDT_ETAT_DEMANDE_SOUS_LIAISON")
	private EtatDemandeSousLiaison etatDemandeSousLiaison;

	public Long getIdt() {
		return idt;
	}

	public void setIdt(Long idt) {
		this.idt = idt;
	}

	public Date getDateEtat() {
		return dateEtat;
	}

	public void setDateEtat(Date dateEtat) {
		this.dateEtat = dateEtat;
	}

	public SousLiaison getSousLiaison() {
		return sousLiaison;
	}

	public void setSousLiaison(SousLiaison sousLiaison) {
		this.sousLiaison = sousLiaison;
	}

	public EtatDemandeSousLiaison getEtatDemandeSousLiaison() {
		return etatDemandeSousLiaison;
	}

	public void setEtatDemandeSousLiaison(EtatDemandeSousLiaison etatDemandeSousLiaison) {
		this.etatDemandeSousLiaison = etatDemandeSousLiaison;
	}

	

}
