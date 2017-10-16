package generic.dao.interfaces.business.impl;

import generic.dao.interfaces.business.ProfileService;
import generic.dao.interfaces.dao.ProfileDao;
import generic.dao.model.Profile;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service("profileServiceImpl")
@Scope("prototype")
public class ProfileServiceImpl implements ProfileService {

	@Autowired
	private ProfileDao pDao;

	@Override
	public Profile create(Profile p) {
		return pDao.create(p);
	}

	@Override
	public Profile merge(Profile p) {
		return this.pDao.update(p);
	}
	
	@Override
	public EntityManager getEntityManager() {
		return this.pDao.getEntityManager();
	}
	
	@Override
	public void delete(Object id) {
		this.pDao.delete(id);	
	}
	
}
