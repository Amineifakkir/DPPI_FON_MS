package ma.iam.dppi.fon.domain;

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
@Table(name = "dppi_type_desaturation")
public class TypeDesaturation extends BaseEntityCodeLabel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_type_desaturation")
	@SequenceGenerator(name = "seq_type_desaturation", sequenceName = "seq_type_desaturation", allocationSize = 1)
	@Column(name = "IDT", nullable = false)
	private Long idt;
	
	public Long getIdt() {
		return idt;
	}

	public void setIdt(Long idt) {
		this.idt = idt;
	}
	
}
