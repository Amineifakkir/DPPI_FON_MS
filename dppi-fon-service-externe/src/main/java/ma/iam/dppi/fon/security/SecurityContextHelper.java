package ma.iam.dppi.fon.security;

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
	public static final String DR = "ROLE_DR";

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
	public static boolean isDr() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		for(GrantedAuthority authority : authentication.getAuthorities()) {
			if (DR.equals(authority.getAuthority())) {
				return true;
			}
		}
		return false;
	}
	
}
