package tn.esprit.TP.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.TP.entity.Bloc;
import java.util.List;


@Repository
public interface BlocRepository extends JpaRepository <Bloc, Long> {

    /*
    @Query("SELECT b FROM Bloc b WHERE b.foyer.universite.nomUniversite LIKE %:keyword%")
    List<Bloc> findBlocsByUniversiteName(@Param("keyword") String keyword);
     */

    List<Bloc> findBlocsByFoyerUniversiteNomUniversiteContainingIgnoreCase(String keyword);

}
