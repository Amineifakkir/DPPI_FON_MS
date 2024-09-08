package ma.iam.dppi.fon.communs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ma.iam.dppi.fon.communs.domain.Operateur;
/**
 * 
 * @author Z.BELGHAOUTI
 *
 */
@Repository
public interface OperateurRepository extends JpaRepository<Operateur, Long> {
	
	
}
