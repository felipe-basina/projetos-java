package spring.jpa.eclipselink.service;

import java.util.List;

import spring.jpa.eclipselink.domain.MessageModel;

public interface MessageModelInterface {
	public List<MessageModel> recuperarMensagensPorIdEmpresa(Long idEmpresa);
}
