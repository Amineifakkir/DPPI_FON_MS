package ma.iam.dppi.fon.interne.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 
 * @author Z.BELGHAOUTI
 *
 */
public class SecurityContextHelper {

	public static final String ADMIN = "ROLE_ADMINISTRATEUR";
	public static final String DON = "ROLE_DON";
	public static final String DEF = "ROLE_DEF";
	public static final String DEMT = "ROLE_DEMT";

	public static final String DR = "ROLE_DR";
	public static final String ETUDE = "ROLE_ETUDE";
	public static final String CONSOLIDATION = "ROLE_CONSOLIDATION";

	public static String getUserName() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}

	public static boolean isAdmin() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		for(GrantedAuthority authority : authentication.getAuthorities()) {
			if (ADMIN.equals(authority.getAuthority())) {
				return true;
			}
		}
		return false;
	}

	public static boolean isDon() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		for(GrantedAuthority authority : authentication.getAuthorities()) {
			if (DON.equals(authority.getAuthority())) {
				return true;
			}
		}
		return false;
	}

	public static boolean isDef() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		for(GrantedAuthority authority : authentication.getAuthorities()) {
			if (DEF.equals(authority.getAuthority())) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean isDemt() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		for(GrantedAuthority authority : authentication.getAuthorities()) {
			if (DEMT.equals(authority.getAuthority())) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean isDr() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		for(GrantedAuthority authority : authentication.getAuthorities()) {
			if (DR.equals(authority.getAuthority())) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean isEtude() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		for(GrantedAuthority authority : authentication.getAuthorities()) {
			if (ETUDE.equals(authority.getAuthority())) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean isConsolidation() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		for(GrantedAuthority authority : authentication.getAuthorities()) {
			if (CONSOLIDATION.equals(authority.getAuthority())) {
				return true;
			}
		}
		return false;
	}
	
}
