package br.com.spring.schedule.jms.domain;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import org.springframework.core.style.ToStringCreator;

/**
 * The persistent class for the SEMW_GRUPO_USUARIOS database table.
 * 
 */
@Entity
@Table(name = "SEMW_MEMBRO_GRUPO")
public class UserGroup extends AbstractPersistent<UserGroupPK> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@EmbeddedId
	private UserGroupPK id;

	/** The msisdn creation user. */
	@Column(name = "CD_USUARIO_CRIACAO")
	private String msisdnCreationUser;

	/** The msisdn update user. */
	@Column(name = "CD_USUARIO_MANUT")
	private String msisdnUpdateUser;

	/** The user. */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CD_USUARIO")
	@MapsId("cdUser")
	private User user;

	/** The company group. */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CD_GRUPO_USUARIOS")
	@MapsId("cdCompanyGroup")
	private CompanyGroup companyGroup;

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

	@Override
	public final UserGroupPK getId() {
		return id;
	}

	@Override
	public final void setId(final UserGroupPK pId) {
		this.id = pId;
	}

	@Override
	public String toString() {
		ToStringCreator builder = new ToStringCreator(this);
		builder.append("id", id);
		builder.append("msisdnCreationUser", msisdnCreationUser);
		builder.append("msisdnUpdateUser", msisdnUpdateUser);
		builder.append("user", user);
		builder.append("companyGroup", companyGroup);
		builder.append("creationDate", getCreationDate());
		builder.append("updateDate", getUpdateDate());
		return builder.toString();
	}

}