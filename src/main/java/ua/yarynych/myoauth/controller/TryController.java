package ua.yarynych.myoauth.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.yarynych.myoauth.entity.Client;
import ua.yarynych.myoauth.repository.store.ClientStore;

import java.util.List;

@RestController
public class TryController {

    @Autowired
    private ClientStore clientStore;

    @GetMapping("/clients")
    public List<Client> getAllClients() {
        clientStore.setStartValue();

        return clientStore.findAllClients();
    }


}
