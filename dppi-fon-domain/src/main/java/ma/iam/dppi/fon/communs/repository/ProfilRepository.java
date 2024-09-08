package ma.iam.dppi.fon.communs.repository;

import java.util.List;

import ma.iam.dppi.fon.communs.domain.Profil;
import ma.iam.dppi.fon.constants.Constante;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
/**
 * 
 * @author Z.BELGHAOUTI
 *
 */
@Repository
public interface ProfilRepository extends JpaRepository<Profil, Long> {

	Profil findByLibelle(String libelle);
	
	@Query("SELECT DISTINCT p FROM Profil p "
			+ " WHERE p.idt not in (:ids) and p.libelle not in ('" + Constante.PROFIL_CONSULTATION_ERPT + "', '" + Constante.PROFIL_MODIFICATION_ERPT + "') ")
	List<Profil> getAllProfilNotList(@Param("ids") List<Long> ids);
	
	@Query("SELECT DISTINCT p FROM Profil p "
			+ " WHERE p.libelle in ('" + Constante.PROFIL_CONSULTATION_ERPT + "', '" + Constante.PROFIL_MODIFICATION_ERPT +  "', '" + Constante.PROFIL_ADMINISTRATION_ERPT + "') ")
	List<Profil> getAllProfilExternes();
	
	@Query("SELECT DISTINCT p FROM Profil p "
			+ " WHERE p.libelle not in ('" + Constante.PROFIL_CONSULTATION_ERPT + "', '" + Constante.PROFIL_MODIFICATION_ERPT + "', '" + Constante.PROFIL_ADMINISTRATION_ERPT + "') ")
	List<Profil> getAllProfilInternes();
	
	@Query("SELECT DISTINCT p FROM Profil p "
			+ " WHERE p.libelle in ('" + Constante.PROFIL_CONSULTATION_ERPT + "', '" + Constante.PROFIL_MODIFICATION_ERPT + "') ")
	List<Profil> getAllProfilExterne();
}
