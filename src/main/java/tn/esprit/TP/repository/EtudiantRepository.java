package tn.esprit.TP.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.TP.entity.Etudiant;
import tn.esprit.TP.entity.Reservation;


@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {

    Etudiant findByCin(long cinEtudiant);


}
