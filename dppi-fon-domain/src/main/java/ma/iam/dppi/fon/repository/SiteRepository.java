package ma.iam.dppi.fon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ma.iam.dppi.fon.domain.Site;

/**
 * @author Z.BELGHAOUTI
 *
 */
@Repository
public interface SiteRepository extends JpaRepository<Site, Long> {

	Site findByCode(String code);

	@Query(value = "SELECT * FROM dppi_site s WHERE s.IDT_DR= :idtDr ", nativeQuery = true)
	List<Site> getListSiteByDr(@Param("idtDr") Long idtDr);

}
