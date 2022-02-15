package ua.yarynych.myoauth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ua.yarynych.myoauth.dto.ErrorResponseDto;
import ua.yarynych.myoauth.exeptions.LoginException;
import ua.yarynych.myoauth.exeptions.OAuth2Exception;

@ControllerAdvice
public class AuthorizationExceptionHandler {

    @ExceptionHandler(OAuth2Exception.class)
    public ResponseEntity<?> handleOAuth2Exception(OAuth2Exception oAuth2Exception) {
        return ResponseEntity
                .badRequest()
                .body(new ErrorResponseDto(oAuth2Exception.getError(), oAuth2Exception.getError_description()));
    }


    @ExceptionHandler(LoginException.class)
    public String handleLoginException(LoginException loginException, Model model) {
        model.addAttribute("exception", loginException.toString());
        return "error";
    }

}
