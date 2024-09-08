package ma.iam.dppi.fon.communs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ma.iam.dppi.fon.communs.domain.Parametrage;

/**
 * @author Z.BELGHAOUTI
 *
 */
@Repository
public interface ParametrageRepository extends JpaRepository<Parametrage, Long> {

	Parametrage findByCode(String code);
}
