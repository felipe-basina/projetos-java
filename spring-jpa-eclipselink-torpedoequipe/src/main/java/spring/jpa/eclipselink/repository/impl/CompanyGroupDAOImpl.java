package spring.jpa.eclipselink.repository.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import spring.jpa.eclipselink.domain.CompanyGroup;
import spring.jpa.eclipselink.repository.CompanyGroupDAO;

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
		StringBuilder consulta = new StringBuilder(" select cg from ".concat(getClazz().getSimpleName()));
		
		/*** 
		 * JOIN FETCH não funciona em relacionamento onde a chave primária da tabela do relacionamento é composta 
		 * Tabela SEMW_MEMBRO_GRUPO 
		 ***/
		consulta.append(" cg ");
		consulta.append(" where cg.groupName = :param ");
		
		Query query = this.getEntityManager().createQuery(consulta.toString());

		/** Define HINTS para diminuir acesso à base de dados **/
		query.setHint("eclipselink.join-fetch", "cg.company");
		query.setHint("eclipselink.join-fetch", "cg.user");
		query.setHint("eclipselink.join-fetch", "cg.user.company");
		query.setHint("eclipselink.join-fetch", "cg.user.profile");
		query.setHint("eclipselink.join-fetch", "cg.user.profile.company");
		query.setHint("eclipselink.join-fetch", "cg.userGroup");
		query.setHint("eclipselink.join-fetch", "cg.userGroup.user");
		query.setHint("eclipselink.join-fetch", "cg.userGroup.user.company");
		query.setHint("eclipselink.join-fetch", "cg.userGroup.user.profile");
		query.setHint("eclipselink.join-fetch", "cg.userGroup.user.profile.company");
		
		query.setParameter("param", groupName);
		return query.getResultList();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<CompanyGroup> findByCompanyGroupId(Long companyId) {
		StringBuilder consulta = new StringBuilder(" select cg from ".concat(getClazz().getSimpleName()));
		
		/*** 
		 * JOIN FETCH não funciona em relacionamento onde a chave primária da tabela do relacionamento é composta 
		 * Tabela SEMW_MEMBRO_GRUPO 
		 ***/
		consulta.append(" cg ");
		consulta.append(" where cg.id = :param ");
		
		Query query = this.getEntityManager().createQuery(consulta.toString());

		/** Define HINTS para diminuir acesso à base de dados **/
		query.setHint("eclipselink.join-fetch", "cg.company");
		query.setHint("eclipselink.join-fetch", "cg.user");
		query.setHint("eclipselink.join-fetch", "cg.user.company");
		query.setHint("eclipselink.join-fetch", "cg.user.profile");
		query.setHint("eclipselink.join-fetch", "cg.user.profile.company");
		query.setHint("eclipselink.join-fetch", "cg.userGroup");
		query.setHint("eclipselink.join-fetch", "cg.userGroup.user");
		query.setHint("eclipselink.join-fetch", "cg.userGroup.user.company");
		query.setHint("eclipselink.join-fetch", "cg.userGroup.user.profile");
		query.setHint("eclipselink.join-fetch", "cg.userGroup.user.profile.company");
		
		query.setParameter("param", companyId);
		return query.getResultList();
	}

}