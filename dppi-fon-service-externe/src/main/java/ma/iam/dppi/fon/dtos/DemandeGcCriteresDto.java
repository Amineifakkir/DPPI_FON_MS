package ma.iam.dppi.fon.dtos;

import java.util.List;

/**
 * 
 * @author Z.BELGHAOUTI
 *
 */
public class DemandeGcCriteresDto {

	private String dateDebut;
	private String dateFin;
	private Long idDr;
	private Long idDc;
	private Long idCommune;
	private Long idTypeDemande;
	private String codeTypeDemande;
	private List<String> codeTypeDemandes;
	private List<String> codeEtatLiaison;
	private List<String> etatDemandeCode;
	
	private String codeLiaisonErpt;
	private Long idEtatLiaison;
	private List<Long> idsEtatLiaison;
	
	private Long idEtatDemande;
	private Long idOperateur;
	private String reference;
    private String descriptionIncident;
	private String localisationIncident;
    private Long pointObstacleGPSN;
	private Long pointObstacleGPSW;
	private String descriptionObstacle;
	private Integer start;
	private Integer end;
	
	private String dateReception;
	
	private String interlocuteur;
	
	
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
	public List<String> getEtatDemandeCode() {
		return etatDemandeCode;
	}
	public void setEtatDemandeCode(List<String> etatDemandeCode) {
		this.etatDemandeCode = etatDemandeCode;
	}
	public String getCodeLiaisonErpt() {
		return codeLiaisonErpt;
	}
	public void setCodeLiaisonErpt(String codeLiaisonErpt) {
		this.codeLiaisonErpt = codeLiaisonErpt;
	}
	public List<Long> getIdsEtatLiaison() {
		return idsEtatLiaison;
	}
	public void setIdsEtatLiaison(List<Long> idsEtatLiaison) {
		this.idsEtatLiaison = idsEtatLiaison;
	}

	public List<String> getCodeEtatLiaison() {
		return codeEtatLiaison;
	}
	public void setCodeEtatLiaison(List<String> codeEtatLiaison) {
		this.codeEtatLiaison = codeEtatLiaison;
	}
	public String getCodeTypeDemande() {
		return codeTypeDemande;
	}
	public void setCodeTypeDemande(String codeTypeDemande) {
		this.codeTypeDemande = codeTypeDemande;
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
	public List<String> getCodeTypeDemandes() {
		return codeTypeDemandes;
	}
	public void setCodeTypeDemandes(List<String> codeTypeDemandes) {
		this.codeTypeDemandes = codeTypeDemandes;
	}
	public String getDateReception() {
		return dateReception;
	}
	public void setDateReception(String dateReception) {
		this.dateReception = dateReception;
	}
	public String getInterlocuteur() {
		return interlocuteur;
	}
	public void setInterlocuteur(String interlocuteur) {
		this.interlocuteur = interlocuteur;
	}
	
	
	
//	private String dateDebut;
//	private String dateFin;
//	private Long idDr;
//	private Long idDc;
//	private Long idCommune;
//	private Long idTypeDemande;
//	private String codeTypeDemande;
//	private String codeChambreErpt;
//	private Long idEtatLiaison;
//	private List<Long> idsEtatLiaison;
//	private List<String> codeEtatLiaison;
//	private String codeTypeLiaison;
//	private String codeSr;
//	private String etatDemandeCode;
//	private Long idEtatDemande;
//	private Long idOperateur;
//	private String reference;
//    private String descriptionIncident;
//	private String localisationIncident;
//    private Long pointObstacleGPSN;
//	private Long pointObstacleGPSW;
//	private String descriptionObstacle;
//	private Integer start;
//	private Integer end;
//	private String codeLiaisonErpt;
//	
//	
//	
//	public Long getPointObstacleGPSN() {
//		return pointObstacleGPSN;
//	}
//	public void setPointObstacleGPSN(Long pointObstacleGPSN) {
//		this.pointObstacleGPSN = pointObstacleGPSN;
//	}
//	public Long getPointObstacleGPSW() {
//		return pointObstacleGPSW;
//	}
//	public void setPointObstacleGPSW(Long pointObstacleGPSW) {
//		this.pointObstacleGPSW = pointObstacleGPSW;
//	}
//	public String getDescriptionObstacle() {
//		return descriptionObstacle;
//	}
//	public void setDescriptionObstacle(String descriptionObstacle) {
//		this.descriptionObstacle = descriptionObstacle;
//	}
//	public String getDescriptionIncident() {
//		return descriptionIncident;
//	}
//	public void setDescriptionIncident(String descriptionIncident) {
//		this.descriptionIncident = descriptionIncident;
//	}
//	public String getLocalisationIncident() {
//		return localisationIncident;
//	}
//	public void setLocalisationIncident(String localisationIncident) {
//		this.localisationIncident = localisationIncident;
//	}
//
//	public String getCodeChambreErpt() {
//		return codeChambreErpt;
//	}
//	public void setCodeChambreErpt(String codeChambreErpt) {
//		this.codeChambreErpt = codeChambreErpt;
//	}
//	public List<Long> getIdsEtatLiaison() {
//		return idsEtatLiaison;
//	}
//	public void setIdsEtatLiaison(List<Long> idsEtatLiaison) {
//		this.idsEtatLiaison = idsEtatLiaison;
//	}
//
//	public List<String> getCodeEtatLiaison() {
//		return codeEtatLiaison;
//	}
//	public void setCodeEtatLiaison(List<String> codeEtatLiaison) {
//		this.codeEtatLiaison = codeEtatLiaison;
//	}
//	public String getCodeTypeDemande() {
//		return codeTypeDemande;
//	}
//	public void setCodeTypeDemande(String codeTypeDemande) {
//		this.codeTypeDemande = codeTypeDemande;
//	}
//	public String getDateDebut() {
//		return dateDebut;
//	}
//	public void setDateDebut(String dateDebut) {
//		this.dateDebut = dateDebut;
//	}
//	public String getDateFin() {
//		return dateFin;
//	}
//	public void setDateFin(String dateFin) {
//		this.dateFin = dateFin;
//	}
//	public Long getIdDr() {
//		return idDr;
//	}
//	public void setIdDr(Long idDr) {
//		this.idDr = idDr;
//	}
//	public Long getIdDc() {
//		return idDc;
//	}
//	public void setIdDc(Long idDc) {
//		this.idDc = idDc;
//	}
//	public Long getIdCommune() {
//		return idCommune;
//	}
//	public void setIdCommune(Long idCommune) {
//		this.idCommune = idCommune;
//	}
//	public Long getIdTypeDemande() {
//		return idTypeDemande;
//	}
//	public void setIdTypeDemande(Long idTypeDemande) {
//		this.idTypeDemande = idTypeDemande;
//	}
//	public Long getIdEtatLiaison() {
//		return idEtatLiaison;
//	}
//	public void setIdEtatLiaison(Long idEtatLiaison) {
//		this.idEtatLiaison = idEtatLiaison;
//	}
//	public Long getIdEtatDemande() {
//		return idEtatDemande;
//	}
//	public void setIdEtatDemande(Long idEtatDemande) {
//		this.idEtatDemande = idEtatDemande;
//	}
//	public Long getIdOperateur() {
//		return idOperateur;
//	}
//	public void setIdOperateur(Long idOperateur) {
//		this.idOperateur = idOperateur;
//	}
//	public String getReference() {
//		return reference;
//	}
//	public void setReference(String reference) {
//		this.reference = reference;
//	}
//	public Integer getStart() {
//		return start;
//	}
//	public void setStart(Integer start) {
//		this.start = start;
//	}
//	public Integer getEnd() {
//		return end;
//	}
//	public void setEnd(Integer end) {
//		this.end = end;
//	}
//	public String getCodeTypeLiaison() {
//		return codeTypeLiaison;
//	}
//	public void setCodeTypeLiaison(String codeTypeLiaison) {
//		this.codeTypeLiaison = codeTypeLiaison;
//	}
//	public String getCodeSr() {
//		return codeSr;
//	}
//	public void setCodeSr(String codeSr) {
//		this.codeSr = codeSr;
//	}
//	public String getEtatDemandeCode() {
//		return etatDemandeCode;
//	}
//	public void setEtatDemandeCode(String etatDemandeCode) {
//		this.etatDemandeCode = etatDemandeCode;
//	}
//	public String getCodeLiaisonErpt() {
//		return codeLiaisonErpt;
//	}
//	public void setCodeLiaisonErpt(String codeLiaisonErpt) {
//		this.codeLiaisonErpt = codeLiaisonErpt;
//	}
//	

	
	
	

}
