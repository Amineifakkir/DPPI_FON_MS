package ma.iam.dppi.fon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ma.iam.dppi.fon.domain.EtatLiaison;

/**
 * @author Z.BELGHAOUTI
 *
 */
@Repository
public interface EtatLiaisonRepository extends JpaRepository<EtatLiaison, Long>{
	
	@Query(value = "SELECT et FROM EtatLiaison et "
			+ "WHERE et.code = :codeEtatLiaison  ")
	EtatLiaison getByCodeEtatLiaison(@Param("codeEtatLiaison") String codeEtatLiaison);

	EtatLiaison findByCode(String code);
}
