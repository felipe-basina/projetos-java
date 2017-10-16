package spring.jpa.eclipselink.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.eclipse.persistence.annotations.Convert;
import org.springframework.core.style.ToStringCreator;

/**
 * The persistent class for the SEMW_ACAO database table.
 * 
 */
@Entity
@Table(name = "SEMW_O.SEMW_ACAO")
public class Action extends AbstractPersistent<Long> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@Column(name = "CD_ACAO")
	private Long id;

	/** The creation user. */
	@Column(name = "CD_USUARIO_CRIACAO")
	private String creationUser;

	/** The update user. */
	@Column(name = "CD_USUARIO_MANUT")
	private String updateUser;

	/** The action description. */
	@Column(name = "DS_ACAO")
	private String actionDescription;

	/** The active register. */
	@Column(name = "FG_REGISTRO_ATIVO")
	@Convert("stringToBooleanConverter")
	private boolean activeRegister;

	// bi-directional many-to-one association to SemwComposicaoPerfil
	/** The action profile. */
	@OneToMany(mappedBy = "action", fetch = FetchType.LAZY)
	private List<ActionProfile> actionProfile;

	/** The action key. */
	@Transient
	private transient String actionKey;

	/** The module title. */
	@Transient
	private transient String actionGroup;

	/** The action title. */
	@Transient
	private transient String actionTitle;

	/**
	 * Gets the creation user.
	 * 
	 * @return the creation user
	 */
	public final String getCreationUser() {
		return creationUser;
	}

	/**
	 * Sets the creation user.
	 * 
	 * @param pCreationUser
	 *            the new creation user
	 */
	public final void setCreationUser(final String pCreationUser) {
		this.creationUser = pCreationUser;
	}

	/**
	 * Gets the update user.
	 * 
	 * @return the update user
	 */
	public final String getUpdateUser() {
		return updateUser;
	}

	/**
	 * Sets the update user.
	 * 
	 * @param pUpdateUser
	 *            the new update user
	 */
	public final void setUpdateUser(final String pUpdateUser) {
		this.updateUser = pUpdateUser;
	}

	/**
	 * Gets the action description.
	 * 
	 * @return the action description
	 */
	public final String getActionDescription() {
		return actionDescription;
	}

	/**
	 * Sets the action description.
	 * 
	 * @param pActionDescription
	 *            the new action description
	 */
	public final void setActionDescription(final String pActionDescription) {
		this.actionDescription = pActionDescription;
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
	 * Gets the action profile.
	 * 
	 * @return the action profile
	 */
	public final List<ActionProfile> getActionProfile() {
		return actionProfile;
	}

	/**
	 * Sets the action profile.
	 * 
	 * @param pActionProfile
	 *            the new action profile
	 */
	public final void setActionProfile(final List<ActionProfile> pActionProfile) {
		this.actionProfile = pActionProfile;
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
	 * Gets the action key.
	 *
	 * @return the action key
	 */
	public final String getActionKey() {
		return actionKey;
	}

	/**
	 * Sets the action key.
	 *
	 * @param pActionKey
	 *            the new action key
	 */
	public final void setActionKey(final String pActionKey) {
		this.actionKey = pActionKey;
	}

	/**
	 * Gets the action group.
	 *
	 * @return the action group
	 */
	public final String getActionGroup() {
		return actionGroup;
	}

	/**
	 * Sets the action group.
	 *
	 * @param pActionGroup
	 *            the new action group
	 */
	public final void setActionGroup(final String pActionGroup) {
		this.actionGroup = pActionGroup;
	}

	/**
	 * Gets the action title.
	 *
	 * @return the action title
	 */
	public final String getActionTitle() {
		return actionTitle;
	}

	/**
	 * Sets the action title.
	 *
	 * @param pActionTitle
	 *            the new action title
	 */
	public final void setActionTitle(final String pActionTitle) {
		this.actionTitle = pActionTitle;
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
		builder.append("creationUser", creationUser);
		builder.append("updateUser", updateUser);
		builder.append("actionDescription", actionDescription);
		builder.append("activeRegister", activeRegister);
		builder.append("creationDate", getCreationDate());
		builder.append("updateDate", getUpdateDate());
		return builder.toString();
	}

}