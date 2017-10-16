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
 * The persistent class for the SEMW_COMPOSICAO_PERFIL database table.
 * 
 */
@Entity
@Table(name = "SEMW_COMPOSICAO_PERFIL")
public class ActionProfile extends AbstractPersistent<ActionProfilePK> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@EmbeddedId
	private ActionProfilePK id;

	/** The msidn creation user. */
	@Column(name = "CD_USUARIO_CRIACAO")
	private String msisdnCreationUser;

	/** The msisdn update user. */
	@Column(name = "CD_USUARIO_MANUT")
	private String msisdnUpdateUser;

	// bi-directional many-to-one association to SemwAcao
	/** The action. */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CD_ACAO")
	@MapsId("cdAction")
	private Action action;

	// bi-directional many-to-one association to SemwPerfilAcesso
	/** The profile. */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CD_PERFIL_ACESSO")
	@MapsId("cdProfile")
	private Profile profile;

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
	 *            the new msidn creation user
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
	 * Gets the action.
	 * 
	 * @return the action
	 */
	public final Action getAction() {
		return action;
	}

	/**
	 * Sets the action.
	 * 
	 * @param pAction
	 *            the new action
	 */
	public final void setAction(final Action pAction) {
		this.action = pAction;
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
	public final ActionProfilePK getId() {
		return id;
	}

	@Override
	public final void setId(final ActionProfilePK pId) {
		this.id = pId;
	}

	@Override
	public String toString() {
		ToStringCreator builder = new ToStringCreator(this);
		builder.append("id", id);
		builder.append("msisdnCreationUser", msisdnCreationUser);
		builder.append("msisdnUpdateUser", msisdnUpdateUser);
		builder.append("action", action);
		builder.append("profile", profile);
		builder.append("creationDate", getCreationDate());
		builder.append("updateDate", getUpdateDate());
		return builder.toString();
	}

}