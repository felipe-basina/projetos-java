package br.com.spring.schedule.jms.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.eclipse.persistence.annotations.Convert;
import org.springframework.core.style.ToStringCreator;

/**
 * The Class SPSMessage.
 */
@Entity
@Table(name = "SEMW_NOTIFICACAO_SPS")
public class SPSMessage extends AbstractPersistent<Integer> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_SEQ_NOTIFICACAO_SPS")
	@SequenceGenerator(name = "ID_SEQ_NOTIFICACAO_SPS", sequenceName = "SEMW_NOTIFICACAO_SPS_SQ01", allocationSize = 1)
	@Column(name = "CD_NOTIFICACAO_SPS")
	private Integer id;

	/** The msisdn. */
	@Column(name = "NU_MSISDN")
	private String msisdn;

	/** The supply status. */
	@Column(name = "CD_STATUS_APROVISIONAMENTO")
	private String supplyStatus;

	/** The active register. */
	@Column(name = "FG_REGISTRO_ATIVO")
	@Convert("stringToBooleanConverter")
	private boolean activeRegister;

	@Override
	public final Integer getId() {
		return id;
	}

	@Override
	public final void setId(final Integer pId) {
		this.id = pId;
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
	 * Gets the supply status.
	 *
	 * @return the supply status
	 */
	public final String getSupplyStatus() {
		return supplyStatus;
	}

	/**
	 * Sets the supply status.
	 *
	 * @param pSupplyStatus
	 *            the new supply status
	 */
	public final void setSupplyStatus(final String pSupplyStatus) {
		this.supplyStatus = pSupplyStatus;
	}

	/**
	 * Checks if is active register.
	 *
	 * @return true, if is active register
	 */
	public final boolean isActiveRegister() {
		return activeRegister;
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

	@Override
	public String toString() {
		ToStringCreator builder = new ToStringCreator(this);
		builder.append("id", id);
		builder.append("msisdn", msisdn);
		builder.append("supplyStatus", supplyStatus);
		builder.append("activeRegister", activeRegister);
		builder.append("creationDate", getCreationDate());
		builder.append("updateDate", getUpdateDate());
		return builder.toString();
	}

}