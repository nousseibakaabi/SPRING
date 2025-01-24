package tn.esprit.TP.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.TP.entity.Foyer;


@Repository
public interface FoyerRepository extends JpaRepository<Foyer, Long> {
}
