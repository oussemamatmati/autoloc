package tn.esprit.autoloc.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "agence")
@Getter
@Setter
@NoArgsConstructor
public class Agence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAgence;

    private String nom;
    private String ville;
    private String adresse;
    private String telephone;

    // NEW
    @OneToMany(mappedBy = "agence")
    private List<Employe> employes = new ArrayList<>();

    // NEW
    @OneToMany(mappedBy = "agence")
    private List<Vehicule> vehicules = new ArrayList<>();

    // NEW
    public void addEmploye(Employe e) {
        employes.add(e);
        e.setAgence(this);
    }

    // NEW
    public void addVehicule(Vehicule v) {
        vehicules.add(v);
        v.setAgence(this);
    }
}