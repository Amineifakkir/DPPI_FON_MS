package ma.iam.dppi.fon.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ma.iam.dppi.fon.domain.TypeDemande;

/**
 * @author Z.BELGHAOUTI
 *
 */
@Repository
public interface TypeDemandeRepository extends JpaRepository<TypeDemande, Long>{


	@Query(value = "SELECT dm FROM TypeDemande dm "
			+ "WHERE dm.code = :code ")
	Optional<TypeDemande> getListByTypeDemande(@Param("code") String code);

	
	TypeDemande findByCode(String code);
}
