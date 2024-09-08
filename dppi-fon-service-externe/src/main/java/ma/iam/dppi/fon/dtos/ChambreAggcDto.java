package ma.iam.dppi.fon.dtos;

public class ChambreAggcDto {

	private Long idt;
	private String code;
	private Double gpsN;
	private Double gpsW;
	private String typeChambreLabel;
	public Long getIdt() {
		return idt;
	}
	public void setIdt(Long idt) {
		this.idt = idt;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Double getGpsN() {
		return gpsN;
	}
	public void setGpsN(Double gpsN) {
		this.gpsN = gpsN;
	}
	public Double getGpsW() {
		return gpsW;
	}
	public void setGpsW(Double gpsW) {
		this.gpsW = gpsW;
	}
	public String getTypeChambreLabel() {
		return typeChambreLabel;
	}
	public void setTypeChambreLabel(String typeChambreLabel) {
		this.typeChambreLabel = typeChambreLabel;
	}
	
}
