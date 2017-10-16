package spring.jpa.eclipselink.repository.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import spring.jpa.eclipselink.domain.Company;
import spring.jpa.eclipselink.domain.User;
import spring.jpa.eclipselink.repository.UserDAO;
import spring.jpa.eclipselink.utils.ProfileEnum;

/**
 * The Class UserDAOImpl.
 */
@SuppressWarnings("unchecked")
@Repository(value = "userDAO")
public class UserDAOImpl extends BaseDAOImpl<User> implements UserDAO {

	private boolean useHints = true;
	
	/**
	 * Instantiates a new user dao impl.
	 */
	public UserDAOImpl() {
		super(User.class);
	}

	@Override
	public final User findByMsisdn(final String msisdn,
			final Boolean activeRegister) {
		StringBuilder sQuery = new StringBuilder("SELECT user from ");
		sQuery.append(getClazz().getSimpleName());
		sQuery.append(" user where user.msisdn = :msisdn ");

		if (activeRegister != null) {
			sQuery.append(" and user.activeRegister = " + activeRegister);
		}

		Query query = getEntityManager().createQuery(sQuery.toString());
		
		/** Adiciona hints para diminuir acesso à base de dados **/
		if (useHints) {
			query.setHint("eclipselink.join-fetch", "user.company");
			query.setHint("eclipselink.join-fetch", "user.profile");
			query.setHint("eclipselink.join-fetch", "user.profile.company");
		}
		
		query.setParameter("msisdn", msisdn);

		List<User> users = query.getResultList();
		User user = null;
		if (!users.isEmpty()) {
			user = users.iterator().next();
		}
		return user;
	}

	@Override
	public final User findByProfileAndCompany(final ProfileEnum profile,
			final Company company, final Boolean activeRegister) {
		StringBuilder sQuery = new StringBuilder("SELECT user from ");
		sQuery.append(getClazz().getSimpleName());
		sQuery.append(" user where user.company = :company ");
		sQuery.append(" and user.profile.id = :profileId ");

		if (activeRegister != null) {
			sQuery.append(" and user.activeRegister = " + activeRegister);
		}

		Query query = getEntityManager().createQuery(sQuery.toString());

		/** Adiciona hints para diminuir acesso à base de dados **/
		if (useHints) {
			query.setHint("eclipselink.join-fetch", "user.company");
			query.setHint("eclipselink.join-fetch", "user.company.users");
			query.setHint("eclipselink.join-fetch", "user.company.users.company");
			query.setHint("eclipselink.join-fetch", "user.company.users.profile");
			query.setHint("eclipselink.join-fetch", "user.company.users.profile.company");
			query.setHint("eclipselink.join-fetch", "user.company.companyGroups");
			query.setHint("eclipselink.join-fetch", "user.profile");
			query.setHint("eclipselink.join-fetch", "user.profile.company");
		}
		
		query.setParameter("profileId", profile.getId());
		query.setParameter("company", company);

		List<User> users = query.getResultList();
		User user = null;
		if (!users.isEmpty()) {
			user = users.iterator().next();
		}
		return user;
	}

	@Override
	public User findByProfileAndMsisdn(final ProfileEnum profile,
			final String msisdn, final Boolean activeRegister) {
		StringBuilder sQuery = new StringBuilder("SELECT user from ");
		sQuery.append(getClazz().getSimpleName());
		sQuery.append(" user where user.msisdn = :msisdn ");
		sQuery.append(" and user.profile.id = :profileId ");

		if (activeRegister != null) {
			sQuery.append(" and user.activeRegister = " + activeRegister);
		}

		Query query = getEntityManager().createQuery(sQuery.toString());
		
		/** Adiciona hints para diminuir acesso à base de dados **/
		if (useHints) {
			query.setHint("eclipselink.join-fetch", "user.company");
			query.setHint("eclipselink.join-fetch", "user.profile");
			query.setHint("eclipselink.join-fetch", "user.profile.company");
		}
		
		query.setParameter("msisdn", msisdn);
		query.setParameter("profileId", profile.getId());

		List<User> users = query.getResultList();
		User user = null;
		if (!users.isEmpty()) {
			user = users.iterator().next();
		}

		return user;
	}

	@Override
	public List<User> findAllByCompany(Company company) {
		StringBuilder sb = new StringBuilder(" SELECT DISTINCT u FROM ");
		sb.append(this.getClazz().getSimpleName());
		sb.append(" u WHERE u.company = :company");
		
		Query consulta = this.getEntityManager().createQuery(sb.toString());
		consulta.setHint("eclipselink.join-fetch", "u.company");
		consulta.setHint("eclipselink.join-fetch", "u.profile");
		consulta.setHint("eclipselink.join-fetch", "u.profile.company");
		
		consulta.setParameter("company", company);
		
		List<User> users = consulta.getResultList();
		return users;
	}
	
}