package spring.boot.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import spring.boot.jpa.entity.Customer;
import spring.boot.jpa.repository.CustomerRepository;

@SpringBootApplication
public class InitApplication implements CommandLineRunner {

    @Autowired
    private CustomerRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(InitApplication.class);
    }
    
    @Override
    public void run(String... strings) throws Exception {
    	this.createCustomers();
    	this.getAll();
    	this.getByLastName("BAUER");
    	this.deleteAll();
    }
    
    private void createCustomers() {
        // save a couple of customers
        repository.save(new Customer("Jack", "Bauer"));
        repository.save(new Customer("Chloe", "O'Brian"));
        repository.save(new Customer("Kim", "Bauer"));
        repository.save(new Customer("David", "Palmer"));
        repository.save(new Customer("Michelle", "Dessler"));
    }
    
    private void getAll() {
        // fetch all customers
        System.out.println("Customers found with findAll():");
        System.out.println("-------------------------------");
        for (Customer customer : repository.findAll()) {
            System.out.println(customer);
        }
        System.out.println();
    }
    
    private void getByLastName(String lastName) {
        // fetch customers by last name
        System.out.println("Customer found with findByLastName('" + lastName + "'):");
        System.out.println("--------------------------------------------");
        for (Customer bauer : repository.findByLastName(lastName)) {
            System.out.println(bauer);
        }
    }
    
    private void getById(long id) {
        // fetch an individual customer by ID
        Customer customer = repository.findOne(id);
        System.out.println("Customer found with findOne(" + id + "):");
        System.out.println("--------------------------------");
        System.out.println(customer);
        System.out.println();
    }

    private void deleteAll() {
    	// fetch all customers
        System.out.println("Remove with delete:");
        System.out.println("-------------------------------");
        for (Customer customer : repository.findAll()) {
            System.out.println(" ### Deleting [" + customer + "]....");
            repository.delete(customer);
        }
        System.out.println();
    }
    
}