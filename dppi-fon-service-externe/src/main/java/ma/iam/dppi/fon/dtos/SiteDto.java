package ma.iam.dppi.fon.dtos;

public class SiteDto {

	private Long idt;

	private String code;

	private String label;

	private Double gpsN;

	private Double gpsW;

	private Long idtCommune;

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

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
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

	public Long getIdtCommune() {
		return idtCommune;
	}

	public void setIdtCommune(Long idtCommune) {
		this.idtCommune = idtCommune;
	}
	
	
}
