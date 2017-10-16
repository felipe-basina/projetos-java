package generic.dao.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="compound_profile", schema="jpa")
public class ActionProfile implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2522030189790476492L;

	/** The id. */
	@EmbeddedId
	private ActionProfilePK id;

	/** The msidn creation user. */
	@Column(name = "CD_USUARIO_CRIACAO")
	private String msisdnCreationUser;

	/** The msisdn update user. */
	@Column(name = "CD_USUARIO_MANUT")
	private String msisdnUpdateUser;

	public String getMsisdnCreationUser() {
		return msisdnCreationUser;
	}

	public void setMsisdnCreationUser(String msisdnCreationUser) {
		this.msisdnCreationUser = msisdnCreationUser;
	}

	public String getMsisdnUpdateUser() {
		return msisdnUpdateUser;
	}

	public void setMsisdnUpdateUser(String msisdnUpdateUser) {
		this.msisdnUpdateUser = msisdnUpdateUser;
	}

	
	public ActionProfilePK getId() {
		return id;
	}

	public void setId(ActionProfilePK id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ActionProfile other = (ActionProfile) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ActionProfile [id=");
		builder.append(id);
		builder.append(", msisdnCreationUser=");
		builder.append(msisdnCreationUser);
		builder.append(", msisdnUpdateUser=");
		builder.append(msisdnUpdateUser);
		builder.append("]");
		return builder.toString();
	}
}
