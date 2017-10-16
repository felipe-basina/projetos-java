package generic.dao.interfaces.dao.impl;

import generic.dao.impl.GenericDaoImpl;
import generic.dao.interfaces.dao.PhoneDao;
import generic.dao.model.Phone;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class PhoneDaoImpl extends GenericDaoImpl<Phone> implements PhoneDao {
	
	@Override
	@Transactional
	public void deletePhoneByUserId(int id) {
		Query sqlDelete = em.createNamedQuery("deletePhoneByUserId");
		sqlDelete.setParameter("id", id);
		sqlDelete.executeUpdate();
	}
}

