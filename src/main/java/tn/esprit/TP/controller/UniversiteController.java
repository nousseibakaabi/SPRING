package tn.esprit.TP.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.TP.entity.Universite;
import tn.esprit.TP.service.interfaces.IUniversiteService;

import java.util.List;

@Tag(name="Gestion des Universites",description = "Permet la gestion des universites, y compris l'affichage, l'ajout, la mise à jour et la suppression des enregistrements")
@RestController
@AllArgsConstructor
@RequestMapping("api/universite")
public class UniversiteController {

    @Autowired
     IUniversiteService universiteService;


    @Operation(description = "Recuperer la liste des universites ")
    @GetMapping("get-all")
    public List<Universite> readAll() {
        return universiteService.readAll();
    }

    @Operation(description = "Récupérer une universite par id  ")
    @GetMapping("get-by-id/{id}")
    public Universite readById(@PathVariable Long id) {
        return universiteService.readById(id);
    }


    @Operation(description = "Creation d'une nouvelle universite ")
    @PostMapping("add")
    public Universite createUniversite(@RequestBody Universite universite) {
        return universiteService.create(universite);
    }


    @Operation(description = "Modification d'une universite ")
    @PutMapping("update")
    public Universite updateUniversite(@RequestBody Universite universite) {
        return universiteService.update(universite);
    }


    @Operation(description = "Suppression d'une universite par id  ")
    @DeleteMapping("delete/{id}")
    public String deleteUniversite(@PathVariable Long id) {
        return universiteService.delete(id);
    }

    @Operation(description = "Affectation d’un Foyer à une Universite  ")
    @PutMapping("/affecterFoyer")
    public Universite affecterFoyerAUniversite(@RequestParam long idFoyer, @RequestParam String nomUniversite) {
        return universiteService.affecterFoyerAUniversite(idFoyer, nomUniversite);
    }

    @Operation(description = "Desaffectation d’un Foyer d'une Universite  ")
    @PutMapping("/desaffecterFoyer")
    public Universite desaffecterFoyerAUniversite(@RequestParam long idUniversite){
        return universiteService.desaffecterFoyerAUniversite(idUniversite);
    }
}
