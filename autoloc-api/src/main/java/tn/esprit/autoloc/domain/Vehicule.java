package tn.esprit.autoloc.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "vehicule")
@Getter
@Setter
@NoArgsConstructor
public class Vehicule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVehicule;

    @Column(nullable = false, unique = true, length = 20)
    private String immatriculation;

    @Column(nullable = false, length = 50)
    private String marque;

    @Column(nullable = false, length = 50)
    private String modele;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private CategorieVehicule categorie;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal tarifJournalier;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private StatutVehicule statut;

    // NEW: Agence 1 - * Vehicule (owning side)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "agence_id", nullable = false)
    private Agence agence;

    // NEW: Vehicule 1 - * Maintenance
    @OneToMany(mappedBy = "vehicule", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Maintenance> maintenances = new ArrayList<>();

    // NEW: Vehicule * - * Equipement (owning side, creates the join table)
    @ManyToMany
    @JoinTable(
            name = "vehicule_equipement",
            joinColumns = @JoinColumn(name = "vehicule_id"),
            inverseJoinColumns = @JoinColumn(name = "equipement_id"))
    private Set<Equipement> equipements = new HashSet<>();

    // NEW: Vehicule 1 - * Reservation
    @OneToMany(mappedBy = "vehicule")
    private List<Reservation> reservations = new ArrayList<>();

    // NEW
    public void addMaintenance(Maintenance m) {
        maintenances.add(m);
        m.setVehicule(this);
    }

    // NEW
    public void addEquipement(Equipement e) {
        equipements.add(e);
        e.getVehicules().add(this);
    }

    // NEW
    public void removeEquipement(Equipement e) {
        equipements.remove(e);
        e.getVehicules().remove(this);
    }
}