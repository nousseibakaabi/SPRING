package tn.esprit.TP.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.TP.entity.Chambre;
import tn.esprit.TP.entity.TypeChambre;
import tn.esprit.TP.service.interfaces.IChambreService;

import java.util.List;

@Tag(name="Gestion des Chambres" ,description = "Permet la gestion des chambres, y compris l'affichage, l'ajout, la mise à jour et la suppression des enregistrements")
@RestController
@AllArgsConstructor
@RequestMapping("api/chambre")
public class ChambreController {

    @Autowired
     IChambreService chambreService;

   /* @GetMapping("/reservations/above/{number}")
    public List<Reservation> getReservationsAboveNumber(@PathVariable Long number) {
        return chambreService.getReservationsForChambresWithNumberGreaterThan(number);
    }


    */
   @Operation(description = "Recuperer la liste des chambres ")
    @GetMapping("get-all")
    public List<Chambre> readAll() {
        return chambreService.readAll();
    }

    @Operation(description = "Récupérer une chambre par id  ")

    @GetMapping("get-by-id/{id}")
    public Chambre readById(@PathVariable Long id) {
        return chambreService.readById(id);
    }

    @Operation(description = "Creation d'une nouvelle chambre")
    @PostMapping("add")
    public Chambre createChambre(@RequestBody Chambre chambre) {
        return chambreService.create(chambre);
    }

    @Operation(description = "Modification d'une chambre ")
    @PutMapping("update")
    public Chambre updateChambre(@RequestBody Chambre chambre) {
        return chambreService.update(chambre);
    }

    @Operation(description = "Suppression d'une chambre par id  ")
    @DeleteMapping("delete/{id}")
    public String deleteChambre(@PathVariable Long id) {
        return chambreService.delete(id);
    }


    @Operation(description = "Récupérer les chambres d'une université spécifique par son nom")
    @GetMapping("/get-by-nom-universite")
    public List<Chambre> getChambresParNomUniversite(@RequestParam String nomUniversite) {
        return chambreService.getChambresParNomUniversite(nomUniversite);
    }

    @Operation(description = "Récupérer les chambres d'un bloc selon leur type (jpql)")
    @GetMapping("/get-by-bloc-and-type")
    public List<Chambre> getChambresParBlocEtType(@RequestParam long idBloc, @RequestParam TypeChambre typeC) {
        return chambreService.getChambresParBlocEtType(idBloc, typeC);
    }

    @Operation(description = "Récupérer les chambres d'un bloc selon leur type (keyword)")
    @GetMapping("/get-by-bloc-and-type-key")
    public List<Chambre> getChambresParBlocEtTypeKey(@RequestParam long idBloc, @RequestParam TypeChambre typeC) {
        return chambreService.getChambresParBlocEtTypeKey(idBloc, typeC);
    }


}
