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
@Table(name = "dppi_type_liaison")
public class TypeLiaison extends BaseEntityCode{

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_type_Liaison")
	@SequenceGenerator(name = "seq_type_liaison", sequenceName = "seq_type_liaison", allocationSize = 1)
	@Column(name = "IDT", nullable = false)
	private Long idt;
	
	@Column(name = "DESIGNATION")
	private String designation;
	
	public Long getIdt() {
		return idt;
	}

	public void setIdt(Long idt) {
		this.idt = idt;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}
	
	
}
