package ua.yarynych.myoauth.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.yarynych.myoauth.dto.TokenRequestDto;
import ua.yarynych.myoauth.exeptions.Exceptions;
import ua.yarynych.myoauth.exeptions.OAuth2Exception;
import ua.yarynych.myoauth.service.token.TokenService;
import ua.yarynych.myoauth.service.validation.ValidationService;

@RestController
public class TokenController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private ValidationService validationService;

    public TokenController() {
    }

    public TokenController(TokenService tokenService, ValidationService validationService) {
        this.tokenService = tokenService;
        this.validationService = validationService;
    }




    @PostMapping(value = {"/token"}, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<?> getToken(TokenRequestDto tokenRequestDto) throws JsonProcessingException {
        validationService.validate(tokenRequestDto);
        if("authorization_code".equals(tokenRequestDto.getGrant_type())) {
            return ResponseEntity.ok().body(tokenService.getAccessToken(tokenRequestDto));
        }
        else if("refresh_token".equals(tokenRequestDto.getGrant_type())) {
            return ResponseEntity.ok().body(tokenService.getRefreshToken(tokenRequestDto));
        }
        else {
            throw new OAuth2Exception(Exceptions.invalid_scope, "Invalid Grand Type");
        }
    }
//    http://localhost:8080/auth?client_id=Vitalii&response_type=code&redirect_uri=https://developers.google.com/oauthplayground
//    http://localhost:8080/token?client_id=Vitalii&client_secret=6F39LjNiy8&grant_type=authorization_code&code=eyJpZCI6MCwiY2xpZW50X2lkIjoiVml0YWxpaSIsInVzZXJuYW1lIjoibG9naW4iLCJleHBpcmVzX2luIjo0NDA0NH0=&redirect_uri=https://developers.google.com/oauthplayground
//    http://localhost:8080/token?client_id=Vitalii&client_secret=6F39LjNiy8&grant_type=refresh_token&refresh_token=eyJjbGllbnRfaWQiOiJWaXRhbGlpIiwidXNlcm5hbWUiOiJsb2dpbiIsInNjb3BlIjpudWxsLCJhY2Nlc3NUb2tlbl9pZCI6MH0=&redirect_uri=https://developers.google.com/oauthplayground

}
