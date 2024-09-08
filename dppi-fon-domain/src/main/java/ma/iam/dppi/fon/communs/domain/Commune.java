package ma.iam.dppi.fon.communs.domain;

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
@Table(name = "dppi_commune")
public class Commune extends BaseEntityCodeLabel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_commune")
	@SequenceGenerator(name = "seq_commune", sequenceName = "seq_commune", allocationSize = 1)
	@Column(name = "IDT", nullable = false)
	private Long idt;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "IDT_DC")
	private Dc dc;

	public Long getIdt() {
		return idt;
	}

	public void setIdt(Long idt) {
		this.idt = idt;
	}

	public Dc getDc() {
		return dc;
	}

	public void setDc(Dc dc) {
		this.dc = dc;
	}

}
