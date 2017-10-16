package generic.dao.interfaces.business.impl;

import generic.dao.interfaces.business.TesteService;
import generic.dao.interfaces.dao.TesteDao;
import generic.dao.model.Teste;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service("testeServiceImpl")
@Scope("prototype")
public class TesteServiceImpl implements TesteService {

	@Autowired
	private TesteDao tDao;

	@Override
	public Teste create(Teste t) {
		return this.tDao.create(t);
	}

	@Override
	public Teste find(Object id) {
		return tDao.find(id);
	}
	
	public List<Teste> getAll() {
		return this.tDao.getAll();
	}
	
	@Override
	public void delete(Teste t) {
		this.tDao.delete(t.getId());
	}
	
}
