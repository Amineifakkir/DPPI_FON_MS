package ma.iam.dppi.fon.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 
 * @author Z.BELGHAOUTI
 *
 */
@Entity
@Table(name = "dppi_demande")
public class Demande {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_demande")
	@SequenceGenerator(name = "seq_demande", sequenceName = "seq_demande", allocationSize = 1)
	@Column(name = "IDT", nullable = false)
	private Long idt;

	@Column(name = "COMMENTAIRE", columnDefinition = "TEXT")
	private String commentaire;

	@Column(name = "IDT_DEMANDEUR")
	private Long idtDemandeur;

	@Column(name = "NOM_DEMANDEUR")
	private String nomDemandeur;

	@Column(name = "PRENOM_DEMANDEUR")
	private String prenomDemandeur;

	@Column(name = "LOGIN_DEMANDEUR")
	private String loginDemandeur;

	@Column(name = "CONTACT_DEMANDEUR")
	private String contactDemandeur;

	@Column(name = "DATE_DEMANDE")
	private Date dateDemande;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "IDT_ETAT_DEMANDE")
	private EtatDemande etatDemande;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "IDT_LIAISON")
	private Liaison liaison;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "IDT_TYPE_DEMANDE")
	private TypeDemande typeDemande;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "IDT_TYPE_DESATURATION")
	private TypeDesaturation typeDesaturation;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "IDT_ETAT_DRAFT")
	private EtatDemande etatDraft;

	@Column(name = "DESCIPTION_INCIDENT")
	private String descriptionIncident;

	@Column(name = "LOCALISATION_INCIDENT")
	private String localisationIncident;

	@Column(name = "DESCRIPTION_ACCES")
	private String descriptionAcces;

	@Column(name = "POINT_OBSTACLE_GPS_N")
	private Long pointObstacleGPSN;

	@Column(name = "POINT_OBSTACLE_GPS_W")
	private Long pointObstacleGPSW;

	@Column(name = "DESCRIPTION_OBSTACLE")
	private String descriptionObstacle;

	@Column(name = "TYPE_INTERVENTION")
	private Boolean typeIntervention;

	@Column(name = "DATE_DEBUT")
	private Date dateDebut;

	@Column(name = "DATE_RECEPTION")
	private Date dateReception;

	@Column(name = "DATE_FIN_RECEPTION")
	private Date dateFinReception;

	@Column(name = "DATE_FIN")
	private Date dateFin;

	@Column(name = "INTERVENANT")
	private String intervenant;

	@Column(name = "DIAGNOSTIC")
	private String diagnostic;

	@Column(name = "ACTIONS_RECOMMANDEES")
	private String actionsRecommandees;

	@Column(name = "REPONDEUR")
	private String repondeur;

	@Column(name = "IDT_DR_REPONSE")
	private Long idtDrReponse;

	@Column(name = "MOTIF")
	private String motif;

	@Column(name = "DISTANCE_MESURE_LIAISON_FON")
	private String distanceMesureLiaisonFon;

	@Column(name = "AXE_GLOBAL_MESURE")
	private String axeGlobalMesure;

	@Column(name = "BILAN_OPTIQUE_DB")
	private String bilanOptiqueDb;

	@Column(name = "NOM_INTERACTEUR_DR")
	private String nomInterlocuteurDr;

	@Column(name = "PRENOM_INTERACTEUR_DR")
	private String prenomInterlocuteurDr;

	@Column(name = "CONTACT_EMAIL")
	private String contactEmail;

	@Column(name = "POINT_REGENERATION_GPS_X")
	private Double prGpsX;

	@Column(name = "POINT_REGENERATION_GPS_Y")
	private Double prGpsY;

	@Column(name = "REFERANCE_REGENERATION_ERPT")
	private String refRegenerationErpt;

	@Column(name = "TELEPHONE_DR")
	private String telephoneDr;

	@Column(name = "INTERLOCUTEUR")
	private String interlocuteur;

	public Long getIdtDrReponse() {
		return idtDrReponse;
	}

	public void setIdtDrReponse(Long idtDrReponse) {
		this.idtDrReponse = idtDrReponse;
	}

	public String getIntervenant() {
		return intervenant;
	}

	public void setIntervenant(String intervenant) {
		this.intervenant = intervenant;
	}

	public String getDiagnostic() {
		return diagnostic;
	}

	public void setDiagnostic(String diagnostic) {
		this.diagnostic = diagnostic;
	}

	public String getActionsRecommandees() {
		return actionsRecommandees;
	}

	public void setActionsRecommandees(String actionsRecommandees) {
		this.actionsRecommandees = actionsRecommandees;
	}

	public String getRepondeur() {
		return repondeur;
	}

	public void setRepondeur(String repondeur) {
		this.repondeur = repondeur;
	}

	@OneToMany(mappedBy = "demande", fetch = FetchType.EAGER, orphanRemoval = true)
	private List<Devis> devis;

	public Long getIdt() {
		return idt;
	}

	public void setIdt(Long idt) {
		this.idt = idt;
	}

	public Date getDateReception() {
		return dateReception;
	}

	public void setDateReception(Date dateReception) {
		this.dateReception = dateReception;
	}

	public TypeDesaturation getTypeDesaturation() {
		return typeDesaturation;
	}

	public void setTypeDesaturation(TypeDesaturation typeDesaturation) {
		this.typeDesaturation = typeDesaturation;
	}

	public Liaison getLiaison() {
		return liaison;
	}

	public void setLiaison(Liaison liaison) {
		this.liaison = liaison;
	}

	public TypeDemande getTypeDemande() {
		return typeDemande;
	}

	public void setTypeDemande(TypeDemande typeDemande) {
		this.typeDemande = typeDemande;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public Long getIdtDemandeur() {
		return idtDemandeur;
	}

	public void setIdtDemandeur(Long idtDemandeur) {
		this.idtDemandeur = idtDemandeur;
	}

	public String getNomDemandeur() {
		return nomDemandeur;
	}

	public void setNomDemandeur(String nomDemandeur) {
		this.nomDemandeur = nomDemandeur;
	}

	public String getPrenomDemandeur() {
		return prenomDemandeur;
	}

	public void setPrenomDemandeur(String prenomDemandeur) {
		this.prenomDemandeur = prenomDemandeur;
	}

	public String getLoginDemandeur() {
		return loginDemandeur;
	}

	public void setLoginDemandeur(String loginDemandeur) {
		this.loginDemandeur = loginDemandeur;
	}

	public Date getDateDemande() {
		return dateDemande;
	}

	public void setDateDemande(Date dateDemande) {
		this.dateDemande = dateDemande;
	}

	public EtatDemande getEtatDemande() {
		return etatDemande;
	}

	public void setEtatDemande(EtatDemande etatDemande) {
		this.etatDemande = etatDemande;
	}

	public EtatDemande getEtatDraft() {
		return etatDraft;
	}

	public void setEtatDraft(EtatDemande etatDraft) {
		this.etatDraft = etatDraft;
	}

	public String getDescriptionIncident() {
		return descriptionIncident;
	}

	public void setDescriptionIncident(String descriptionIncident) {
		this.descriptionIncident = descriptionIncident;
	}

	public String getLocalisationIncident() {
		return localisationIncident;
	}

	public void setLocalisationIncident(String localisationIncident) {
		this.localisationIncident = localisationIncident;
	}

	public String getDescriptionAcces() {
		return descriptionAcces;
	}

	public void setDescriptionAcces(String descriptionAcces) {
		this.descriptionAcces = descriptionAcces;
	}

	public Long getPointObstacleGPSN() {
		return pointObstacleGPSN;
	}

	public void setPointObstacleGPSN(Long pointObstacleGPSN) {
		this.pointObstacleGPSN = pointObstacleGPSN;
	}

	public Long getPointObstacleGPSW() {
		return pointObstacleGPSW;
	}

	public void setPointObstacleGPSW(Long pointObstacleGPSW) {
		this.pointObstacleGPSW = pointObstacleGPSW;
	}

	public String getDescriptionObstacle() {
		return descriptionObstacle;
	}

	public void setDescriptionObstacle(String descriptionObstacle) {
		this.descriptionObstacle = descriptionObstacle;
	}

	public Boolean getTypeIntervention() {
		return typeIntervention;
	}

	public void setTypeIntervention(Boolean typeIntervention) {
		this.typeIntervention = typeIntervention;
	}

	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public List<Devis> getDevis() {
		return devis;
	}

	public void setDevis(List<Devis> devis) {
		this.devis = devis;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((dateDemande == null) ? 0 : dateDemande.hashCode());
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
		Demande other = (Demande) obj;
		if (dateDemande == null) {
			if (other.dateDemande != null)
				return false;
		} else if (!dateDemande.equals(other.dateDemande))
			return false;
		return true;
	}

	public String getMotif() {
		return motif;
	}

	public void setMotif(String motif) {
		this.motif = motif;
	}

	public String getDistanceMesureLiaisonFon() {
		return distanceMesureLiaisonFon;
	}

	public void setDistanceMesureLiaisonFon(String distanceMesureLiaisonFon) {
		this.distanceMesureLiaisonFon = distanceMesureLiaisonFon;
	}

	public String getAxeGlobalMesure() {
		return axeGlobalMesure;
	}

	public void setAxeGlobalMesure(String axeGlobalMesure) {
		this.axeGlobalMesure = axeGlobalMesure;
	}

	public String getBilanOptiqueDb() {
		return bilanOptiqueDb;
	}

	public void setBilanOptiqueDb(String bilanOptiqueDb) {
		this.bilanOptiqueDb = bilanOptiqueDb;
	}

	public String getNomInterlocuteurDr() {
		return nomInterlocuteurDr;
	}

	public void setNomInterlocuteurDr(String nomInterlocuteurDr) {
		this.nomInterlocuteurDr = nomInterlocuteurDr;
	}

	public String getPrenomInterlocuteurDr() {
		return prenomInterlocuteurDr;
	}

	public void setPrenomInterlocuteurDr(String prenomInterlocuteurDr) {
		this.prenomInterlocuteurDr = prenomInterlocuteurDr;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public String getTelephoneDr() {
		return telephoneDr;
	}

	public void setTelephoneDr(String telephoneDr) {
		this.telephoneDr = telephoneDr;
	}

	public String getContactDemandeur() {
		return contactDemandeur;
	}

	public void setContactDemandeur(String contactDemandeur) {
		this.contactDemandeur = contactDemandeur;
	}

	public Double getPrGpsX() {
		return prGpsX;
	}

	public void setPrGpsX(Double prGpsX) {
		this.prGpsX = prGpsX;
	}

	public Double getPrGpsY() {
		return prGpsY;
	}

	public void setPrGpsY(Double prGpsY) {
		this.prGpsY = prGpsY;
	}

	public String getRefRegenerationErpt() {
		return refRegenerationErpt;
	}

	public void setRefRegenerationErpt(String refRegenerationErpt) {
		this.refRegenerationErpt = refRegenerationErpt;
	}

	public Date getDateFinReception() {
		return dateFinReception;
	}

	public void setDateFinReception(Date dateFinReception) {
		this.dateFinReception = dateFinReception;
	}

	public String getInterlocuteur() {
		return interlocuteur;
	}

	public void setInterlocuteur(String interlocuteur) {
		this.interlocuteur = interlocuteur;
	}

	
}
