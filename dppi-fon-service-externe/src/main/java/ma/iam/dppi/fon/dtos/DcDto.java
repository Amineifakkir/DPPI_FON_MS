package ma.iam.dppi.fon.dtos;


public class DcDto {

	private Long idt;
	private String label;
	private String code;
	private DrDto dr;

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
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public DrDto getDr() {
		return dr;
	}
	public void setDr(DrDto dr) {
		this.dr = dr;
	}	
}
