package ma.iam.dppi.fon.communs.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 
 * @author Z.BELGHAOUTI
 *
 */
@Entity
@Table(name = "dppi_operateur")
public class Operateur extends BaseEntityCodeLabel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_operateur")
	@SequenceGenerator(name = "seq_operateur", sequenceName = "seq_operateur", allocationSize = 1)
	@Column(name = "IDT", nullable = false)
	private Long idt;

	public Long getIdt() {
		return idt;
	}

	public void setIdt(Long idt) {
		this.idt = idt;
	}

}
