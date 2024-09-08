package ma.iam.dppi.fon.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 
 * @author Z.BELGHAOUTI
 *
 */
@Entity
@Table(name = "dppi_site")
public class Site {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_site")
	@SequenceGenerator(name = "seq_site", sequenceName = "seq_site", allocationSize = 1)
	@Column(name = "IDT", nullable = false)
	private Long idt;

	
	@Column(name = "CODE")
	private String code;
	
	

	@Column(name = "NOM", unique = true)
	private String nom;

	@Column(name = "GPS_N")
	private Double gpsN;

	@Column(name = "GPS_W")
	private Double gpsW;

	@Column(name = "IDT_COMMUNE")
	private Long idtCommune;

	@Column(name = "IDT_DR")
	private Long idtDr;

	@Column(name = "IDT_DC")
	private Long idtDc;
	
	public Long getIdt() {
		return idt;
	}

	public void setIdt(Long idt) {
		this.idt = idt;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((gpsN == null) ? 0 : gpsN.hashCode());
		result = prime * result + ((gpsW == null) ? 0 : gpsW.hashCode());
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		return result;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Site other = (Site) obj;
		if (gpsN == null) {
			if (other.gpsN != null)
				return false;
		} else if (!gpsN.equals(other.gpsN))
			return false;
		if (gpsW == null) {
			if (other.gpsW != null)
				return false;
		} else if (!gpsW.equals(other.gpsW))
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		return true;
	}

}
