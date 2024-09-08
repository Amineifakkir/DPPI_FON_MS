package ma.iam.dppi.fon.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "dppi_RaisonInfaisabilite")
public class RaisonInfaisabilite extends BaseEntityCodeLabel{

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_raison_infaisabilite")
	@SequenceGenerator(name = "seq_raison_infaisabilite", sequenceName = "seq_raison_infaisabilite", allocationSize = 1)
	@Column(name = "IDT", nullable = false)
	private Long idt;
	
	public Long getIdt() {
		return idt;
	}

	public void setIdt(Long idt) {
		this.idt = idt;
	}
}
