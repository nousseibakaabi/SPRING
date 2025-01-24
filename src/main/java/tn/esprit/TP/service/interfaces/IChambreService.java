package tn.esprit.TP.service.interfaces;

import tn.esprit.TP.entity.Bloc;
import tn.esprit.TP.entity.Chambre;
import tn.esprit.TP.entity.Reservation;
import tn.esprit.TP.entity.TypeChambre;

import java.util.List;

public interface IChambreService {
    // List<Reservation> getReservationsForChambresWithNumberGreaterThan(Long number);

    Chambre create(Chambre chambre);

    Chambre update(Chambre chambre);

    String delete(Long id);

    Chambre readById(Long id);

    List<Chambre> readAll();

    List<Chambre> getChambresParNomUniversite(String nomUniversite);

    List<Chambre> getChambresParBlocEtType(long idBloc, TypeChambre typeC);

    List<Chambre> getChambresParBlocEtTypeKey(long idBloc, TypeChambre typeC);

    void listChambresParBloc();

    void pourcentageChambreParTypeChambre();

    void nbPlacesDisponibleParChambreAnneeEnCours();

    }