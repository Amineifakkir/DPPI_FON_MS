package ma.iam.dppi.fon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ma.iam.dppi.fon.domain.DemandeTravauxProgramme;

@Repository
public interface DemandeTravauxProgrammeRepository extends JpaRepository<DemandeTravauxProgramme, Long>{

	@Query("select distinct tp from DemandeTravauxProgramme tp " 
	+ "inner join tp.demande d "
			+ "where d.idt = :idtDemande ")
	List<DemandeTravauxProgramme> getListDemandeTravauxProgrammeeByDemandeIdt(@Param("idtDemande") Long idtDemande);
}
