package ma.iam.dppi.fon.interne.dtos;

import java.util.List;

/**
 * 
 * @author Z.BELGHAOUTI
 *
 */
public class CriteriaCommandeDto {

	private String dateDebut;
	private String dateFin;
	private Long idDr;
	private Long idDc;
	private Long idCommune;
	private Long idTypeDemande;
	private Long idEtatLiaison;
	private Long idEtatDemande;
	private Long idOperateur;
	private String reference;
	private Integer start;
	private Integer end;

	private List<Long> listIdEtatDemandes;
	
	private String codeTypeDemande;
	
	private String codeEtatDemande;



	public List<Long> getListIdEtatDemandes() {
		return listIdEtatDemandes;
	}

	public void setListIdEtatDemandes(List<Long> listIdEtatDemandes) {
		this.listIdEtatDemandes = listIdEtatDemandes;
	}

	public String getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(String dateDebut) {
		this.dateDebut = dateDebut;
	}

	public String getDateFin() {
		return dateFin;
	}

	public void setDateFin(String dateFin) {
		this.dateFin = dateFin;
	}

	public Long getIdDr() {
		return idDr;
	}

	public void setIdDr(Long idDr) {
		this.idDr = idDr;
	}

	public Long getIdDc() {
		return idDc;
	}

	public void setIdDc(Long idDc) {
		this.idDc = idDc;
	}

	public Long getIdCommune() {
		return idCommune;
	}

	public void setIdCommune(Long idCommune) {
		this.idCommune = idCommune;
	}

	

	

	public Long getIdTypeDemande() {
		return idTypeDemande;
	}

	public void setIdTypeDemande(Long idTypeDemande) {
		this.idTypeDemande = idTypeDemande;
	}

	public Long getIdEtatLiaison() {
		return idEtatLiaison;
	}

	public void setIdEtatLiaison(Long idEtatLiaison) {
		this.idEtatLiaison = idEtatLiaison;
	}

	public Long getIdEtatDemande() {
		return idEtatDemande;
	}

	public void setIdEtatDemande(Long idEtatDemande) {
		this.idEtatDemande = idEtatDemande;
	}

	public Long getIdOperateur() {
		return idOperateur;
	}

	public void setIdOperateur(Long idOperateur) {
		this.idOperateur = idOperateur;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getEnd() {
		return end;
	}

	public void setEnd(Integer end) {
		this.end = end;
	}

	public String getCodeTypeDemande() {
		return codeTypeDemande;
	}

	public void setCodeTypeDemande(String codeTypeDemande) {
		this.codeTypeDemande = codeTypeDemande;
	}

	public String getCodeEtatDemande() {
		return codeEtatDemande;
	}

	public void setCodeEtatDemande(String codeEtatDemande) {
		this.codeEtatDemande = codeEtatDemande;
	}
	
	

}
