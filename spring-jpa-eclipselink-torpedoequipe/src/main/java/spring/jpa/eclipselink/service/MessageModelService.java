package spring.jpa.eclipselink.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.jpa.eclipselink.domain.MessageModel;
import spring.jpa.eclipselink.repository.MessageModelDAO;

@Service
public class MessageModelService implements MessageModelInterface {

	@Autowired
	private MessageModelDAO mDao;
	
	@Override
	public List<MessageModel> recuperarMensagensPorIdEmpresa(Long idEmpresa) {
		return mDao.getAllByCompanyId(idEmpresa);
	}
	
}
