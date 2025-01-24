package tn.esprit.TP.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

/*
@Getter
@Setter
@ToString(exclude = {"idBloc", "nomBloc"})
@EqualsAndHashCode(exclude = {"idBloc"})
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
*/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "T_Bloc")

public class Bloc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idB")
    private long idBloc;

    @Column(name = "nameB", length = 200)
    private String nomBloc;

    @Column(name = "capaciteB")
    private long capaciteBloc;


    /*@JsonIgnore
    @EqualsAndHashCode.Exclude

     */
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Foyer foyer;


    @OneToMany(mappedBy = "bloc", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Chambre> chambres;



}