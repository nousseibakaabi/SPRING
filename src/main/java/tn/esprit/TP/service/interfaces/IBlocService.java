package tn.esprit.TP.service.interfaces;

import tn.esprit.TP.entity.Bloc;

import java.util.List;

public interface IBlocService {

    Bloc create(Bloc bloc);
    Bloc update(Bloc bloc);
    String delete(Long id);
    Bloc readById(Long id);
    List<Bloc> readAll();
    List<Bloc> findBlocsByUniversiteName(String keyword);
    Bloc affecterChambresABloc(List<Long> numChambre, long idBloc);

}


