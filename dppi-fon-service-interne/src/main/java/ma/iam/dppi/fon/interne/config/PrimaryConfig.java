package ma.iam.dppi.fon.interne.config;

import java.util.HashMap;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.PropertySource;
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
@PropertySource({ "classpath:application.properties" })
@EnableJpaRepositories(basePackages = "ma.iam.dppi.fon.communs.repository", 
	entityManagerFactoryRef = "communEntityManager", 
	transactionManagerRef = "transactionManager"
	)
public class PrimaryConfig {

	@Autowired
	private JpaVendorAdapter jpaVendorAdapter;

	@Autowired
    private Environment env;
    
    @Value("${ds.url1}")
	private String url;
	
	@Value("${ds.username}")
	private String username;
	
	@Value("${ds.password}")
	private String password;
	
	@Value("${ds.driver}")
	private String driver;
	
	@Bean(name = "communDataSource")
    public DataSource communDataSource() {
 
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
	

	@Bean(name = "communEntityManager")
	@DependsOn("transactionManager")
	public LocalContainerEntityManagerFactoryBean communEntityManager() throws Throwable {

		HashMap<String, Object> properties = new HashMap<>();
		properties.put("hibernate.transaction.jta.platform", AtomikosJtaPlatform.class.getName());
		properties.put("javax.persistence.transactionType", "JTA");
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("spring.jpa.hibernate.ddl-auto"));
		properties.put("hibernate.dialect", env.getProperty("spring.jpa.properties.hibernate.dialect"));
		properties.put("hibernate.show_sql", env.getProperty("spring.jpa.show-sql"));
		properties.put("hibernate.format_sql", env.getProperty("spring.jpa.properties.hibernate.format_sql"));
		properties.put("hibernate.naming.physical-strategy", env.getProperty("spring.jpa.hibernate.naming.physical-strategy"));

		LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
		entityManager.setJtaDataSource(communDataSource());
		entityManager.setJpaVendorAdapter(jpaVendorAdapter);
		entityManager.setPackagesToScan("ma.iam.dppi.fon.communs.domain");
		entityManager.setPersistenceUnitName("communPersistenceUnit");
		entityManager.setJpaPropertyMap(properties);
		return entityManager;
	}

}
