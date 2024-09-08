package ma.iam.dppi.fon.dtos;

public class IntervenantDto {
	private Long idt;

	private String nom;
    private String prenom;
    private String cin;
    private String erpt;
    private String sousTraitant;
    private Long idtOperateur;
    private String operateur;
    private String telephone;

    
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
	public String getCin() {
		return cin;
	}
	public void setCin(String cin) {
		this.cin = cin;
	}
	public String getErpt() {
		return erpt;
	}
	public void setErpt(String erpt) {
		this.erpt = erpt;
	}
	public String getSousTraitant() {
		return sousTraitant;
	}
	public void setSousTraitant(String sousTraitant) {
		this.sousTraitant = sousTraitant;
	}
	public Long getIdtOperateur() {
		return idtOperateur;
	}
	public void setIdtOperateur(Long idtOperateur) {
		this.idtOperateur = idtOperateur;
	}
	public String getOperateur() {
		return operateur;
	}
	public void setOperateur(String operateur) {
		this.operateur = operateur;
	}
	public Long getIdt() {
		return idt;
	}
	public void setIdt(Long idt) {
		this.idt = idt;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
}
