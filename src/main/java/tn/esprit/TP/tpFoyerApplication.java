package tn.esprit.TP;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
@OpenAPIDefinition(
        info = @Info(
        title = "TP FOYER",
        version = "v1",
        description = "API for TP Foyer Application",
        contact = @Contact(name = "4TWIN7",url = "http://google.com",email ="nousseiba.kaabi@esprit.tn")
        )
)
public class tpFoyerApplication {

    public static void main(String[] args) {

        SpringApplication.run(tpFoyerApplication.class, args);

    }


}