package tn.esprit.TP.service.interfaces;

import tn.esprit.TP.entity.Etudiant;

import java.util.List;

public interface IEtudiantService {
    Etudiant create(Etudiant etudiant);
    Etudiant update(Etudiant etudiant);
    String delete(Long id);
    Etudiant readById(Long id);
    List<Etudiant> readAll();
}
