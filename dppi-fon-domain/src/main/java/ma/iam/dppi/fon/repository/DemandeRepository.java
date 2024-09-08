package ma.iam.dppi.fon.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ma.iam.dppi.fon.domain.Demande;

/**
 * @author Z.BELGHAOUTI
 *
 */
@Repository
public interface DemandeRepository extends JpaRepository<Demande, Long>{

	@Query(value = "SELECT * FROM dppi_demande dm "
			+ "WHERE dm.IDT_LIAISON = :idtLiaison ", nativeQuery = true)
	List<Demande> getListByDemandeIdt(@Param("idtLiaison") Long idtLiaison);
	
	@Query(value = "SELECT * FROM dppi_demande dm "
			+ "WHERE dm.IDT_LIAISON = :idtLiaison ORDER BY dm.IDT DESC LIMIT 1 ", nativeQuery = true)
	Demande getLastDemandeByIdtLiaison(@Param("idtLiaison") Long idtLiaison);
	
	@Query(value = "select distinct d from Demande d "
			+ "inner join d.liaison l "
			+ "inner join l.etatLiaison el "
			+ "inner join d.typeDemande td "
			+ "inner join d.etatDemande ed "
			+ "where (l.idtDr = COALESCE(:idDr, l.idtDr) or 0L = :idDr) "
			+ "and (l.idtDc = COALESCE(:idDc, l.idtDc) or 0L = :idDc)"
			+ "and (l.idtCommune = COALESCE(:idCommune, l.idtCommune) or 0L = :idCommune)"
			+ "and (l.idtOperateur = COALESCE(:idtOper, l.idtOperateur) or 0L = :idtOper) "
			+ "and (d.dateDemande between :dateDebut and :dateFin) "
			+ "and (:codeTypeDemande is NULL OR td.code = :codeTypeDemande) "
			+ "and (:codeEtatDemande is NULL OR ed.code = :codeEtatDemande) "
			+ "and (:codeEtatLiaison is NULL OR el.code = :codeEtatLiaison) "
			+ "and (lower(l.reference) like lower(CONCAT('%',:reference,'%'))) "
			+ "and (COALESCE(:idsEtatDemande, NULL) IS NULL OR ed.idt in (:idsEtatDemande)) ")
	List<Demande> findDemandesByParamsDates(@Param("dateDebut") Date dateDebut, @Param("dateFin") Date dateFin,
				@Param("idDr") Long idDr, @Param("idDc") Long idDc, @Param("idCommune") Long idCommune,
				@Param("codeTypeDemande") List<String> codeTypeDemande, @Param("codeEtatLiaison") List<String> codeEtatLiaison,
				@Param("codeEtatDemande") List<String> codeEtatDemande, @Param("idtOper") Long idtOper, 
				@Param("reference") String reference, @Param("idsEtatDemande") List<Long> idsEtatDemande, 
				Pageable pageable);
	
	@Query(value = "select distinct d from Demande d "
			+ "inner join d.liaison l "
			+ "inner join l.etatLiaison el "
			+ "inner join d.typeDemande td "
			+ "inner join d.etatDemande ed "
			+ "where (l.idtDr = COALESCE(:idDr, l.idtDr) or 0L = :idDr) "
			+ "and (l.idtDc = COALESCE(:idDc, l.idtDc) or 0L = :idDc)"
			+ "and (l.idtCommune = COALESCE(:idCommune, l.idtCommune) or 0L = :idCommune)"
			+ "and (l.idtOperateur = COALESCE(:idtOper, l.idtOperateur) or 0L = :idtOper) "
			+ "and (:codeTypeDemande is NULL OR td.code = :codeTypeDemande) "
			+ "and (:codeEtatDemande is NULL OR ed.code = :codeEtatDemande) "
			+ "and (:codeEtatLiaison is NULL OR el.code = :codeEtatLiaison) "
			+ "and (lower(l.reference) like lower(CONCAT('%',:reference,'%'))) "
			+ "and (COALESCE(:idsEtatDemande, NULL) IS NULL OR ed.idt in (:idsEtatDemande)) ")
	List<Demande> findDemandesByParams(@Param("idDr") Long idDr, @Param("idDc") Long idDc, @Param("idCommune") Long idCommune,
			@Param("codeTypeDemande") List<String> codeTypeDemande, @Param("codeEtatLiaison") List<String> codeEtatLiaison,
			@Param("codeEtatDemande") List<String> codeEtatDemande, @Param("idtOper") Long idtOper,
			@Param("reference") String reference, @Param("idsEtatDemande") List<Long> idsEtatDemande,
			Pageable pageable);
	
	@Query("select count(d.idt) from Demande d "
			+ "inner join d.liaison l "
			+ "inner join l.etatLiaison el "
			+ "inner join d.typeDemande td "
			+ "inner join d.etatDemande ed "
			+ "where (l.idtDr = COALESCE(:idDr, l.idtDr) or 0L = :idDr) "
			+ "and (l.idtDc = COALESCE(:idDc, l.idtDc) or 0L = :idDc)"
			+ "and (l.idtCommune = COALESCE(:idCommune, l.idtCommune) or 0L = :idCommune)"
			+ "and (l.idtOperateur = COALESCE(:idtOper, l.idtOperateur) or 0L = :idtOper) "
			+ "and (d.dateDemande between :dateDebut and :dateFin) "
			+ "and (:codeTypeDemande is NULL OR td.code = :codeTypeDemande) "
			+ "and (:codeEtatDemande is NULL OR ed.code = :codeEtatDemande) "
			+ "and (:codeEtatLiaison is NULL OR el.code = :codeEtatLiaison) "
			+ "and (lower(l.reference) like lower(CONCAT('%',:reference,'%'))) "
			+ "and (COALESCE(:idsEtatDemande, NULL) IS NULL OR ed.idt in (:idsEtatDemande))  ")
	Long countDemandesByParamsDates(@Param("dateDebut") Date dateDebut, @Param("dateFin") Date dateFin,@Param("idDr") Long idDr, @Param("idDc") Long idDc, @Param("idCommune") Long idCommune,
			@Param("codeTypeDemande") List<String> codeTypeDemande, @Param("codeEtatLiaison") List<String> codeEtatLiaison,
			@Param("codeEtatDemande") List<String> codeEtatDemande, @Param("idtOper") Long idtOper,
			@Param("reference") String reference, @Param("idsEtatDemande") List<Long> idsEtatDemande);
	
	@Query("select count(d.idt) from Demande d "
			+ "inner join d.liaison l "
			+ "inner join l.etatLiaison el "
			+ "inner join d.typeDemande td "
			+ "inner join d.etatDemande ed "
			+ "where (l.idtDr = COALESCE(:idDr, l.idtDr) or 0L = :idDr) "
			+ "and (l.idtDc = COALESCE(:idDc, l.idtDc) or 0L = :idDc)"
			+ "and (l.idtCommune = COALESCE(:idCommune, l.idtCommune) or 0L = :idCommune)"
			+ "and (l.idtOperateur = COALESCE(:idtOper, l.idtOperateur) or 0L = :idtOper) "
			+ "and (:codeTypeDemande is NULL OR td.code = :codeTypeDemande) "
			+ "and (:codeEtatDemande is NULL OR ed.code = :codeEtatDemande) "
			+ "and (:codeEtatLiaison is NULL OR el.code = :codeEtatLiaison) "
			+ "and (lower(l.reference) like lower(CONCAT('%',:reference,'%'))) "
			+ "and (COALESCE(:idsEtatDemande, NULL) IS NULL OR ed.idt in (:idsEtatDemande)) ")
	Long countDemandesByParams(@Param("idDr") Long idDr, @Param("idDc") Long idDc, @Param("idCommune") Long idCommune,
			@Param("codeTypeDemande") List<String> codeTypeDemande, @Param("codeEtatLiaison") List<String> codeEtatLiaison,
			@Param("codeEtatDemande") List<String> codeEtatDemande, @Param("idtOper") Long idtOper,
			@Param("reference") String reference, @Param("idsEtatDemande") List<Long> idsEtatDemande);
	
	@Query(value = "select distinct d from Demande d "
			+ "inner join d.liaison l "
			+ "inner join l.etatLiaison el "
			+ "inner join d.typeDemande td "
			+ "inner join d.etatDemande ed "
			+ "where (l.idtDr = COALESCE(:idDr, l.idtDr) or 0L = :idDr) "
			+ "and (l.idtDc = COALESCE(:idDc, l.idtDc) or 0L = :idDc)"
			+ "and (l.idtCommune = COALESCE(:idCommune, l.idtCommune) or 0L = :idCommune)"
			+ "and (l.idtOperateur = COALESCE(:idtOper, l.idtOperateur) or 0L = :idtOper) "
			+ "and (d.dateDemande between :dateDebut and :dateFin) "
			+ "and (td.idt = COALESCE(:idTypeDemande, td.idt)  or 0L = :idTypeDemande) "
			+ "and (el.idt = COALESCE(:idEtatLiaison, el.idt)  or 0L = :idEtatLiaison) "
			+ "and (ed.idt = COALESCE(:idEtatDemande, ed.idt)  or 0L = :idEtatDemande) "
			+ "and (lower(l.reference) like lower(CONCAT('%',:reference,'%'))) "
			+ "order by l.idt asc")
	List<Demande> findDemandesReportingByParamsDates(@Param("dateDebut") Date dateDebut, @Param("dateFin") Date dateFin,
				@Param("idDr") Long idDr, @Param("idDc") Long idDc, @Param("idCommune") Long idCommune,
				@Param("idTypeDemande") Long idTypeDemande, @Param("idEtatLiaison") Long idEtatLiaison,
				@Param("idEtatDemande") Long idEtatDemande, @Param("idtOper") Long idtOper, 
				@Param("reference") String reference);
	
	@Query(value = "select distinct d from Demande d "
			+ "inner join d.liaison l "
			+ "inner join l.etatLiaison el "
			+ "inner join d.typeDemande td "
			+ "inner join d.etatDemande ed "
			+ "where (l.idtDr = COALESCE(:idDr, l.idtDr) or 0L = :idDr) "
			+ "and (l.idtDc = COALESCE(:idDc, l.idtDc) or 0L = :idDc)"
			+ "and (l.idtCommune = COALESCE(:idCommune, l.idtCommune) or 0L = :idCommune)"
			+ "and (l.idtOperateur = COALESCE(:idtOper, l.idtOperateur) or 0L = :idtOper) "
			+ "and (td.idt = COALESCE(:idTypeDemande, td.idt)  or 0L = :idTypeDemande) "
			+ "and (el.idt = COALESCE(:idEtatLiaison, el.idt)  or 0L = :idEtatLiaison) "
			+ "and (ed.idt = COALESCE(:idEtatDemande, ed.idt)  or 0L = :idEtatDemande) "
			+ "and (lower(l.reference) like lower(CONCAT('%',:reference,'%'))) "
			+ "order by l.idt asc")
	List<Demande> findDemandesReportingByParams(@Param("idDr") Long idDr, @Param("idDc") Long idDc, @Param("idCommune") Long idCommune,
				@Param("idTypeDemande") Long idTypeDemande, @Param("idEtatLiaison") Long idEtatLiaison,
				@Param("idEtatDemande") Long idEtatDemande, @Param("idtOper") Long idtOper, 
				@Param("reference") String reference);
	
	@Query(value = "select distinct d from DemandeTravauxProgramme tp " + "inner join tp.demande d "
			+ "inner join tp.liaison l " + "inner join l.etatLiaison el " + "inner join d.typeDemande td "
			+ "inner join d.etatDemande ed " + "where (l.idtDr = COALESCE(:idDr, l.idtDr) or 0L = :idDr) "
			+ "and (l.idtDc = COALESCE(:idDc, l.idtDc) or 0L = :idDc)"
			+ "and (l.idtCommune = COALESCE(:idCommune, l.idtCommune) or 0L = :idCommune)"
			+ "and (l.idtOperateur = COALESCE(:idtOper, l.idtOperateur) or 0L = :idtOper) "
			+ "and (d.dateDemande between :dateDebut and :dateFin) "
			+ "and (:codeTypeDemande is NULL OR td.code = :codeTypeDemande) "
			+ "and (:codeEtatDemande is NULL OR ed.code = :codeEtatDemande) "
			+ "and (el.idt = COALESCE(:idEtatLiaison, el.idt)  or 0L = :idEtatLiaison) "
			+ "and (lower(l.reference) like lower(CONCAT('%',:reference,'%')))"
			+ "and (COALESCE(:idsEtatDemande, NULL) IS NULL OR ed.idt in (:idsEtatDemande))")
	List<Demande> findDemandesTravauxProgrammeeByParamsDates(@Param("dateDebut") Date dateDebut,
			@Param("dateFin") Date dateFin, @Param("idDr") Long idDr, @Param("idDc") Long idDc,
			@Param("idCommune") Long idCommune, @Param("codeTypeDemande") String codeTypeDemande,
			@Param("idEtatLiaison") Long idEtatLiaison, @Param("codeEtatDemande") String codeEtatDemande,
			@Param("idtOper") Long idtOper, @Param("reference") String reference,
			@Param("idsEtatDemande") List<Long> idsEtatDemande, Pageable pageable);
	
	@Query(value = "select distinct d from DemandeTravauxProgramme tp " + "inner join tp.demande d "
			+ "inner join tp.liaison l " + "inner join l.etatLiaison el " + "inner join d.typeDemande td "
			+ "inner join d.etatDemande ed " + "where (l.idtDr = COALESCE(:idDr, l.idtDr) or 0L = :idDr) "
			+ "and (l.idtDc = COALESCE(:idDc, l.idtDc) or 0L = :idDc)"
			+ "and (l.idtCommune = COALESCE(:idCommune, l.idtCommune) or 0L = :idCommune)"
			+ "and (l.idtOperateur = COALESCE(:idtOper, l.idtOperateur) or 0L = :idtOper) "
			+ "and (:codeTypeDemande is NULL OR td.code = :codeTypeDemande) "
			+ "and (el.idt = COALESCE(:idEtatLiaison, el.idt)  or 0L = :idEtatLiaison) "
			+ "and (:codeEtatDemande is NULL OR ed.code = :codeEtatDemande) "
			+ "and (lower(l.reference) like lower(CONCAT('%',:reference,'%'))) "
			+ "and (COALESCE(:idsEtatDemande, NULL) IS NULL OR ed.idt in (:idsEtatDemande)) ")
	List<Demande> findDemandesTravauxProgrammeeByParams(@Param("idDr") Long idDr, @Param("idDc") Long idDc,
			@Param("idCommune") Long idCommune, @Param("codeTypeDemande") String codeTypeDemande,
			@Param("idEtatLiaison") Long idEtatLiaison, @Param("codeEtatDemande") String codeEtatDemande,
			@Param("idtOper") Long idtOper, @Param("reference") String reference,
			@Param("idsEtatDemande") List<Long> idsEtatDemande, Pageable pageable);
	
	
	@Query("select count(d.idt) from DemandeTravauxProgramme tp " + "inner join tp.demande d "
			+ "inner join tp.liaison l " + "inner join l.etatLiaison el " + "inner join d.typeDemande td "
			+ "inner join d.etatDemande ed " + "where (l.idtDr = COALESCE(:idDr, l.idtDr) or 0L = :idDr) "
			+ "and (l.idtDc = COALESCE(:idDc, l.idtDc) or 0L = :idDc)"
			+ "and (l.idtCommune = COALESCE(:idCommune, l.idtCommune) or 0L = :idCommune)"
			+ "and (l.idtOperateur = COALESCE(:idtOper, l.idtOperateur) or 0L = :idtOper) "
			+ "and (d.dateDemande between :dateDebut and :dateFin) "
			+ "and (:codeTypeDemande is NULL OR td.code = :codeTypeDemande) "
			+ "and (el.idt = COALESCE(:idEtatLiaison, el.idt)  or 0L = :idEtatLiaison) "
			+ "and (:codeEtatDemande is NULL OR ed.code = :codeEtatDemande) "
			+ "and (lower(l.reference) like lower(CONCAT('%',:reference,'%'))) "
			+ "and (COALESCE(:idsEtatDemande, NULL) IS NULL OR ed.idt in (:idsEtatDemande))  ")
	Long countDemandesTravauxProgrammeByParamsDates(@Param("dateDebut") Date dateDebut, @Param("dateFin") Date dateFin,
			@Param("idDr") Long idDr, @Param("idDc") Long idDc, @Param("idCommune") Long idCommune,
			@Param("codeTypeDemande") String codeTypeDemande, @Param("idEtatLiaison") Long idEtatLiaison,
			@Param("codeEtatDemande") String codeEtatDemande, @Param("idtOper") Long idtOper,
			@Param("reference") String reference, @Param("idsEtatDemande") List<Long> idsEtatDemande);

	@Query("select count(d.idt) from DemandeTravauxProgramme tp " + "inner join tp.demande d "
			+ "inner join tp.liaison l " + "inner join l.etatLiaison el " + "inner join d.typeDemande td "
			+ "inner join d.etatDemande ed " + "where (l.idtDr = COALESCE(:idDr, l.idtDr) or 0L = :idDr) "
			+ "and (l.idtDc = COALESCE(:idDc, l.idtDc) or 0L = :idDc)"
			+ "and (l.idtCommune = COALESCE(:idCommune, l.idtCommune) or 0L = :idCommune)"
			+ "and (l.idtOperateur = COALESCE(:idtOper, l.idtOperateur) or 0L = :idtOper) "
			+ "and (:codeTypeDemande is NULL OR td.code = :codeTypeDemande) "
			+ "and (el.idt = COALESCE(:idEtatLiaison, el.idt)  or 0L = :idEtatLiaison) "
			+ "and (:codeEtatDemande is NULL OR ed.code = :codeEtatDemande) "
			+ "and (lower(l.reference) like lower(CONCAT('%',:reference,'%'))) "
			+ "and (COALESCE(:idsEtatDemande, NULL) IS NULL OR ed.idt in (:idsEtatDemande)) ")
	Long countDemandesTravauxProgrammeByParams(@Param("idDr") Long idDr, @Param("idDc") Long idDc,
			@Param("idCommune") Long idCommune, @Param("codeTypeDemande") String codeTypeDemande,
			@Param("idEtatLiaison") Long idEtatLiaison, @Param("codeEtatDemande") String codeEtatDemande,
			@Param("idtOper") Long idtOper, @Param("reference") String reference,
			@Param("idsEtatDemande") List<Long> idsEtatDemande);
}
