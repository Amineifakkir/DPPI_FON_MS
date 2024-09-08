package ma.iam.dppi.fon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ma.iam.dppi.fon.domain.Demande;
import ma.iam.dppi.fon.domain.Escalade;

/**
 * @author Z.BELGHAOUTI
 *
 */
@Repository
public interface EscaladeRepository extends JpaRepository<Escalade, Long>{

	List<Demande> findByDemande(Demande demande);
	
	@Query(value = "select count(e.idt) from Escalade e"
			+ " where e.demande.idt = :idtDemande")
	Long countDemandeInEscalade( @Param("idtDemande") Long demande);
	
	@Query(value = "SELECT * FROM dppi_escalade dm "
			+ "WHERE dm.IDT_DEMANDE = :idtDemande ORDER BY dm.IDT DESC LIMIT 1 ", nativeQuery = true)
	Escalade getLastEscaladeByIdtDemande(@Param("idtDemande") Long idtDemande);
	
	@Query(value = "SELECT * FROM dppi_escalade dm "
			+ "WHERE dm.IDT_DEMANDE = :idtDemande ", nativeQuery = true)
	List<Escalade> getListByEscaladeIdt(@Param("idtDemande") Long idt);

}
