package ma.iam.dppi.fon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ma.iam.dppi.fon.domain.Devis;

/**
 * @author Z.BELGHAOUTI
 *
 */
@Repository
public interface DevisRepository extends JpaRepository<Devis, Long> {

	@Query(value = "SELECT * FROM dppi_devis dm " + "WHERE dm.IDT_DEMANDE = :idtDemande ", nativeQuery = true)
	List<Devis> getListByDevisIdt(@Param("idtDemande") Long idtDemande);
	
	@Query(value = "SELECT * FROM dppi_devis dm " + "WHERE dm.IDT_DEMANDE = :idtDemande and DATE_REFUS is null", nativeQuery = true)
	List<Devis> getListDevisByIdtDemande(@Param("idtDemande") Long idtDemande);
}
