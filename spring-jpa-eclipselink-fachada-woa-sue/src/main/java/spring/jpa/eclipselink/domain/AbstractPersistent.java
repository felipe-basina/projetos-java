package spring.jpa.eclipselink.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The Class AbstractSequencePersistent.
 * 
 * @param <ID>
 *            the generic type
 */
@MappedSuperclass
public abstract class AbstractPersistent<ID extends Serializable> implements
		Persistent<ID> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1155276256121057300L;

	/** The creation time. */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DT_CRIACAO")
	private Date creationDate;

	/** The update time. */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DT_ATUALIZACAO")
	private Date updateDate;

	@Override
	public final int hashCode() {
		if (getId() != null) {
			return getId().hashCode();
		} else {
			return super.hashCode();
		}
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
		AbstractPersistent<?> other = (AbstractPersistent<?>) obj;
		if (getId() == null) {
			if (other.getId() != null) {
				return false;
			}
		} else if (!getId().equals(other.getId())) {
			return false;
		}
		return true;
	}

	/**
	 * Gets the creation date.
	 * 
	 * @return the creation date
	 */
	public Date getCreationDate() {
		return creationDate;
	}

	/**
	 * Sets the creation date.
	 * 
	 * @param pCreationDate
	 *            the new creation date
	 */
	public void setCreationDate(final Date pCreationDate) {
		this.creationDate = pCreationDate;
	}

	/**
	 * Gets the update date.
	 * 
	 * @return the update date
	 */
	public Date getUpdateDate() {
		return updateDate;
	}

	/**
	 * Sets the update date.
	 * 
	 * @param pUpdateDate
	 *            the new update date
	 */
	public void setUpdateDate(final Date pUpdateDate) {
		this.updateDate = pUpdateDate;
	}
}
