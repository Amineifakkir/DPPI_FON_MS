
package ma.iam.dppi.fon.interne.dtos;



/**
 * @author A.IFAKKIR
 *
 */
public class SiteSdrDto{
	
	private Long idt;	
	private String label;
	private Long idtEtatSite;
	
	/**
	 * 
	 */
	public SiteSdrDto() {
	}
	/**
	 * @return the idt
	 */
	public Long getIdt() {
		return idt;
	}
	/**
	 * @param idt the idt to set
	 */
	public void setIdt(Long idt) {
		this.idt = idt;
	}
	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}
	/**
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}
	public Long getIdtEtatSite() {
		return idtEtatSite;
	}
	public void setIdtEtatSite(Long idtEtatSite) {
		this.idtEtatSite = idtEtatSite;
	}
	
}
