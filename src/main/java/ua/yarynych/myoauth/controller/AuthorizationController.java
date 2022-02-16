package ua.yarynych.myoauth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.yarynych.myoauth.dto.AuthRequestDto;
import ua.yarynych.myoauth.dto.LoginRequestDto;
import ua.yarynych.myoauth.repository.store.ClientStore;
import ua.yarynych.myoauth.service.token.TokenService;
import ua.yarynych.myoauth.service.validation.ValidationService;


import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class AuthorizationController {

    @Autowired
    private final ValidationService validationService;

    @Autowired
    private final TokenService tokenService;

    @Autowired
    private final ClientStore clientStore;

    public AuthorizationController(ValidationService validationService, TokenService tokenService, ClientStore clientStore) {
        this.validationService = validationService;
        this.tokenService = tokenService;
        this.clientStore = clientStore;
        clientStore.setStartValue();
    }


    @GetMapping("/auth")
    public String getLogin(LoginRequestDto loginRequestDto, Model model) {
        validationService.validate(loginRequestDto);
        model.addAttribute("client_id", loginRequestDto.getClient_id());
        model.addAttribute("redirect_uri", loginRequestDto.getRedirect_uri());
        model.addAttribute("response_type", loginRequestDto.getResponse_type());
        model.addAttribute("access_type", loginRequestDto.getAccess_type());
        model.addAttribute("scope", loginRequestDto.getScope());
        return "login";
    }

    @PostMapping(value = {"/auth"}, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public void getCode(@RequestParam("client_id") String client_id,
                        @RequestParam("redirect_uri") String redirect_uri,
                        @RequestParam("response_type") String response_type,
                        AuthRequestDto authRequestDto, HttpServletResponse response) throws IOException {
        authRequestDto.setClient_id(client_id);
        authRequestDto.setRedirect_uri(redirect_uri);
        authRequestDto.setResponse_type(response_type);
        validationService.validate(authRequestDto);
        response.sendRedirect(authRequestDto.getRedirect_uri() + "?code=" + tokenService.getCode(authRequestDto));
    }

}
