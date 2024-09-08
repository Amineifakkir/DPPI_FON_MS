package ma.iam.dppi.fon.config;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppBeans {
	@Bean
	public DozerBeanMapper dozerBeanMapper() {

		return new DozerBeanMapper();
	}
}
