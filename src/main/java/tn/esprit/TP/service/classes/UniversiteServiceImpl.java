package tn.esprit.TP.service.classes;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.TP.entity.Chambre;
import tn.esprit.TP.entity.Foyer;
import tn.esprit.TP.entity.Universite;
import tn.esprit.TP.repository.FoyerRepository;
import tn.esprit.TP.repository.UniversiteRepository;
import tn.esprit.TP.service.interfaces.IUniversiteService;

import java.util.List;
import java.util.Optional;

@Service
public class UniversiteServiceImpl implements IUniversiteService {

    @Autowired
     UniversiteRepository universiteRepo;

    @Autowired
    FoyerRepository foyerRepo;


    @Override
    public Universite readById(Long id) {
        return universiteRepo.findById(id).orElse(new Universite());
    }

    @Override
    public List<Universite> readAll() {
        return universiteRepo.findAll();
    }


    @Override
    public Universite create(Universite universite) {

        if (universiteRepo.existsByNomUniversite(universite.getNomUniversite())) {
            throw new IllegalArgumentException("Une université avec ce nom existe déjà.");
        }

        // Enregistrer la nouvelle université
        return universiteRepo.save(universite);
    }

    @Override
    public Universite update(Universite universite) {
        return universiteRepo.save(universite);
    }


    @Override
    public String delete(Long id) {
        if (universiteRepo.existsById(id)) {
            universiteRepo.deleteById(id);
            return("suppression avec succés");
        }else {
            return("suppression échoue");
        }
    }

    @Override
    public Universite affecterFoyerAUniversite(long idFoyer, String nomUniversite) {
        Optional<Universite> universiteOpt = universiteRepo.findByNomUniversite(nomUniversite);
        Optional<Foyer> foyerOpt = foyerRepo.findById(idFoyer);

        if (universiteOpt.isPresent() && foyerOpt.isPresent()) {
            Universite universite = universiteOpt.get();
            Foyer foyer = foyerOpt.get();

            // Set the foyer for the universite
            universite.setFoyer(foyer);

            // Also, set the universite reference in foyer (bidirectional relationship)
            foyer.setUniversite(universite);

            // Save changes
            universiteRepo.save(universite);
            return universite;
        } else {
            throw new EntityNotFoundException("Foyer or Universite not found");
        }
    }


    @Override
    public Universite desaffecterFoyerAUniversite(long idUniversite) {
        Optional<Universite> universiteOpt = universiteRepo.findById(idUniversite);
        if (universiteOpt.isPresent()) {
            Universite universite = universiteOpt.get();
            Foyer foyer = universite.getFoyer();
            if (foyer != null) {
                // Remove the reference to Universite in Foyer (for bidirectional consistency)
                foyer.setUniversite(null);
            }
            // Remove the Foyer association from Universite
            universite.setFoyer(null);

            // Save changes to Universite (and Foyer if necessary)
            universiteRepo.save(universite);
            if (foyer != null) {
                foyerRepo.save(foyer); // Ensure changes in Foyer are persisted
            }
            return universite;
        }
        throw new EntityNotFoundException("Universite not found");
    }



}


