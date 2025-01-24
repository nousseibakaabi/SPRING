package tn.esprit.TP.service.interfaces;

import tn.esprit.TP.entity.Universite;

import java.util.List;

public interface IUniversiteService {
    Universite create(Universite universite);
    Universite update(Universite universite);
    String delete(Long id);
    Universite readById(Long id);
    List<Universite> readAll();
    Universite affecterFoyerAUniversite (long idFoyer, String nomUniversite) ;
    Universite desaffecterFoyerAUniversite(long idFoyer);
}
