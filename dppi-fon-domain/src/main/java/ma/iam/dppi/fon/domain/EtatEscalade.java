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
@Table(name = "dppi_etat_escalade")
public class EtatEscalade extends BaseEntityCodeLabel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_etat_escalade")
	@SequenceGenerator(name = "seq_etat_escalade", sequenceName = "seq_etat_escalade", allocationSize = 1)
	@Column(name = "IDT", nullable = false)
	private Long idt;
	
	public Long getIdt() {
		return idt;
	}

	public void setIdt(Long idt) {
		this.idt = idt;
	}
}
