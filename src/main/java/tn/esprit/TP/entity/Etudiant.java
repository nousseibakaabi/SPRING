package tn.esprit.TP.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Set;


@Getter
@Setter
//@ToString(exclude = {"idEtudiant"})
//@EqualsAndHashCode(exclude = {"idEtudiant"})
//@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder


@Entity
@Table(name = "T_Etudiant")

public class Etudiant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEt")
    @Setter(AccessLevel.NONE)
    private long idEtudiant;

    @Column(name = "nomEt", length = 200)
    private String nomEt;

    @Column(name = "preEt", length = 200)
    private String prenomEt;

    @Column(name = "cin")
    private long cin;

    @Column(name = "ecole")
    private String ecole;

    @Column(name = "dateN")
    private Date dateNaissance;



   /* @JsonIgnore
    @EqualsAndHashCode.Exclude

    */
    @ManyToMany(mappedBy = "etudiants" ,cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Reservation> reservations;



}