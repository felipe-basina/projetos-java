package br.com.spring.schedule.jms.domain;

import java.util.List;

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
import javax.validation.constraints.Size;

import org.springframework.core.style.ToStringCreator;

/**
 * The persistent class for the SEMW_MODELO_MENSAGEM database table.
 * 
 */
@Entity
@Table(name = "SEMW_MODELO_MENSAGEM")
public class MessageModel extends AbstractPersistent<Long> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant NOME_GRUPO_MAX_SIZE. */
	private static final int MESSAGE_LENGHT_MAX = 148;

	/** The Constant NOME_GRUPO_MAX_SIZE. */
	private static final int TITLE_LENGHT_MAX = 100;

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_SEQ_MODELO_MENSAGEM")
	@SequenceGenerator(name = "ID_SEQ_MODELO_MENSAGEM", sequenceName = "SEMW_MODELO_MENSAGEM_SQ01", allocationSize = 1)
	@Column(name = "CD_MODELO_MENSAGEM")
	private Long id;

	/** The msisdn creation user. */
	@Column(name = "CD_USUARIO_CRIACAO")
	private String msisdnCreationUser;

	/** The msisdn update user. */
	@Column(name = "CD_USUARIO_MANUT")
	private String msisdnUpdateUser;

	/** The message model description. */
	@Column(name = "DS_MODELO_MENSAGEM")
	@Size(max = TITLE_LENGHT_MAX, message = "{message.messagetitle.size}")
	private String messageModelDescription;

	/** The tx message. */
	@Column(name = "TX_MENSAGEM")
	@Size(max = MESSAGE_LENGHT_MAX, message = "{message.messagetext.size}")
	private String txMessage;

	// bi-directional many-to-one association to SemwAgenda
	/** The appointment. */
	@OneToMany(mappedBy = "messageModel", fetch = FetchType.LAZY)
	private List<Appointment> appointment;

	// bi-directional many-to-one association to SemwEmpresa
	/** The company. */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CD_EMPRESA")
	private Company company;

	// bi-directional many-to-one association to SemwUsuario
	/** The user. */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CD_USUARIO_RESPONSAVEL")
	private User user;

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
	 * Gets the message model description.
	 *
	 * @return the message model description
	 */
	public final String getMessageModelDescription() {
		return messageModelDescription;
	}

	/**
	 * Sets the message model description.
	 *
	 * @param pMessageModelDescription
	 *            the new message model description
	 */
	public final void setMessageModelDescription(
			final String pMessageModelDescription) {
		this.messageModelDescription = pMessageModelDescription;
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
		builder.append("msisdnCreationUser", msisdnCreationUser);
		builder.append("msisdnUpdateUser", msisdnUpdateUser);
		builder.append("messageModelDescription", messageModelDescription);
		builder.append("txMessage", txMessage);
		builder.append("company", company);
		builder.append("user", user);
		builder.append("creationDate", getCreationDate());
		builder.append("updateDate", getUpdateDate());
		return builder.toString();
	}
}