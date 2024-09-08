package ma.iam.dppi.fon.dtos;

/**
 * 
 * @author Z.BELGHAOUTI
 *
 */
public class ProfilDto {

	private Long idt;
	
	private String label;

	public ProfilDto(Long idt, String label) {
		super();
		this.idt = idt;
		this.label = label;
	}

	public ProfilDto() {
		super();
	}

	public Long getIdt() {
		return idt;
	}

	public void setIdt(Long idt) {
		this.idt = idt;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}