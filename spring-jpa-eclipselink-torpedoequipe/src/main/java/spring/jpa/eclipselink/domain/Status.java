package spring.jpa.eclipselink.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.core.style.ToStringCreator;

/**
 * The persistent class for the SEMW_STATUS_PROCESSO database table.
 * 
 */
@Entity
@Table(name = "SEMW_O.SEMW_STATUS_PROCESSO")
public class Status extends AbstractPersistent<String> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@Column(name = "CD_STATUS_PROCESSO")
	private String id;

	/** The msisdn creation user. */
	@Column(name = "CD_USUARIO_CRIACAO")
	private String msisdnCreationUser;

	/** The msisdn update user. */
	@Column(name = "CD_USUARIO_MANUT")
	private String msisdnUpdateUser;

	/** The status description. */
	@Column(name = "DS_STATUS_PROCESSO")
	private String statusDescription;

	// bi-directional many-to-one association to SemwAgenda
	/** The appointment. */
	@OneToMany(mappedBy = "status", fetch = FetchType.LAZY)
	private List<Appointment> appointment;

	// bi-directional many-to-one association to SemwCtlProcessamentoMsg
	/** The processing. */
	@OneToMany(mappedBy = "status", fetch = FetchType.LAZY)
	private List<Processing> processing;

	/**
	 * Gets the msisdn creation user.
	 * 
	 * @return the msisdn creation user
	 */
	public final String getMsisdnCreationUser() {
		return msisdnCreationUser;
	}

	/**
	 * Sets the msisdn creation user.
	 * 
	 * @param pMsisdnCreationUser
	 *            the new msisdn creation user
	 */
	public final void setMsisdnCreationUser(final String pMsisdnCreationUser) {
		this.msisdnCreationUser = pMsisdnCreationUser;
	}

	/**
	 * Gets the msisdn update user.
	 * 
	 * @return the msisdn update user
	 */
	public final String getMsisdnUpdateUser() {
		return msisdnUpdateUser;
	}

	/**
	 * Sets the msisdn update user.
	 * 
	 * @param pMsisdnUpdateUser
	 *            the new msisdn update user
	 */
	public final void setMsisdnUpdateUser(final String pMsisdnUpdateUser) {
		this.msisdnUpdateUser = pMsisdnUpdateUser;
	}

	/**
	 * Gets the status description.
	 * 
	 * @return the status description
	 */
	public final String getStatusDescription() {
		return statusDescription;
	}

	/**
	 * Sets the status description.
	 * 
	 * @param pStatusDescription
	 *            the new status description
	 */
	public final void setStatusDescription(final String pStatusDescription) {
		this.statusDescription = pStatusDescription;
	}

	/**
	 * Gets the appointment.
	 * 
	 * @return the appointment
	 */
	public final List<Appointment> getAppointment() {
		return appointment;
	}

	/**
	 * Sets the appointment.
	 * 
	 * @param pAppointment
	 *            the new appointment
	 */
	public final void setAppointment(final List<Appointment> pAppointment) {
		this.appointment = pAppointment;
	}

	/**
	 * Gets the processing.
	 * 
	 * @return the processing
	 */
	public final List<Processing> getProcessing() {
		return processing;
	}

	/**
	 * Sets the processing.
	 * 
	 * @param pProcessing
	 *            the new processing
	 */
	public final void setProcessing(final List<Processing> pProcessing) {
		this.processing = pProcessing;
	}

	@Override
	public final String getId() {
		return id;
	}

	@Override
	public final void setId(final String pId) {
		this.id = pId;
	}

	@Override
	public String toString() {
		ToStringCreator builder = new ToStringCreator(this);
		builder.append("id", id);
		builder.append("msisdnCreationUser", msisdnCreationUser);
		builder.append("msisdnUpdateUser", msisdnUpdateUser);
		builder.append("statusDescription", statusDescription);
		builder.append("creationDate", getCreationDate());
		builder.append("updateDate", getUpdateDate());
		return builder.toString();
	}

}