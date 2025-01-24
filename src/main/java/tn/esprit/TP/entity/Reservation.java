package tn.esprit.TP.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Set;


@Getter
@Setter
//@ToString(exclude = {"idReservation"})
//@EqualsAndHashCode(exclude = {"idReservation"})
//@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder


@Entity
@Table(name = "T_Reservation")

public class Reservation {

    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="idR")
    private String idReservation;

    @Column (name="anneeU")
    private Date anneeUniversitaire;

    @Column(name = "estValide")
    private boolean estValide;




    @ManyToMany (cascade = CascadeType.ALL , fetch = FetchType.LAZY)
     private Set<Etudiant> etudiants ;



}