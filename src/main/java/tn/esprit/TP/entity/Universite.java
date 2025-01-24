package tn.esprit.TP.entity;

import jakarta.persistence.*;
import lombok.*;

//@ToString(exclude = {"idUniversite"})
//@ToString(includeFieldNames = false)
//@EqualsAndHashCode(exclude = {"idUniversite"})
//@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "T_Universite")

public class Universite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@ToString.Exclude
    //@EqualsAndHashCode.Exclude
    @Column(name = "idU")
    private long idUniversite;

    @Column (name="nameU",length = 200 , unique = true, nullable = false)
    private String nomUniversite;

    @Column(name = "adresseU",length = 200)
    private String adresse;



    @OneToOne( fetch = FetchType.EAGER , cascade = CascadeType.ALL)
    private Foyer foyer;



}