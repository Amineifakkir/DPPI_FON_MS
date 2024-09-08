package ma.iam.dppi.fon.communs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ma.iam.dppi.fon.communs.domain.Dc;
import ma.iam.dppi.fon.communs.domain.Dr;

/**
 * @author Z.BELGHAOUTI
 *
 */
@Repository
public interface DcRepository extends JpaRepository<Dc, Long>{

	Dc findByLabel(String label);
	
	@Query("SELECT d FROM Dc d WHERE d.label = :labelDc")
	List<Dc> findByLabelList(@Param("labelDc") String label);
	
	List<Dc> findByDr(Dr dr);
	
	@Query("SELECT d FROM Dc d inner join d.dr dr WHERE (dr.idt = COALESCE(:idDr, dr.idt) or 0L = :idDr) "
			+ "AND (lower(d.label) like lower(CONCAT('%',:labelDc,'%'))) ")
	List<Dc> findDCByParams(@Param("idDr") Long idDr, @Param("labelDc") String labelDC);
	
	@Query("SELECT d FROM Dc d inner join d.dr dr WHERE (dr.idt = COALESCE(:idDr, dr.idt) or 0L = :idDr) ")
	List<Dc> findDCByParams(@Param("idDr") Long idDr);

	
	@Query(value = "select o from Dc o where o.label =:label")
	Dc findDcByLabel(@Param("label") String label);
	
	@Query(value = "SELECT * FROM dppi_dc dc "
			+ "WHERE dc.LABEL = :label "
			+ "ORDER BY dc.IDT DESC LIMIT 1", nativeQuery = true)
	Dc getDcByLabel(@Param("label") String label);
}
