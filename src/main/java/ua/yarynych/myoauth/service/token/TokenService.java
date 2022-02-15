package ua.yarynych.myoauth.service.token;

import com.fasterxml.jackson.core.JsonProcessingException;
import ua.yarynych.myoauth.dto.AuthRequestDto;
import ua.yarynych.myoauth.dto.TokenRequestDto;
import ua.yarynych.myoauth.dto.TokenResponseDto;

public interface TokenService {
    String getCode(AuthRequestDto authRequestDto);
    TokenResponseDto getAccessToken(TokenRequestDto tokenRequestDto) throws JsonProcessingException;
}
