package ma.iam.dppi.fon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ma.iam.dppi.fon.domain.TraceEtatDemandeSousLiaison;

/**
 * @author Z.BELGHAOUTI
 *
 */
@Repository
public interface TraceEtatDemandeSousLiaisonRepository extends JpaRepository<TraceEtatDemandeSousLiaison, Long>{

	@Query("select distinct o from TraceEtatDemandeSousLiaison o "
			+ "inner join o.sousLiaison t "
			+ "where t.idt = :idtSousLiason "
			+ "order by o.dateEtat asc")
	List<TraceEtatDemandeSousLiaison> getTraceEtatBySousLiaison(@Param("idtSousLiason") Long idtSousLiason);
}
