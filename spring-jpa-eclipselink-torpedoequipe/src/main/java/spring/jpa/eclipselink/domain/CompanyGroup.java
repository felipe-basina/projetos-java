package spring.jpa.eclipselink.domain;

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
import javax.validation.constraints.Size;

import org.eclipse.persistence.annotations.PrivateOwned;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.core.style.ToStringCreator;

/**
 * The persistent class for the SEMW_MEMBRO_GRUPO database table.
 * 
 */
@Entity
@Table(name = "SEMW_O.SEMW_GRUPO_USUARIOS")
public class CompanyGroup extends AbstractPersistent<Long> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -6382727265731249490L;

	/** The Constant GROUP_NAME_MAX_SIZE. */
	private static final int GROUP_NAME_MAX_SIZE = 100;

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_SEQ_GRUPO")
	@SequenceGenerator(name = "ID_SEQ_GRUPO", sequenceName = "SEMW_GRUPO_USUARIOS_SQ01", allocationSize = 1)
	@Column(name = "CD_GRUPO_USUARIOS")
	private Long id;

	/** The msisdn creation user. */
	@Column(name = "CD_USUARIO_CRIACAO")
	private String msisdnCreationUser;

	/** The msisdn update user. */
	@Column(name = "CD_USUARIO_MANUT")
	private String msisdnUpdateUser;

	/** The group name. */
	@Size(max = GROUP_NAME_MAX_SIZE, message = "{group.name.size}")
	@NotEmpty(message = "{group.name.empty}")
	@Column(name = "NO_GRUPO_USUARIOS")
	private String groupName;

	/** The processing. */
	@OneToMany(mappedBy = "companyGroup", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Processing> processing;

	/** The user group. */
	@NotEmpty(message = "{group.list2.empty}")
	@PrivateOwned
	@OneToMany(mappedBy = "companyGroup", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<UserGroup> userGroup;

	/** The company. */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CD_EMPRESA")
	private Company company;

	/** The user. */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CD_USUARIO_RESPONSAVEL")
	private User user;

	/**
	 * Gets the group name.
	 * 
	 * @return the group name
	 */
	public final String getGroupName() {
		return groupName;
	}

	/**
	 * Sets the group name.
	 * 
	 * @param pGroupName
	 *            the new group name
	 */
	public final void setGroupName(final String pGroupName) {
		this.groupName = pGroupName;
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

	/**
	 * Gets the user group.
	 * 
	 * @return the user group
	 */
	public final List<UserGroup> getUserGroup() {
		return userGroup;
	}

	/**
	 * Sets the user group.
	 * 
	 * @param pUserGroup
	 *            the new user group
	 */
	public final void setUserGroup(final List<UserGroup> pUserGroup) {
		this.userGroup = pUserGroup;
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
		builder.append("groupName", groupName);
		builder.append("company", company);
		builder.append("user", user);
		builder.append("creationDate", getCreationDate());
		builder.append("updateDate", getUpdateDate());
		return builder.toString();
	}

}