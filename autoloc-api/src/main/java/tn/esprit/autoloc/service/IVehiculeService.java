package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Vehicule;

import java.util.List;
import java.util.Optional;

public interface IVehiculeService {
    Vehicule addVehicule(Vehicule vehicule);

    Vehicule updateVehicule(Long id, Vehicule vehicule);

    void deleteVehicule(Long id);

    Optional<Vehicule> getVehiculeById(Long id);

    List<Vehicule> getAllVehicules();
}
