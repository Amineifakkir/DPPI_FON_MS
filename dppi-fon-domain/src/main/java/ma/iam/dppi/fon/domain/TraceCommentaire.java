package ma.iam.dppi.fon.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 
 * @author Z.BELGHAOUTI
 *
 */
@Entity
@Table(name = "dppi_trace_commentaire")
public class TraceCommentaire {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_tr_commentaire")
	@SequenceGenerator(name = "seq_tr_commentaire", sequenceName = "seq_tr_commentaire", allocationSize = 1)
	@Column(name = "IDT", nullable = false)
	private Long idt;

	@Column(name = "DATE_COMMENTAIRE")
	private Date dateCommentaire;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "IDT_DEMANDE")
	private Demande demande;

	@Column(name = "COMMENTAIRE", columnDefinition = "TEXT")
	private String commentaire;

	@Column(name = "ENTITE")
	private String entite;

	@Column(name = "DEMANDEUR_LOGIN")
	private String demandeurLogin;

	@Column(name = "DEMANDEUR_NOM")
	private String demandeurNom;

	@Column(name = "DEMANDEUR_PRENOM")
	private String demandeurPrenom;

	public Long getIdt() {
		return idt;
	}

	public void setIdt(Long idt) {
		this.idt = idt;
	}

	public Date getDateCommentaire() {
		return dateCommentaire;
	}

	public void setDateCommentaire(Date dateCommentaire) {
		this.dateCommentaire = dateCommentaire;
	}

	public Demande getDemande() {
		return demande;
	}

	public void setDemande(Demande demande) {
		this.demande = demande;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public String getEntite() {
		return entite;
	}

	public void setEntite(String entite) {
		this.entite = entite;
	}

	public String getDemandeurLogin() {
		return demandeurLogin;
	}

	public void setDemandeurLogin(String demandeurLogin) {
		this.demandeurLogin = demandeurLogin;
	}

	public String getDemandeurNom() {
		return demandeurNom;
	}

	public void setDemandeurNom(String demandeurNom) {
		this.demandeurNom = demandeurNom;
	}

	public String getDemandeurPrenom() {
		return demandeurPrenom;
	}

	public void setDemandeurPrenom(String demandeurPrenom) {
		this.demandeurPrenom = demandeurPrenom;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((dateCommentaire == null) ? 0 : dateCommentaire.hashCode());
		result = prime * result + ((demande == null) ? 0 : demande.hashCode());
		result = prime * result + ((entite == null) ? 0 : entite.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		TraceCommentaire other = (TraceCommentaire) obj;
		if (dateCommentaire == null) {
			if (other.dateCommentaire != null)
				return false;
		} else if (!dateCommentaire.equals(other.dateCommentaire))
			return false;
		if (demande == null) {
			if (other.demande != null)
				return false;
		} else if (!demande.equals(other.demande))
			return false;
		if (entite == null) {
			if (other.entite != null)
				return false;
		} else if (!entite.equals(other.entite))
			return false;
		return true;
	}

}
