package br.com.spring.schedule.jms.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.eclipse.persistence.annotations.ConversionValue;
import org.eclipse.persistence.annotations.ObjectTypeConverter;

/**
 * The Class AbstractSequencePersistent.
 * 
 * @param <ID>
 *            the generic type
 */
@MappedSuperclass
@ObjectTypeConverter(name = "stringToBooleanConverter", dataType = java.lang.String.class, objectType = java.lang.Boolean.class, defaultObjectValue = "FALSE", conversionValues = {
		@ConversionValue(dataValue = "N", objectValue = "False"),
		@ConversionValue(dataValue = "Y", objectValue = "True") })
public abstract class AbstractPersistent<ID extends Serializable> implements
		Persistent<ID> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1155276256121057300L;

	/** The checked. */
	@Transient
	private transient boolean checked;

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
	public final Date getCreationDate() {
		return creationDate;
	}

	/**
	 * Sets the creation date.
	 * 
	 * @param pCreationDate
	 *            the new creation date
	 */
	public final void setCreationDate(final Date pCreationDate) {
		this.creationDate = pCreationDate;
	}

	/**
	 * Gets the update date.
	 * 
	 * @return the update date
	 */
	public final Date getUpdateDate() {
		return updateDate;
	}

	/**
	 * Sets the update date.
	 * 
	 * @param pUpdateDate
	 *            the new update date
	 */
	public final void setUpdateDate(final Date pUpdateDate) {
		this.updateDate = pUpdateDate;
	}

	/**
	 * Checks if is checked.
	 *
	 * @return true, if is checked
	 */
	public final boolean isChecked() {
		return checked;
	}

	/**
	 * Sets the checked.
	 *
	 * @param pChecked
	 *            the new checked
	 */
	public final void setChecked(final boolean pChecked) {
		this.checked = pChecked;
	}

}
