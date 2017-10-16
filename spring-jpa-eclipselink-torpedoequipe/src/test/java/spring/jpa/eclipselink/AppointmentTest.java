package spring.jpa.eclipselink;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import spring.jpa.eclipselink.context.SpringContext;
import spring.jpa.eclipselink.domain.Appointment;
import spring.jpa.eclipselink.domain.CompanyGroup;
import spring.jpa.eclipselink.domain.MessageModel;
import spring.jpa.eclipselink.domain.Processing;
import spring.jpa.eclipselink.domain.Report;
import spring.jpa.eclipselink.domain.User;
import spring.jpa.eclipselink.service.AppointmentInterface;
import spring.jpa.eclipselink.service.AppointmentInterfaceImpl;
import spring.jpa.eclipselink.service.UserInterface;
import spring.jpa.eclipselink.service.UserInterfaceImpl;
import spring.jpa.eclipselink.utils.ProcessStatusEnum;

public class AppointmentTest {

	private static SpringContext context;

	private static UserInterface uService;
	
	private static AppointmentInterface service;

	private static final SimpleDateFormat SDF = new SimpleDateFormat("hh:mm:ss");

	private static String inicio;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = SpringContext.getInstance();
		service = context.getBean(AppointmentInterfaceImpl.class);
		uService = context.getBean(UserInterfaceImpl.class);
		inicio = SDF.format(new Date());
	}

	@AfterClass
	public static void setDownAfterClass() {
		System.out.println("\n >>>>>> Inicio :: " + inicio
				+ "\n >>>>>>    Fim :: " + SDF.format(new Date()));
	}

	@Test
	public void recuperarAgendasFinalizadas() {
		List<Appointment> agendas = service.recuperarAgendasFinalizadas();
		for (Appointment agenda : agendas) {
			System.out.println(" #### " + agenda);
		}
	}

	@Test
	public void recuperarAntesDataEStatus() {
		Appointment agenda = service.recuperarAntesDataEStatus(ProcessStatusEnum.AGENDADO);
		System.out.println("###\t" + agenda);
	}
	
	@Test
	public void contabilizarPorDataInicioEEmpresaEStatus() {
		Date dataInicio = null;
		
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
			dataInicio = sdf.parse("25/10/14");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		Long total = service.contabilizarPorDataInicioEEmpresaEStatus(dataInicio, 21L, ProcessStatusEnum.PROCESSADO);
		System.out.println("###\tTotal: " + total);
	}

	@Test
	public void recuperarAgendasPorGrupoEmpresaEStatus() {
		CompanyGroup companyGroup = new CompanyGroup();
		companyGroup.setId(49L);
		
		List<Appointment> agendas = service.recuperarAgendasPorGrupoEmpresaEStatus(companyGroup, ProcessStatusEnum.PROCESSADO);
		this.imprimirInformacoesAgendas(agendas);
	}
	
	@Test
	public void recuperarAgendasPorMensagemModeloEStatus() {
		MessageModel messageModel = new MessageModel();
		messageModel.setId(34L);
		
		List<Appointment> agendas = service.recuperarAgendasPorMensagemModeloEStatus(messageModel, ProcessStatusEnum.PROCESSADO);
		this.imprimirInformacoesAgendas(agendas);
	}
	
	@Test
	public void recuperarAgendasPorMsisdnEStatus() {
		List<Appointment> agendas = service.recuperarAgendasPorMsisdnEStatus("11991111111", ProcessStatusEnum.PROCESSADO);
		this.imprimirInformacoesAgendas(agendas);
	}
	
	@Test
	public void recuperarDadosRelatorios() {
		Date startDate = null, endDate = null;
	
		List<Appointment> agendas = service.recuperarDadosRelatorios(startDate, endDate, 
				null, null, null, null, 4);
		this.imprimirInformacoesAgendas(agendas);
	}
	
	private void imprimirInformacoesAgendas(List<Appointment> agendas) {
		if (agendas != null) {
			System.out.println("###\tTotal de agendas: "
					.concat(String.valueOf(agendas.size())));
		}
		
		for (Appointment agenda : agendas) {
			System.out.println("###\t" + agenda + "\nAgenda = "
					.concat(String.valueOf(agenda.getId()))
					.concat(", total de mensagens = ")
					.concat(String.valueOf(agenda.getProcessings().size())));
			for (Processing mensagem : agenda.getProcessings()) {
				System.out.println(">>>\t" + mensagem);
			}
		}
	}
	
	@Test
	public void recuperarAgendasFinalizadasJoinFetch() {
		List<Appointment> agendas = service
				.recuperarAgendasFinalizadasJoinFetch();
		for (Appointment agenda : agendas) {
			System.out.println("\n ----------------------------------------------- \n");
			System.out.println(" #### " + agenda + this.getProcessing(agenda));
		}
	}
	
	@Test
	public void recuperarAgendasFinalizadasJoinFetchPorPaginacao() {
		int totalRegistros = 5;
		
		for (int paginaInicial = 1; paginaInicial < totalRegistros; paginaInicial++) {
			List<Appointment> agendas = service.recuperarAgendasPaginacao(paginaInicial, totalRegistros);
			
			System.out.println("\n# Total de registros: " + agendas.size() + ", pagina inicial [ " + paginaInicial + " ]");
			
			List<Long> ids = new ArrayList<Long>();
			
			for (Appointment agenda : agendas) {
				ids.add(agenda.getId());
				//System.out.println(" #### " + agenda /*+ this.getProcessing(agenda)*/);
			}
			
			agendas = service.recuperarMensagensAgenda(ids);
			
			for (Appointment agenda : agendas) {
				System.out.println("#### " + agenda 
						/*+ this.getProcessing(agenda)*/);
			}
			
			System.out.println("\n/* --------------------- */\n");
		}
		
	}

	@Test
	public void recuperarAgendasFinalizadasJoinBatch() {
		List<Appointment> agendas = service
				.recuperarAgendasFinalizadasJoinBatch();
		for (Appointment agenda : agendas) {
			System.out.println(" #### " + agenda + this.getProcessing(agenda));
		}
	}

	private String getProcessing(Appointment agenda) {
		if (agenda != null && agenda.getProcessings() != null) {
			System.out
					.println(" ############## Total de registros de processamento :: "
							+ agenda.getProcessings().size());
		}

		StringBuilder sb = new StringBuilder();
		for (Processing processing : agenda.getProcessings()) {
			sb.append("\n ....... " + processing.getId());
			sb.append(" ....... "
					+ processing.getStatus().getStatusDescription());
			if (processing.getInChargeStatus() != null) {
				sb.append(" ....... "
						+ processing.getInChargeStatus().getStatusDescription());
			}
		}
		return sb.toString();
	}

	@Test
	public void recuperarTotalMensagensPorCliente() {
		List<Report> relatorio = service.recuperarTotalMensagensPorCliente();
		System.out.println(" ### Total de registros: " + relatorio.size());
		for (Report report : relatorio) {
			System.out.println(" #### " + report);
		}
	}

	@Test
	public void recuperarTotalMensagensEnviadasPorCliente() {
		List<Report> relatorio = service
				.recuperarTotalMensagensPorClienteEStatus("12");
		for (Report report : relatorio) {
			System.out.println(" #### " + report);
		}
	}

	@Test
	public void recuperarTotalMensagensEnviadasPorStatus() {
		List<Report> relatorio = service.recuperarTotalMensagensPorStatus(null);
		for (Report report : relatorio) {
			System.out.println(" #### " + report);
		}
	}

	@Test
	public void recuperarTotalMensagensEnviadasPorStatusMesAno() {
		/*
		 * [Erro MSISDN Inválido = 4] : 1 [Erro Tarifação = 11] : 3 [Mensagem
		 * Tarifada = 12] : 30 [Aguardando Recibo = 9] : 3
		 */
		List<Object[]> relatorios = service
				.recuperarTotalMensagensPorStatusMesAno(null);

		for (Object[] relatorio : relatorios) {
			System.out.println("\n ### Relatorio: "
					.concat("\n .... CODIGO_EMPRESA: ")
					.concat(String.valueOf((BigDecimal) relatorio[0]))
					.concat("\n .... NOME_EMPRESA: ")
					.concat((String) relatorio[1])
					.concat("\n .... PERIODO_REGISTRO: ")
					.concat((String) relatorio[2])
					.concat("\n .... TOTAL_MENSAGENS: ")
					.concat(String.valueOf((BigDecimal) relatorio[3]))
					.concat("\n"));
		}
	}

	@Test
	public void recuperarExtract() {
		String retorno = service.recuperarExtract();
		System.out.println(" #### Retorno: " + retorno);
	}
	
	@Test
	public void createAppointments() {
		String msisdn = "11973008408";

		try {
			User usuario = uService.recuperarUsuarioPorMsisdnJoin(msisdn);
			service.generateAppointment(12, usuario);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void deleteAppointments() {
		String creationUser = "11991111111";

		try {
			service.deleteAppointments(creationUser);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void recuperarAgendasPorIdEmpresa() {
		Long idEmpresa = 21L;
		
		List<Appointment> agendas = service.recuperarAgendasPorIdEmpresa(idEmpresa);
		this.imprimirInformacoesAgendas(agendas);
	}
}