package tn.esprit.autoloc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.repository.IVehiculeRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class VehiculeServiceImpl implements IVehiculeService {

    private final IVehiculeRepository vehiculeRepository;

    @Override
    public Vehicule addVehicule(Vehicule vehicule) {
        return vehiculeRepository.save(vehicule);
    }

    @Override
    public Vehicule updateVehicule(Long id, Vehicule vehicule) {
        Optional<Vehicule> existingVehicule = vehiculeRepository.findById(id);
        if (existingVehicule.isEmpty()) {
            throw new IllegalArgumentException("Véhicule introuvable avec l'id : " + id);
        }

        Vehicule vehiculeToUpdate = existingVehicule.get();
        vehiculeToUpdate.setImmatriculation(vehicule.getImmatriculation());
        vehiculeToUpdate.setMarque(vehicule.getMarque());
        vehiculeToUpdate.setModele(vehicule.getModele());
        vehiculeToUpdate.setCategorie(vehicule.getCategorie());
        vehiculeToUpdate.setTarifJournalier(vehicule.getTarifJournalier());
        vehiculeToUpdate.setStatut(vehicule.getStatut());
        vehiculeToUpdate.setAgence(vehicule.getAgence());

        return vehiculeRepository.save(vehiculeToUpdate);
    }

    @Override
    public void deleteVehicule(Long id) {
        if (!vehiculeRepository.existsById(id)) {
            throw new IllegalArgumentException("Véhicule introuvable avec l'id : " + id);
        }
        vehiculeRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Vehicule> getVehiculeById(Long id) {
        return vehiculeRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Vehicule> getAllVehicules() {
        return vehiculeRepository.findAll();
    }
}
