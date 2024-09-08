package ma.iam.dppi.fon.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ma.iam.dppi.fon.domain.TypeDemande;
import ma.iam.dppi.fon.domain.TypeLiaison;

/**
 * @author Z.BELGHAOUTI
 *
 */
@Repository
public interface TypeLiaisonRepository extends JpaRepository<TypeLiaison, Long>{


	@Query(value = "SELECT dm FROM TypeLiaison dm "
			+ "WHERE dm.code = :code ")
	Optional<TypeDemande> getListByTypeLiaison(@Param("code") String code);

	TypeLiaison findByCode(String code);
}
