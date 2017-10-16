package br.com.spring.schedule.jms.domain;

import java.util.ArrayList;
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
import javax.persistence.Transient;

import org.eclipse.persistence.annotations.Convert;
import org.springframework.core.style.ToStringCreator;
import org.springframework.util.StringUtils;

/**
 * The persistent class for the SEMW_USUARIO database table.
 * 
 */
@Entity
@Table(name = "SEMW_USUARIO")
public class User extends AbstractPersistent<Long> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_SEQ_USUARIO")
	@SequenceGenerator(name = "ID_SEQ_USUARIO", sequenceName = "SEMW_USUARIO_SQ01", allocationSize = 1)
	@Column(name = "CD_USUARIO")
	private Long id;

	/** The msisdn cancel user. */
	@Column(name = "CD_USUARIO_CANCELAMENTO")
	private String msisdnCancelUser;

	/** The msisdn creation user. */
	@Column(name = "CD_USUARIO_CRIACAO")
	private String msisdnCreationUser;

	/** The msisdn update user. */
	@Column(name = "CD_USUARIO_MANUT")
	private String msisdnUpdateUser;

	/** The email. */
	@Column(name = "DS_EMAIL")
	private String email;

	/** The cancel date. */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DT_CANCELAMENTO")
	private Date cancelDate;

	/** The pwd expiration date. */
	@Temporal(TemporalType.DATE)
	@Column(name = "DT_EXPIRACAO_SENHA")
	private Date pwdExpirationDate;

	/** The active offer. */
	@Column(name = "FG_OFERTA_ATIVA")
	@Convert("stringToBooleanConverter")
	private boolean activeOffer;

	/** The first access. */
	@Column(name = "FG_PRIMEIRO_ACESSO")
	@Convert("stringToBooleanConverter")
	private boolean firstAccess;

	/** The active register. */
	@Column(name = "FG_REGISTRO_ATIVO")
	@Convert("stringToBooleanConverter")
	private boolean activeRegister;

	/** The user name. */
	@Column(name = "NO_CLIENTE")
	private String userName;

	/** The msisdn. */
	@Column(name = "NU_MSISDN")
	private String msisdn;

	/** The password. */
	@Column(name = "TX_SENHA")
	private String password;

	/** The appointment. */
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<Appointment> appointment;

	/** The user groups. */
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<UserGroup> userGroups;

	/** The message model. */
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<MessageModel> messageModel;

	/** The company. */
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name = "CD_EMPRESA")
	private Company company;

	/** The profile. */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CD_PERFIL_ACESSO")
	private Profile profile;

	/**
	 * Gets the user groups names.
	 *
	 * @return the user groups names
	 */
	@Transient
	public String getUserGroupsNames() {
		List<String> userGroupName = new ArrayList<String>();

		if (this.userGroups != null) {
			for (UserGroup userGroup : userGroups) {
				userGroupName.add(userGroup.getCompanyGroup().getGroupName());
			}
		}

		return StringUtils.collectionToDelimitedString(userGroupName, ", ");
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
	 * Gets the email.
	 * 
	 * @return the email
	 */
	public final String getEmail() {
		return email;
	}

	/**
	 * Sets the email.
	 * 
	 * @param pEmail
	 *            the new email
	 */
	public final void setEmail(final String pEmail) {
		this.email = pEmail;
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
	 * Gets the pwd expiration date.
	 * 
	 * @return the pwd expiration date
	 */
	public final Date getPwdExpirationDate() {
		return pwdExpirationDate;
	}

	/**
	 * Sets the pwd expiration date.
	 * 
	 * @param pPwdExpirationDate
	 *            the new pwd expiration date
	 */
	public final void setPwdExpirationDate(final Date pPwdExpirationDate) {
		this.pwdExpirationDate = pPwdExpirationDate;
	}

	/**
	 * Sets the active offer.
	 * 
	 * @param pActiveOffer
	 *            the new active offer
	 */
	public final void setActiveOffer(final boolean pActiveOffer) {
		this.activeOffer = pActiveOffer;
	}

	/**
	 * Sets the first access.
	 * 
	 * @param pFirstAccess
	 *            the new first access
	 */
	public final void setFirstAccess(final boolean pFirstAccess) {
		this.firstAccess = pFirstAccess;
	}

	/**
	 * Sets the active register.
	 * 
	 * @param pActiveRegister
	 *            the new active register
	 */
	public final void setActiveRegister(final boolean pActiveRegister) {
		this.activeRegister = pActiveRegister;
	}

	/**
	 * Gets the user name.
	 * 
	 * @return the user name
	 */
	public final String getUserName() {
		return userName;
	}

	/**
	 * Sets the user name.
	 * 
	 * @param pUserName
	 *            the new user name
	 */
	public final void setUserName(final String pUserName) {
		this.userName = pUserName;
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
	 * Gets the password.
	 * 
	 * @return the password
	 */
	public final String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 * 
	 * @param pPassword
	 *            the new password
	 */
	public final void setPassword(final String pPassword) {
		this.password = pPassword;
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
	 * Gets the user groups.
	 * 
	 * @return the user groups
	 */
	public final List<UserGroup> getUserGroups() {
		return userGroups;
	}

	/**
	 * Sets the user groups.
	 * 
	 * @param pUserGroups
	 *            the new user groups
	 */
	public final void setUserGroups(final List<UserGroup> pUserGroups) {
		this.userGroups = pUserGroups;
	}

	/**
	 * Gets the message model.
	 * 
	 * @return the message model
	 */
	public final List<MessageModel> getMessageModel() {
		return messageModel;
	}

	/**
	 * Sets the message model.
	 * 
	 * @param pMessageModel
	 *            the new message model
	 */
	public final void setMessageModel(final List<MessageModel> pMessageModel) {
		this.messageModel = pMessageModel;
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
	 * Gets the profile.
	 * 
	 * @return the profile
	 */
	public final Profile getProfile() {
		return profile;
	}

	/**
	 * Sets the profile.
	 * 
	 * @param pProfile
	 *            the new profile
	 */
	public final void setProfile(final Profile pProfile) {
		this.profile = pProfile;
	}

	@Override
	public final Long getId() {
		return id;
	}

	@Override
	public final void setId(final Long pId) {
		this.id = pId;
	}

	/**
	 * Checks if is active offer.
	 *
	 * @return true, if is active offer
	 */
	public final boolean isActiveOffer() {
		return activeOffer;
	}

	/**
	 * Checks if is first access.
	 *
	 * @return true, if is first access
	 */
	public final boolean isFirstAccess() {
		return firstAccess;
	}

	/**
	 * Checks if is active register.
	 *
	 * @return true, if is active register
	 */
	public final boolean isActiveRegister() {
		return activeRegister;
	}

	@Override
	public String toString() {
		ToStringCreator builder = new ToStringCreator(this);
		builder.append("id", id);
		builder.append("msisdnCancelUser", msisdnCancelUser);
		builder.append("msisdnCreationUser", msisdnCreationUser);
		builder.append("msisdnUpdateUser", msisdnUpdateUser);
		builder.append("email", email);
		builder.append("cancelDate", cancelDate);
		builder.append("pwdExpirationDate", pwdExpirationDate);
		builder.append("activeOffer", activeOffer);
		builder.append("firstAccess", firstAccess);
		builder.append("activeRegister", activeRegister);
		builder.append("userName", userName);
		builder.append("msisdn", msisdn);
		builder.append("password", password);
		builder.append("company", company);
		builder.append("profile", profile);
		builder.append("creationDate", getCreationDate());
		builder.append("updateDate", getUpdateDate());
		return builder.toString();
	}
}