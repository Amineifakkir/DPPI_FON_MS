package ma.iam.dppi.fon.communs.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ma.iam.dppi.fon.communs.domain.TraceConnexion;

@Repository
public interface TraceConnexionRepository extends JpaRepository<TraceConnexion, Long>{

	
	@Query(value = "SELECT COUNT(DISTINCT(t.IDT)) from dppi_trace_connexion t "
			+ " WHERE (?1 IS NULL OR t.CODE = ?1) "
			+ " AND (?2 IS NULL OR t.APPLICATION = ?2) "
			+ " AND (t.DATE_OPERATION BETWEEN ?3 AND ?4) "
			+ " AND t.MODULE = 'DPPI-GC' ", nativeQuery = true)
	Long countListTraceConnexionByCriteresDates(String code, String projet,
		String dateDebut, String dateFin);
	
	@Query(value = "SELECT COUNT(DISTINCT(t.IDT)) from dppi_trace_connexion t "
			+ " WHERE (?1 IS NULL OR t.CODE = ?1) "
			+ " AND (?2 IS NULL OR t.APPLICATION = ?2) "
			+ " AND t.MODULE = 'DPPI-GC' ", nativeQuery = true)
	Long countListTraceConnexionByCriteres(String code, String projet);
	
	@Query("select distinct t from TraceConnexion t "
			+ "WHERE (?1 is null or  t.code = ?1) "
			+ "AND (?2 is null or  t.application = ?2) "
			+ "AND (t.dateOperation between ?3 AND ?4) "
			+ "AND t.module = 'DPPI-GC'")
	List<TraceConnexion> getTraceConnexionsByCriteresDates(String code, String projet,
		Date dateDebut, Date dateFin, Pageable pageable);
	
	@Query("select distinct t from TraceConnexion t "
			+ "WHERE (?1 is null or  t.code = ?1) "
			+ "AND (?2 is null or  t.application = ?2) "
			+ "AND t.module = 'DPPI-GC'")
	List<TraceConnexion> getTraceConnexionsByCriteres(String code, String projet, Pageable pageable);
	
}
