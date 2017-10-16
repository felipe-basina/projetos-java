package br.com.spring.schedule.jms.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.springframework.core.style.ToStringCreator;

/**
 * The primary key class for the SEMW_COMPOSICAO_PERFIL database table.
 * 
 */
@Embeddable
public class ActionProfilePK implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The cd profile. */
	@Column(name = "CD_PERFIL_ACESSO", insertable = false, updatable = false)
	private long cdProfile;

	/** The cd action. */
	@Column(name = "CD_ACAO", insertable = false, updatable = false)
	private long cdAction;

	/**
	 * Gets the cd profile.
	 * 
	 * @return the cd profile
	 */
	public final long getCdProfile() {
		return cdProfile;
	}

	/**
	 * Sets the cd profile.
	 * 
	 * @param pCdProfile
	 *            the new cd profile
	 */
	public final void setCdProfile(final long pCdProfile) {
		this.cdProfile = pCdProfile;
	}

	/**
	 * Gets the cd action.
	 * 
	 * @return the cd action
	 */
	public final long getCdAction() {
		return cdAction;
	}

	/**
	 * Sets the cd action.
	 * 
	 * @param pCdAction
	 *            the new cd action
	 */
	public final void setCdAction(final long pCdAction) {
		this.cdAction = pCdAction;
	}

	@Override
	public String toString() {
		ToStringCreator builder = new ToStringCreator(this);
		builder.append("cdProfile", cdProfile);
		builder.append("cdAction", cdAction);
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (cdAction ^ (cdAction >>> 32));
		result = prime * result + (int) (cdProfile ^ (cdProfile >>> 32));
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
		if (cdAction != other.cdAction)
			return false;
		if (cdProfile != other.cdProfile)
			return false;
		return true;
	}
}