package br.com.spring.schedule.jms.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.core.style.ToStringCreator;

/**
 * The Class Appointment.
 */
@Entity
@Table(name = "SEMW_AGENDA")
public class Appointment extends AbstractPersistent<Long> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_SEQ_AGENDA")
	@SequenceGenerator(name = "ID_SEQ_AGENDA", sequenceName = "SEMW_AGENDA_SQ01", allocationSize = 1)
	@Column(name = "CD_AGENDA")
	private Long id;

	/** The id company groups. */
	@Column(name = "CD_GRUPOS_USUARIOS")
	private String idCompanyGroups;

	/** The msisdn cancel user. */
	@Column(name = "CD_USUARIO_CANCELAMENTO")
	private String msisdnCancelUser;

	/** The msisdn creation user. */
	@Column(name = "CD_USUARIO_CRIACAO")
	private String msisdnCreationUser;

	/** The msisdn update user. */
	@Column(name = "CD_USUARIO_MANUT")
	private String msisdnUpdateUser;

	/** The cancel date. */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DT_CANCELAMENTO")
	private Date cancelDate;

	/** The start date send message. */
	@Temporal(TemporalType.DATE)
	@Column(name = "DT_INICIAL_ENVIO_MENSAGEM")
	private Date startDateSendMessage;

	/** The end date send message. */
	@Temporal(TemporalType.DATE)
	@Column(name = "DT_FINAL_ENVIO_MENSAGEM")
	private Date endDateSendMessage;

	/** The dt status processo. */
	@Temporal(TemporalType.DATE)
	@Column(name = "DT_STATUS_PROCESSO")
	private Date dtStatusProcesso;

	/** The start time send message. */
	@Column(name = "HH_FINAL_ENVIO_MENSAGEM")
	private Long endTimeSendMessage;

	/** The end time send message. */
	@Column(name = "HH_INICIAL_ENVIO_MENSAGEM")
	private Long startTimeSendMessage;

	/** The total messages. */
	@Column(name = "QT_TOTAL_MSGS")
	private Long totalMessages;

	/** The tx message. */
	@Column(name = "TX_MENSAGEM")
	private String txMessage;

	/** The company. */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CD_EMPRESA")
	private Company company;

	/** The message model. */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CD_MODELO_MENSAGEM")
	private MessageModel messageModel;

	/** The status. */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CD_STATUS_PROCESSO")
	private Status status;

	/** The user. */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CD_USUARIO_AGENDOU")
	private User user;

	/** The processings. */
	@OneToMany(mappedBy = "appointment", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Processing> processings;

	/**
	 * Gets the id company groups.
	 * 
	 * @return the id company groups
	 */
	public final String getIdCompanyGroups() {
		return idCompanyGroups;
	}

	/**
	 * Sets the id company groups.
	 * 
	 * @param pIdCompanyGroups
	 *            the new id company groups
	 */
	public final void setIdCompanyGroups(final String pIdCompanyGroups) {
		this.idCompanyGroups = pIdCompanyGroups;
	}

	/**
	 * Gets the msisdn cancel user.
	 * 
	 * @return the msisdn cancel user
	 */
	public final String getMsisdnCancelUser() {
		return msisdnCancelUser;
	}

	/**
	 * Sets the msisdn cancel user.
	 * 
	 * @param pMsisdnCancelUser
	 *            the new msisdn cancel user
	 */
	public final void setMsisdnCancelUser(final String pMsisdnCancelUser) {
		this.msisdnCancelUser = pMsisdnCancelUser;
	}

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
	 * Gets the cancel date.
	 * 
	 * @return the cancel date
	 */
	public final Date getCancelDate() {
		return cancelDate;
	}

	/**
	 * Sets the cancel date.
	 * 
	 * @param pCancelDate
	 *            the new cancel date
	 */
	public final void setCancelDate(final Date pCancelDate) {
		this.cancelDate = pCancelDate;
	}

	/**
	 * Gets the start date send message.
	 * 
	 * @return the start date send message
	 */
	public final Date getStartDateSendMessage() {
		return startDateSendMessage;
	}

	/**
	 * Sets the start date send message.
	 * 
	 * @param pStartDateSendMessage
	 *            the new start date send message
	 */
	public final void setStartDateSendMessage(final Date pStartDateSendMessage) {
		this.startDateSendMessage = pStartDateSendMessage;
	}

	/**
	 * Gets the end date send message.
	 * 
	 * @return the end date send message
	 */
	public final Date getEndDateSendMessage() {
		return endDateSendMessage;
	}

	/**
	 * Sets the end date send message.
	 * 
	 * @param pEndDateSendMessage
	 *            the new end date send message
	 */
	public final void setEndDateSendMessage(final Date pEndDateSendMessage) {
		this.endDateSendMessage = pEndDateSendMessage;
	}

	/**
	 * Gets the dt status processo.
	 * 
	 * @return the dt status processo
	 */
	public final Date getDtStatusProcesso() {
		return dtStatusProcesso;
	}

	/**
	 * Sets the dt status processo.
	 * 
	 * @param pDtStatusProcesso
	 *            the new dt status processo
	 */
	public final void setDtStatusProcesso(final Date pDtStatusProcesso) {
		this.dtStatusProcesso = pDtStatusProcesso;
	}

	/**
	 * Gets the start time send message.
	 * 
	 * @return the start time send message
	 */
	public final Long getStartTimeSendMessage() {
		return startTimeSendMessage;
	}

	/**
	 * Sets the start time send message.
	 * 
	 * @param pStartTimeSendMessage
	 *            the new start time send message
	 */
	public final void setStartTimeSendMessage(final Long pStartTimeSendMessage) {
		this.startTimeSendMessage = pStartTimeSendMessage;
	}

	/**
	 * Gets the end time send message.
	 * 
	 * @return the end time send message
	 */
	public final Long getEndTimeSendMessage() {
		return endTimeSendMessage;
	}

	/**
	 * Sets the end time send message.
	 * 
	 * @param pEndTimeSendMessage
	 *            the new end time send message
	 */
	public final void setEndTimeSendMessage(final Long pEndTimeSendMessage) {
		this.endTimeSendMessage = pEndTimeSendMessage;
	}

	/**
	 * Gets the total messages.
	 * 
	 * @return the total messages
	 */
	public final Long getTotalMessages() {
		return totalMessages;
	}

	/**
	 * Sets the total messages.
	 * 
	 * @param pTotalMessages
	 *            the new total messages
	 */
	public final void setTotalMessages(final Long pTotalMessages) {
		this.totalMessages = pTotalMessages;
	}

	/**
	 * Gets the tx message.
	 * 
	 * @return the tx message
	 */
	public final String getTxMessage() {
		return txMessage;
	}

	/**
	 * Sets the tx message.
	 * 
	 * @param pTxMessage
	 *            the new tx message
	 */
	public final void setTxMessage(final String pTxMessage) {
		this.txMessage = pTxMessage;
	}

	/**
	 * Gets the company.
	 * 
	 * @return the company
	 */
	public final Company getCompany() {
		return company;
	}

	/**
	 * Sets the company.
	 * 
	 * @param pCompany
	 *            the new company
	 */
	public final void setCompany(final Company pCompany) {
		this.company = pCompany;
	}

	/**
	 * Gets the message model.
	 * 
	 * @return the message model
	 */
	public final MessageModel getMessageModel() {
		return messageModel;
	}

	/**
	 * Sets the message model.
	 * 
	 * @param pMessageModel
	 *            the new message model
	 */
	public final void setMessageModel(final MessageModel pMessageModel) {
		this.messageModel = pMessageModel;
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

	/**
	 * Gets the user.
	 * 
	 * @return the user
	 */
	public final User getUser() {
		return user;
	}

	/**
	 * Sets the user.
	 * 
	 * @param pUser
	 *            the new user
	 */
	public final void setUser(final User pUser) {
		this.user = pUser;
	}

	/**
	 * Gets the processings.
	 *
	 * @return the processings
	 */
	public final List<Processing> getProcessings() {
		return processings;
	}

	/**
	 * Sets the processings.
	 *
	 * @param pProcessings
	 *            the new processings
	 */
	public final void setProcessings(final List<Processing> pProcessings) {
		this.processings = pProcessings;
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
		builder.append("idCompanyGroups", idCompanyGroups);
		builder.append("msisdnCancelUser", msisdnCancelUser);
		builder.append("msisdnCreationUser", msisdnCreationUser);
		builder.append("msisdnUpdateUser", msisdnUpdateUser);
		builder.append("cancelDate", cancelDate);
		builder.append("startDateSendMessage", startDateSendMessage);
		builder.append("endDateSendMessage", endDateSendMessage);
		builder.append("dtStatusProcesso", dtStatusProcesso);
		builder.append("endTimeSendMessage", endTimeSendMessage);
		builder.append("startTimeSendMessage", startTimeSendMessage);
		builder.append("totalMessages", totalMessages);
		builder.append("txMessage", txMessage);
		builder.append("company", company);
		builder.append("messageModel", messageModel);
		builder.append("status", status);
		builder.append("user", user);
		builder.append("creationDate", getCreationDate());
		builder.append("updateDate", getUpdateDate());
		return builder.toString();
	}

}