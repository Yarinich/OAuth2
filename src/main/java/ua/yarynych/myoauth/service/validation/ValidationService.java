package ua.yarynych.myoauth.service.validation;

import org.springframework.stereotype.Service;
import ua.yarynych.myoauth.dto.AuthRequestDto;
import ua.yarynych.myoauth.dto.LoginRequestDto;
import ua.yarynych.myoauth.dto.TokenRequestDto;
import ua.yarynych.myoauth.exeptions.LoginException;
import ua.yarynych.myoauth.exeptions.OAuth2Exception;

@Service
public interface ValidationService {

    void validate(LoginRequestDto loginRequestDto) throws LoginException;

    void validate(AuthRequestDto authRequestDto) throws OAuth2Exception;

    void validate(TokenRequestDto tokenRequestDto) throws OAuth2Exception;

}
