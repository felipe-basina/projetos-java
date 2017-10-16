package br.com.spring.schedule.jms.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.eclipse.persistence.annotations.Convert;
import org.springframework.core.style.ToStringCreator;

/**
 * The persistent class for the SEMW_RECIBO_ENTREGA_MSE database table.
 * 
 */
@Entity
@Table(name = "SEMW_RECIBO_ENTREGA_MSE")
public class Receipt extends AbstractPersistent<Long> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@Column(name = "CD_TRANSACAO_ENVIO_SMS")
	private Long id;

	/** The active register. */
	@Column(name = "FG_REGISTRO_ATIVO")
	@Convert("stringToBooleanConverter")
	private boolean activeRegister;

	/** The in status smpp. */
	@Column(name = "IN_STATUS_SMPP")
	private String inStatusSmpp;

	/** The nu msisdn. */
	@Column(name = "NU_MSISDN")
	private String nuMsisdn;

	/**
	 * Gets the in status smpp.
	 * 
	 * @return the in status smpp
	 */
	public final String getInStatusSmpp() {
		return inStatusSmpp;
	}

	/**
	 * Sets the in status smpp.
	 * 
	 * @param pInStatusSmpp
	 *            the new in status smpp
	 */
	public final void setInStatusSmpp(final String pInStatusSmpp) {
		this.inStatusSmpp = pInStatusSmpp;
	}

	/**
	 * Gets the nu msisdn.
	 * 
	 * @return the nu msisdn
	 */
	public final String getNuMsisdn() {
		return nuMsisdn;
	}

	/**
	 * Sets the nu msisdn.
	 * 
	 * @param pNuMsisdn
	 *            the new nu msisdn
	 */
	public final void setNuMsisdn(final String pNuMsisdn) {
		this.nuMsisdn = pNuMsisdn;
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
		builder.append("activeRegister", activeRegister);
		builder.append("inStatusSmpp", inStatusSmpp);
		builder.append("nuMsisdn", nuMsisdn);
		builder.append("creationDate", getCreationDate());
		builder.append("updateDate", getUpdateDate());
		return builder.toString();
	}

}