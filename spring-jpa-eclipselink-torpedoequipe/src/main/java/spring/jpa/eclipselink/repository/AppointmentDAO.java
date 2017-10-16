package spring.jpa.eclipselink.repository;

import java.util.Date;
import java.util.List;

import spring.jpa.eclipselink.domain.Appointment;
import spring.jpa.eclipselink.domain.CompanyGroup;
import spring.jpa.eclipselink.domain.MessageModel;
import spring.jpa.eclipselink.utils.ProcessStatusEnum;

/**
 * The Interface AppointmentDAO.
 */
public interface AppointmentDAO extends BaseDAO<Appointment> {

	/**
	 * Find all by company group and status.
	 *
	 * @param companyGroup
	 *            the company group
	 * @param status
	 *            the status
	 * @return the list
	 */
	List<Appointment> findAllByCompanyGroupAndStatus(CompanyGroup companyGroup,
			ProcessStatusEnum status);

	List<Appointment> findAllByCompanyId(Long companyId);
	
	/**
	 * Find all by message model and status.
	 *
	 * @param messageModel
	 *            the message model
	 * @param status
	 *            the status
	 * @return the list
	 */
	List<Appointment> findAllByMessageModelAndStatus(MessageModel messageModel,
			ProcessStatusEnum status);

	/**
	 * Find all by msisdn and status.
	 *
	 * @param msisdn
	 *            the msisdn
	 * @param status
	 *            the status
	 * @return the list
	 */
	List<Appointment> findAllByMsisdnAndStatus(String msisdn,
			ProcessStatusEnum status);

	/**
	 * Find before date and status.
	 *
	 * @param status
	 *            the status
	 * @return the appointment
	 */
	Appointment findBeforeDateAndStatus(ProcessStatusEnum status);

	/**
	 * Find all after date and status.
	 *
	 * @param status
	 *            the status
	 * @return the appointment
	 */
	List<Appointment> findAllAfterDateAndStatus(ProcessStatusEnum... status);

	/**
	 * Count by start date and company and status.
	 *
	 * @param startDate
	 *            the start date
	 * @param companyId
	 *            the company id
	 * @param status
	 *            the status
	 * @return the long
	 */
	Long countByStartDateAndCompanyAndStatus(Date startDate, Long companyId,
			ProcessStatusEnum... status);

	/**
	 * Gets the report data.
	 *
	 * @param startDate
	 *            the start date
	 * @param endDate
	 *            the end date
	 * @param status
	 *            the status
	 * @param appointmentId
	 *            the appointment id
	 * @param userId
	 *            the user id
	 * @param companyId
	 *            the company id
	 * @param maxResults
	 *            the max results
	 * @return the report data
	 */
	List<Appointment> getReportData(Date startDate, Date endDate,
			String status, List<String> appointmentId, final Long userId,
			final Long companyId, int maxResults);

}
