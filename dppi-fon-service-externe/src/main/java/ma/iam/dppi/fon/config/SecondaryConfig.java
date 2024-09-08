package ma.iam.dppi.fon.config;

import java.util.HashMap;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

/**
 * 
 * @author Z.BELGHAOUTI
 *
 */
@Configuration
@DependsOn("transactionManager")
@EnableJpaRepositories(basePackages = "ma.iam.dppi.fon.repository", 
	entityManagerFactoryRef = "dppiFonEntityManager",
	transactionManagerRef = "transactionManager"
	)
public class SecondaryConfig {

	@Autowired
	private JpaVendorAdapter jpaVendorAdapter;

	@Autowired
    private Environment env;
    
    @Value("${ds.url2}")
	private String url;
	
	@Value("${ds.username}")
	private String username;
	
	@Value("${ds.password}")
	private String password;
	
	@Value("${ds.driver}")
	private String driver;
	
	@Bean(name = "dppiGcDataSource")
    public DataSource dppiGcDataSource() {
 
    	if(this.username == null || this.username.equals("")){
			JndiDataSourceLookup dataSourceLookup = new JndiDataSourceLookup();
			return dataSourceLookup.getDataSource(url);
		}else{
			BasicDataSource bds = new BasicDataSource();
			bds.setDriverClassName(driver);
			bds.setUrl(url);
			bds.setUsername(username);
			bds.setPassword(password);
			bds.setValidationQuery("SELECT 1");
			bds.setTestOnBorrow(true);
	        return bds;
		}
    }

	@Bean(name = "dppiFonEntityManager")
	public LocalContainerEntityManagerFactoryBean dppiFonEntityManager() throws Throwable {

		HashMap<String, Object> properties = new HashMap<>();
		properties.put("hibernate.transaction.jta.platform", AtomikosJtaPlatform.class.getName());
		properties.put("javax.persistence.transactionType", "JTA");
		properties.put("hibernate.hbm2ddl.auto", env.getProperty("spring.jpa.hibernate.ddl-auto"));
		properties.put("hibernate.dialect", env.getProperty("spring.jpa.properties.hibernate.dialect"));
		properties.put("hibernate.show_sql", env.getProperty("spring.jpa.show-sql"));
		properties.put("hibernate.format_sql", env.getProperty("spring.jpa.properties.hibernate.format_sql"));
		properties.put("hibernate.naming.physical-strategy", env.getProperty("spring.jpa.hibernate.naming.physical-strategy"));

		LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
		entityManager.setJtaDataSource(dppiGcDataSource());
		entityManager.setJpaVendorAdapter(jpaVendorAdapter);
		entityManager.setPackagesToScan("ma.iam.dppi.fon.domain");
		entityManager.setPersistenceUnitName("dppiGcPersistenceUnit");
		entityManager.setJpaPropertyMap(properties);
		return entityManager;
	}

}
