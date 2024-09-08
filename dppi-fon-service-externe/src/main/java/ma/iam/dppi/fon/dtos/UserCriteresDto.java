package ma.iam.dppi.fon.dtos;

/**
 * 
 * @author Z.BELGHAOUTI
 *
 */
public class UserCriteresDto {

	private String login;
	private String nom;
	private String prenom;
	private Long idtProfil;
	private Integer idtStatut;
	private Long idtOperateur;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
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

	public Long getIdtOperateur() {
		return idtOperateur;
	}

	public void setIdtOperateur(Long idtOperateur) {
		this.idtOperateur = idtOperateur;
	}

}
