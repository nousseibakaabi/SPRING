package tn.esprit.TP.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.TP.entity.Reservation;
import tn.esprit.TP.service.interfaces.IReservationService;

import java.util.List;

@Tag(name="Gestion des Reservations",description = "Permet la gestion des reservations, y compris l'affichage, l'ajout, la mise à jour et la suppression des enregistrements")
@RestController
@AllArgsConstructor
@RequestMapping("api/reservation")
public class ReservationController {

    @Autowired
     IReservationService reservationService;


    @Operation(description = "Recuperer la liste des reservations ")
    @GetMapping("get-all")
    public List<Reservation> readAll() {
        return reservationService.readAll();
    }


    @Operation(description = "Récupérer une reservation par id  ")
    @GetMapping("get-by-id/{id}")
    public Reservation readById(@PathVariable String id) {
        return reservationService.readById(id);
    }



    @Operation(description = "Creation d'une nouvelle reservation")
    @PostMapping("add")
    public Reservation createReservation(@RequestBody Reservation reservation) {
        return reservationService.create(reservation);
    }



    @Operation(description = "Modification d'une reservation ")
    @PutMapping("update")
    public Reservation updateReservation(@RequestBody Reservation reservation) {
        return reservationService.update(reservation);
    }


    @Operation(description = "Suppression d'une reservation par id  ")
    @DeleteMapping("delete/{id}")
    public Boolean deleteReservation(@PathVariable String id) {
        return reservationService.delete(id);
    }

    @Operation(description = "Creation d'une reservation  et l’affecter à la fois à une chambre à un étudiant donné")
    @PostMapping("/addReservation")
    public Reservation ajouterReservation(@RequestParam long idBloc, @RequestParam long cinEtudiant) {
        return reservationService.ajouterReservation(idBloc, cinEtudiant);
    }

   /* @Operation(description = "Annuler une réservation d'un étudiant donné par son CIN")
    @PutMapping("/cancelReservation")
    public Reservation annulerReservation(@RequestParam long cinEtudiant) {
        return reservationService.annulerReservation(cinEtudiant);
    }

    */











}
