package ma.iam.dppi.fon.communs.domain;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * 
 * @author Z.BELGHAOUTI
 *
 */
@MappedSuperclass
public abstract class BaseEntityCodeLabel extends BaseEntityCode {

	@Column(name = "LABEL")
	protected String label;

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((label == null) ? 0 : label.hashCode());
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
		BaseEntityCodeLabel other = (BaseEntityCodeLabel) obj;
		if (label == null) {
			if (other.label != null)
				return false;
		} else if (!label.equals(other.label))
			return false;
		return true;
	}

}