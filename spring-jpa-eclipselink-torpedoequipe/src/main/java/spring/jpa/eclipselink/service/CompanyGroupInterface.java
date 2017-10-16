package spring.jpa.eclipselink.service;

import java.util.List;

import spring.jpa.eclipselink.domain.CompanyGroup;
import spring.jpa.eclipselink.domain.User;

public interface CompanyGroupInterface {

	CompanyGroup findAllById(Long id);

	List<CompanyGroup> findAllByCompanyAndUser(User user);

	List<CompanyGroup> findAllByCompany(User user);

	List<CompanyGroup> findAllByCompanyJoin(User user);

	List<CompanyGroup> findAllByCompanyJoin2(User user);

	public List<CompanyGroup> recuperarPorGrupoEmpresaNome(final String groupName);

	public List<CompanyGroup> recuperarPorGrupoEmpresaId(final Long id);
}
