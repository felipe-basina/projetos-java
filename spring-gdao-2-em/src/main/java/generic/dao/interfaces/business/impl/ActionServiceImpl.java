package generic.dao.interfaces.business.impl;

import generic.dao.interfaces.business.ActionService;
import generic.dao.interfaces.dao.ActionDao;
import generic.dao.model.Action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service("actionServiceImpl")
@Scope("prototype")
public class ActionServiceImpl implements ActionService {

	@Autowired
	private ActionDao aDao;

	@Override
	public List<Action> getAllActions() {
		return this.aDao.getAll();
	}
	
	@Override
	public Action getById(Integer id) {
		return this.aDao.find(id);
	}
	
	@Override
	public Action create(Action a) {
		return aDao.create(a);
	}
	
	@Override
	public Action update(Action a) {
		return aDao.update(a);
	}
}
