package ma.iam.dppi.fon.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ma.iam.dppi.fon.domain.EtatDemande;

/**
 * @author Z.BELGHAOUTI
 *
 */
@Repository
public interface EtatDemandeRepository extends JpaRepository<EtatDemande, Long>{

	@Query(value = "SELECT * FROM dppi_etat_demande e "
			+ "WHERE e.CODE NOT IN ('VALIDE_DON','VALIDE_DEF') ", nativeQuery = true)
	List<EtatDemande> getAllEtatExterne();
	
	@Query(value = "SELECT * FROM dppi_etat_demande e "
			+ "WHERE e.CODE IN ('PENDING', 'VALIDE_DON')", nativeQuery = true)
	List<EtatDemande> getAllEtatForDon();
	
	EtatDemande findByCode(String code);
	
	@Query(value = "SELECT * FROM dppi_etat_demande e "
			+ "WHERE e.CODE IN ('VALIDE_DON', 'VALIDE_DEF')", nativeQuery = true)
	List<EtatDemande> getAllEtatForDef();
	
	@Query(value = "SELECT * FROM dppi_etat_demande e "
			+ "WHERE e.CODE IN ('FAISABLE',  'NON_FAISABLE')", nativeQuery = true)
	List<EtatDemande> getAllEtatForConsolidation();
	
}
