package br.com.spring.schedule.jms.dao;

import org.springframework.stereotype.Repository;

import br.com.spring.schedule.jms.domain.MessageModel;

/**
 * The Class MessageModelDAOImpl.
 */
@Repository("messageModelDAO")
public class MessageModelDAOImpl extends BaseDAOImpl<MessageModel> implements
		MessageModelDAO {

	/**
	 * Instantiates a new message model dao impl.
	 */
	public MessageModelDAOImpl() {
		super(MessageModel.class);
	}

}
