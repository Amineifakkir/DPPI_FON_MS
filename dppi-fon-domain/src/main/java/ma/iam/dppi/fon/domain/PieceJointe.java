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

/**
 * 
 * @author Z.BELGHAOUTI
 *
 */
@Entity
@Table(name = "dppi_piece_jointe")
public class PieceJointe {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_piece_jointe")
	@SequenceGenerator(name = "seq_piece_jointe", sequenceName = "seq_piece_jointe", allocationSize = 1)
	@Column(name = "IDT", nullable = false)
	private Long idt;

	@Column(name = "FILE_NAME")
	private String fileName;

	@Column(name = "ORIGIN_FILE_NAME")
	private String originFileName;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "IDT_DEMANDE")
	private Demande demande;
	
	
	
	public Demande getDemande() {
		return demande;
	}

	public void setDemande(Demande demande) {
		this.demande = demande;
	}

	public Long getIdt() {
		return idt;
	}

	public void setIdt(Long idt) {
		this.idt = idt;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getOriginFileName() {
		return originFileName;
	}

	public void setOriginFileName(String originFileName) {
		this.originFileName = originFileName;
	}

}
