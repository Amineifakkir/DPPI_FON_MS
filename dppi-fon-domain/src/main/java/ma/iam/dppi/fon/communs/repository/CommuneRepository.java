package ma.iam.dppi.fon.communs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ma.iam.dppi.fon.communs.domain.Commune;
import ma.iam.dppi.fon.communs.domain.Dc;

/**
 * @author Z.BELGHAOUTI
 *
 */
@Repository
public interface CommuneRepository extends JpaRepository<Commune, Long>{

	List<Commune> findByDcOrderByLabelAsc(Dc dc);
	
	@Query(value = "select c from Commune c where c.label =:label")
	Commune findCommuneByLabel(@Param("label") String label);
	
	Commune findByCode(String code);
}
