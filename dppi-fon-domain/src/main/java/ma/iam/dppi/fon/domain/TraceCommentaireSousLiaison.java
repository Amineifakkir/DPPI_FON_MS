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
@Table(name = "dppi_trace_commentaire_sous_liaison")
public class TraceCommentaireSousLiaison {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_tr_commentaire_sous_liaison")
	@SequenceGenerator(name = "seq_tr_commentaire_sous_liaison", sequenceName = "seq_tr_commentaire_sous_liaison", allocationSize = 1)
	@Column(name = "IDT", nullable = false)
	private Long idt;

	@Column(name = "DATE_COMMENTAIRE")
	private Date dateCommentaire;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "IDT_SOUS_LIAISON")
	private SousLiaison sousLiaison;

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

	public SousLiaison getSousLiaison() {
		return sousLiaison;
	}

	public void setSousLiaison(SousLiaison sousLiaison) {
		this.sousLiaison = sousLiaison;
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
		int result = 1;
		result = prime * result
				+ ((dateCommentaire == null) ? 0 : dateCommentaire.hashCode());
		result = prime * result
				+ ((demandeurLogin == null) ? 0 : demandeurLogin.hashCode());
		result = prime * result + ((idt == null) ? 0 : idt.hashCode());
		result = prime * result + ((sousLiaison == null) ? 0 : sousLiaison.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TraceCommentaireSousLiaison other = (TraceCommentaireSousLiaison) obj;
		if (dateCommentaire == null) {
			if (other.dateCommentaire != null)
				return false;
		} else if (!dateCommentaire.equals(other.dateCommentaire))
			return false;
		if (demandeurLogin == null) {
			if (other.demandeurLogin != null)
				return false;
		} else if (!demandeurLogin.equals(other.demandeurLogin))
			return false;
		if (idt == null) {
			if (other.idt != null)
				return false;
		} else if (!idt.equals(other.idt))
			return false;
		if (sousLiaison == null) {
			if (other.sousLiaison != null)
				return false;
		} else if (!sousLiaison.equals(other.sousLiaison))
			return false;
		return true;
	}
	

}
