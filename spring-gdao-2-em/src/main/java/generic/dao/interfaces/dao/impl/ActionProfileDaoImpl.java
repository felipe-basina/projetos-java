package generic.dao.interfaces.dao.impl;

import generic.dao.impl.GenericDaoImpl;
import generic.dao.interfaces.dao.ActionProfileDao;
import generic.dao.model.ActionProfile;

import org.springframework.stereotype.Repository;

@Repository
public class ActionProfileDaoImpl extends GenericDaoImpl<ActionProfile> implements ActionProfileDao {
	
}

