package ua.yarynych.myoauth.service.token.implementation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import ua.yarynych.myoauth.dto.AuthRequestDto;
import ua.yarynych.myoauth.dto.TokenRequestDto;
import ua.yarynych.myoauth.dto.TokenResponseDto;
import ua.yarynych.myoauth.entity.AccessToken;
import ua.yarynych.myoauth.entity.AuthCode;
import ua.yarynych.myoauth.entity.RefreshToken;
import ua.yarynych.myoauth.exeptions.Exceptions;
import ua.yarynych.myoauth.exeptions.OAuth2Exception;
import ua.yarynych.myoauth.service.token.TokenService;

import java.util.Base64;
import java.util.Objects;

@Service
public class TokenServiceImpl implements TokenService {

    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String getCode(AuthRequestDto authRequestDto) throws OAuth2Exception {
        AuthCode authCode = new AuthCode(authRequestDto.getClient_id(), authRequestDto.getUsername(), 22 * 2002);
        try {
            return encodeObject(objectToJson(authCode));
        } catch (JsonProcessingException e) {
            throw new OAuth2Exception(Exceptions.server_error, "Something with encoding...");
        }
    }

    @Override
    public TokenResponseDto getAccessToken(TokenRequestDto tokenRequestDto) throws JsonProcessingException {
        AuthCode authCode = objectFromJson(decodeObject(tokenRequestDto.getCode()));
        AccessToken accessToken = new AccessToken(authCode.getClient_id(), authCode.getUsername(),
                                                  authCode.getExpires_in(), "grant_all", "bearer");
        RefreshToken refreshToken = new RefreshToken(accessToken.getClient_id(), accessToken.getUsername(),
                                                     accessToken.getScope());

        return new TokenResponseDto(
                encodeObject(objectToJson(accessToken)), accessToken.getType(),
                accessToken.getExpires_in(), encodeObject(objectToJson(refreshToken)),
                accessToken.getScope()
        );

    }

    @Override
    public TokenResponseDto getRefreshToken(TokenRequestDto tokenRequestDto) throws JsonProcessingException {
        RefreshToken refreshToken = getRefreshTokenFromJson(tokenRequestDto.getRefresh_token());
        AccessToken accessToken = new AccessToken(refreshToken.getClient_id(), refreshToken.getUsername(),
                                                  66 * 1000, "grant_all", "bearer");

        return new TokenResponseDto(
                encodeObject(objectToJson(accessToken)), accessToken.getType(),
                accessToken.getExpires_in(), accessToken.getScope()
        );
    }



    private RefreshToken getRefreshTokenFromJson(String token) throws JsonProcessingException {
        return objectMapper.readValue(decodeObject(token), RefreshToken.class);
    }

    private String encodeObject(String object) {
        return Base64.getEncoder().encodeToString(Objects.requireNonNull(object).getBytes());
    }

    private String objectToJson(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }

    private AuthCode objectFromJson(String string) throws JsonProcessingException {
        return objectMapper.readValue(string, AuthCode.class);
    }

    private String decodeObject(String object) {
        return new String(Base64.getDecoder().decode(object.getBytes()));
    }

}
