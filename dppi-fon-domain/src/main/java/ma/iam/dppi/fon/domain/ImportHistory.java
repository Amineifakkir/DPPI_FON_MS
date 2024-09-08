package ma.iam.dppi.fon.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

/**
 * 
 * @author Z.BELGHAOUTI
 *
 */
@Entity
@Table(name = "dppi_import_history")
public class ImportHistory extends BaseEntityCodeLabel {

	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_import_history")
	@SequenceGenerator(name = "seq_import_history", sequenceName = "seq_import_history", allocationSize = 1)
	@Column(name = "IDT", nullable = false)
	private Long idt;

	@Column(name = "DATE_IMPORT")
	private Date dateImport;
	@Column(name = "IMPORTED_BY")
	private String importedBy;
	@Enumerated(EnumType.STRING)
	@Column(name = "TYPE")
	private ImportType type;
	@Column(name = "DATE_FIN_IMPORT")
	private Date dateFinImport;
	@Column(name = "FILE")
	private String file;
	@Column(name = "LOG")
	private String log;
	@Column(name = "ROLES")
	private String roles;
	@Column(name = "SUCCESS", nullable = false)
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean success;
	@Column(name = "ENTITE", columnDefinition = "TEXT")
	private String entite;

	public Long getIdt() {
		return idt;
	}

	public void setIdt(Long idt) {
		this.idt = idt;
	}

	public Date getDateImport() {
		return dateImport;
	}

	public void setDateImport(Date dateImport) {
		this.dateImport = dateImport;
	}

	public String getImportedBy() {
		return importedBy;
	}

	public void setImportedBy(String importedBy) {
		this.importedBy = importedBy;
	}

	public ImportType getType() {
		return type;
	}

	public void setType(ImportType type) {
		this.type = type;
	}

	public Date getDateFinImport() {
		return dateFinImport;
	}

	public void setDateFinImport(Date dateFinImport) {
		this.dateFinImport = dateFinImport;
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

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String getEntite() {
		return entite;
	}

	public void setEntite(String entite) {
		this.entite = entite;
	}
}
