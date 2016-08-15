package by.imsha;

import by.imsha.domain.City;
import by.imsha.repository.CityRepository;
import by.imsha.repository.factory.QuerableMongoRepositoryFactoryBean;
import com.mangofactory.swagger.plugin.EnableSwagger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
/*
@SpringBootApplication
@ComponentScan(basePackages = {"by.imsha", "com.auth"})

@EnableAutoConfiguration(   )  // Sprint Boot Auto Configuration
@EnableMongoRepositories(
        repositoryFactoryBeanClass = QuerableMongoRepositoryFactoryBean.class
)
@EnableSwagger // auto generation of API docs
@PropertySources({
        @PropertySource("classpath:application.properties"),
        @PropertySource("classpath:auth0.properties")
})*/
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

        System.out.println("Query cities with:");
        this.repository.search(null, null);


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
