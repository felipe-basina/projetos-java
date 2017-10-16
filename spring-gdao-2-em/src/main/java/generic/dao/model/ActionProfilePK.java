package generic.dao.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ForeignKey;

@Embeddable
public class ActionProfilePK implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The cd profile. */
	@ManyToOne(fetch = FetchType.LAZY)
	@ForeignKey(name="PROFILE_FK")
	@JoinColumn(name="ID_PERFIL", referencedColumnName="IDENTIFICADOR")
	private Profile profile;

	/** The cd action. */
	@ManyToOne(fetch = FetchType.LAZY)
	@ForeignKey(name="ACTION_FK")
	@JoinColumn(name="ID_ACAO", referencedColumnName="CD_ACAO")
	private Action action;

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((action == null) ? 0 : action.hashCode());
		result = prime * result + ((profile == null) ? 0 : profile.hashCode());
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
		ActionProfilePK other = (ActionProfilePK) obj;
		if (action == null) {
			if (other.action != null)
				return false;
		} else if (!action.equals(other.action))
			return false;
		if (profile == null) {
			if (other.profile != null)
				return false;
		} else if (!profile.equals(other.profile))
			return false;
		return true;
	}

}