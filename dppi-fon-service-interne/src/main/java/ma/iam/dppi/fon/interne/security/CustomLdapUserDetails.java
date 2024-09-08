package ma.iam.dppi.fon.interne.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.ldap.userdetails.LdapUserDetails;

public class CustomLdapUserDetails implements LdapUserDetails {
	
	private static final long serialVersionUID = 1L;

	private LdapUserDetails details;

	public CustomLdapUserDetails(LdapUserDetails details) {
		this.details = details;
	}

	public boolean isEnabled() {
		return details.isEnabled();
	}

	public String getDn() {
		return details.getDn();
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return details.getAuthorities();
	}

	public String getPassword() {
		return details.getPassword();
	}

	public String getUsername() {
		return details.getUsername();
	}

	public boolean isAccountNonExpired() {
		return details.isAccountNonExpired();
	}

	public boolean isAccountNonLocked() {
		return details.isAccountNonLocked();
	}

	public boolean isCredentialsNonExpired() {
		return details.isCredentialsNonExpired();
	}

	public void eraseCredentials() {
		// TODO Auto-generated method stub

	}
}