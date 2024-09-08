package ma.iam.dppi.fon.interne.dtos;

/**
 * 
 * @author Z.BELGHAOUTI
 *
 */
public class UserInterneCritDto {

	private String login;
	private Long idtProfil;
	private Integer idtStatut;
	private Long idtDr;
	private Long idtDc;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public Long getIdtProfil() {
		return idtProfil;
	}

	public void setIdtProfil(Long idtProfil) {
		this.idtProfil = idtProfil;
	}

	public Integer getIdtStatut() {
		return idtStatut;
	}

	public void setIdtStatut(Integer idtStatut) {
		this.idtStatut = idtStatut;
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

}
