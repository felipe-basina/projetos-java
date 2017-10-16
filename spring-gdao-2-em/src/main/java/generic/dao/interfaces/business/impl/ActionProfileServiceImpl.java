package generic.dao.interfaces.business.impl;

import generic.dao.interfaces.business.ActionProfileService;
import generic.dao.interfaces.dao.ActionProfileDao;
import generic.dao.model.ActionProfile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service("actionProfileServiceImpl")
@Scope("prototype")
public class ActionProfileServiceImpl implements ActionProfileService {

	@Autowired
	private ActionProfileDao apDao;

	@Override
	public ActionProfile save(ActionProfile ap) {
		return apDao.create(ap);
	}
	
	public ActionProfile update(ActionProfile ap) {
		return apDao.update(ap);
	};
}
