package tn.esprit.TP.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.TP.entity.Universite;

import java.util.Optional;


@Repository
public interface UniversiteRepository extends JpaRepository<Universite, Long> {

    Optional<Universite> findByNomUniversite(String nomUniversite);

    boolean existsByNomUniversite(String nomUniversite); //unicite
}
