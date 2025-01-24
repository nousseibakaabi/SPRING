package tn.esprit.TP.service.interfaces;

import tn.esprit.TP.entity.Foyer;

import java.util.List;

public interface IFoyerService {
    Foyer create(Foyer foyer);
    Foyer update(Foyer foyer);
    String delete(Long id);
    Foyer readById(Long id);
    List<Foyer> readAll();
    Foyer ajouterFoyerEtAffecterAUniversite(Foyer foyer, long idUniversite);
}
