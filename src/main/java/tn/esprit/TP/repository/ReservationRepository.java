package tn.esprit.TP.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.TP.entity.*;


import java.util.List;
import java.util.Optional;


@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {


    @Query("select r from Reservation r join Chambre c on (r member of c.reservations) where c.bloc.idBloc =:idBloc and year(r.anneeUniversitaire) = year(current date) and r.estValide = true  order by r.idReservation limit 1")
    Reservation getForReservation(@Param("idBloc") Long idBloc);




    Optional<Reservation> findByEtudiantsContains(Etudiant etudiant);

    List<Reservation> findByEtudiantsContainingAndEstValideTrue(Etudiant etudiant);


/*
    @Query("SELECT r FROM Reservation r " +
            "JOIN r.etudiants e " +
            "JOIN e.universite u " +
            "WHERE r.anneeUniversitaire = :anneeUniversitaire " +
            "AND u.nomUniversite = :nomUniversite")
    List<Reservation> findByAnneeUniversitaireAndNomUniversite(@Param("anneeUniversitaire") Date anneeUniversitaire,
                                                               @Param("nomUniversite") String nomUniversite);


 */

}