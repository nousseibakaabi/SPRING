package tn.esprit.TP.service.classes;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.TP.entity.Etudiant;
import tn.esprit.TP.repository.EtudiantRepository;
import tn.esprit.TP.service.interfaces.IEtudiantService;

import java.util.List;

@Service
@AllArgsConstructor
public class EtudiantServiceImpl implements IEtudiantService {

    @Autowired
     EtudiantRepository etudiantRepo;

    @Override
    public Etudiant readById(Long id) {
        return etudiantRepo.findById(id).orElse(new Etudiant());
    }

    @Override
    public List<Etudiant> readAll() {
        return etudiantRepo.findAll();
    }


    @Override
    public Etudiant create(Etudiant etudiant) {
        return etudiantRepo.save(etudiant);
    }

    @Override
    public Etudiant update(Etudiant etudiant) {
        return etudiantRepo.save(etudiant);
    }


    @Override
    public String delete(Long id) {
        if (etudiantRepo.existsById(id)) {
            etudiantRepo.deleteById(id);
            return("suppression avec succés");
        }else {
            return("suppression échoue");
        }
    }


}
