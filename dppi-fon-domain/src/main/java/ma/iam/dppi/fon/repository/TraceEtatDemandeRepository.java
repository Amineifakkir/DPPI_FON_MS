package ma.iam.dppi.fon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ma.iam.dppi.fon.domain.TraceEtatDemande;

/**
 * @author Z.BELGHAOUTI
 *
 */
@Repository
public interface TraceEtatDemandeRepository extends JpaRepository<TraceEtatDemande, Long>{

	@Query("select distinct t from TraceEtatDemande t "
			+ "inner join t.demande d "
			+ "where d.idt = :idtDemande "
			+ "order by t.dateEtat asc")
	List<TraceEtatDemande> getListTraceEtatByDemandeIdt(@Param("idtDemande") Long idtDemande);
}
