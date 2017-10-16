package spring.jpa.eclipselink.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.eclipse.persistence.annotations.Convert;
import org.springframework.core.style.ToStringCreator;

/**
 * The persistent class for the SEMW_EMPRESA database table.
 * 
 */
@Entity
@Table(name = "SEMW_O.SEMW_EMPRESA")
public class Company extends AbstractPersistent<Long> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_SEQ_EMPRESA")
	@SequenceGenerator(name = "ID_SEQ_EMPRESA", sequenceName = "SEMW_EMPRESA_SQ01", allocationSize = 1)
	@Column(name = "CD_EMPRESA")
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

	/** The cancel date. */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DT_CANCELAMENTO")
	private Date cancelDate;

	/** The active register. */
	@Column(name = "FG_REGISTRO_ATIVO")
	@Convert("stringToBooleanConverter")
	private boolean activeRegister;

	/** The company name. */
	@Column(name = "NO_EMPRESA")
	private String companyName;

	/** The appointment. */
	@OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
	private List<Appointment> appointment;

	/** The company groups. */
	@OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
	private List<CompanyGroup> companyGroups;

	/** The message models. */
	@OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
	private List<MessageModel> messageModels;

	/** The profiles. */
	@OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
	private List<Profile> profiles;

	/** The users. */
	@OneToMany(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<User> users;

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
	 * Sets the active register.
	 * 
	 * @param pActiveRegister
	 *            the new active register
	 */
	public final void setActiveRegister(final boolean pActiveRegister) {
		this.activeRegister = pActiveRegister;
	}

	/**
	 * Gets the company name.
	 * 
	 * @return the company name
	 */
	public final String getCompanyName() {
		return companyName;
	}

	/**
	 * Sets the company name.
	 * 
	 * @param pCompanyName
	 *            the new company name
	 */
	public final void setCompanyName(final String pCompanyName) {
		this.companyName = pCompanyName;
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
	 * Gets the message models.
	 * 
	 * @return the message models
	 */
	public final List<MessageModel> getMessageModels() {
		return messageModels;
	}

	/**
	 * Sets the message models.
	 * 
	 * @param pMessageModels
	 *            the new message models
	 */
	public final void setMessageModels(final List<MessageModel> pMessageModels) {
		this.messageModels = pMessageModels;
	}

	/**
	 * Gets the profiles.
	 * 
	 * @return the profiles
	 */
	public final List<Profile> getProfiles() {
		return profiles;
	}

	/**
	 * Sets the profiles.
	 * 
	 * @param pProfiles
	 *            the new profiles
	 */
	public final void setProfiles(final List<Profile> pProfiles) {
		this.profiles = pProfiles;
	}

	/**
	 * Gets the users.
	 * 
	 * @return the users
	 */
	public final List<User> getUsers() {
		return users;
	}

	/**
	 * Sets the users.
	 * 
	 * @param pUsers
	 *            the new users
	 */
	public final void setUsers(final List<User> pUsers) {
		this.users = pUsers;
	}

	/**
	 * Gets the company groups.
	 * 
	 * @return the company groups
	 */
	public final List<CompanyGroup> getCompanyGroups() {
		return companyGroups;
	}

	/**
	 * Sets the company groups.
	 * 
	 * @param pCompanyGroups
	 *            the new company groups
	 */
	public final void setCompanyGroups(final List<CompanyGroup> pCompanyGroups) {
		this.companyGroups = pCompanyGroups;
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
		builder.append("msisdnCancelUser", msisdnCancelUser);
		builder.append("msisdnCreationUser", msisdnCreationUser);
		builder.append("msisdnUpdateUser", msisdnUpdateUser);
		builder.append("cancelDate", cancelDate);
		builder.append("activeRegister", activeRegister);
		builder.append("companyName", companyName);
		builder.append("creationDate", getCreationDate());
		builder.append("updateDate", getUpdateDate());
		return builder.toString();
	}

}