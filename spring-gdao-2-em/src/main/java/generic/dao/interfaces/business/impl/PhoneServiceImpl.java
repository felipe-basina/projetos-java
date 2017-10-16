package generic.dao.interfaces.business.impl;

import javax.persistence.EntityManager;

import generic.dao.interfaces.business.PhoneService;
import generic.dao.interfaces.dao.PhoneDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service("phoneServiceImpl")
@Scope("prototype")
public class PhoneServiceImpl implements PhoneService {

	@Autowired
	private PhoneDao dao;
    
    @Override
    public void deletePhoneByUserId(int id) {
    	this.dao.deletePhoneByUserId(id);
    }

	@Override
	public EntityManager getEntityManager() {
		return this.dao.getEntityManager();
	}
}
