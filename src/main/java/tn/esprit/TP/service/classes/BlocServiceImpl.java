package tn.esprit.TP.service.classes;


import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.NoResultException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.TP.entity.Bloc;
import tn.esprit.TP.entity.Chambre;
import tn.esprit.TP.repository.BlocRepository;
import tn.esprit.TP.repository.ChambreRepository;
import tn.esprit.TP.service.interfaces.IBlocService;

import java.util.List;

@Service
@AllArgsConstructor
public class BlocServiceImpl implements IBlocService {
    @Autowired
    BlocRepository blocRepo;

    @Autowired
    ChambreRepository chambreRepo;


    @Override
    public Bloc readById(Long id) {
        return blocRepo.findById(id).orElse(new Bloc());
    }

    @Override
    public List<Bloc> readAll() {
        return blocRepo.findAll();
    }


    @Override
    public Bloc create(Bloc bloc) {
        return blocRepo.save(bloc);
    }

    @Override
    public Bloc update(Bloc bloc) {
        return blocRepo.save(bloc);
    }


    @Override
    public String delete(Long id) {
        if (blocRepo.existsById(id)) {
            blocRepo.deleteById(id);
            return ("suppression avec succés");
        } else {
            return ("suppression échoue");
        }
    }


    @Override
    public List<Bloc> findBlocsByUniversiteName(String keyword) {
        return blocRepo.findBlocsByFoyerUniversiteNomUniversiteContainingIgnoreCase(keyword);
    }


    public Bloc affecterChambresABloc(List<Long> numChambre, long idBloc) {
        Bloc bloc = blocRepo.findById(idBloc)
                .orElseThrow(() -> new EntityNotFoundException("Bloc not found with id: " + idBloc));

        List<Chambre> chambres = chambreRepo.findByNumeroChambreIn(numChambre);
        if (chambres.size() != numChambre.size()) {
            throw new NoResultException("Some chambres not found in the provided list");
        }

        for (Chambre chambre : chambres) {
            chambre.setBloc(bloc);
        }

        chambreRepo.saveAll(chambres);

        return bloc;
    }




}






