package ua.yarynych.myoauth.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.yarynych.myoauth.entity.AccessToken;
import ua.yarynych.myoauth.entity.Client;
import ua.yarynych.myoauth.repository.store.AccessTokenStore;
import ua.yarynych.myoauth.repository.store.ClientStore;

import java.util.List;

@RestController
public class ExchangeController {

    @Autowired
    private ClientStore clientStore;

    @Autowired
    private AccessTokenStore accessTokenStore;

    @GetMapping("/clients")
    public List<Client> getAllClients() {
        clientStore.setStartValue();

        return clientStore.findAllClients();
    }

    @GetMapping("/tokensAll")
    public List<AccessToken> getAllTokens() {
        return accessTokenStore.findAllTokens();
    }


}
