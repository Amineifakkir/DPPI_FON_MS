package ma.iam.dppi.fon.interne.dtos;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author Z.BELGHAOUTI
 */
public class UserRoleDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<LibelleDto> role;
	private String direction="-1";
	private Long agence=-1L;

	public List<LibelleDto> getRole() {
		return role;
	}

	public void setRole(List<LibelleDto> role) {
		this.role = role;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public Long getAgence() {
		return agence;
	}

	public void setAgence(Long agence) {
		this.agence = agence;
	}
}
