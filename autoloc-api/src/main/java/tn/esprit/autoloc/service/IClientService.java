package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Client;

import java.util.List;
import java.util.Optional;

public interface IClientService {
    Client addClient(Client client);

    Client updateClient(Long id, Client client);

    void deleteClient(Long id);

    Optional<Client> getClientById(Long id);

    List<Client> getAllClients();
}
