package ma.iam.dppi.fon.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ma.iam.dppi.fon.domain.EtatDemandeSousLiaison;

/**
 * @author Z.BELGHAOUTI
 *
 */
@Repository
public interface EtatDemandeSousLiaisonRepository extends JpaRepository<EtatDemandeSousLiaison, Long>{

	EtatDemandeSousLiaison findByCode(String code);
	
	@Query(value = "SELECT * FROM dppi_etat_demande_sous_liaison e "
			+ "WHERE e.CODE NOT IN ('PENDING', 'CLOTURE','P_FAISABLE')", nativeQuery = true)
	List<EtatDemandeSousLiaison> getListEtatDemandeSousLiaison();
}
