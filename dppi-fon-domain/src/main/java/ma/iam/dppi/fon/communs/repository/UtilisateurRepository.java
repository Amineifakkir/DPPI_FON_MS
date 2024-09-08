package ma.iam.dppi.fon.communs.repository;

import java.util.List;

import ma.iam.dppi.fon.communs.domain.Utilisateur;
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
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

	Utilisateur findByLogin(String login);
	
	@Query("SELECT u FROM Utilisateur u WHERE u.nom LIKE %:name% or u.prenom LIKE %:name% or u.login LIKE %:name%")
	List<Utilisateur> searchByCompleteNameLike(@Param("name") String name);
	
	@Query("SELECT u FROM Utilisateur u WHERE u.idt in (:idts) ORDER BY u.login ASC")
	List<Utilisateur> findUsersByIdts(@Param("idts") List<Long> idts);
	
	@Query("SELECT DISTINCT u FROM Utilisateur u "
			+ "inner join u.dr dr inner join u.listProfils p "
			+ "WHERE u MEMBER OF p.utilisateurs "
			+ "AND (dr.idt = COALESCE(:idtDr, dr.idt) or 0L = :idtDr) "
			+ "AND p.libelle in (:profils) ")
	List<Utilisateur> findUsersByDrAndProfil(@Param("idtDr") Long idtDr, 
			@Param("profils") List<String> profils);
	
	@Query(value = "SELECT DISTINCT(u.IDT) FROM dppi_utilisateur u "
			+ "LEFT JOIN dppi_profil_utilisateur pu ON u.IDT = pu.IDT_UTILISATEUR "
			+ "LEFT JOIN dppi_profil p ON p.IDT = pu.IDT_PROFIL "
			+ "WHERE (:nom IS NULL OR u.NOM LIKE CONCAT('%',:nom,'%')) "
			+ "AND u.LOGIN LIKE %:login% "
			+ "AND (:prenom IS NULL OR u.PRENOM LIKE CONCAT('%',:prenom,'%')) "
			+ "AND (:idtProfil is NULL OR pu.IDT_PROFIL = :idtProfil) "
			+ "AND (:enable is NULL OR u.ENABLED = :enable) "
			+ "AND p.LIBELLE IN ('" + Constante.PROFIL_CONSULTATION_ERPT + "', '" + Constante.PROFIL_MODIFICATION_ERPT + "', '" + Constante.PROFIL_ADMINISTRATION_ERPT+ "') "
			+ "AND (:idtOper is NULL OR u.IDT_OPERATEUR = :idtOper)", nativeQuery = true)
	List<Object> getListUsersExterneByParams(@Param("login") String login, 
			@Param("nom") String nom, @Param("prenom") String prenom,
			@Param("idtProfil") Long idtProfil, @Param("enable") Boolean enable, 
			@Param("idtOper") Long idtOper);
	
	@Query(value = "SELECT * FROM dppi_utilisateur u "
			+ "LEFT JOIN dppi_profil_utilisateur pu ON u.IDT = pu.IDT_UTILISATEUR "
			+ "LEFT JOIN dppi_profil p ON p.IDT = pu.IDT_PROFIL "
			+ "WHERE u.LOGIN = :login "
			+ "AND p.LIBELLE IN ('" + Constante.PROFIL_CONSULTATION_ERPT + "', '" + Constante.PROFIL_MODIFICATION_ERPT + "', '" + Constante.PROFIL_ADMINISTRATION_ERPT+ "') "
			+ "ORDER BY u.login ASC LIMIT 1", nativeQuery = true)
	Utilisateur findUserExterneByLogin(@Param("login") String login);
	
	@Query(value = "SELECT * FROM dppi_utilisateur u "
			+ "LEFT JOIN dppi_profil_utilisateur pu ON u.IDT = pu.IDT_UTILISATEUR "
			+ "LEFT JOIN dppi_profil p ON p.IDT = pu.IDT_PROFIL "
			+ "WHERE u.LOGIN = :login "
			+ "AND p.LIBELLE NOT IN ('" + Constante.PROFIL_CONSULTATION_ERPT + "', '" + Constante.PROFIL_MODIFICATION_ERPT + "', '" + Constante.PROFIL_ADMINISTRATION_ERPT+ "') "
			+ "ORDER BY u.login ASC LIMIT 1", nativeQuery = true)
	Utilisateur findUserInterneByLogin(@Param("login") String login);
	
	@Query(value = "SELECT DISTINCT(u.IDT) FROM dppi_utilisateur u "
			+ "LEFT JOIN dppi_profil_utilisateur pu ON u.IDT = pu.IDT_UTILISATEUR "
			+ "LEFT JOIN dppi_profil p ON p.IDT = pu.IDT_PROFIL "
			+ "WHERE (u.NOM LIKE %:name% or u.PRENOM LIKE %:name% or u.LOGIN LIKE %:name%) "
			+ "AND (:idtDr is NULL OR u.IDT_DR = :idtDr) "
			+ "AND (:idtDc is NULL OR u.IDT_DC = :idtDc) "
			+ "AND (:idtProfil is NULL OR pu.IDT_PROFIL = :idtProfil) "
			+ "AND (:enable is NULL OR u.ENABLED = :enable) "
			+ "AND p.LIBELLE NOT IN ('" + Constante.PROFIL_CONSULTATION_ERPT 
			+ "', '" + Constante.PROFIL_MODIFICATION_ERPT + "', '" + Constante.PROFIL_ADMINISTRATION_ERPT+ "') ", nativeQuery = true)
	List<Object> getListUsersInterneByParams(@Param("name") String name, 
			@Param("idtDr") Long idtDr, @Param("idtDc") Long idtDc,
			@Param("idtProfil") Long idtProfil, @Param("enable") Boolean enable);
	
}
