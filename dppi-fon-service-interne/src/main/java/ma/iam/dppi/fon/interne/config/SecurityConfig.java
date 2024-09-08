package ma.iam.dppi.fon.interne.config;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.authentication.configurers.ldap.LdapAuthenticationProviderConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;
import org.springframework.security.ldap.userdetails.LdapAuthoritiesPopulator;
import org.springframework.security.ldap.userdetails.LdapUserDetails;
import org.springframework.security.ldap.userdetails.LdapUserDetailsMapper;
import org.springframework.security.ldap.userdetails.UserDetailsContextMapper;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import ma.iam.dppi.fon.interne.exception.DppiGcException;
import ma.iam.dppi.fon.interne.exception.ErrorCodeEnum;
import ma.iam.dppi.fon.interne.security.AppAuthoritiesPopulator;
import ma.iam.dppi.fon.interne.security.CustomEntryPoint;
import ma.iam.dppi.fon.interne.security.CustomLdapUserDetails;
import ma.iam.dppi.fon.interne.security.CustomLogoutSuccessHandler;
import ma.iam.dppi.fon.interne.utils.CryptUtil;

/**
 * @author Z.BELGHAOUTI
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	/**
	 * Roles
	 */
	@Value("${role.admin}")
	private String roleAdmin;

	@Value("${role.don}")
	private String roleDon;

	@Value("${role.demt}")
	private String roleDemt;

	@Value("${role.dr}")
	private String roleDr;
	
	@Value("${role.etude}")
	private String roleEtude;
	
	@Value("${role.consolidation}")
	private String roleConsolidation;
	
	@Value("${application.url}")
	private String applicationUrl;
	
	@Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }
	
	@Bean
	public HttpSessionEventPublisher httpSessionEventPublisher() {
	    return new HttpSessionEventPublisher();
	}
	
	@Bean
	public CustomLogoutSuccessHandler customLogoutSuccessHandler() {
		return new CustomLogoutSuccessHandler();
	}
	
	@Bean
	public CustomEntryPoint customEntryPoint() {
		return new CustomEntryPoint();
	}
	
	@Configuration
	protected static class AuthenticationConfiguration extends GlobalAuthenticationConfigurerAdapter {

		@Value("${ldap.urls}")
		private String url;
		
		@Value("${ldap.base.dn}")
		private String userDn;
		
		@Value("${ldap.search.base}")
		private String userSearchBase;
		
		@Value("${ldap.search.filter}")
		private String userSearchFilter;
		
		@Value("${ldap.password}")
		private String pswdLdap;
		
		@Bean
		public DefaultSpringSecurityContextSource contextSource(){
			DefaultSpringSecurityContextSource contextSource = new DefaultSpringSecurityContextSource(url);
			contextSource.setUserDn(userDn);
			String pass = "";
			try {
				pass = CryptUtil.decrypt(pswdLdap);
			} catch (DppiGcException e) {
				e.getMessage();
			}
			contextSource.setPassword(pass);
			Map<String, Object> baseEnvironmentProperties = new HashMap<>();
			baseEnvironmentProperties.put("java.naming.referral", "follow");
			contextSource.setBaseEnvironmentProperties(baseEnvironmentProperties);
			contextSource.afterPropertiesSet();
			return contextSource;
		}

		@Override
		public void init(AuthenticationManagerBuilder auth) throws DppiGcException {

			try {
				LdapAuthenticationProviderConfigurer<AuthenticationManagerBuilder> ldapAuthenticationProviderConfigurer = auth
						.ldapAuthentication().userDetailsContextMapper(userDetailsContextMapper());

				ldapAuthenticationProviderConfigurer.ldapAuthoritiesPopulator(adAuthoritiesPopulator())
				.userSearchFilter(userSearchFilter)
				.userSearchBase(userSearchBase).contextSource(contextSource());
			} catch (Exception e) {
				e.getMessage();
				throw new DppiGcException(ErrorCodeEnum.AUTH,"Probleme Security Config");
			}
			
		}

		@Bean
		public LdapAuthoritiesPopulator adAuthoritiesPopulator(){
			return new AppAuthoritiesPopulator();
		}

		@Bean
		public UserDetailsContextMapper userDetailsContextMapper() {
			return new LdapUserDetailsMapper() {
				@Override
				public UserDetails mapUserFromContext(DirContextOperations ctx, String username, Collection<? extends GrantedAuthority> authorities) {
					UserDetails details = super.mapUserFromContext(ctx, username, authorities);
					return new CustomLdapUserDetails((LdapUserDetails) details);
				}
			};
		}

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
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.cors();
		http.logout()
		.logoutUrl("/logout")
		.logoutSuccessHandler(customLogoutSuccessHandler());
		http.authorizeRequests()
			.antMatchers("/login**").permitAll()
			.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
			.antMatchers("/services/**").hasAnyRole(roleAdmin, roleDon, roleDemt, 
				roleDr, roleEtude, roleConsolidation)
			.anyRequest().authenticated();
		http.httpBasic().authenticationEntryPoint(customEntryPoint());
		return http.build();
	}
	
}