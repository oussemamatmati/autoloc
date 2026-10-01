package tn.esprit.autoloc.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "equipement")
@Getter
@Setter
@NoArgsConstructor
public class Equipement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEquipement;

    private String libelle;

    // NEW (inverse side, no cascade)
    @ManyToMany(mappedBy = "equipements")
    private Set<Vehicule> vehicules = new HashSet<>();
}