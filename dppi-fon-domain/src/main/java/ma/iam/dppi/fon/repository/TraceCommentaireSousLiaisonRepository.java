package ma.iam.dppi.fon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ma.iam.dppi.fon.domain.TraceCommentaireSousLiaison;

/**
 * @author Z.BELGHAOUTI
 *
 */
@Repository
public interface TraceCommentaireSousLiaisonRepository extends JpaRepository<TraceCommentaireSousLiaison, Long>{

	@Query("select distinct o from TraceCommentaireSousLiaison o "
			+ "inner join o.sousLiaison t "
			+ "where t.idt = :idtTroncon "
			+ "order by o.dateCommentaire asc")
	List<TraceCommentaireSousLiaison> getTraceCommentBySousLiaison(@Param("idtTroncon") Long idtTroncon);
	
}
