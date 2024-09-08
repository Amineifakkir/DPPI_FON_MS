package ma.iam.dppi.fon.config;

import java.util.Arrays;

import ma.iam.dppi.fon.security.CustomEntryPoint;
import ma.iam.dppi.fon.security.CustomLogoutSuccessHandler;
import ma.iam.dppi.fon.security.DBAuthenticationProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * @author Z.BELGHAOUTI
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig  {
	
	@Autowired
	UserDetailsService userDetailsService;
	/**
	 * Roles
	 */
	@Value("${role.erpt.admin}")
	private String roleErptAdmin;
	
	@Value("${role.erpt.consult}")
	private String roleErptConsult;
	
	@Value("${role.erpt.modif}")
	private String roleErptModif;
	
	@Value("${application.url}")
	private String applicationUrl;
	
	
	
	@Bean
	public CustomEntryPoint customEntryPoint() {
		return new CustomEntryPoint();
	}
	
	@Bean
    public DBAuthenticationProvider authProvider() {
        return new DBAuthenticationProvider();
    }
	
	@Bean
	public CustomLogoutSuccessHandler customLogoutSuccessHandler() {
		return new CustomLogoutSuccessHandler();
	}
	
	@Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }
	
	@Bean
	public HttpSessionEventPublisher httpSessionEventPublisher() {
	    return new HttpSessionEventPublisher();
	}
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	/**
	 * cors
	 * 
	 * @return source
	 */
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList(applicationUrl));
		configuration.setAllowCredentials(true);
		configuration.setAllowedHeaders(Arrays.asList("Content-Disposition", "Access-Control-Allow-Headers",
				"Access-Control-Allow-Origin", "Access-Control-Request-Method", "Access-Control-Request-Headers",
				"Origin", "Cache-Control", "Content-Type", "Authorization"));
		configuration.setAllowedMethods(Arrays.asList("DELETE", "GET", "POST", "PATCH", "PUT", "OPTIONS"));
		configuration.setMaxAge(Long.valueOf(3600));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
	
	/**
	 * spring security
	 */
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.cors();
		http.logout()
			.logoutUrl("/logout")
			.logoutSuccessHandler(customLogoutSuccessHandler());
		http.authorizeRequests()
				.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
				.antMatchers("/dppi-gc-externe/services/**").hasAnyRole(roleErptModif, roleErptConsult,roleErptAdmin)
				.anyRequest().authenticated()
		        .and().sessionManagement()
		        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.httpBasic().authenticationEntryPoint(customEntryPoint())
				.and().userDetailsService(userDetailsService);
		return http.build();
	}
   
}