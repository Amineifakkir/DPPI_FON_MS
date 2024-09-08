package ma.iam.dppi.fon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ma.iam.dppi.fon.domain.EtatEscalade;

/**
 * @author Z.BELGHAOUTI
 *
 */
@Repository
public interface EtatEscaladeRepository extends JpaRepository<EtatEscalade, Long>{


	
}
