package br.com.spring.schedule.jms.domain;

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

import org.eclipse.persistence.annotations.PrivateOwned;
import org.springframework.core.style.ToStringCreator;

/**
 * The persistent class for the SEMW_PERFIL_ACESSO database table.
 * 
 */
@Entity
@Table(name = "SEMW_PERFIL_ACESSO")
public class Profile extends AbstractPersistent<Long> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_SEQ_PERFIL_ACESSO")
	@SequenceGenerator(name = "ID_SEQ_PERFIL_ACESSO", sequenceName = "SEMW_PERFIL_ACESSO_SQ01", allocationSize = 1)
	@Column(name = "CD_PERFIL_ACESSO")
	private Long id;

	/** The msisdn creation user. */
	@Column(name = "CD_USUARIO_CRIACAO")
	private String msisdnCreationUser;

	/** The msisdn update user. */
	@Column(name = "CD_USUARIO_MANUT")
	private String msisdnUpdateUser;

	/** The desc profile. */
	@Column(name = "DS_PERFIL_ACESSO")
	private String descProfile;
	// bi-directional many-to-one association to SemwComposicaoPerfil
	/** The action profile. */
	@PrivateOwned
	@OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<ActionProfile> actionProfile;

	// bi-directional many-to-one association to SemwEmpresa
	/** The company. */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CD_EMPRESA")
	private Company company;

	/** The msisdn creation user. */
	@Column(name = "CD_USUARIO_RESPONSAVEL")
	private Long userId;

	// bi-directional many-to-one association to SemwUsuario
	/** The user. */
	@OneToMany(mappedBy = "profile", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	private List<User> user;

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
	 * Gets the desc profile.
	 * 
	 * @return the desc profile
	 */
	public final String getDescProfile() {
		return descProfile;
	}

	/**
	 * Sets the desc profile.
	 * 
	 * @param pDescProfile
	 *            the new desc profile
	 */
	public final void setDescProfile(final String pDescProfile) {
		this.descProfile = pDescProfile;
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
	public final List<User> getUser() {
		return user;
	}

	/**
	 * Sets the user.
	 * 
	 * @param pUser
	 *            the new user
	 */
	public final void setUser(final List<User> pUser) {
		this.user = pUser;
	}

	/**
	 * Gets the userId.
	 * 
	 * @return the userId
	 */
	public final Long getUserId() {
		return userId;
	}

	/**
	 * Sets the userId.
	 * 
	 * @param pUserId
	 *            the new userId
	 */
	public final void setUserId(final Long pUserId) {
		this.userId = pUserId;
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
		builder.append("descProfile", descProfile);
		builder.append("company", company);
		builder.append("userId", userId);
		builder.append("creationDate", getCreationDate());
		builder.append("updateDate", getUpdateDate());
		return builder.toString();
	}
}