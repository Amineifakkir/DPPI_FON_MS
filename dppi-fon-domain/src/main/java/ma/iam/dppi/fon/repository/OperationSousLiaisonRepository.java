package ma.iam.dppi.fon.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ma.iam.dppi.fon.domain.OperationSousLiaison;

/**
 * @author Z.BELGHAOUTI
 *
 */
@Repository
public interface OperationSousLiaisonRepository extends JpaRepository<OperationSousLiaison, Long>{

	@Query(value = "SELECT * FROM dppi_operation_sous_liaison o "
			+ "WHERE o.IDT_SOUS_LIAISON = :idtSousLiaison "
			+ "ORDER BY o.IDT DESC LIMIT 1", nativeQuery = true)
	OperationSousLiaison getOperationBySousLiaisonIdt(@Param("idtSousLiaison") Long idtSousLiaison);
	
	@Query("select distinct o from OperationSousLiaison o "
			+ "inner join o.demande d "
			+ "where d.idt = :idtDemande ")
	List<OperationSousLiaison> getListOperationSousLiaisonByDemande(@Param("idtDemande") Long idtDemande);
	
	@Query("select distinct o from OperationSousLiaison o "
			+ "inner join o.sousLiaison t "
			+ "where t.idt = :idtSousLiaison ")
	List<OperationSousLiaison> getListOperationSousLiaisonBySousLiaison(@Param("idtSousLiaison") Long idtSousLiaison);
}
