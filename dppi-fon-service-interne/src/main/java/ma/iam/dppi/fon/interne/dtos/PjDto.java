/**
 * 
 */
package ma.iam.dppi.fon.interne.dtos;

/**
 * 
 * @author Z.BELGHAOUTI
 *
 */
public class PjDto {

	private Long idt;
	
	private String fileNameSaved;

	private String originFileName;
	
	private Long idtDemande;

	public PjDto() {
	}

	public Long getIdt() {
		return idt;
	}

	public void setIdt(Long idt) {
		this.idt = idt;
	}

	public String getFileNameSaved() {
		return fileNameSaved;
	}

	public void setFileNameSaved(String fileNameSaved) {
		this.fileNameSaved = fileNameSaved;
	}

	public String getOriginFileName() {
		return originFileName;
	}

	public void setOriginFileName(String originFileName) {
		this.originFileName = originFileName;
	}

	public Long getIdtDemande() {
		return idtDemande;
	}

	public void setIdtDemande(Long idtDemande) {
		this.idtDemande = idtDemande;
	}

}
