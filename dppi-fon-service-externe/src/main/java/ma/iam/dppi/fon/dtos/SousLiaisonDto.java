package ma.iam.dppi.fon.dtos;




public class SousLiaisonDto {

    private Long idt;
	
    private String code;

	private Long idtDr;


	private SiteDto chambre1;


	private SiteDto chambre2;



	private String etat;

	private String distance;

	private String distanceDisponible;

	private String distanceSature;

	private Integer number;

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

	public Long getIdtDr() {
		return idtDr;
	}

	public void setIdtDr(Long idtDr) {
		this.idtDr = idtDr;
	}

	public SiteDto getChambre1() {
		return chambre1;
	}

	public void setChambre1(SiteDto chambre1) {
		this.chambre1 = chambre1;
	}

	public SiteDto getChambre2() {
		return chambre2;
	}

	public void setChambre2(SiteDto chambre2) {
		this.chambre2 = chambre2;
	}



	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	public String getDistanceDisponible() {
		return distanceDisponible;
	}

	public void setDistanceDisponible(String distanceDisponible) {
		this.distanceDisponible = distanceDisponible;
	}

	public String getDistanceSature() {
		return distanceSature;
	}

	public void setDistanceSature(String distanceSature) {
		this.distanceSature = distanceSature;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}


	
}
