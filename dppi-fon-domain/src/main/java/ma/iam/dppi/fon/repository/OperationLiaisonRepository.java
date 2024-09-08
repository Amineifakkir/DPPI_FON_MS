package ma.iam.dppi.fon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ma.iam.dppi.fon.domain.OperationLiason;

@Repository
public interface OperationLiaisonRepository extends JpaRepository<OperationLiason, Long>{

}
