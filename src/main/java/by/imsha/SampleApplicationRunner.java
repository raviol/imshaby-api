package by.imsha;

import by.imsha.domain.City;
import by.imsha.domain.Mass;
import by.imsha.repository.CityRepository;
import by.imsha.repository.MassRepository;
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

import java.util.Calendar;


//@SpringBootApplication
@ComponentScan(basePackages = {"by.imsha", "com.auth"})

@EnableAutoConfiguration(   )  // Sprint Boot Auto Configuration
@EnableMongoRepositories(
        repositoryFactoryBeanClass = QuerableMongoRepositoryFactoryBean.class
)
@EnableSwagger // auto generation of API docs
@PropertySources({
        @PropertySource("classpath:application.properties"),
        @PropertySource("classpath:auth0.properties")
})
public class SampleApplicationRunner implements CommandLineRunner {

    @Autowired
    private CityRepository repository;

    @Autowired
    private MassRepository massRepo;

    @Override
    public void run(String... args) throws Exception {

        // save a couple of customers
        Mass mass = new Mass();
        mass.setDays(new short[]{1,2,3,4});
        mass.setDuration(60*120);
        mass.setLang("BY");
        mass.setParishId("574360fcccbd5297c86047fd");
        mass.setTime("11:30");
        this.massRepo.save(mass);
  }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SampleApplicationRunner.class, args);
    }

}
