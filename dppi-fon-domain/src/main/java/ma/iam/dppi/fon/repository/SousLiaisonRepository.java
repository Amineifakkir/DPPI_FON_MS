package ma.iam.dppi.fon.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ma.iam.dppi.fon.domain.SousLiaison;

/**
 * @author Z.BELGHAOUTI
 *
 */
@Repository
public interface SousLiaisonRepository extends JpaRepository<SousLiaison, Long>{

	@Query("select distinct t from SousLiaison t "
			+ "inner join t.liaison l "
			+ "where l.idt = :idtLiaison "
			+ "order by t.idt asc")
	List<SousLiaison> getListSousLiaisonByLiaisonIdt(@Param("idtLiaison") Long idtLiaison);
}
