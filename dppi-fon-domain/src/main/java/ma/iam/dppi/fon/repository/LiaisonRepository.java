package ma.iam.dppi.fon.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ma.iam.dppi.fon.domain.EtatLiaison;
import ma.iam.dppi.fon.domain.Liaison;

/**
 * @author Z.BELGHAOUTI
 *
 */
@Repository
public interface LiaisonRepository extends JpaRepository<Liaison, Long> {

	// QUERIES FOR PROJECT EXTERNE
	
//	@Query(value = "select distinct l from Liaison l "
//			+ "inner join l.demandes d "
//			+ "inner join l.etatLiaison el "
//			+ "inner join d.typeDemande td "
//			+ "inner join d.etatDemande ed "
//			+ "where (l.idtDr = COALESCE(:idDr, l.idtDr) or 0L = :idDr) "
//			+ "and (l.idtDc = COALESCE(:idDc, l.idtDc) or 0L = :idDc) "
//			+ "and (l.idtCommune = COALESCE(:idCommune, l.idtCommune) or 0L = :idCommune) "
//			+ "and (l.idtOperateur = COALESCE(:idtOper, l.idtOperateur) or 0L = :idtOper) "
//			+ "and (d.dateDemande between :dateDebut and :dateFin) "
//			+ "and (td.idt = COALESCE(:idTypeDemande, td.idt)  or 0L = :idTypeDemande) "
//			+ "and (:code is null or td.code = :code) "
//			+ "and (COALESCE(:codeEtatLiaison, NULL) IS NULL OR el.code in (:codeEtatLiaison)) "
//			+ "and (COALESCE(:etatDemandeCode, NULL) IS NULL OR ed.code in (:etatDemandeCode)) "
//			+ "and (COALESCE(:idsEtatLiaison, NULL) IS NULL OR el.idt in (:idsEtatLiaison)) "
//			+ "and (lower(l.reference) like lower(CONCAT('%',:reference,'%'))) "
//			+ "and (:codeSiteErpt is NULL OR l.codeSiteErpt like (CONCAT('%',:codeSiteErpt,'%')) ) "
//			+ "and l.demandeAnnuler = 0  "
//			+ "and l.archive = 0   ")
//	List<Liaison> findDemandeGcByParamsDates(@Param("dateDebut") Date dateDebut, @Param("dateFin") Date dateFin,
//			@Param("idDr") Long idDr, @Param("idDc") Long idDc, @Param("idCommune") Long idCommune,
//			@Param("idTypeDemande") Long idTypeDemande,@Param("code") String code, @Param("codeEtatLiaison")  List<String> codeEtatLiaison,
//			@Param("etatDemandeCode") List<String> etatDemandeCode,
//			@Param("idsEtatLiaison") List<Long> idsEtatLiaison, 
//			 @Param("idtOper") Long idtOper, 
//			@Param("reference") String reference,
//			@Param("codeSiteErpt") String codeSiteErpt,
//			Pageable pageable);
//	
//	
//	@Query(value = "select distinct l from Liaison l "
//			+ "inner join l.demandes d "
//			+ "inner join l.etatLiaison el "
//			+ "inner join d.typeDemande td "
//			+ "inner join d.etatDemande ed "
//			+ "where (l.idtDr = COALESCE(:idDr, l.idtDr) or 0L = :idDr) "
//			+ "and (l.idtDc = COALESCE(:idDc, l.idtDc) or 0L = :idDc) "
//			+ "and (l.idtCommune = COALESCE(:idCommune, l.idtCommune) or 0L = :idCommune) "
//			+ "and (l.idtOperateur = COALESCE(:idtOper, l.idtOperateur) or 0L = :idtOper) "
//			+ "and (td.idt = COALESCE(:idTypeDemande, td.idt)  or 0L = :idTypeDemande) "
//			+ "and (:code is null or td.code = :code) "
//			+ "and (COALESCE(:codeEtatLiaison, NULL) IS NULL OR el.code in (:codeEtatLiaison)) "
//			+ "and (:codeEtatDemande is NULL OR ed.code in (:codeEtatDemande)) "
//			+ "and (COALESCE(:idsEtatLiaison, NULL) IS NULL OR el.idt in (:idsEtatLiaison)) "
//			+ "and (lower(l.reference) like lower(CONCAT('%',:reference,'%'))) "
//			+ "and (:codeSiteErpt is NULL OR l.codeSiteErpt like (CONCAT('%',:codeSiteErpt,'%')) ) "
//			+ "and l.demandeAnnuler = 0  "
//			+ "and l.archive = 0   ")
//	List<Liaison> findDemandeGcByParams(@Param("idDr") Long idDr, @Param("idDc") Long idDc, @Param("idCommune") Long idCommune,
//			@Param("idTypeDemande") Long idTypeDemande,@Param("code") String code, @Param("codeEtatLiaison")  List<String> codeEtatLiaison,
//			@Param("codeEtatDemande") List<String> codeEtatDemande,
//			@Param("idsEtatLiaison") List<Long> idsEtatLiaison,
//			 @Param("idtOper") Long idtOper, 
//			@Param("reference") String reference,
//			@Param("codeSiteErpt") String codeSiteErpt,
//
//			Pageable pageable);
//	
//	@Query(value = "select count(distinct(l.idt)) from Liaison l "
//			+ "inner join l.demandes d "
//			+ "inner join l.etatLiaison el "
//			+ "inner join d.typeDemande td "
//			+ "inner join d.etatDemande ed "
//			+ "where (l.idtDr = COALESCE(:idDr, l.idtDr) or 0L = :idDr) "
//			+ "and (l.idtDc = COALESCE(:idDc, l.idtDc) or 0L = :idDc)"
//			+ "and (l.idtCommune = COALESCE(:idCommune, l.idtCommune) or 0L = :idCommune) "
//			+ "and (l.idtOperateur = COALESCE(:idtOper, l.idtOperateur) or 0L = :idtOper) "
//			+ "and (d.dateDemande between :dateDebut and :dateFin) "
//			+ "and (td.idt = COALESCE(:idTypeDemande, td.idt)  or 0L = :idTypeDemande) "
//			+ "and (lower(td.code) like lower(CONCAT('%',:code,'%'))) "
//			+ "and (COALESCE(:codeEtatLiaison, NULL) IS NULL OR el.code in (:codeEtatLiaison)) "
//			+ "and (COALESCE(:etatDemandeCode, NULL) IS NULL OR ed.code in (:etatDemandeCode)) "
//			+ "and (COALESCE(:idsEtatLiaison, NULL) IS NULL OR el.idt in (:idsEtatLiaison)) "
//			+ "and (lower(l.reference) like lower(CONCAT('%',:reference,'%'))) "
//			+ "and (:codeChambreErpt is NULL OR l.codeSiteErpt like (CONCAT('%',:codeChambreErpt,'%')) ) "
//			+ "and l.demandeAnnuler = 0  "
//			+ "and l.archive = 0   ")
//	Long countDemandeGcByParamsDates(@Param("dateDebut") Date dateDebut, @Param("dateFin") Date dateFin,
//			@Param("idDr") Long idDr, @Param("idDc") Long idDc, @Param("idCommune") Long idCommune,
//			@Param("idTypeDemande") Long idTypeDemande,@Param("code") String code, @Param("codeEtatLiaison") List<String> codeEtatLiaison,
//			@Param("etatDemandeCode") List<String> etatDemandeCode,
//			@Param("idsEtatLiaison") List<Long> idsEtatLiaison,
//			 @Param("idtOper") Long idtOper, 
//			@Param("reference") String reference,@Param("codeChambreErpt") String codeChambreErpt
//		);
//	
//	@Query(value = "select count(distinct(l.idt)) from Liaison l "
//			+ "inner join l.demandes d "
//			+ "inner join l.etatLiaison el "
//			+ "inner join d.typeDemande td "
//			+ "inner join d.etatDemande ed "
//			+ "where (l.idtDr = COALESCE(:idDr, l.idtDr) or 0L = :idDr) "
//			+ "and (l.idtDc = COALESCE(:idDc, l.idtDc) or 0L = :idDc)"
//			+ "and (l.idtCommune = COALESCE(:idCommune, l.idtCommune) or 0L = :idCommune)"
//			+ "and (l.idtOperateur = COALESCE(:idtOper, l.idtOperateur) or 0L = :idtOper) "
//			+ "and (td.idt = COALESCE(:idTypeDemande, td.idt)  or 0L = :idTypeDemande) "
//			+ "and (lower(td.code) like lower(CONCAT('%',:code,'%'))) "
//			+ "and (COALESCE(:codeEtatLiaison, NULL) IS NULL OR el.code in (:codeEtatLiaison)) "
//			+ "and (COALESCE(:etatDemandeCode, NULL) IS NULL OR ed.code in (:etatDemandeCode)) "		
//			+ "and (COALESCE(:idsEtatLiaison, NULL) IS NULL OR el.idt in (:idsEtatLiaison)) "
//			+ "and (lower(l.reference) like lower(CONCAT('%',:reference,'%'))) "
//			+ "and (:codeChambreErpt is NULL OR l.codeSiteErpt like (CONCAT('%',:codeChambreErpt,'%')) ) "
//			+ "and l.demandeAnnuler = 0  "
//			+ "and l.archive = 0   ")
//	Long countDemandeGcByParams(@Param("idDr") Long idDr, @Param("idDc") Long idDc, @Param("idCommune") Long idCommune,
//			@Param("idTypeDemande") Long idTypeDemande,@Param("code") String code, @Param("codeEtatLiaison") List<String> codeEtatLiaison,
//			@Param("etatDemandeCode") List<String> etatDemandeCode,
//			@Param("idsEtatLiaison") List<Long> idsEtatLiaison,
//			 @Param("idtOper") Long idtOper, 
//			@Param("reference") String reference ,
//@Param("codeChambreErpt") String codeChambreErpt
//
//			);




	@Query(value = "select distinct l from Liaison l "
			+ "inner join l.demandes d "
			+ "inner join l.etatLiaison el "
			+ "inner join d.typeDemande td "
			+ "inner join d.etatDemande ed "
			+ "where (l.idtDr = COALESCE(:idDr, l.idtDr) or 0L = :idDr) "
			+ "and (l.idtDc = COALESCE(:idDc, l.idtDc) or 0L = :idDc) "
			+ "and (l.idtCommune = COALESCE(:idCommune, l.idtCommune) or 0L = :idCommune) "
			+ "and (l.idtOperateur = COALESCE(:idtOper, l.idtOperateur) or 0L = :idtOper) "
			+ "and (d.dateDemande between :dateDebut and :dateFin) "
			+ "and (lower(td.code) like lower(CONCAT('%',:codeTypeDemande,'%'))) "
			+ "and (COALESCE(:codeEtatLiaison, NULL) IS NULL OR el.code in (:codeEtatLiaison)) "
			+ "and (COALESCE(:etatDemandeCode, NULL) IS NULL OR ed.code in (:etatDemandeCode)) "
			+ "and (COALESCE(:idsEtatLiaison, NULL) IS NULL OR el.idt in (:idsEtatLiaison)) "
			+ "and (lower(l.reference) like lower(CONCAT('%',:reference,'%'))) "
			+ "and l.demandeAnnuler = 0  "
			+ "and l.archive = 0  "
			+ "and (COALESCE(:idsEtatDemande, NULL) IS NULL OR ed.idt in (:idsEtatDemande)) ")
	List<Liaison> findDemandeGcByParamsDates(@Param("dateDebut") Date dateDebut, @Param("dateFin") Date dateFin,
			@Param("idDr") Long idDr, @Param("idDc") Long idDc, @Param("idCommune") Long idCommune,
			@Param("codeTypeDemande") String codeTypeDemande, @Param("codeEtatLiaison")  List<String> codeEtatLiaison,
			@Param("etatDemandeCode") List<String> etatDemandeCode,
			@Param("idsEtatLiaison") List<Long> idsEtatLiaison, 
			 @Param("idtOper") Long idtOper, 
			@Param("reference") String reference, @Param("idsEtatDemande") List<Long> idsEtatDemande, 
			Pageable pageable);
	
	
	@Query(value = "select distinct l from Liaison l "
			+ "inner join l.demandes d "
			+ "inner join l.etatLiaison el "
			+ "inner join d.typeDemande td "
			+ "inner join d.etatDemande ed "
			+ "where (l.idtDr = COALESCE(:idDr, l.idtDr) or 0L = :idDr) "
			+ "and (l.idtDc = COALESCE(:idDc, l.idtDc) or 0L = :idDc) "
			+ "and (l.idtCommune = COALESCE(:idCommune, l.idtCommune) or 0L = :idCommune) "
			+ "and (l.idtOperateur = COALESCE(:idtOper, l.idtOperateur) or 0L = :idtOper) "
			+ "and (lower(td.code) like lower(CONCAT('%',:codeTypeDemande,'%'))) "
			+ "and (COALESCE(:codeEtatLiaison, NULL) IS NULL OR el.code in (:codeEtatLiaison)) "
			+ "and (COALESCE(:codeEtatDemande, NULL) IS NULL OR ed.code in (:codeEtatDemande)) "
			+ "and (COALESCE(:idsEtatLiaison, NULL) IS NULL OR el.idt in (:idsEtatLiaison)) "
			+ "and (lower(l.reference) like lower(CONCAT('%',:reference,'%'))) "
			+ "and l.demandeAnnuler = 0 "
			+ "and l.archive = 0 "
			+ "and (COALESCE(:idsEtatDemande, NULL) IS NULL OR ed.idt in (:idsEtatDemande)) ")
	List<Liaison> findDemandeGcByParams(@Param("idDr") Long idDr, @Param("idDc") Long idDc, @Param("idCommune") Long idCommune,
			@Param("codeTypeDemande") String codeTypeDemande, @Param("codeEtatLiaison")  List<String> codeEtatLiaison,
			@Param("codeEtatDemande") List<String> codeEtatDemande,
			@Param("idsEtatLiaison") List<Long> idsEtatLiaison,
			 @Param("idtOper") Long idtOper, 
			@Param("reference") String reference, @Param("idsEtatDemande") List<Long> idsEtatDemande, 
			Pageable pageable);

	
	
	
	@Query(value = "select count(distinct(l.idt)) from Liaison l "
			+ "inner join l.demandes d "
			+ "inner join l.etatLiaison el "
			+ "inner join d.typeDemande td "
			+ "inner join d.etatDemande ed "
			+ "where (l.idtDr = COALESCE(:idDr, l.idtDr) or 0L = :idDr) "
			+ "and (l.idtDc = COALESCE(:idDc, l.idtDc) or 0L = :idDc)"
			+ "and (l.idtCommune = COALESCE(:idCommune, l.idtCommune) or 0L = :idCommune)"
			+ "and (l.idtOperateur = COALESCE(:idtOper, l.idtOperateur) or 0L = :idtOper) "
			+ "and (d.dateDemande between :dateDebut and :dateFin) "
			+ "and (lower(td.code) like CONCAT('%',:codeTypeDemande,'%')) "
			+ "and (COALESCE(:codeEtatLiaison, NULL) IS NULL OR el.code in (:codeEtatLiaison)) "
			+ "and (COALESCE(:etatDemandeCode, NULL) IS NULL OR ed.code in (:etatDemandeCode)) "
			+ "and (COALESCE(:idsEtatLiaison, NULL) IS NULL OR el.idt in (:idsEtatLiaison)) "
			+ "and (lower(l.reference) like lower(CONCAT('%',:reference,'%'))) "
			+ "and l.demandeAnnuler = 0  "
			+ "and l.archive = 0  "
			+ "and (COALESCE(:idsEtatDemande, NULL) IS NULL OR ed.idt in (:idsEtatDemande)) ")
	Long countDemandeGcByParamsDates(@Param("dateDebut") Date dateDebut, @Param("dateFin") Date dateFin,
			@Param("idDr") Long idDr, @Param("idDc") Long idDc, @Param("idCommune") Long idCommune,
			@Param("codeTypeDemande") String codeTypeDemande, @Param("codeEtatLiaison") List<String> codeEtatLiaison,
			@Param("etatDemandeCode") List<String> etatDemandeCode,
			@Param("idsEtatLiaison") List<Long> idsEtatLiaison,
			 @Param("idtOper") Long idtOper, 
			@Param("reference") String reference, @Param("idsEtatDemande") List<Long> idsEtatDemande 
		);
	
	@Query(value = "select count(distinct(l.idt)) from Liaison l "
			+ "inner join l.demandes d "
			+ "inner join l.etatLiaison el "
			+ "inner join d.typeDemande td "
			+ "inner join d.etatDemande ed "
			+ "where (l.idtDr = COALESCE(:idDr, l.idtDr) or 0L = :idDr) "
			+ "and (l.idtDc = COALESCE(:idDc, l.idtDc) or 0L = :idDc)"
			+ "and (l.idtCommune = COALESCE(:idCommune, l.idtCommune) or 0L = :idCommune)"
			+ "and (l.idtOperateur = COALESCE(:idtOper, l.idtOperateur) or 0L = :idtOper) "
			+ "and (lower(td.code) like lower(CONCAT('%',:codeTypeDemande,'%'))) "
			+ "and (COALESCE(:codeEtatLiaison, NULL) IS NULL OR el.code in (:codeEtatLiaison)) "
			+ "and (COALESCE(:etatDemandeCode, NULL) IS NULL OR ed.code in (:etatDemandeCode)) "
			+ "and (COALESCE(:idsEtatLiaison, NULL) IS NULL OR el.idt in (:idsEtatLiaison)) "
			+ "and (lower(l.reference) like lower(CONCAT('%',:reference,'%'))) "
			+ "and l.demandeAnnuler = 0  "
			+ "and l.archive = 0  "
			+ "and (COALESCE(:idsEtatDemande, NULL) IS NULL OR ed.idt in (:idsEtatDemande)) ")
	Long countDemandeGcByParams(@Param("idDr") Long idDr, @Param("idDc") Long idDc, @Param("idCommune") Long idCommune,
			@Param("codeTypeDemande") String codeTypeDemande, @Param("codeEtatLiaison") List<String> codeEtatLiaison,
			@Param("etatDemandeCode") List<String> etatDemandeCode,
			@Param("idsEtatLiaison") List<Long> idsEtatLiaison,
			 @Param("idtOper") Long idtOper, 
			@Param("reference") String reference, @Param("idsEtatDemande") List<Long> idsEtatDemande
			);
	
	@Query("SELECT DISTINCT l FROM Liaison l "
			+ " WHERE l.idt in (:ids) ORDER BY l.idt DESC ")
	List<Liaison> getListDemandeByIdts(@Param("ids") List<Long> ids);

	// QUERIES FOR PROJECT INTERNE
	@Query(value = "select distinct l from Liaison l " + "inner join l.demandes d " + "inner join l.etatLiaison el "
			+ "inner join d.typeDemande td " + "inner join d.etatDemande ed "
			+ "where d.idt in (select coalesce(max(de.idt), 0) from Demande de "
			+ "inner join de.liaison li where li.idt = l.idt) "
			+ "and (l.idtDr = COALESCE(:idDr, l.idtDr) or 0L = :idDr) "
			+ "and (l.idtDc = COALESCE(:idDc, l.idtDc) or 0L = :idDc)"
			+ "and (l.idtCommune = COALESCE(:idCommune, l.idtCommune) or 0L = :idCommune)"
			+ "and (l.idtOperateur = COALESCE(:idtOper, l.idtOperateur) or 0L = :idtOper) "
			+ "and (d.dateDemande between :dateDebut and :dateFin) "
			+ "and (td.idt = COALESCE(:idTypeDemande, td.idt)  or 0L = :idTypeDemande) "
			+ "and (el.idt = COALESCE(:idEtatLiaison, el.idt)  or 0L = :idEtatLiaison) "
			+ "and (ed.idt = COALESCE(:idEtatDemande, ed.idt)  or 0L = :idEtatDemande) "
			+ "and (COALESCE(:listIdEtatDemande, NULL) IS NULL OR ed.idt in (:listIdEtatDemande)) "
			+ "and (lower(l.reference) like lower(CONCAT('%',:reference,'%'))) "
			+ "and (COALESCE(:idsEtatDemande, NULL) IS NULL OR ed.idt in (:idsEtatDemande)) ")
	List<Liaison> findLiaisonInterneByParamsDates(@Param("dateDebut") Date dateDebut, @Param("dateFin") Date dateFin,
			@Param("idDr") Long idDr, @Param("idDc") Long idDc, @Param("idCommune") Long idCommune,
			@Param("idTypeDemande") Long idTypeDemande, @Param("idEtatLiaison") Long idEtatLiaison,
			@Param("idEtatDemande") Long idEtatDemande, @Param("idtOper") Long idtOper,
			@Param("reference") String reference, @Param("idsEtatDemande") List<Long> idsEtatDemande,
			@Param("listIdEtatDemande") List<Long> listIdEtatDemande, Pageable pageable);

	@Query(value = "select distinct l from Liaison l " + "inner join l.demandes d " + "inner join l.etatLiaison el "
			+ "inner join d.typeDemande td " + "inner join d.etatDemande ed "
			+ "where d.idt in (select coalesce(max(de.idt), 0) from Demande de "
			+ "inner join de.liaison li where li.idt = l.idt) "
			+ "and (l.idtDr = COALESCE(:idDr, l.idtDr) or 0L = :idDr) "
			+ "and (l.idtDc = COALESCE(:idDc, l.idtDc) or 0L = :idDc)"
			+ "and (l.idtCommune = COALESCE(:idCommune, l.idtCommune) or 0L = :idCommune)"
			+ "and (l.idtOperateur = COALESCE(:idtOper, l.idtOperateur) or 0L = :idtOper) "
			+ "and (td.idt = COALESCE(:idTypeDemande, td.idt)  or 0L = :idTypeDemande) "
			+ "and (el.idt = COALESCE(:idEtatLiaison, el.idt)  or 0L = :idEtatLiaison) "
			+ "and (ed.idt = COALESCE(:idEtatDemande, ed.idt)  or 0L = :idEtatDemande) "
			+ "and (COALESCE(:listIdEtatDemande, NULL) IS NULL OR ed.idt in (:listIdEtatDemande)) "
			+ "and (lower(l.reference) like lower(CONCAT('%',:reference,'%'))) "
			+ "and (COALESCE(:idsEtatDemande, NULL) IS NULL OR ed.idt in (:idsEtatDemande)) ")
	List<Liaison> findLiaisonInterneByParams(@Param("idDr") Long idDr, @Param("idDc") Long idDc,
			@Param("idCommune") Long idCommune, @Param("idTypeDemande") Long idTypeDemande,
			@Param("idEtatLiaison") Long idEtatLiaison, @Param("idEtatDemande") Long idEtatDemande,
			@Param("idtOper") Long idtOper, @Param("reference") String reference,
			@Param("idsEtatDemande") List<Long> idsEtatDemande,
			@Param("listIdEtatDemande") List<Long> listIdEtatDemande, Pageable pageable);

	@Query("select count(l.idt) from Liaison l " + "inner join l.demandes d " + "inner join l.etatLiaison el "
			+ "inner join d.typeDemande td " + "inner join d.etatDemande ed "
			+ "where d.idt in (select coalesce(max(de.idt), 0) from Demande de "
			+ "inner join de.liaison li where li.idt = l.idt) "
			+ "and (l.idtDr = COALESCE(:idDr, l.idtDr) or 0L = :idDr) "
			+ "and (l.idtDc = COALESCE(:idDc, l.idtDc) or 0L = :idDc)"
			+ "and (l.idtCommune = COALESCE(:idCommune, l.idtCommune) or 0L = :idCommune)"
			+ "and (l.idtOperateur = COALESCE(:idtOper, l.idtOperateur) or 0L = :idtOper) "
			+ "and (d.dateDemande between :dateDebut and :dateFin) "
			+ "and (td.idt = COALESCE(:idTypeDemande, td.idt)  or 0L = :idTypeDemande) "
			+ "and (el.idt = COALESCE(:idEtatLiaison, el.idt)  or 0L = :idEtatLiaison) "
			+ "and (ed.idt = COALESCE(:idEtatDemande, ed.idt)  or 0L = :idEtatDemande) "
			+ "and (lower(l.reference) like lower(CONCAT('%',:reference,'%'))) "
			+ "and (COALESCE(:idsEtatDemande, NULL) IS NULL OR ed.idt in (:idsEtatDemande))  ")
	Long countLiaisonInterneByParamsDates(@Param("dateDebut") Date dateDebut, @Param("dateFin") Date dateFin,
			@Param("idDr") Long idDr, @Param("idDc") Long idDc, @Param("idCommune") Long idCommune,
			@Param("idTypeDemande") Long idTypeDemande, @Param("idEtatLiaison") Long idEtatLiaison,
			@Param("idEtatDemande") Long idEtatDemande, @Param("idtOper") Long idtOper,
			@Param("reference") String reference, @Param("idsEtatDemande") List<Long> idsEtatDemande);

	@Query("select count(l.idt) from Liaison l " + "inner join l.demandes d " + "inner join l.etatLiaison el "
			+ "inner join d.typeDemande td " + "inner join d.etatDemande ed "
			+ "where d.idt in (select coalesce(max(de.idt), 0) from Demande de "
			+ "inner join de.liaison li where li.idt = l.idt) "
			+ "and (l.idtDr = COALESCE(:idDr, l.idtDr) or 0L = :idDr) "
			+ "and (l.idtDc = COALESCE(:idDc, l.idtDc) or 0L = :idDc)"
			+ "and (l.idtCommune = COALESCE(:idCommune, l.idtCommune) or 0L = :idCommune)"
			+ "and (l.idtOperateur = COALESCE(:idtOper, l.idtOperateur) or 0L = :idtOper) "
			+ "and (td.idt = COALESCE(:idTypeDemande, td.idt)  or 0L = :idTypeDemande) "
			+ "and (el.idt = COALESCE(:idEtatLiaison, el.idt)  or 0L = :idEtatLiaison) "
			+ "and (ed.idt = COALESCE(:idEtatDemande, ed.idt)  or 0L = :idEtatDemande) "
			+ "and (lower(l.reference) like lower(CONCAT('%',:reference,'%'))) "
			+ "and (COALESCE(:idsEtatDemande, NULL) IS NULL OR ed.idt in (:idsEtatDemande)) ")
	Long countLiaisonInterneByParams(@Param("idDr") Long idDr, @Param("idDc") Long idDc,
			@Param("idCommune") Long idCommune, @Param("idTypeDemande") Long idTypeDemande,
			@Param("idEtatLiaison") Long idEtatLiaison, @Param("idEtatDemande") Long idEtatDemande,
			@Param("idtOper") Long idtOper, @Param("reference") String reference,
			@Param("idsEtatDemande") List<Long> idsEtatDemande);

	@Query("SELECT DISTINCT l FROM Liaison l " + " WHERE l.idt in (:ids) ORDER BY l.idt DESC ")
	List<Liaison> getListLiaisonsByIdts(@Param("ids") List<Long> ids);

	@Query("SELECT l FROM Liaison l WHERE (lower(l.reference) like lower(CONCAT('%',:ref,'%')))")
	List<Liaison> getListLiasonByReference(@Param("ref") String ref);

	List<Liaison> findByEtatLiaison(EtatLiaison etatLiaison);
	
	List<Liaison> findByEtatLiaisonAndOperateur(EtatLiaison etatLiaison,String operateur);
	
	Liaison findByReference(String reference);
//	@Query(value = "SELECT  DISTINCT l from Liaison where "
//			+ " (l.idtOperateur = COALESCE(:idtOper, l.idtOperateur) or 0L = :idtOper) "
//			+ "and l.etatLiaison.code='PARTAGE' ")
//	List<Liaison> getLiaisonPartageAndOperateur( @Param("idtOper") Long operateur);
}
