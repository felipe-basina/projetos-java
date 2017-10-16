package br.com.spring.schedule.jms.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.spring.schedule.jms.domain.Company;
import br.com.spring.schedule.jms.domain.User;

/**
 * The Class UserDAOImpl.
 */
@SuppressWarnings("unchecked")
@Repository(value = "userDAO")
public class UserDAOImpl extends BaseDAOImpl<User> implements UserDAO {

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
		query.setParameter("msisdn", msisdn);

		List<User> users = query.getResultList();
		User user = null;
		if (!users.isEmpty()) {
			user = users.iterator().next();
		}
		return user;
	}

	@Override
	public final User findByProfileAndCompany(final Company company, final Boolean activeRegister) {
		StringBuilder sQuery = new StringBuilder("SELECT user from ");
		sQuery.append(getClazz().getSimpleName());
		sQuery.append(" user where user.company = :company ");
		sQuery.append(" and user.profile.id = :profileId ");

		if (activeRegister != null) {
			sQuery.append(" and user.activeRegister = " + activeRegister);
		}

		Query query = getEntityManager().createQuery(sQuery.toString());
		query.setParameter("company", company);

		List<User> users = query.getResultList();
		User user = null;
		if (!users.isEmpty()) {
			user = users.iterator().next();
		}
		return user;
	}

	@Override
	public User findByProfileAndMsisdn(final String msisdn, final Boolean activeRegister) {
		StringBuilder sQuery = new StringBuilder("SELECT user from ");
		sQuery.append(getClazz().getSimpleName());
		sQuery.append(" user where user.msisdn = :msisdn ");
		sQuery.append(" and user.profile.id = :profileId ");

		if (activeRegister != null) {
			sQuery.append(" and user.activeRegister = " + activeRegister);
		}

		Query query = getEntityManager().createQuery(sQuery.toString());
		query.setParameter("msisdn", msisdn);

		List<User> users = query.getResultList();
		User user = null;
		if (!users.isEmpty()) {
			user = users.iterator().next();
		}

		return user;
	}

}
