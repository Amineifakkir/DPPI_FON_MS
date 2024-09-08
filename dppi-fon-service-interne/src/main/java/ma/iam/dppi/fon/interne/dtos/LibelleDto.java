package ma.iam.dppi.fon.interne.dtos;



/**
 * @author Z.BELGHAOUTI
 */
@SuppressWarnings("serial")
public class LibelleDto implements java.io.Serializable {
	private Long idt;
	private String code;
	private String libelle;
	private Float valeur;
	private String description;
	
	public Float getValeur() {
		return valeur;
	}

	public void setValeur(Float valeur) {
		this.valeur = valeur;
	}

	

	public LibelleDto(Long idt, String libelle) {
		super();
		this.idt = idt;
		this.libelle = libelle;
	}

	public LibelleDto() {

	}

	/**
	 * @return the idt
	 */
	public Long getIdt() {
		return idt;
	}

	/**
	 * @param idt
	 *            the idt to set
	 */
	public void setIdt(Long idt) {
		this.idt = idt;
	}

	/**
	 * @return the libelle
	 */
	public String getLibelle() {
		return libelle;
	}

	/**
	 * @param libelle
	 *            the libelle to set
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
