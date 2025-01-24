package tn.esprit.TP.service.classes;


import jakarta.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.TP.entity.Bloc;
import tn.esprit.TP.entity.Foyer;
import tn.esprit.TP.entity.Universite;
import tn.esprit.TP.repository.BlocRepository;
import tn.esprit.TP.repository.FoyerRepository;
import tn.esprit.TP.repository.UniversiteRepository;
import tn.esprit.TP.service.interfaces.IFoyerService;

import java.util.List;

@Service
public class FoyerServiceImpl implements IFoyerService {
    @Autowired
     FoyerRepository foyerRepo;

    @Autowired
    UniversiteRepository universiteRepo;

    @Autowired
    BlocRepository blocRepo;

    @Override
    public Foyer readById(Long id) {
        return foyerRepo.findById(id).orElse(new Foyer());
    }

    @Override
    public List<Foyer> readAll() {
        return foyerRepo.findAll();
    }


    @Override
    public Foyer create(Foyer foyer) {
        return foyerRepo.save(foyer);
    }

    @Override
    public Foyer update(Foyer foyer) {
        return foyerRepo.save(foyer);
    }


    @Override
    public String delete(Long id) {
        if (foyerRepo.existsById(id)) {
            foyerRepo.deleteById(id);
            return("suppression avec succés");
        }else {
            return("suppression échoue");
        }
    }

    @Override
    public Foyer ajouterFoyerEtAffecterAUniversite(Foyer foyer, long idUniversite) {
        // Récupérer l'université par son ID
        Universite universite = universiteRepo.findById(idUniversite)
                .orElseThrow(() -> new NoResultException("Université non trouvée"));

        // Affecter l'université au foyer
        universite.setFoyer(foyer);

        // Lier les blocs au foyer (si les blocs sont dans la liste envoyée)
        if (foyer.getBlocs() != null && !foyer.getBlocs().isEmpty()) {
            for (Bloc bloc : foyer.getBlocs()) {
                // Affecter chaque bloc au foyer
                bloc.setFoyer(foyer);
            }
        }

        // Sauvegarder les blocs, le foyer et l'université
        for (Bloc bloc : foyer.getBlocs()) {
            blocRepo.save(bloc);  // Sauvegarder chaque bloc
        }

        Foyer savedFoyer = foyerRepo.save(foyer); // Sauvegarder le foyer
        universiteRepo.save(universite); // Sauvegarder l'université avec son foyer

        return savedFoyer; // Retourner le foyer sauvegardé
    }
}
