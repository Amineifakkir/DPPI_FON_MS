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
@Table(name = "dppi_liaison")
public class Liaison {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_liaison")
	@SequenceGenerator(name = "seq_liaison", sequenceName = "seq_liaison", allocationSize = 1)
	@Column(name = "IDT", nullable = false)
	private Long idt;

	@Column(name = "REFERENCE", unique = true)
	private String reference;

	@Column(name = "CODE_SITE_ERPT")
	private String codeSiteErpt;

	@Column(name = "IDT_COMMUNE")
	private Long idtCommune;

	@Column(name = "IDT_DR")
	private Long idtDr;

	@Column(name = "IDT_DC")
	private Long idtDc;


	

	@Column(name = "XGPS_DEPART")
	private Double xGpsDepart;
	
	@Column(name = "YGPS_ARRIVEE")
	private Double yGpsArrivee;

	@Column(name = "YGPS_DEPART")
	private Double yGpsDepart;

	@Column(name = "XGPS_ARRIVEE")
	private Double xGpsArrivee;
	
	@Column(name = "CODE_SITE_A")
	private String codeSiteA;
	
	@Column(name = "CODE_SITE_B")
	private String codeSiteB;

	

	@Column(name = "DISTANCE_DISPONIBLE")
	private String distanceDisponible;

	@Column(name = "DISTANCE_SATURE")
	private String distanceSature;

	@Column(name = "IDT_OPERATEUR")
	private Long idtOperateur;

	@Column(name = "OPERATEUR")
	private String operateur;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "IDT_SITE_A")
	private Site SiteA;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "IDT_SITE_B")
	private Site SiteB;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "IDT_ETAT_LIAISON")
	private EtatLiaison etatLiaison;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "IDT_TYPE_LIAISON")
	private TypeLiaison typeLiaison;
	
	@Column(name = "DATE_RECEPTION")
	private Date dateReception;

	@Column(name = "DEMANDE_ANNULER")
	private Boolean demandeAnnuler;

	@Column(name = "ARCHIVE")
	private Boolean archive;

	@OneToMany(mappedBy = "liaison", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Demande> demandes;

	@OneToMany(mappedBy = "liaison", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<SousLiaison> sousLiaison;

	

	public Long getIdt() {
		return idt;
	}

	public void setIdt(Long idt) {
		this.idt = idt;
	}

	public EtatLiaison getEtatLiaison() {
		return etatLiaison;
	}

	public void setEtatLiaison(EtatLiaison etatLiaison) {
		this.etatLiaison = etatLiaison;
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

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	
	public TypeLiaison getTypeLiaison() {
		return typeLiaison;
	}

	public void setTypeLiaison(TypeLiaison typeLiaison) {
		this.typeLiaison = typeLiaison;
	}

	
	public Long getIdtCommune() {
		return idtCommune;
	}

	public void setIdtCommune(Long idtCommune) {
		this.idtCommune = idtCommune;
	}

	
	public Double getxGpsDepart() {
		return xGpsDepart;
	}

	public void setxGpsDepart(Double xGpsDepart) {
		this.xGpsDepart = xGpsDepart;
	}

	public Double getyGpsArrivee() {
		return yGpsArrivee;
	}

	public void setyGpsArrivee(Double yGpsArrivee) {
		this.yGpsArrivee = yGpsArrivee;
	}

	public Double getyGpsDepart() {
		return yGpsDepart;
	}

	public void setyGpsDepart(Double yGpsDepart) {
		this.yGpsDepart = yGpsDepart;
	}

	public Double getxGpsArrivee() {
		return xGpsArrivee;
	}

	public void setxGpsArrivee(Double xGpsArrivee) {
		this.xGpsArrivee = xGpsArrivee;
	}

	public String getDistanceDisponible() {
		return distanceDisponible;
	}

	public void setDistanceDisponible(String distanceDisponible) {
		this.distanceDisponible = distanceDisponible;
	}

	public String getDistanceSature() {
		return distanceSature;
	}

	public void setDistanceSature(String distanceSature) {
		this.distanceSature = distanceSature;
	}



	

	public Site getSiteA() {
		return SiteA;
	}

	public void setSiteA(Site siteA) {
		SiteA = siteA;
	}

	public Site getSiteB() {
		return SiteB;
	}

	public void setSiteB(Site siteB) {
		SiteB = siteB;
	}

	public List<SousLiaison> getSousLiaison() {
		return sousLiaison;
	}

	public void setSousLiaison(List<SousLiaison> sousLiaison) {
		this.sousLiaison = sousLiaison;
	}

	public Boolean getDemandeAnnuler() {
		return demandeAnnuler;
	}

	public void setDemandeAnnuler(Boolean demandeAnnuler) {
		this.demandeAnnuler = demandeAnnuler;
	}

	public Boolean getArchive() {
		return archive;
	}

	public void setArchive(Boolean archive) {
		this.archive = archive;
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

	public List<Demande> getDemandes() {
		return demandes;
	}

	public void setDemandes(List<Demande> demandes) {
		this.demandes = demandes;
	}

	public List<SousLiaison> getTroncon() {
		return sousLiaison;
	}

	public void setTroncon(List<SousLiaison> sousLiaison) {
		this.sousLiaison = sousLiaison;
	}




	

	
	public String getCodeSiteErpt() {
		return codeSiteErpt;
	}

	public void setCodeSiteErpt(String codeSiteErpt) {
		this.codeSiteErpt = codeSiteErpt;
	}

	public String getCodeSiteA() {
		return codeSiteA;
	}

	public void setCodeSiteA(String codeSiteA) {
		this.codeSiteA = codeSiteA;
	}

	public String getCodeSiteB() {
		return codeSiteB;
	}

	public void setCodeSiteB(String codeSiteB) {
		this.codeSiteB = codeSiteB;
	}

	public Date getDateReception() {
		return dateReception;
	}

	public void setDateReception(Date dateReception) {
		this.dateReception = dateReception;
	}

	

	

	

}
