package generic.dao.interfaces.business;

import javax.persistence.EntityManager;


public interface PhoneService {
    void deletePhoneByUserId(int id);
    EntityManager getEntityManager();
}
