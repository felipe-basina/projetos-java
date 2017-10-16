package spring.jpa.eclipselink.repository.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import spring.jpa.eclipselink.domain.MessageModel;
import spring.jpa.eclipselink.repository.MessageModelDAO;

/**
 * The Class MessageModelDAOImpl.
 */
@Repository("messageModelDAO")
public class MessageModelDAOImpl extends BaseDAOImpl<MessageModel> implements
		MessageModelDAO {

	/**
	 * Instantiates a new message model dao impl.
	 */
	public MessageModelDAOImpl() {
		super(MessageModel.class);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<MessageModel> getAllByCompanyId(Long companyId) {
		Query query = null;
		
		try {
			String sql = " SELECT DISTINCT m FROM MessageModel m "
					.concat(" JOIN FETCH m.company WHERE m.company.id = :param ");
			
			query = this.getEntityManager().createQuery(sql);

			/** Define HINTS para diminuir acesso à base de dados **/
			query.setHint("eclipselink.join-fetch", "m.user");
			query.setHint("eclipselink.join-fetch", "m.user.profile");
			query.setHint("eclipselink.join-fetch", "m.user.profile.company");
			query.setHint("eclipselink.left-join-fetch", "m.appointment");
			query.setHint("eclipselink.left-join-fetch", "m.appointment.status");
			
			query.setParameter("param", companyId);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return query.getResultList();
	}
}
