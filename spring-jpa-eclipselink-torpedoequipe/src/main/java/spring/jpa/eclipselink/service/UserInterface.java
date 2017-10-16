package spring.jpa.eclipselink.service;

import java.util.List;

import spring.jpa.eclipselink.domain.Company;
import spring.jpa.eclipselink.domain.User;
import spring.jpa.eclipselink.exception.DataException;
import spring.jpa.eclipselink.utils.ProfileEnum;

public interface UserInterface {

	public User recuperarUsuarioPorMsisdn(String msisdn) throws DataException;

	public User recuperarPorPerfilEEmpresa(final ProfileEnum profile,
			final Company company, final Boolean activeRegister) throws DataException;
	
	public User recuperarPorPerfilEMsisdn(final ProfileEnum profile,
			final String msisdn, final Boolean activeRegister) throws DataException;
	
	public User recuperarUsuarioPorMsisdnJoin(String msisdn)
			throws DataException;
	
	public List<User> findAllByCompany(final User user);
	
	public List<User> findAllByCompanyJoin(final User user);

}
