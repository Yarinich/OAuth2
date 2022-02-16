package ua.yarynych.myoauth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ua.yarynych.myoauth.dto.TokenRequestDto;
import ua.yarynych.myoauth.service.validation.ValidationService;

@Controller
public class MainController {

    @Autowired
    private ValidationService validationService;

    @GetMapping("/")
    public String home(Model model) {
        return "index";
    }

    @GetMapping(value = "/token")
    public String getTokenInfoPage(TokenRequestDto tokenRequestDto, Model model) {
        validationService.validate(tokenRequestDto);
        model.addAttribute("client_id", tokenRequestDto.getClient_id());
        model.addAttribute("client_secret", tokenRequestDto.getClient_secret());
        model.addAttribute("grant_type", tokenRequestDto.getGrant_type());
        model.addAttribute("code", tokenRequestDto.getCode());
        model.addAttribute("redirect_uri", tokenRequestDto.getRedirect_uri());
        model.addAttribute("refresh_token", tokenRequestDto.getRefresh_token());
        return "token";
    }
}
