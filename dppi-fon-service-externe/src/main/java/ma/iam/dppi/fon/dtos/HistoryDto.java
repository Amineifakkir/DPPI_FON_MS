package ma.iam.dppi.fon.dtos;

import java.util.List;

/**
 * @author K.ELBAGUARI
 *
 */
public class HistoryDto {

	private Long idt;

	private String dateImport;

	private String dateFinImport;

	private String importedBy;

	private Boolean success;

	private String type;

	private String file;

	private String log;

	private List<String> roles;

	private String entite;


	public Long getIdt() {
		return idt;
	}

	public void setIdt(Long idt) {
		this.idt = idt;
	}

	public String getDateImport() {
		return dateImport;
	}

	public void setDateImport(String dateImport) {
		this.dateImport = dateImport;
	}

	public String getDateFinImport() {
		return dateFinImport;
	}

	public void setDateFinImport(String dateFinImport) {
		this.dateFinImport = dateFinImport;
	}

	public String getImportedBy() {
		return importedBy;
	}

	public void setImportedBy(String importeddBy) {
		this.importedBy = importeddBy;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public String getLog() {
		return log;
	}

	public void setLog(String log) {
		this.log = log;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public String getEntite() {
		return entite;
	}

	public void setEntite(String entite) {
		this.entite = entite;
	}
}
