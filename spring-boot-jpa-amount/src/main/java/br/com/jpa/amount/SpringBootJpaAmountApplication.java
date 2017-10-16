package br.com.jpa.amount;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.com.jpa.amount.model.Many;
import br.com.jpa.amount.model.One;
import br.com.jpa.amount.model.Person;
import br.com.jpa.amount.service.PersonService;

@SpringBootApplication
public class SpringBootJpaAmountApplication {

	private static final int MAX = 1000, PARTIAL = 10;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootJpaAmountApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner setInitialData(final PersonService personService) {
		return new CommandLineRunner() {
			
			@Override
			public void run(String... arg0) throws Exception {
				System.out.println("Removing existing data...");
				
				try {
					personService.deleteAll();
				} catch (Exception ex) {
					ex.printStackTrace();
					System.exit(1);
				}
				
				System.out.println("Setting default data...");
				
				Random random = new Random();

				List<Person> persons = new ArrayList<Person>();
				
				for (int index = 0; index < MAX; index++) {
					Person person = new Person("name-" + index, new Date(), 
							(index % 2 == 0 ? true : false));
					
					One one = new One("desc-one-" + index, 
							new Date(), new Date(System.nanoTime()));
					one.setPerson(person);
					
					List<Many> manies = new ArrayList<Many>();
					
					for (int count = 0; count < PARTIAL; count++) {
						Many many = new Many("desc-many-" + count + random.nextFloat(), 
								random.nextLong());
						many.setPerson(person);
						manies.add(many);
					}
					
					person.setOne(one);
					person.setManies(manies);
					
					persons.add(person);
				}
				
				try {
					
					for (Person p : persons) {
						personService.save(p);
					}
					
					/*System.out.println("\n\n");
					
					for (Person pp : personService.getPersonWithChilds()) {
						System.out.println("\n" + pp);
						System.out.println("\n" + pp.getOne());
						for (Many many : pp.getManies()) {
							System.out.println("\n" + many);
						}
						System.out.println("-----------------------");
					}
					
					System.out.println("\n\n");*/
					
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				
			}
		};
	}
	
}
