package ma.iam.dppi.fon.domain;

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

@Entity
@Table(name = "dppi_demande_travaux_programme")
public class DemandeTravauxProgramme {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_demande_tp")
	@SequenceGenerator(name = "seq_demande_tp", sequenceName = "seq_demande_tp", allocationSize = 1)
	@Column(name = "IDT", nullable = false)
	private Long idt;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "IDT_DEMANDE")
	private Demande demande;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "IDT_LIAISON")
	private Liaison liaison;

	public Long getIdt() {
		return idt;
	}

	public void setIdt(Long idt) {
		this.idt = idt;
	}

	public Demande getDemande() {
		return demande;
	}

	public void setDemande(Demande demande) {
		this.demande = demande;
	}

	public Liaison getLiaison() {
		return liaison;
	}

	public void setLiaison(Liaison liaison) {
		this.liaison = liaison;
	}

}
