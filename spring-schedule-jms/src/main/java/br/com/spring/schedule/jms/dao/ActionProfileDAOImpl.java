package br.com.spring.schedule.jms.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.spring.schedule.jms.domain.ActionProfile;
import br.com.spring.schedule.jms.domain.User;

/**
 * The Class ActionProfileDAOImpl.
 */
@Repository("actionProfileDAO")
public class ActionProfileDAOImpl extends BaseDAOImpl<ActionProfile> implements
		ActionProfileDAO {

	/**
	 * Instantiates a new action profile dao impl.
	 */
	public ActionProfileDAOImpl() {
		super(ActionProfile.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public final List<ActionProfile> getActionsProfileByUser(final User user) {
		StringBuilder sQuery = new StringBuilder("SELECT actionProfile from ");
		sQuery.append(getClazz().getSimpleName());
		sQuery.append(" actionProfile where actionProfile.id.cdProfile = :profileId ");
		sQuery.append(" and actionProfile.action.activeRegister = true");
		Query query = getEntityManager().createQuery(sQuery.toString());
		query.setParameter("profileId", user.getProfile().getId());
		return query.getResultList();
	}
}
