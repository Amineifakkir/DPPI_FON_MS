package ma.iam.dppi.fon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ma.iam.dppi.fon.domain.Demande;
import ma.iam.dppi.fon.domain.PieceJointe;



@Repository
public interface PieceJointeRepository extends JpaRepository<PieceJointe, Long>{

	List<PieceJointe> findByDemande(Demande demande);
	
	@Query(value = "select c from PieceJointe c where c.demande.idt =:idt")
	List<PieceJointe> findPieceJointByIdDeamde(@Param("idt") Long idt);
}
