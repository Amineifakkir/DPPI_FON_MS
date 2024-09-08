package ma.iam.dppi.fon.interne.dtos;

/**
 *
 * @author Z.BELGHAOUTI
 */
public class SiteDto {

	private Long idt;
	private String code;
	private Double gpsN;
	private Double gpsW;
	private String typeSiteLabel;
	private Long idtCommune;
	private Long idtDr;
	private Long idtDc;

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

	public Long getIdtCommune() {
		return idtCommune;
	}

	public void setIdtCommune(Long idtCommune) {
		this.idtCommune = idtCommune;
	}

	public Long getIdtDr() {
		return idtDr;
	}

	public void setIdtDr(Long idtDr) {
		this.idtDr = idtDr;
	}

	public Long getIdtDc() {
		return idtDc;
	}

	public void setIdtDc(Long idtDc) {
		this.idtDc = idtDc;
	}

	public String getTypeSiteLabel() {
		return typeSiteLabel;
	}

	public void setTypeSiteLabel(String typeSiteLabel) {
		this.typeSiteLabel = typeSiteLabel;
	}

}
