package tn.esprit.TP.service.interfaces;

import tn.esprit.TP.entity.Reservation;

import java.util.List;

public interface IReservationService {
    Reservation create(Reservation reservation);
    Reservation update(Reservation reservation);
    Boolean delete(String id);
    Reservation readById(String id);
    List<Reservation> readAll();

    Reservation ajouterReservation (long idBloc, long cinEtudiant) ;

//    Reservation annulerReservation(long cinEtudiant);

    //List<Reservation> getReservationParAnneeUniversitaireEtNomUniversite(Date anneeUniversitaire, String nomUniversite);


}
