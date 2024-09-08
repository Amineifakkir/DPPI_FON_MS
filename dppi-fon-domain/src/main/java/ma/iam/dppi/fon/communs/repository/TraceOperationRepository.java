package ma.iam.dppi.fon.communs.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ma.iam.dppi.fon.communs.domain.TraceOperation;

@Repository
public interface TraceOperationRepository extends JpaRepository<TraceOperation, Long>{

	@Query(value = "SELECT COUNT(DISTINCT(t.IDT)) from dppi_trace_operation t "
			+ " WHERE (:code IS NULL OR t.CODE = :code) "
			+ " AND (:projet IS NULL OR t.APPLICATION = :projet) "
			+ " AND (t.DATE_OPERATION BETWEEN :dateDebut AND :dateFin) "
			+ " AND t.MODULE = 'DPPI-GC' ", nativeQuery = true)
	Long countListTraceOperationByCriteresDates(@Param("code") String code, @Param("projet") String projet,
		@Param("dateDebut") String dateDebut, @Param("dateFin") String dateFin);
	
	@Query(value = "SELECT COUNT(DISTINCT(t.IDT)) from dppi_trace_operation t "
			+ " WHERE (:code IS NULL OR t.CODE = :code) "
			+ " AND (:projet IS NULL OR t.APPLICATION = :projet) "
			+ " AND t.MODULE = 'DPPI-GC' ", nativeQuery = true)
	Long countListTraceOperationByCriteres(@Param("code") String code, @Param("projet") String projet);
	
	@Query("select distinct t from TraceOperation t "
			+ "WHERE (?1 is null or  t.code = ?1) "
			+ "AND (?2 is null or  t.application = ?2) "
			+ "AND (t.dateOperation between ?3 AND ?4) "
			+ "AND t.module = 'DPPI-GC'")
	List<TraceOperation> getListTraceOperationByCriteresDates(String code, String projet,
		Date dateDebut, Date dateFin, Pageable pageable);
	
	@Query("select distinct t from TraceOperation t "
			+ "WHERE (?1 is null or  t.code = ?1) "
			+ "AND (?2 is null or  t.application = ?2) "
			+ "AND t.module = 'DPPI-GC'")
	List<TraceOperation> getListTraceOperationByCriteres(String code, String projet, Pageable pageable);
	
}
