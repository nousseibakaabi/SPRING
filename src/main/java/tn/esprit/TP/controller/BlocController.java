package tn.esprit.TP.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.TP.entity.Bloc;
import tn.esprit.TP.service.interfaces.IBlocService;

import java.util.List;

@Tag(name="Gestion des Blocs" ,description = "Permet la gestion des blocs, y compris l'affichage, l'ajout, la mise à jour et la suppression des enregistrements")
@RestController
@AllArgsConstructor
@RequestMapping("api/bloc")
public class BlocController {

    /*@Autowired
    //@Qualifier("blocService2")
    private IBlocService blocServiceImpl; //blocServiceImpl2
     */
        @Autowired
         IBlocService blocService;

        @Operation(description = "Recuperer la liste des blocs ")
    @GetMapping("get-all")
    public List<Bloc> readAll() {
        return blocService.readAll();
    }
    @Operation(description = "Récupérer un bloc par id  ")
    @GetMapping("get-by-id/{id}")
    public Bloc readById(@PathVariable Long id) {
        return blocService.readById(id);
    }

    @Operation(description = "Creation d'un nouveau bloc")
    @PostMapping("add")
    public Bloc createBloc(@RequestBody Bloc bloc) {
        return blocService.create(bloc);
    }

    @Operation(description = "Modification d'un bloc ")
    @PutMapping("update")
    public Bloc updateBloc(@RequestBody Bloc bloc) {
        return blocService.update(bloc);
    }

    @Operation(description = "Suppression d'un bloc par id  ")
    @DeleteMapping("delete/{id}")
    public String deleteBloc(@PathVariable Long id) {
        return blocService.delete(id);
    }

    @Operation(description = "Recherche des blocs par le nom d'universite ")
    @GetMapping("search-by-keyword")
    public List<Bloc> findBlocsByUniName(@RequestParam String keyword) {
        return blocService.findBlocsByUniversiteName(keyword);
    }


    @Operation(description = "Assign selected chambres to a bloc")
    @PutMapping("/affecter-chambres")
    public Bloc affecterChambresABloc(@RequestBody List<Long> numChambre, @RequestParam long idBloc) {
        return blocService.affecterChambresABloc(numChambre, idBloc);
    }




}





