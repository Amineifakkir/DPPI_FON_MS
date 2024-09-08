package ma.iam.dppi.fon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ma.iam.dppi.fon.domain.TraceCommentaire;

/**
 * @author Z.BELGHAOUTI
 *
 */
@Repository
public interface TraceCommentaireRepository extends JpaRepository<TraceCommentaire, Long>{

	@Query("select distinct t from TraceCommentaire t "
			+ "inner join t.demande d "
			+ "where d.idt = :idtDemande "
			+ "order by t.dateCommentaire asc")
	List<TraceCommentaire> getListTraceCommentaireByDemandeIdt(@Param("idtDemande") Long idtDemande);
	
}
