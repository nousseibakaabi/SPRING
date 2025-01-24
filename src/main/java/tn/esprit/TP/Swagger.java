package tn.esprit.TP;



import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class Swagger {
    @Bean
    public GroupedOpenApi gestionAllApi(){
        return GroupedOpenApi.builder()
                .group("All Gestion")
                .pathsToMatch("/api/**")
                .build();
    }


    @Bean
    public GroupedOpenApi gestionBlocApi(){
        return GroupedOpenApi.builder()
                .group("Gestion Bloc")
                .pathsToMatch("/api/bloc/**")
                .build();
    }

    @Bean
    public GroupedOpenApi gestionChambreApi(){
        return GroupedOpenApi.builder()
                .group("Gestion Chambre")
                .pathsToMatch("/api/chambre/**")
                .build();
    }

    @Bean
    public GroupedOpenApi gestionEtudiantApi(){
        return GroupedOpenApi.builder()
                .group("Gestion Etudiant")
                .pathsToMatch("/api/etudiant/**")
                .build();
    }

    @Bean
    public GroupedOpenApi gestionFoyerApi(){
        return GroupedOpenApi.builder()
                .group("Gestion Foyer")
                .pathsToMatch("/api/foyer/**")
                .build();
    }


    @Bean
    public GroupedOpenApi gestionReservationApi(){
        return GroupedOpenApi.builder()
                .group("Gestion Reservation")
                .pathsToMatch("/api/reservation/**")
                .build();
    }


    @Bean
    public GroupedOpenApi gestionUniversiteApi(){
        return GroupedOpenApi.builder()
                .group("Gestion Universite")
                .pathsToMatch("/api/universite/**")
                .build();
    }




}