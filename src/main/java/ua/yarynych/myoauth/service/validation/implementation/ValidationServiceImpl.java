package ua.yarynych.myoauth.service.validation.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.yarynych.myoauth.dto.AuthRequestDto;
import ua.yarynych.myoauth.dto.LoginRequestDto;
import ua.yarynych.myoauth.dto.TokenRequestDto;
import ua.yarynych.myoauth.exeptions.Exceptions;
import ua.yarynych.myoauth.exeptions.LoginException;
import ua.yarynych.myoauth.exeptions.OAuth2Exception;
import ua.yarynych.myoauth.repository.store.ClientStore;
import ua.yarynych.myoauth.service.validation.ValidationService;

@Service
public class ValidationServiceImpl implements ValidationService {

    @Autowired
    private final ClientStore clientStore;

    public ValidationServiceImpl(ClientStore clientStore) {
        this.clientStore = clientStore;
    }


    @Override
    public void validate(LoginRequestDto loginRequestDto) throws LoginException {
        if(clientStore.checkClientById(loginRequestDto.getClient_id())) {
            return;
        }
        throw new LoginException("Invalid Client ID");
    }

    @Override
    public void validate(AuthRequestDto authRequestDto) throws OAuth2Exception {
        if(!clientStore.checkClientById(authRequestDto.getClient_id())) {
            throw new OAuth2Exception(Exceptions.invalid_request, "Invalid Client ID");
        }
        if(!"code".equals(authRequestDto.getResponse_type())) {
            throw new OAuth2Exception(Exceptions.invalid_request, "Invalid client`s authentication code");
        }
        if(!"https://developers.google.com/oauthplayground".equals(authRequestDto.getRedirect_uri())) {
            throw new OAuth2Exception(Exceptions.invalid_request, "Invalid redirect request");
        }
    }


    @Override
    public void validate(TokenRequestDto tokenRequestDto) throws OAuth2Exception {
        if(!clientStore.checkClientById(tokenRequestDto.getClient_id())) {
            throw new OAuth2Exception(Exceptions.unauthorized_client, "Invalid Client ID");
        }
        if(!clientStore.checkClientSecret(tokenRequestDto.getClient_id(), tokenRequestDto.getClient_secret())) {
            throw new OAuth2Exception(Exceptions.invalid_request, "Invalid secret");
        }
        if(!"https://developers.google.com/oauthplayground".equals(tokenRequestDto.getRedirect_uri())) {
            throw new OAuth2Exception(Exceptions.invalid_request, "Invalid redirect_uri");
        }
        if(tokenRequestDto.getGrant_type() == null) {
            throw new OAuth2Exception(Exceptions.invalid_request, "Invalid grand type");
        }

    }
}
