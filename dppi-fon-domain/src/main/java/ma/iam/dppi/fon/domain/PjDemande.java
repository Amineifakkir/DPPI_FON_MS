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
@Table(name = "dppi_pj_demande")
public class PjDemande {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_pj_demande")
	@SequenceGenerator(name = "seq_pj_demande", sequenceName = "seq_pj_demande", allocationSize = 1)
	@Column(name = "IDT", nullable = false)
	private Long idt;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "IDT_PIECE_JOINTE")
	private PieceJointe pieceJointe;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "IDT_DEMANDE")
	private Demande demande;
	
	public Long getIdt() {
		return idt;
	}

	public void setIdt(Long idt) {
		this.idt = idt;
	}

	public PieceJointe getPieceJointe() {
		return pieceJointe;
	}

	public void setPieceJointe(PieceJointe pieceJointe) {
		this.pieceJointe = pieceJointe;
	}

	public Demande getDemande() {
		return demande;
	}

	public void setDemande(Demande demande) {
		this.demande = demande;
	}

}
