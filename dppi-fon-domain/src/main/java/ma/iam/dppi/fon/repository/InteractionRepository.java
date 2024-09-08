package ma.iam.dppi.fon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ma.iam.dppi.fon.domain.Interaction;

/**
 * @author Z.BELGHAOUTI
 *
 */
@Repository
public interface InteractionRepository extends JpaRepository<Interaction, Long>{

	@Query("select distinct i from Interaction i "
			+ "inner join i.demande d "
			+ "inner join d.liaison l "
			+ "where l.idt = :idtLiaison "
			+ "and (COALESCE(:entities, NULL) IS NULL OR i.entiteCible in (:entities) OR i.entiteSource in (:entities) ) ")
	List<Interaction> getListInteractionByLiaisonIdt(@Param("idtLiaison") Long idtLiaison, 
			@Param("entities") List<String> entities);
	
	@Query("select distinct i from Interaction i "
			+ "inner join i.demande d "
			+ "where d.idt = :idtDemande "
			+ "and (COALESCE(:entities, NULL) IS NULL OR i.entiteCible in (:entities) OR i.entiteSource in (:entities) ) "
			+ "order by i.dateInteraction asc")
	List<Interaction> getListInteractionByDemandeIdt(@Param("idtDemande") Long idtDemande, 
			@Param("entities") List<String> entities);
	
	@Query("select distinct i from Interaction i "
			+ "inner join i.demande d "
			+ "inner join d.liaison l "
			+ "where l.idt = :idtLiaison "
			+ "and (COALESCE(:entities, NULL) IS NULL OR i.entiteCible in (:entities) ) ")
	List<Interaction> getListInteractionErptByLiaisonIdt(@Param("idtLiaison") Long idtLiaison, @Param("entities") String entities);
	
	@Query("select distinct i from Interaction i "
			+ "inner join i.demande d "
			+ "where d.idt = :idtDemande "
			+ "and (COALESCE(:entitySource, NULL) IS NULL OR i.entiteSource in (:entitySource) ) "
			+ "and i.entiteCible='DON' ")
	List<Interaction> getListInteractionByEnitySource(@Param("idtDemande") Long idtDemande, @Param("entitySource") String entitySource);
}
