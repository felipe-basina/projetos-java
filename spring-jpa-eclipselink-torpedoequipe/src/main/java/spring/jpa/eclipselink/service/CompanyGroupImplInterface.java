package spring.jpa.eclipselink.service;

import java.util.List;
import java.util.Vector;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.jpa.eclipselink.domain.CompanyGroup;
import spring.jpa.eclipselink.domain.User;
import spring.jpa.eclipselink.repository.CompanyGroupDAO;

@Service
public class CompanyGroupImplInterface implements CompanyGroupInterface {

	@Autowired
	private CompanyGroupDAO companyGroupDao;

	@Override
	public CompanyGroup findAllById(Long id) {
		return companyGroupDao.findById(id);
	}

	@Override
	public List<CompanyGroup> findAllByCompany(User user) {
		return companyGroupDao.findAllByCompany(user.getCompany(), null);
	}

	@Override
	public List<CompanyGroup> findAllByCompanyAndUser(User user) {
		return companyGroupDao.findAllByCompanyAndUser(user.getCompany(), user);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CompanyGroup> findAllByCompanyJoin(User user) {

		String sql = "select cg from CompanyGroup cg WHERE cg.company.id = :id";

		Query consulta = companyGroupDao.getEntityManager().createQuery(sql);
		consulta.setHint("eclipselink.join-fetch", "cg.company");
		consulta.setHint("eclipselink.join-fetch", "cg.user");
		consulta.setHint("eclipselink.join-fetch", "cg.user.company");
		consulta.setHint("eclipselink.join-fetch", "cg.user.profile");
		consulta.setHint("eclipselink.join-fetch", "cg.user.profile.company");
		consulta.setHint("eclipselink.join-fetch", "cg.userGroup");
		consulta.setHint("eclipselink.join-fetch", "cg.userGroup.user");
		consulta.setHint("eclipselink.join-fetch", "cg.userGroup.user.company");
		consulta.setHint("eclipselink.join-fetch", "cg.userGroup.user.profile");
		consulta.setHint("eclipselink.join-fetch",
				"cg.userGroup.user.profile.company");
		consulta.setParameter("id", user.getCompany().getId());

		List<CompanyGroup> lista = null;
		try {
			lista = (Vector<CompanyGroup>) consulta.getResultList();
		} catch (Exception ex) {
			System.err.println(" ### ".concat(ex.getMessage()));
		}

		return lista;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CompanyGroup> findAllByCompanyJoin2(User user) {

		String sql = "select distinct cg from CompanyGroup cg "
				.concat(" JOIN FETCH cg.company as c ")
				.concat(" JOIN FETCH cg.user as u ")
				// .concat(" JOIN u.company as uc ")
				// .concat(" JOIN u.profile as up ")
				// .concat(" JOIN u.profile.company as upc ")
				.concat(" JOIN FETCH cg.userGroup as ug ")
				// .concat(" JOIN ug.user as ugu ")
				// .concat(" JOIN ug.user.company as uguc ")
				// .concat(" JOIN ug.user.profile as ugup ")
				// .concat(" JOIN ug.user.profile.company as ugupc ")
				.concat(" WHERE c.id = :id ");

		Query consulta = companyGroupDao.getEntityManager().createQuery(sql);
		/*
		 * consulta.setHint("eclipselink.join-fetch", "cg.company");
		 * consulta.setHint("eclipselink.join-fetch", "cg.user");
		 * consulta.setHint("eclipselink.join-fetch", "cg.user.company");
		 * consulta.setHint("eclipselink.join-fetch", "cg.user.profile");
		 * consulta.setHint("eclipselink.join-fetch",
		 * "cg.user.profile.company");
		 * consulta.setHint("eclipselink.join-fetch", "cg.userGroup");
		 * consulta.setHint("eclipselink.join-fetch", "cg.userGroup.user");
		 * consulta.setHint("eclipselink.join-fetch",
		 * "cg.userGroup.user.company");
		 * consulta.setHint("eclipselink.join-fetch",
		 * "cg.userGroup.user.profile");
		 * consulta.setHint("eclipselink.join-fetch",
		 * "cg.userGroup.user.profile.company");
		 */
		consulta.setParameter("id", user.getCompany().getId());

		List<CompanyGroup> lista = null;
		try {
			lista = (Vector<CompanyGroup>) consulta.getResultList();
		} catch (Exception ex) {
			System.err.println(" ### ".concat(ex.getMessage()));
		}

		return lista;
	}
	
	@Override
	public List<CompanyGroup> recuperarPorGrupoEmpresaNome(String groupName) {
		return companyGroupDao.findByGroupName(groupName);
	}
	
	@Override
	public List<CompanyGroup> recuperarPorGrupoEmpresaId(final Long id) {
		return companyGroupDao.findByCompanyGroupId(id);
	}
}
