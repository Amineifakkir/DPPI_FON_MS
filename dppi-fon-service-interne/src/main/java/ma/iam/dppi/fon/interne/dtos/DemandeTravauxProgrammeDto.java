package ma.iam.dppi.fon.interne.dtos;

public class DemandeTravauxProgrammeDto {

	private Long idt;
	private Long idtDemande;
	private Long idtLiaisson;
	private LiaisonDto liaisons;

	public Long getIdt() {
		return idt;
	}

	public void setIdt(Long idt) {
		this.idt = idt;
	}

	public Long getIdtDemande() {
		return idtDemande;
	}

	public void setIdtDemande(Long idtDemande) {
		this.idtDemande = idtDemande;
	}

	public Long getIdtLiaisson() {
		return idtLiaisson;
	}

	public void setIdtLiaisson(Long idtLiaisson) {
		this.idtLiaisson = idtLiaisson;
	}

	public LiaisonDto getLiaisons() {
		return liaisons;
	}

	public void setLiaisons(LiaisonDto liaisons) {
		this.liaisons = liaisons;
	}
}
