package digit.assignment;


import org.egov.tracer.config.TracerConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@Import({TracerConfiguration.class})
@SpringBootApplication
@ComponentScan(basePackages = {"digit.assignment", "digit.assignment.web.controllers", "digit.assignment.config"})
public class Main {


    public static void main(String[] args) throws Exception {
        SpringApplication.run(Main.class, args);
    }

}
