package spring.jpa.eclipselink.repository;

import java.util.List;

import spring.jpa.eclipselink.domain.MessageModel;

/**
 * The Interface MessageModelDAO.
 */
public interface MessageModelDAO extends BaseDAO<MessageModel> {

	public List<MessageModel> getAllByCompanyId(Long companyId);

}
