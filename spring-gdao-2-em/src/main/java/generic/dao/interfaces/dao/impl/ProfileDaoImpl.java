package generic.dao.interfaces.dao.impl;

import generic.dao.impl.GenericDaoImpl;
import generic.dao.interfaces.dao.ProfileDao;
import generic.dao.model.Profile;

import org.springframework.stereotype.Repository;

@Repository
public class ProfileDaoImpl extends GenericDaoImpl<Profile> implements ProfileDao {
	
}

