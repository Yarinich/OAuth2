package ua.yarynych.myoauth.repository;

import ua.yarynych.myoauth.entity.Client;

import java.util.List;

public interface ClientRepository {

    void saveClient(Client client);

    boolean checkClientById(String client_id);

    boolean checkClientSecret(String client_id, String secret);

    Client findClientById(String client_is);

    List<Client> findAllClients();

}
