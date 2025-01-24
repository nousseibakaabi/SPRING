package tn.esprit.TP.service.classes;


import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.TP.entity.Bloc;
import tn.esprit.TP.entity.Chambre;
import tn.esprit.TP.entity.TypeChambre;
import tn.esprit.TP.entity.Universite;
import tn.esprit.TP.repository.BlocRepository;
import tn.esprit.TP.repository.ChambreRepository;
import tn.esprit.TP.repository.UniversiteRepository;
import tn.esprit.TP.service.interfaces.IChambreService;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ChambreServiceImpl implements IChambreService {

    @Autowired
    ChambreRepository chambreRepo;

    @Autowired
    UniversiteRepository universiteRepo;

    @Autowired
    BlocRepository blocRepo;


    private static final Logger logger = LoggerFactory.getLogger(ChambreServiceImpl.class);


    /*    @Override
    public List<Reservation> getReservationsForChambresWithNumberGreaterThan(Long number) {
        List<Chambre> chambres = chambreRepo.findByNumeroChambreGreaterThan(number);
        return chambres.stream().flatMap(chambre -> chambre.getReservations().stream()).collect(Collectors.toList());
    }


 */
    @Override
    public List<Chambre> readAll() {
        return chambreRepo.findAll();
    }

    @Override
    public Chambre readById(Long id) {
        return chambreRepo.findById(id).orElse(new Chambre());
    }


    @Override
    public Chambre create(Chambre chambre) {
        return chambreRepo.save(chambre);
    }

    @Override
    public Chambre update(Chambre chambre) {
        return chambreRepo.save(chambre);
    }


    @Override
    public String delete(Long id) {
        if (chambreRepo.existsById(id)) {
            chambreRepo.deleteById(id);
            return ("suppression avec succés");
        } else {
            return ("suppression échoue");
        }
    }

    @Override
    public List<Chambre> getChambresParNomUniversite(String nomUniversite) {
        // Recherche de l'université par son nom
        Universite universite = universiteRepo.findByNomUniversite(nomUniversite)
                .orElseThrow(() -> new NoResultException("Université introuvable avec le nom : " + nomUniversite));

        // Vérifier si l'université a un foyer et des blocs
        if (universite.getFoyer() != null && universite.getFoyer().getBlocs() != null) {
            List<Chambre> chambres = new ArrayList<>();
            // Parcourir les blocs et récupérer les chambres de chaque bloc
            for (Bloc bloc : universite.getFoyer().getBlocs()) {
                chambres.addAll(bloc.getChambres());
            }
            return chambres;
        }

        // Si aucun foyer ou bloc n'est associé, retourner une liste vide
        return new ArrayList<>();
    }


    @Override    // Utilisation de la méthode JPQL pour récupérer les chambres
    public List<Chambre> getChambresParBlocEtType(long idBloc, TypeChambre typeC) {
        return chambreRepo.findChambresByBlocAndType(idBloc, typeC);
    }

    @Override  // Utilisation de la méthode Keyword pour récupérer les chambres
    public List<Chambre> getChambresParBlocEtTypeKey(long idBloc, TypeChambre typeC) {
        return chambreRepo.findByBloc_IdBlocAndTypeC(idBloc, typeC);
    }


    @Override
    @Scheduled(fixedRate = 60000)
    @Transactional
    public void listChambresParBloc() {
        // Retrieve all blocks
        Iterable<Bloc> blocs = blocRepo.findAll();

        // Iterate through blocks and log details
        for (Bloc bloc : blocs) {
            logger.info("Bloc => {} ayant une capacité {}", bloc.getNomBloc(), bloc.getCapaciteBloc());

            // Accessing chambres within an active session
            Set<Chambre> chambres = bloc.getChambres();

            if (chambres.isEmpty()) {
                logger.info("Pas de chambre disponible dans ce bloc");
            } else {
                logger.info("La liste des chambres pour ce bloc:");
                for (Chambre chambre : chambres) {
                    logger.info("NumChambre: {} type: {}", chambre.getNumeroChambre(), chambre.getTypeC());
                }
            }
            logger.info("********************");
        }
}



    @Override
    @Scheduled(fixedRate = 300000)
    @Transactional
    public void pourcentageChambreParTypeChambre() {
        // Fetch all chambres from the repository
        List<Chambre> chambres = chambreRepo.findAll();

        // Calculate the total number of chambres
        int totalChambres = chambres.size();
        logger.info("Nombre total des chambres: {}", totalChambres);

        if (totalChambres == 0) {
            logger.info("Aucune chambre disponible.");
            return;
        }

        // Group chambres by their type and calculate percentages
        Map<TypeChambre, Long> chambresByType = chambres.stream()
                .collect(Collectors.groupingBy(Chambre::getTypeC, Collectors.counting()));

        // Log the percentage for each type of chambre
        for (TypeChambre type : TypeChambre.values()) {
            long count = chambresByType.getOrDefault(type, 0L);
            double percentage = (double) count / totalChambres * 100;
            logger.info("Le pourcentage des chambres pour le type {} est égal à {}%", type, String.format("%.1f", percentage));
        }
    }

    @Override
    @Scheduled(fixedRate = 300000)
    @Transactional// 5 minutes interval
    public void nbPlacesDisponibleParChambreAnneeEnCours() {
        // Fetch all chambres from the repository
        List<Chambre> chambres = chambreRepo.findAll();

        if (chambres.isEmpty()) {
            logger.info("Aucune chambre disponible.");
            return;
        }

        // Iterate through each chambre
        for (Chambre chambre : chambres) {
            // Define capacity directly based on the chambre type
            int totalCapacity = 0;

            // Determine capacity based on the room's type
            switch (chambre.getTypeC()) {
                case SIMPLE:
                    totalCapacity = 1;
                    break;
                case DOUBLE:
                    totalCapacity = 2;
                    break;
                case TRIPLE:
                    totalCapacity = 3;
                    break;

                // Add other room types as needed
                default:
                    logger.warn("Type de chambre inconnu: {}", chambre.getTypeC());
                    continue; // Skip unknown room types
            }

            // Get the current number of reservations for the room (assuming reservations are a collection of related entities)
            int currentReservations = chambre.getReservations().size();

            // Calculate available spots
            int availableSpots = totalCapacity - currentReservations;

            // Log the availability
            if (availableSpots <= 0) {
                logger.info("La chambre {} {} est complète", chambre.getTypeC(), chambre.getNumeroChambre());
            } else {
                logger.info("Le nombre de places disponibles pour la chambre {} {} est {}", chambre.getTypeC(), chambre.getNumeroChambre(), availableSpots);
            }
        }
    }



}



