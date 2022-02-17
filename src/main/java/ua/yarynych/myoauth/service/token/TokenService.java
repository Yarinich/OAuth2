package ua.yarynych.myoauth.service.token;

import com.fasterxml.jackson.core.JsonProcessingException;
import ua.yarynych.myoauth.dto.AuthRequestDto;
import ua.yarynych.myoauth.dto.TokenRequestDto;
import ua.yarynych.myoauth.dto.TokenResponseDto;
import ua.yarynych.myoauth.dto.UserInfoResponseDto;
import ua.yarynych.myoauth.exeptions.OAuth2Exception;

public interface TokenService {
    String getCode(AuthRequestDto authRequestDto);
    TokenResponseDto getAccessToken(TokenRequestDto tokenRequestDto) throws JsonProcessingException;
    TokenResponseDto getRefreshToken(TokenRequestDto tokenRequestDto) throws JsonProcessingException;
    UserInfoResponseDto getUserInfo(String authorizationHeader) throws OAuth2Exception, JsonProcessingException;
}
