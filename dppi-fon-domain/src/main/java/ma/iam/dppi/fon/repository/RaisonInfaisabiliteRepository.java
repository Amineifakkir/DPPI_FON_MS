package ma.iam.dppi.fon.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.iam.dppi.fon.domain.RaisonInfaisabilite;

public interface RaisonInfaisabiliteRepository extends JpaRepository<RaisonInfaisabilite, Long>{

	RaisonInfaisabilite findByCode(String code);
}
