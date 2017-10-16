package generic.dao.interfaces.dao.impl;

import generic.dao.impl.GenericDaoImpl;
import generic.dao.interfaces.dao.ActionDao;
import generic.dao.model.Action;

import org.springframework.stereotype.Repository;

@Repository
public class ActionDaoImpl extends GenericDaoImpl<Action> implements ActionDao {
	
}

