package ma.iam.dppi.fon.dtos;

import java.util.List;

/**
 * @author Z.BELGHAOUTI
 *
 */
public class SousRepartiteurDto {
	private Long idt;
	private String codeSR;
	private String nomSR;
	private Long repartiteurIdt;
	private String repartiteur;
	private String codeRep;
	private Long communeIdt;
	private String commune;
	private String codeCommune;
	private Long dcIdt;
	private String dc;
	private String codeDc;
	private Long drIdt;
	private String dr;
	private String codeDr;
	private String gpsNord;
	private String gpsOuest;
	private List<CommuneDto> communes;
	private Long idtChambreRaccordement;
	private String codeChambreRaccordement;

	public Long getIdt() {
		return idt;
	}

	public void setIdt(Long idt) {
		this.idt = idt;
	}

	public String getCodeSR() {
		return codeSR;
	}

	public void setCodeSR(String codeSR) {
		this.codeSR = codeSR;
	}

	public String getNomSR() {
		return nomSR;
	}

	public void setNomSR(String nomSR) {
		this.nomSR = nomSR;
	}

	public Long getRepartiteurIdt() {
		return repartiteurIdt;
	}

	public void setRepartiteurIdt(Long repartiteurIdt) {
		this.repartiteurIdt = repartiteurIdt;
	}

	public String getRepartiteur() {
		return repartiteur;
	}

	public void setRepartiteur(String repartiteur) {
		this.repartiteur = repartiteur;
	}

	public String getCodeRep() {
		return codeRep;
	}

	public void setCodeRep(String codeRep) {
		this.codeRep = codeRep;
	}

	public Long getCommuneIdt() {
		return communeIdt;
	}

	public void setCommuneIdt(Long communeIdt) {
		this.communeIdt = communeIdt;
	}

	public String getCommune() {
		return commune;
	}

	public void setCommune(String commune) {
		this.commune = commune;
	}

	public Long getDcIdt() {
		return dcIdt;
	}

	public void setDcIdt(Long dcIdt) {
		this.dcIdt = dcIdt;
	}

	public String getDc() {
		return dc;
	}

	public void setDc(String dc) {
		this.dc = dc;
	}

	public Long getDrIdt() {
		return drIdt;
	}

	public void setDrIdt(Long drIdt) {
		this.drIdt = drIdt;
	}

	public String getDr() {
		return dr;
	}

	public void setDr(String dr) {
		this.dr = dr;
	}

	public String getGpsNord() {
		return gpsNord;
	}

	public void setGpsNord(String gpsNord) {
		this.gpsNord = gpsNord;
	}

	public String getGpsOuest() {
		return gpsOuest;
	}

	public void setGpsOuest(String gpsOuest) {
		this.gpsOuest = gpsOuest;
	}

	public List<CommuneDto> getCommunes() {
		return communes;
	}

	public void setCommunes(List<CommuneDto> communes) {
		this.communes = communes;
	}

	public Long getIdtChambreRaccordement() {
		return idtChambreRaccordement;
	}

	public void setIdtChambreRaccordement(Long idtChambreRaccordement) {
		this.idtChambreRaccordement = idtChambreRaccordement;
	}

	public String getCodeChambreRaccordement() {
		return codeChambreRaccordement;
	}

	public void setCodeChambreRaccordement(String codeChambreRaccordement) {
		this.codeChambreRaccordement = codeChambreRaccordement;
	}

	public String getCodeCommune() {
		return codeCommune;
	}

	public void setCodeCommune(String codeCommune) {
		this.codeCommune = codeCommune;
	}

	public String getCodeDc() {
		return codeDc;
	}

	public void setCodeDc(String codeDc) {
		this.codeDc = codeDc;
	}

	public String getCodeDr() {
		return codeDr;
	}

	public void setCodeDr(String codeDr) {
		this.codeDr = codeDr;
	}

}
