package spring.jpa.eclipselink.service;

import java.util.List;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.jpa.eclipselink.domain.Company;
import spring.jpa.eclipselink.domain.User;
import spring.jpa.eclipselink.exception.DataException;
import spring.jpa.eclipselink.repository.UserDAO;
import spring.jpa.eclipselink.utils.ProfileEnum;

@Service
public class UserInterfaceImpl implements UserInterface {

	@Autowired
	private UserDAO userDao;

	@Override
	public User recuperarUsuarioPorMsisdn(String msisdn) throws DataException {

		User usuario = null;

		try {
			usuario = userDao.findByMsisdn(msisdn, true);
		} catch (Exception ex) {
			System.err.println(" #### ".concat(ex.getMessage()));
		}

		return usuario;
	}

	@Override
	public User recuperarPorPerfilEEmpresa(ProfileEnum profile,
			Company company, Boolean activeRegister) throws DataException {

		User usuario = null;
		
		try {
			usuario = userDao.findByProfileAndCompany(profile, company, activeRegister);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return usuario;
	}
	
	@Override
	public User recuperarPorPerfilEMsisdn(ProfileEnum profile, String msisdn,
			Boolean activeRegister) throws DataException {

		User usuario = null;
		
		try {
			usuario = userDao.findByProfileAndMsisdn(profile, msisdn, activeRegister);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return usuario;
	}
	
	@Override
	public User recuperarUsuarioPorMsisdnJoin(String msisdn)
			throws DataException {

		String sql = "select u from User u WHERE u.msisdn = :msisdn and u.activeRegister = :status";

		Query consulta = userDao.getEntityManager().createQuery(sql);
		consulta.setHint("eclipselink.join-fetch", "u.company");
		consulta.setHint("eclipselink.join-fetch", "u.profile");
		consulta.setHint("eclipselink.join-fetch", "u.profile.company");
		consulta.setParameter("msisdn", msisdn);
		consulta.setParameter("status", true);

		User usuario = null;
		try {
			usuario = (User) consulta.getSingleResult();
		} catch (Exception ex) {
			System.err.println(" ### ".concat(ex.getMessage()));
		}

		return usuario;
	}
	
	@Override
	public List<User> findAllByCompany(User user) {
		return this.userDao.findAllByCompany(user.getCompany(), true);
	}
	
	@Override
	public List<User> findAllByCompanyJoin(User user) {
		return this.userDao.findAllByCompany(user.getCompany());
	}
}
