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

/**
 * @author Z.BELGHAOUTI
 *
 */
@Entity
@Table(name = "dppi_dc")
public class Dc extends BaseEntityCodeLabel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_dc")
	@SequenceGenerator(name = "seq_dc", sequenceName = "seq_dc", allocationSize = 1)
	@Column(name = "IDT", nullable = false)
	private Long idt;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "IDT_DR")
	private Dr dr;

	public Long getIdt() {
		return idt;
	}

	public void setIdt(Long idt) {
		this.idt = idt;
	}

	public Dr getDr() {
		return dr;
	}

	public void setDr(Dr dr) {
		this.dr = dr;
	}

}
