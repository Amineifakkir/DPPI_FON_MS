package ma.iam.dppi.fon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * 
 * @author Z.BELGHAOUTI
 *
 */

@SpringBootApplication
public class DppiFonApplication extends SpringBootServletInitializer{

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(DppiFonApplication.class);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(DppiFonApplication.class, args);
	}

}
