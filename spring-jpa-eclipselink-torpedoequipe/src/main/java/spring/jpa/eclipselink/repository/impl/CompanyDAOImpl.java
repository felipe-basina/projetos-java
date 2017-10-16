package spring.jpa.eclipselink.repository.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import spring.jpa.eclipselink.domain.Company;
import spring.jpa.eclipselink.repository.CompanyDAO;
import spring.jpa.eclipselink.utils.ProfileEnum;

/**
 * The Class CompanyDAOImpl.
 */
@Repository("companyDAO")
public class CompanyDAOImpl extends BaseDAOImpl<Company> implements CompanyDAO {

	private boolean useJoin = true;
	
	/**
	 * Instantiates a new company dao impl.
	 */
	public CompanyDAOImpl() {
		super(Company.class);
	}

	@Override
	public Company findByName(final String companyName,
			final Boolean activeRegister) {
		StringBuilder sQuery = new StringBuilder("SELECT c from ");
		sQuery.append(getClazz().getSimpleName());
		sQuery.append(" c where UPPER(c.companyName) = :companyName ");

		if (activeRegister != null) {
			sQuery.append(" and c.activeRegister = " + activeRegister);
		}

		Query query = getEntityManager().createQuery(sQuery.toString());
		query.setParameter("companyName", companyName.toUpperCase());

		@SuppressWarnings("unchecked")
		List<Company> companys = query.getResultList();
		Company company = null;
		if (!companys.isEmpty()) {
			company = companys.iterator().next();
		}
		return company;
	}

	/**
	 * Find company master.
	 *
	 * @return the list
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Company> findCompanyMaster() {
		StringBuilder sQuery = new StringBuilder("SELECT distinct c from ");
		sQuery.append(getClazz().getSimpleName());
		sQuery.append(" c join fetch c.users as users ");
		//sQuery.append(" join c.users as users ");
		sQuery.append(" where users.profile.id = :profile ");

		Query query = getEntityManager().createQuery(sQuery.toString());
		
		/** Define HINTS para diminuir acesso à base de dados **/
		if (useJoin) {
			query.setHint("eclipselink.join-fetch", "c.users.profile");
			query.setHint("eclipselink.join-fetch", "c.users.profile.company");
			query.setHint("eclipselink.join-fetch", "c.users.company");
		}
		
		query.setParameter("profile", ProfileEnum.MASTER.getId());

		return query.getResultList();
	}
}
