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
@Table(name = "dppi_fon_sous_liaison")
public class SousLiaison {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_sous_liaison")
	@SequenceGenerator(name = "seq_sous_liaison", sequenceName = "seq_sous_liaison", allocationSize = 1)
	@Column(name = "IDT", nullable = false)
	private Long idt;

	@Column(name = "CODE")
	private String code;

	@Column(name = "IDT_DR")
	private Long idtDr;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "IDT_SITE_A")
	private Site siteA;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "IDT_SITE_B")
	private Site siteB;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "IDT_LIAISON")
	private Liaison liaison;

	@Column(name = "ETAT")
	private String etat;

	@Column(name = "DISTANCE")
	private String distance;

	@Column(name = "DISTANCE_DISPONIBLE")
	private String distanceDisponible;

	@Column(name = "DISTANCE_ESTIMATIVE")
	private String distanceEstimative;

	@Column(name = "BILAN_OBTIQUE_ESTIMATIF")
	private String bilanObtiqueEstimatif;

	@Column(name = "CABLE_FO_PROPOSE")
	private String cableFoPropose;

	@Column(name = "DELAIS_REALISATION")
	private String delaisRealisation;

	@Column(name = "NUMBER")
	private Integer number;

	@Column(name = "DISTANCE_SATURE")
	private String distanceSature;

	@Column(name = "CONTACT_DEMT")
	private String contactDemt;

	@Column(name = "INTERLOCUTEUR_DEMT")
	private String interlocuteurDemt;
	
	@Column(name = "CONTACT_DR")
	private String contactDr;

	@Column(name = "INTERLOCUTEUR_DR")
	private String interlocuteurDr;

	@Column(name = "DATE_AFFECTATION")
	private Date dateAffectation;
	
	@Column(name = "DATE_REPONSE")
	private Date dateReponse;
	
	@Column(name = "DESIGNATION_SOUS_LIAISON")
	private String designationSousLiaison;
	
	@Column(name = "DESIGNATION_TRONCON_SOUS_LIAISON")
	private String designationTronconSousLiaison;

	public Long getIdt() {
		return idt;
	}

	public void setIdt(Long idt) {
		this.idt = idt;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Long getIdtDr() {
		return idtDr;
	}

	public void setIdtDr(Long idtDr) {
		this.idtDr = idtDr;
	}

	public Liaison getLiaison() {
		return liaison;
	}

	public void setLiaison(Liaison liaison) {
		this.liaison = liaison;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	public String getDistanceDisponible() {
		return distanceDisponible;
	}

	public void setDistanceDisponible(String distanceDisponible) {
		this.distanceDisponible = distanceDisponible;
	}

	public String getDistanceEstimative() {
		return distanceEstimative;
	}

	public void setDistanceEstimative(String distanceEstimative) {
		this.distanceEstimative = distanceEstimative;
	}

	public String getBilanObtiqueEstimatif() {
		return bilanObtiqueEstimatif;
	}

	public void setBilanObtiqueEstimatif(String bilanObtiqueEstimatif) {
		this.bilanObtiqueEstimatif = bilanObtiqueEstimatif;
	}

	public String getCableFoPropose() {
		return cableFoPropose;
	}

	public void setCableFoPropose(String cableFoPropose) {
		this.cableFoPropose = cableFoPropose;
	}

	public String getDelaisRealisation() {
		return delaisRealisation;
	}

	public void setDelaisRealisation(String delaisRealisation) {
		this.delaisRealisation = delaisRealisation;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Site getSiteA() {
		return siteA;
	}

	public void setSiteA(Site siteA) {
		this.siteA = siteA;
	}

	public Site getSiteB() {
		return siteB;
	}

	public void setSiteB(Site siteB) {
		this.siteB = siteB;
	}

	public String getDistanceSature() {
		return distanceSature;
	}

	public void setDistanceSature(String distanceSature) {
		this.distanceSature = distanceSature;
	}

	public String getContactDemt() {
		return contactDemt;
	}

	public void setContactDemt(String contactDemt) {
		this.contactDemt = contactDemt;
	}

	public String getInterlocuteurDemt() {
		return interlocuteurDemt;
	}

	public void setInterlocuteurDemt(String interlocuteurDemt) {
		this.interlocuteurDemt = interlocuteurDemt;
	}

	public Date getDateAffectation() {
		return dateAffectation;
	}

	public void setDateAffectation(Date dateAffectation) {
		this.dateAffectation = dateAffectation;
	}

	public String getContactDr() {
		return contactDr;
	}

	public void setContactDr(String contactDr) {
		this.contactDr = contactDr;
	}

	public String getInterlocuteurDr() {
		return interlocuteurDr;
	}

	public void setInterlocuteurDr(String interlocuteurDr) {
		this.interlocuteurDr = interlocuteurDr;
	}

	public Date getDateReponse() {
		return dateReponse;
	}

	public void setDateReponse(Date dateReponse) {
		this.dateReponse = dateReponse;
	}

	public String getDesignationSousLiaison() {
		return designationSousLiaison;
	}

	public void setDesignationSousLiaison(String designationSousLiaison) {
		this.designationSousLiaison = designationSousLiaison;
	}

	public String getDesignationTronconSousLiaison() {
		return designationTronconSousLiaison;
	}

	public void setDesignationTronconSousLiaison(String designationTronconSousLiaison) {
		this.designationTronconSousLiaison = designationTronconSousLiaison;
	}
	
	

}
