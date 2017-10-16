package br.com.spring.schedule.jms.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.spring.schedule.jms.domain.CompanyGroup;

/**
 * The Class CompanyGroupDAOImpl.
 */
@Repository("companyGroupDAO")
public class CompanyGroupDAOImpl extends BaseDAOImpl<CompanyGroup> implements
		CompanyGroupDAO {

	/**
	 * Instantiates a new company group dao impl.
	 */
	public CompanyGroupDAOImpl() {
		super(CompanyGroup.class);
	}

	@Override
	@SuppressWarnings("unchecked")
	public final List<CompanyGroup> findByGroupName(final String groupName) {
		Query query = this.getEntityManager().createQuery(
				"select g from " + getClazz().getSimpleName()
						+ " g where g.groupName=:param");
		query.setParameter("param", groupName);
		return query.getResultList();
	}

}
