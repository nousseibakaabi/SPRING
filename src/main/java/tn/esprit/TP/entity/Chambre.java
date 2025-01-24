package tn.esprit.TP.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
//@ToString(exclude = {"idChambre"})
//@EqualsAndHashCode(exclude = {"idChambre"})
//@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder


@Entity
@Table(name = "T_Chambre")

public class Chambre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment for ID
    @Column(name = "idC")
    private long idChambre;

    @Column (name="numeroC")
    private long numeroChambre;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_chambre")
    private TypeChambre typeC;


    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Bloc bloc;



    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Reservation> reservations ;



}