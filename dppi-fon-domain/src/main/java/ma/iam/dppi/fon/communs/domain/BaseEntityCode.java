package ma.iam.dppi.fon.communs.domain;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * 
 * @author Z.BELGHAOUTI
 *
 */
@MappedSuperclass
public abstract class BaseEntityCode {

	@Column(name = "CODE", unique = true)
	protected String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		BaseEntityCode other = (BaseEntityCode) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}

}