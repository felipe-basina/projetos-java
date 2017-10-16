package spring.jpa.eclipselink;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import spring.jpa.eclipselink.context.SpringContext;
import spring.jpa.eclipselink.domain.Appointment;
import spring.jpa.eclipselink.domain.Company;
import spring.jpa.eclipselink.domain.CompanyGroup;
import spring.jpa.eclipselink.domain.MessageModel;
import spring.jpa.eclipselink.domain.Processing;
import spring.jpa.eclipselink.domain.User;
import spring.jpa.eclipselink.domain.UserGroup;
import spring.jpa.eclipselink.exception.DataException;
import spring.jpa.eclipselink.service.AppointmentInterface;
import spring.jpa.eclipselink.service.AppointmentInterfaceImpl;
import spring.jpa.eclipselink.service.CompanyGroupImplInterface;
import spring.jpa.eclipselink.service.CompanyGroupInterface;
import spring.jpa.eclipselink.service.MessageModelInterface;
import spring.jpa.eclipselink.service.MessageModelService;
import spring.jpa.eclipselink.service.UserInterface;
import spring.jpa.eclipselink.service.UserInterfaceImpl;
import spring.jpa.eclipselink.utils.ProfileEnum;

public class UserTest {

	private static SpringContext context;

	private static UserInterface service;
	
	private static CompanyGroupInterface cgService;
	
	private static AppointmentInterface apService;
	
	private static MessageModelInterface mmService;

	private static final SimpleDateFormat SDF = new SimpleDateFormat("hh:mm:ss");

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = SpringContext.getInstance();
		service = context.getBean(UserInterfaceImpl.class);
		cgService = context.getBean(CompanyGroupImplInterface.class);
		apService = context.getBean(AppointmentInterfaceImpl.class);
		mmService = context.getBean(MessageModelService.class);
		System.out.println(" >>>>>> Inicio :: " + SDF.format(new Date()));
	}

	@AfterClass
	public static void setDownAfterClass() {
		System.out.println(" >>>>>> Fim :: " + SDF.format(new Date()));
	}

	@Test
	public void recuperarUsuarioPorMsisdn() {
		String msisdn = "11973008408";

		try {
			User usuario = service.recuperarUsuarioPorMsisdn(msisdn);
			System.out.println(" #### " + usuario);
		} catch (DataException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void recuperarPorPerfilEEmpresa() {
		Company company = new Company();
		company.setId(21L);
		
		boolean printReturn = true;
		
		try {
			User usuario = service.recuperarPorPerfilEEmpresa(ProfileEnum.MASTER, company, true);
			if (printReturn) {
				System.out.println("###\tUsuario: " + usuario);
			}
			
			if (usuario != null) {
				if (printReturn) {
					System.out.println("###\tUsuario.empresa: " + usuario.getCompany());
				}
				
				// Modelos de mensagens
				List<MessageModel> messages = mmService.recuperarMensagensPorIdEmpresa(company.getId());
				if (printReturn) {
					for (MessageModel mm : messages) {
						System.out.println("###\tModelo mensagem: " + mm 
								+ "\n ------> mensagem: " + mm.getTxMessage());
					}
				}
				
				// Grupo Empresa
				for (CompanyGroup cg : usuario.getCompany().getCompanyGroups()) {
					if (printReturn) {
						System.out.println("###\tUsuario.empresa.grupo.empresa: " + cg.getId());
					}
					List<CompanyGroup> cgAux = cgService.recuperarPorGrupoEmpresaId(cg.getId());
					if (printReturn) {
						if (cgAux != null
								&& cgAux.size() > 0) {
							CompanyGroup aux = cgAux.get(0);
							System.out.println(this.printUserGroup(aux));
						}
					}
				}
				
				// Agendas
				List<Appointment> agendas = apService.recuperarAgendasPorIdEmpresa(usuario.getCompany().getId());
				if (printReturn) {
					this.imprimirInformacoesAgendas(agendas);
				}
				
				// Usuários
				if (usuario.getCompany().getUsers() != null) {
					System.out.println("### Total de usuários: " + usuario.getCompany().getUsers().size());
					if (printReturn) {
						for (User user : usuario.getCompany().getUsers()) {
							System.out.println("###\tUsuario: " + user);
						}
					}
				}
				
				if (printReturn) {
					System.out.println("###\tUsuario.perfil: " + usuario.getProfile());
					System.out.println("###\tUsuario.perfil.empresa: " + usuario.getProfile().getCompany());
				}
			}
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	private String printUserGroup(CompanyGroup cg) {
		StringBuilder sb = new StringBuilder();

		if (cg.getUserGroup() != null) {
			System.out.println("\tTamanho lista 2 = " + cg.getUserGroup().size());
		}
		
		for (UserGroup ug : cg.getUserGroup()) {
			sb.append("\n....... " + ug.getCompanyGroup().getGroupName()
					+ "\n....... " + ug.getUser()
					+ "\n....... " + ug.getUser().getCompany()
					+ "\n....... " + ug.getUser().getProfile());
		}

		return sb.toString();
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
	public void recuperarPorPerfilEMsisdn() {
		try {
			User usuario = service.recuperarPorPerfilEMsisdn(ProfileEnum.MASTER, "11973008408", true);
			System.out.println("###\tUsuario: " + usuario);
			System.out.println("###\tUsuario.empresa: " + usuario.getCompany());
			System.out.println("###\tUsuario.perfil: " + usuario.getProfile());
			System.out.println("###\tUsuario.perfil.empresa: " + usuario.getProfile().getCompany());
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void recuperarUsuarioPorMsisdnJoin() {
		String msisdn = "11973008408";

		try {
			User usuario = service.recuperarUsuarioPorMsisdnJoin(msisdn);

			System.out.println(" #### " + usuario);
			System.out.println(" #### " + usuario.getCompany());
			System.out.println(" #### " + usuario.getProfile());
			System.out.println(" #### " + usuario.getProfile().getCompany());
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void recuperarUsuariosPorEmpresa() {
		Company company = new Company();
		company.setId(21L);
		
		User user = new User();
		user.setCompany(company);
		
		List<User> usuarios = service.findAllByCompany(user);
		this.printUsers(usuarios);
	}
	
	@Test
	public void recuperarUsuariosPorEmpresaJoin() {
		Company company = new Company();
		company.setId(21L);
		
		User user = new User();
		user.setCompany(company);
		
		List<User> usuarios = service.findAllByCompanyJoin(user);
		this.printUsers(usuarios);
	}
	
	private void printUsers(List<User> usuarios) {
		System.out.println(" ### Total de registros = " + usuarios.size());
		
		for (User user : usuarios) {
			System.out.println(" >>> " + user);
		}
	}

}