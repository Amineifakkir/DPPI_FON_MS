package ma.iam.dppi.fon.interne.dtos;

/**
 * 
 * @author Z.BELGHAOUTI
 *
 */
public class TraceEtatDto {

	private Long idt;
	private String etatCode;
	private String etatLabel;
	private Long etatIdt;
	private String dateEtat;
	private Long demandeIdt;

	public Long getIdt() {
		return idt;
	}

	public void setIdt(Long idt) {
		this.idt = idt;
	}

	public String getEtatCode() {
		return etatCode;
	}

	public void setEtatCode(String etatCode) {
		this.etatCode = etatCode;
	}

	public String getEtatLabel() {
		return etatLabel;
	}

	public void setEtatLabel(String etatLabel) {
		this.etatLabel = etatLabel;
	}

	public Long getEtatIdt() {
		return etatIdt;
	}

	public void setEtatIdt(Long etatIdt) {
		this.etatIdt = etatIdt;
	}

	public String getDateEtat() {
		return dateEtat;
	}

	public void setDateEtat(String dateEtat) {
		this.dateEtat = dateEtat;
	}

	public Long getDemandeIdt() {
		return demandeIdt;
	}

	public void setDemandeIdt(Long demandeIdt) {
		this.demandeIdt = demandeIdt;
	}

}
