package br.com.exemplo.persistencia.transacao;

import java.util.Date;
import java.util.HashSet;

import org.apache.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import br.com.exemplo.persistencia.transacao.model.Address;
import br.com.exemplo.persistencia.transacao.model.Audit;
import br.com.exemplo.persistencia.transacao.model.Person;
import br.com.exemplo.persistencia.transacao.service.AuditServiceManager;
import br.com.exemplo.persistencia.transacao.service.PersonServiceManager;

@SpringBootApplication
public class SpringBootPersistenciaTransacaoApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringBootPersistenciaTransacaoApplication.class,
				args);
	}
}

@Component
class DemoData {

	private static final Logger LOGGER = Logger.getLogger(DemoData.class);
	
	@Bean
	public CommandLineRunner setDemoData(final PersonServiceManager s,
			final AuditServiceManager a) {
		
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
				// Remove registros existentes
				a.deleteAllAudit();
				s.deleteAllPerson();
				
				// Cria novos registros
				Person person = new Person();
				person.setAddresses(new HashSet<Address>());
				person.setCreationDate(new Date());
				person.setEmail("email-address@domain.com");
				person.setName("dummy name");

				for (int index = 0; index < 2; index++) {
					Address address = new Address();
					address.setNumber(String.valueOf(index));
					address.setStreet("street name - " + index);
					address.setPerson(person);

					person.getAddresses().add(address);
				}

				try {
					s.savePerson(person);					
				} catch (Exception ex) {
					LOGGER.error(ex.getMessage(), ex);
				} finally {
					LOGGER.debug("=====================");
					LOGGER.debug("###### Audits ######");
					LOGGER.debug("=====================");
					
					for (Audit audit : a.getAudits()) {
						LOGGER.debug(" -> " + audit);
					}
				}
			}
		};
	}

}