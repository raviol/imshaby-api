package by.imsha;

import by.imsha.domain.City;
import by.imsha.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SampleApplicationRunner implements CommandLineRunner {

    @Autowired
    private CityRepository repository;

    @Override
    public void run(String... args) throws Exception {
        this.repository.deleteAll();

        // save a couple of customers
        this.repository.save(new City("Smith"));
        this.repository.save(new City("Alice"));

        // fetch all customers
        System.out.println("City found with findAll():");
        System.out.println("-------------------------------");
        for (City city : this.repository.findAll()) {
            System.out.println(city);
        }
        System.out.println();

        // fetch an individual customer
        System.out.println("Customer found with findByName('Alice'):");
        System.out.println("--------------------------------");
        System.out.println(this.repository.findByName("Alice"));

        System.out.println("Customers found with findByName('Smith'):");
        System.out.println("--------------------------------");
        System.out.println(this.repository.findByName("Smith"));
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SampleApplicationRunner.class, args);
    }

}
