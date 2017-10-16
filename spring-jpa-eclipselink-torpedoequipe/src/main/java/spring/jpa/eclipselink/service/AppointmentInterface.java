package spring.jpa.eclipselink.service;

import java.util.Date;
import java.util.List;

import spring.jpa.eclipselink.domain.Appointment;
import spring.jpa.eclipselink.domain.CompanyGroup;
import spring.jpa.eclipselink.domain.MessageModel;
import spring.jpa.eclipselink.domain.Report;
import spring.jpa.eclipselink.domain.User;
import spring.jpa.eclipselink.utils.ProcessStatusEnum;

public interface AppointmentInterface {

	public List<Appointment> recuperarAgendasFinalizadas();
	
	public Appointment recuperarAntesDataEStatus(final ProcessStatusEnum status);
	
	public Long contabilizarPorDataInicioEEmpresaEStatus(final Date startDate,
			final Long companyId, final ProcessStatusEnum... status);
	
	public List<Appointment> recuperarAgendasPorGrupoEmpresaEStatus(
			final CompanyGroup companyGroup, final ProcessStatusEnum status);
	
	public List<Appointment> recuperarAgendasPorMensagemModeloEStatus(
			final MessageModel messageModel, final ProcessStatusEnum status);
	
	public List<Appointment> recuperarAgendasPorMsisdnEStatus(
			final String msisdn, final ProcessStatusEnum status);
	
	public List<Appointment> recuperarDadosRelatorios(final Date startDate,
			final Date endDate, final String status,
			final List<String> appointmentId, final Long userId,
			final Long companyId, final int pageSize);

	public List<Appointment> recuperarAgendasFinalizadasJoinFetch();

	public List<Appointment> recuperarAgendasFinalizadasJoinBatch();

	public List<Report> recuperarTotalMensagensPorCliente();

	public List<Report> recuperarTotalMensagensPorClienteEStatus(String statusId);

	public List<Report> recuperarTotalMensagensPorStatus(String statusId);

	public List<Object[]> recuperarTotalMensagensPorStatusMesAno(String statusId);

	public String recuperarExtract();
	
	public void generateAppointment(int totalAppointment, User user);
	
	public void deleteAppointments(String creationUser) throws Exception;
	
	public List<Appointment> recuperarAgendasPorIdEmpresa(Long id);
	
	public List<Appointment> recuperarAgendasPaginacao(final int paginaInicial, int totalRegistros);
	
	public List<Appointment> recuperarMensagensAgenda(final List<Long> ids);
}
