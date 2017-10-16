package generic.dao.interfaces.dao;

import generic.dao.interfaces.GenericDao;
import generic.dao.model.Phone;

public interface PhoneDao extends GenericDao<Phone> {
    void deletePhoneByUserId(int id);	
}
