package tn.esprit.TP.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.TP.entity.Etudiant;
import tn.esprit.TP.service.interfaces.IEtudiantService;

import java.util.List;

@Tag(name="Gestion des Etudiants" ,description = "Permet la gestion des etudiants, y compris l'affichage, l'ajout, la mise à jour et la suppression des enregistrements")
@RestController
@AllArgsConstructor
@RequestMapping("api/etudiant")
public class EtudiantController {

    @Autowired
     IEtudiantService etudiantService;

    @Operation(description = "Recuperer la liste des etudiants")
    @GetMapping("get-all")
    public List<Etudiant> readAll() {
        return etudiantService.readAll();
    }


    @Operation(description = "Récupérer un etudiant par id  ")
    @GetMapping("get-by-id/{id}")
    public Etudiant readByIdEtudiant(@PathVariable Long id) {
        return etudiantService.readById(id);
    }


    @Operation(description = "Creation d'un nouveau etudiant")
    @PostMapping("add")
    public Etudiant createEtudiant(@RequestBody Etudiant etudiant) {
        return etudiantService.create(etudiant);
    }


    @Operation(description = "Modification d'un etudiant ")
    @PutMapping("update")
    public Etudiant updateEtudiant(@RequestBody Etudiant etudiant) {
        return etudiantService.update(etudiant);
    }



    @Operation(description = "Suppression d'un etudiant par id  ")
    @DeleteMapping("delete/{id}")
    public String deleteEtudiant(@PathVariable Long id) {
        return etudiantService.delete(id);
    }

}
