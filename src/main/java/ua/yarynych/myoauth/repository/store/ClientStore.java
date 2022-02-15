package ua.yarynych.myoauth.repository.store;

import org.springframework.stereotype.Service;
import ua.yarynych.myoauth.entity.Client;
import ua.yarynych.myoauth.repository.ClientRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientStore implements ClientRepository {

    private final List<Client> clients = new ArrayList<>();


    public ClientStore(){
    }


    public void setStartValue() {
        if(clients.isEmpty()) {
            saveClient(new Client("Vitalii", "6F39LjNiy8"));
            saveClient(new Client("Gorynych", "4ZDmR26v6z"));
        }
    }

    @Override
    public void saveClient(Client client) {
        client.setId(clients.size());
        clients.add(client);
    }

    @Override
    public boolean checkClientById(String client_id) {
        for(Client client: clients) {
            if(client_id.equals(client.getName())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkClientSecret(String client_id, String secret) {
        Client client = findClientById(client_id);
        return secret.equals(client.getSecret());
    }

    @Override
    public Client findClientById(String client_id) {
        for(Client client: clients) {
            if(client_id.equals(client.getName()))
                return client;
        }
        return null;
    }

    @Override
    public List<Client> findAllClients() {
        return clients;
    }
}
