package br.com.spring.schedule.jms.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.springframework.core.style.ToStringCreator;

/**
 * The Class UserGroupPK.
 */
@Embeddable
public class UserGroupPK implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant hashId. */
	private static final int HASH_ID = 32;

	/** The cd company group. */
	@Column(name = "CD_GRUPO_USUARIOS", insertable = false, updatable = false)
	private long cdCompanyGroup;

	/** The cd user. */
	@Column(name = "CD_USUARIO", insertable = false, updatable = false)
	private long cdUser;

	/**
	 * Gets the cd company group.
	 * 
	 * @return the cd company group
	 */
	public final long getCdCompanyGroup() {
		return cdCompanyGroup;
	}

	/**
	 * Sets the cd company group.
	 * 
	 * @param pCdCompanyGroup
	 *            the new cd company group
	 */
	public final void setCdCompanyGroup(final long pCdCompanyGroup) {
		this.cdCompanyGroup = pCdCompanyGroup;
	}

	/**
	 * Gets the cd user.
	 * 
	 * @return the cd user
	 */
	public final long getCdUser() {
		return cdUser;
	}

	/**
	 * Sets the cd user.
	 * 
	 * @param pCdUser
	 *            the new cd user
	 */
	public final void setCdUser(final long pCdUser) {
		this.cdUser = pCdUser;
	}

	@Override
	public final int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ (int) (cdCompanyGroup ^ (cdCompanyGroup >>> HASH_ID));
		result = prime * result + (int) (cdUser ^ (cdUser >>> HASH_ID));
		return result;
	}

	@Override
	public final boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		UserGroupPK other = (UserGroupPK) obj;
		if (cdCompanyGroup != other.cdCompanyGroup) {
			return false;
		}
		if (cdUser != other.cdUser) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		ToStringCreator builder = new ToStringCreator(this);
		builder.append("cdCompanyGroup", cdCompanyGroup);
		builder.append("cdUser", cdUser);
		return builder.toString();
	}

}
