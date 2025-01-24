package tn.esprit.TP.service.classes;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.TP.entity.*;
import tn.esprit.TP.repository.BlocRepository;
import tn.esprit.TP.repository.ChambreRepository;
import tn.esprit.TP.repository.EtudiantRepository;
import tn.esprit.TP.repository.ReservationRepository;
import tn.esprit.TP.service.interfaces.IReservationService;

import java.util.*;

@Service
@AllArgsConstructor
public class ReservationServiceImpl implements IReservationService {

    @Autowired
    ReservationRepository reservationRepo;

    @Autowired
    BlocRepository blocRepo;

    @Autowired
    EtudiantRepository etudiantRepo;

    @Autowired
    ChambreRepository chambreRepo;




    @Override
    public Reservation readById(String id) {
        return reservationRepo.findById(Long.valueOf(id)).orElse(new Reservation());
    }

    @Override
    public List<Reservation> readAll() {
        return reservationRepo.findAll();
    }



    @Override
    public Reservation create(Reservation reservation) {
        return reservationRepo.save(reservation);
    }

    @Override
    public Reservation update(Reservation reservation) {
        return reservationRepo.save(reservation);
    }


    @Override
    public Boolean delete(String id) {
        if (reservationRepo.existsById(Long.valueOf(id))) {
            reservationRepo.deleteById(Long.valueOf(id));
            return true;
        } else {
            return false;
        }
    }


    @Override
    public Reservation ajouterReservation(long idBloc, long cinEtudiant) {
        Etudiant e = etudiantRepo.findByCin(cinEtudiant);
        if (e != null) {
            // Fetch the Reservation in a managed state
            Reservation r = reservationRepo.getForReservation(idBloc);
            if (r != null) {
                if (r.getEtudiants() == null) {
                    r.setEtudiants(new HashSet<>());
                }
                r.getEtudiants().add(e);

                // Ensure Chambre is fetched in a managed state
                Chambre c = chambreRepo.FindByIdReservation(r.getIdReservation());
                if (c.getTypeC() == TypeChambre.DOUBLE ||
                        (c.getTypeC() == TypeChambre.TRIPLE && r.getEtudiants().size() == 3)) {
                    r.setEstValide(false);
                }
                r.setAnneeUniversitaire(getCurrentDate());

                // Return the managed and saved reservation
                return reservationRepo.save(r);
            } else {
                // Fetch all rooms in the given Bloc (idBloc)
                List<Chambre> availableRooms = chambreRepo.findAvailableRoomsInBloc(idBloc);
                Chambre selectedRoom = null;

                // Iterate over the rooms and find a suitable one
                for (Chambre room : availableRooms) {
                    if (room.getTypeC() != TypeChambre.SIMPLE) {
                        selectedRoom = room;
                        break;  // Select the first available room that is not SIMPLE
                    }
                }

                if (selectedRoom == null) {
                    // No suitable rooms found
                    throw new RuntimeException("No suitable room available in Bloc " + idBloc);
                }

                // Build a new Reservation
                Reservation r1 = Reservation.builder()
                        .idReservation( selectedRoom.getNumeroChambre() + "-" + selectedRoom.getBloc().getNomBloc()
                                                                        + "-" + Calendar.getInstance().get(Calendar.YEAR))
                        .estValide(true)
                        .build();

                if (r1.getEtudiants() == null) {
                    r1.setEtudiants(new HashSet<>());
                }
                r1.getEtudiants().add(e);
                r1.setAnneeUniversitaire(getCurrentDate());

                // Mark the room as invalid if it is SIMPLE
                if (selectedRoom.getTypeC() == TypeChambre.SIMPLE) {
                    r1.setEstValide(false);
                }

                // Ensure Reservation is added to the managed Chambre
                if (selectedRoom.getReservations() == null) {
                    selectedRoom.setReservations(new HashSet<>());
                }
                selectedRoom.getReservations().add(r1);

                // Save the managed Chambre (which cascades to Reservation)
                chambreRepo.save(selectedRoom);
                return reservationRepo.save(r1);
            }
        }

        // Return an empty Reservation if no student is found
        return new Reservation();
    }


    private Date getCurrentDate() {
        return Calendar.getInstance().getTime(); // Returns the current date as a Date object
    }


/*


    @Override
    public Reservation annulerReservation(long cinEtudiant) {
        // Find the student by CIN
        Etudiant etudiant = etudiantRepo.findByCin(cinEtudiant)
                .orElseThrow(() -> new RuntimeException("Etudiant not found with CIN: " + cinEtudiant));

        // Find all valid reservations associated with this student
        List<Reservation> reservations = reservationRepo.findByEtudiantsContainingAndEstValideTrue(etudiant);

        if (reservations.isEmpty()) {
            throw new RuntimeException("No valid reservation found for CIN: " + cinEtudiant);
        }

        // Iterate over each reservation and cancel it
        for (Reservation reservation : reservations) {
            // Invalidate the reservation
            reservation.setEstValide(false);

            // Dissociate the student from the reservation
            reservation.getEtudiants().remove(etudiant);

            // Update room reservations dynamically
            Chambre chambre = reservation.getChambre();
            if (chambre != null) {
                // Calculate the current capacity based on the room type
                int maxCapacity = chambreRepo.getCapacityByTypeC(String.valueOf(chambre.getTypeC()));

                // Get the current number of valid reservations for the room
                int currentValidReservations = getValidReservationsCount(chambre);

                // Check if freeing up the room makes it available
                if (currentValidReservations < maxCapacity) {
                    // Room has space now, as a reservation was canceled
                    reservation.setChambre(null); // Dissociate room from reservation
                    chambreRepo.save(chambre);   // Persist changes to the room if needed
                }
            }

            // Save the updated reservation
            reservationRepo.save(reservation);
        }

        // Return the canceled reservation (you can return the list if needed)
        return reservations.get(0);  // or handle the case for multiple reservations
    }



 */






}
