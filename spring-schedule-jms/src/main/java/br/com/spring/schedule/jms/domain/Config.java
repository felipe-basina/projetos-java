package br.com.spring.schedule.jms.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.core.style.ToStringCreator;

import br.com.spring.schedule.jms.enumeration.ConfigEnum;

/**
 * The persistent class for the SEMW_CONFIGURACAO database table.
 * 
 */
@Entity
@Table(name = "SEMW_CONFIGURACAO")
public class Config extends AbstractPersistent<ConfigEnum> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@Column(name = "CD_PARAM_CONFIG_SISTEMA")
	@Enumerated(EnumType.STRING)
	private ConfigEnum id;

	/** The msisdn creation user. */
	@Column(name = "CD_USUARIO_CRIACAO")
	private String msisdnCreationUser;

	/** The msisdn update user. */
	@Column(name = "CD_USUARIO_MANUT")
	private String msisdnUpdateUser;

	/** The ds system config param. */
	@Column(name = "DS_PARAM_CONFIG_SISTEMA")
	private String dsSystemConfigParam;

	/** The vl system config param. */
	@Column(name = "VL_PARAM_CONFIG_SISTEMA")
	private String vlSystemConfigParam;

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
	 * Gets the ds system config param.
	 * 
	 * @return the ds system config param
	 */
	public final String getDsSystemConfigParam() {
		return dsSystemConfigParam;
	}

	/**
	 * Sets the ds system config param.
	 * 
	 * @param pDsSystemConfigParam
	 *            the new ds system config param
	 */
	public final void setDsSystemConfigParam(final String pDsSystemConfigParam) {
		this.dsSystemConfigParam = pDsSystemConfigParam;
	}

	/**
	 * Gets the vl system config param.
	 * 
	 * @return the vl system config param
	 */
	public final String getVlSystemConfigParam() {
		return vlSystemConfigParam;
	}

	/**
	 * Sets the vl system config param.
	 * 
	 * @param pVlSystemConfigParam
	 *            the new vl system config param
	 */
	public final void setVlSystemConfigParam(final String pVlSystemConfigParam) {
		this.vlSystemConfigParam = pVlSystemConfigParam;
	}

	@Override
	public final ConfigEnum getId() {
		return this.id;
	}

	@Override
	public final void setId(final ConfigEnum pId) {
		this.id = pId;

	}

	@Override
	public String toString() {
		ToStringCreator builder = new ToStringCreator(this);
		builder.append("id", id);
		builder.append("msisdnCreationUser", msisdnCreationUser);
		builder.append("msisdnUpdateUser", msisdnUpdateUser);
		builder.append("dsSystemConfigParam", dsSystemConfigParam);
		builder.append("vlSystemConfigParam", vlSystemConfigParam);
		builder.append("creationDate", getCreationDate());
		builder.append("updateDate", getUpdateDate());
		return builder.toString();
	}

}