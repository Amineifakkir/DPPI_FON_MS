package ma.iam.dppi.fon.communs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ma.iam.dppi.fon.communs.domain.Entite;

/**
 * @author Z.BELGHAOUTI
 *
 */
@Repository
public interface EntiteRepository extends JpaRepository<Entite, Long>{

	Entite findByCode(String code);
}
