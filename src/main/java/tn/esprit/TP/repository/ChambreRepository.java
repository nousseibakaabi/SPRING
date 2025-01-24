package tn.esprit.TP.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.TP.entity.Chambre;
import tn.esprit.TP.entity.Reservation;
import tn.esprit.TP.entity.TypeChambre;

import java.util.List;


@Repository
public interface ChambreRepository extends JpaRepository<Chambre, Long> {

    List<Chambre> findByNumeroChambreGreaterThan(Long numeroChambre);

    List<Chambre> findByNumeroChambreIn(List<Long> numChambre);

    // Recherche par bloc et type de chambre avec JPQL
    @Query("SELECT c FROM Chambre c WHERE c.bloc.idBloc = :idBloc AND c.typeC = :typeC")
    List<Chambre> findChambresByBlocAndType(@Param("idBloc") long idBloc, @Param("typeC") TypeChambre typeC);


    @Query(value = "SELECT c from Chambre c join Reservation r on (r not member of c.reservations ) where (c.bloc.idBloc =:idBloc and year (r.anneeUniversitaire) = year(current date )) order by c.idChambre limit 1 ")
    Chambre getChambreForReservation(Long idBloc);


    @Query("select c from Chambre c join Reservation r on (r member of c.reservations) where r.idReservation =:idReservation")
    Chambre FindByIdReservation(@Param("idReservation") String idReservation);


    @Query("SELECT c FROM Chambre c WHERE c.bloc.idBloc = :idBloc AND size(c.reservations) = 0")
    List<Chambre> findAvailableRoomsInBloc(long idBloc);


























    // Recherche par bloc et type de chambre en utilisant Spring Data Keywords
    List<Chambre> findByBloc_IdBlocAndTypeC(long idBloc, TypeChambre typeChambre);




    @Query("SELECT CASE WHEN c.typeC = 'simple' THEN 1 WHEN c.typeC = 'double' THEN 2 WHEN c.typeC = 'triple' THEN 3 ELSE 0 END FROM Chambre c WHERE c.typeC = :typeC")
    int getCapacityByTypeC(@Param("typeC") String typeC);



}
