package ma.iam.dppi.fon.communs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ma.iam.dppi.fon.communs.domain.Dr;

/**
 * @author Z.BELGHAOUTI
 *
 */
@Repository
public interface DrRepository extends JpaRepository<Dr, Long>{

	Dr findByLabel(String label);
	
	@Query("SELECT d FROM Dr d WHERE (lower(d.label) like lower(CONCAT('%',:labelDR,'%'))) ")
	List<Dr> findDRByParams(@Param("labelDR") String labelDR);
	
	@Query(value = "SELECT * FROM dppi_dr dr "
			+ "WHERE dr.LABEL = :label "
			+ "ORDER BY dr.IDT DESC LIMIT 1", nativeQuery = true)
	Dr getDrByLabel(@Param("label") String label);
}
