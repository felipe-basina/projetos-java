package br.com.spring.schedule.jms.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.core.style.ToStringCreator;

/**
 * The persistent class for the SEMW_CTL_PROCESSAMENTO_MSG database table.
 * 
 */
@Entity
@Table(name = "SEMW_CTL_PROCESSAMENTO_MSG")
public class Processing extends AbstractPersistent<Long> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_SEQ_CTL_PROCESSAMENTO_MSG")
	@SequenceGenerator(name = "ID_SEQ_CTL_PROCESSAMENTO_MSG", sequenceName = "SEMW_CTL_PROCESSAMENT_MSG_SQ01", allocationSize = 1)
	@Column(name = "CD_CTL_PROCES_MENSAGEM")
	private Long id;

	/** The appointment. */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CD_AGENDA")
	private Appointment appointment;

	/** The cd send sms transaction. */
	@Column(name = "CD_TRANSACAO_ENVIO_SMS")
	private Long cdSendSmsTransaction;

	/** The cd charge transaction. */
	@Column(name = "CD_TRANSACAO_TARIFACAO")
	private Long cdChargeTransaction;

	/** The sms send date. */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DT_ENVIO")
	private Date smsSendDate;

	/** The process status date. */
	@Temporal(TemporalType.DATE)
	@Column(name = "DT_STATUS_PROCESSO")
	private Date processStatusDate;

	/** The in charge status. */
	/** The status. */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IN_STATUS_TARIFACAO")
	private Status inChargeStatus;

	/** The msisdn. */
	@Column(name = "NU_MSISDN")
	private String msisdn;

	/** The company group. */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CD_GRUPO_USUARIOS")
	private CompanyGroup companyGroup;

	/** The status. */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CD_STATUS_PROCESSO")
	private Status status;

	/**
	 * Gets the appointment.
	 * 
	 * @return the appointment
	 */
	public final Appointment getAppointment() {
		return appointment;
	}

	/**
	 * Sets the appointment.
	 * 
	 * @param pAppointment
	 *            the new appointment
	 */
	public final void setAppointment(final Appointment pAppointment) {
		this.appointment = pAppointment;
	}

	/**
	 * Gets the cd send sms transaction.
	 * 
	 * @return the cd send sms transaction
	 */
	public final Long getCdSendSmsTransaction() {
		return cdSendSmsTransaction;
	}

	/**
	 * Sets the cd send sms transaction.
	 * 
	 * @param pCdSendSmsTransaction
	 *            the new cd send sms transaction
	 */
	public final void setCdSendSmsTransaction(final Long pCdSendSmsTransaction) {
		this.cdSendSmsTransaction = pCdSendSmsTransaction;
	}

	/**
	 * Gets the cd charge transaction.
	 * 
	 * @return the cd charge transaction
	 */
	public final Long getCdChargeTransaction() {
		return cdChargeTransaction;
	}

	/**
	 * Sets the cd charge transaction.
	 * 
	 * @param pCdChargeTransaction
	 *            the new cd charge transaction
	 */
	public final void setCdChargeTransaction(final Long pCdChargeTransaction) {
		this.cdChargeTransaction = pCdChargeTransaction;
	}

	/**
	 * Gets the sms send date.
	 * 
	 * @return the sms send date
	 */
	public final Date getSmsSendDate() {
		return smsSendDate;
	}

	/**
	 * Sets the sms send date.
	 * 
	 * @param pSmsSendDate
	 *            the new sms send date
	 */
	public final void setSmsSendDate(final Date pSmsSendDate) {
		this.smsSendDate = pSmsSendDate;
	}

	/**
	 * Gets the process status date.
	 * 
	 * @return the process status date
	 */
	public final Date getProcessStatusDate() {
		return processStatusDate;
	}

	/**
	 * Sets the process status date.
	 * 
	 * @param pProcessStatusDate
	 *            the new process status date
	 */
	public final void setProcessStatusDate(final Date pProcessStatusDate) {
		this.processStatusDate = pProcessStatusDate;
	}

	/**
	 * Gets the in charge status.
	 * 
	 * @return the in charge status
	 */
	public final Status getInChargeStatus() {
		return inChargeStatus;
	}

	/**
	 * Sets the in charge status.
	 * 
	 * @param pInChargeStatus
	 *            the new in charge status
	 */
	public final void setInChargeStatus(final Status pInChargeStatus) {
		this.inChargeStatus = pInChargeStatus;
	}

	/**
	 * Gets the msisdn.
	 * 
	 * @return the msisdn
	 */
	public final String getMsisdn() {
		return msisdn;
	}

	/**
	 * Sets the msisdn.
	 * 
	 * @param pMsisdn
	 *            the new msisdn
	 */
	public final void setMsisdn(final String pMsisdn) {
		this.msisdn = pMsisdn;
	}

	/**
	 * Gets the company group.
	 * 
	 * @return the company group
	 */
	public final CompanyGroup getCompanyGroup() {
		return companyGroup;
	}

	/**
	 * Sets the company group.
	 * 
	 * @param pCompanyGroup
	 *            the new company group
	 */
	public final void setCompanyGroup(final CompanyGroup pCompanyGroup) {
		this.companyGroup = pCompanyGroup;
	}

	/**
	 * Gets the status.
	 * 
	 * @return the status
	 */
	public final Status getStatus() {
		return status;
	}

	/**
	 * Sets the status.
	 * 
	 * @param pStatus
	 *            the new status
	 */
	public final void setStatus(final Status pStatus) {
		this.status = pStatus;
	}

	@Override
	public final Long getId() {
		return id;
	}

	@Override
	public final void setId(final Long pId) {
		this.id = pId;
	}

	@Override
	public String toString() {
		ToStringCreator builder = new ToStringCreator(this);
		builder.append("id", id);
		builder.append("appointment", appointment);
		builder.append("cdSendSmsTransaction", cdSendSmsTransaction);
		builder.append("cdChargeTransaction", cdChargeTransaction);
		builder.append("smsSendDate", smsSendDate);
		builder.append("processStatusDate", processStatusDate);
		builder.append("inChargeStatus", inChargeStatus);
		builder.append("msisdn", msisdn);
		builder.append("companyGroup", companyGroup);
		builder.append("status", status);
		builder.append("creationDate", getCreationDate());
		builder.append("updateDate", getUpdateDate());
		return builder.toString();
	}

}