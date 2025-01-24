package tn.esprit.TP.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;


//@ToString(exclude = {"idFoyer"})
//@EqualsAndHashCode(exclude = {"idFoyer"})
//@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "T_Foyer")

public class Foyer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idF")
    private long idFoyer;


    @Column(name = "nameF", length = 200)
    private String nomFoyer;

    @Column(name = "capaciteF")
    private long capaciteFoyer;



    @OneToOne(mappedBy = "foyer")
    private Universite universite;


    @OneToMany(mappedBy = "foyer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Bloc> blocs  ;
}