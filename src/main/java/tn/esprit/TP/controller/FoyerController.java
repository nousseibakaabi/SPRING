package tn.esprit.TP.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.TP.entity.Foyer;
import tn.esprit.TP.service.interfaces.IFoyerService;

import java.util.List;

@Tag(name="Gestion des Foyers ",description = "Permet la gestion des foyers, y compris l'affichage, l'ajout, la mise à jour et la suppression des enregistrements")
@RestController
@AllArgsConstructor
@RequestMapping("api/foyer")
public class FoyerController {

    @Autowired
     IFoyerService foyerService;

    @Operation(description = "Recuperer la liste des foyer ")
    @GetMapping("get-all")
    public List<Foyer> readAll() {
        return foyerService.readAll();
    }


    @Operation(description = "Récupérer un foyer par id  ")
    @GetMapping("get-by-id/{id}")
    public Foyer readById(@PathVariable Long id) {
        return foyerService.readById(id);
    }


    @Operation(description = "Creation d'un nouveau foyer")
    @PostMapping("add")
    public Foyer createFoyer(@RequestBody Foyer foyer) {
        return foyerService.create(foyer);
    }


    @Operation(description = "Modification d'un foyer ")
    @PutMapping("update")
    public Foyer updateFoyer(@RequestBody Foyer foyer) {
        return foyerService.update(foyer);
    }

    @Operation(description = "Suppression d'un foyer par id  ")
    @DeleteMapping("delete/{id}")
    public String deleteFoyer(@PathVariable Long id) {
        return foyerService.delete(id);
    }


    @Operation(description="Ajouter un foyer avec ses blocs et l'affecter à une université")
    @PostMapping("/ajouter-et-affecter")
    public Foyer ajouterFoyerEtAffecterAUniversite(@RequestBody Foyer foyer, @RequestParam long idUniversite) {
        return foyerService.ajouterFoyerEtAffecterAUniversite(foyer, idUniversite);
    }


}
