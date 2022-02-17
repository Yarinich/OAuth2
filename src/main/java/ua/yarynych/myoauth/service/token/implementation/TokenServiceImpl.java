package ua.yarynych.myoauth.service.token.implementation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.yarynych.myoauth.dto.AuthRequestDto;
import ua.yarynych.myoauth.dto.TokenRequestDto;
import ua.yarynych.myoauth.dto.TokenResponseDto;
import ua.yarynych.myoauth.dto.UserInfoResponseDto;
import ua.yarynych.myoauth.entity.AccessToken;
import ua.yarynych.myoauth.entity.AuthCode;
import ua.yarynych.myoauth.entity.Client;
import ua.yarynych.myoauth.entity.RefreshToken;
import ua.yarynych.myoauth.exeptions.Exceptions;
import ua.yarynych.myoauth.exeptions.OAuth2Exception;
import ua.yarynych.myoauth.repository.store.AccessTokenStore;
import ua.yarynych.myoauth.repository.store.ClientStore;
import ua.yarynych.myoauth.service.token.TokenService;

import java.util.Base64;
import java.util.Objects;

@Service
public class TokenServiceImpl implements TokenService {

    private final Logger logger = LoggerFactory.getLogger(TokenServiceImpl.class);

    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    ClientStore clientStore;

    @Autowired
    AccessTokenStore accessTokenStore;


    @Override
    public String getCode(AuthRequestDto authRequestDto) throws OAuth2Exception {
        AuthCode authCode = new AuthCode(authRequestDto.getClient_id(), authRequestDto.getUsername(), 66 * 1000 * 2002);
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

        accessTokenStore.saveToken(accessToken);

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
                                                  66 * 10000 * 2002, "grant_all", "bearer");

        accessTokenStore.saveToken(accessToken);

        return new TokenResponseDto(
                encodeObject(objectToJson(accessToken)), accessToken.getType(),
                accessToken.getExpires_in(), accessToken.getScope()
        );
    }


    @Override
    public UserInfoResponseDto getUserInfo(String authorizationHeader) throws OAuth2Exception, JsonProcessingException {
        String[] authRequest = authorizationHeader.split("\\s");
        String accessTokenInJSON = authRequest[1];

        AccessToken accessToken = getAccessTokenFromJson(accessTokenInJSON);

        logger.info("Cur: " + System.currentTimeMillis());
        logger.info("Acc: " + accessToken.getTime());

        if(!clientStore.checkClientById(accessToken.getClient_id())) {
            throw new OAuth2Exception(Exceptions.unauthorized_client, "Invalid Access Token");
        }
        if(System.currentTimeMillis() < accessToken.getTime()) {
            throw new OAuth2Exception(Exceptions.invalid_request, "Token has inspired");
        }

        Client client = clientStore.findClientById(accessToken.getClient_id());

        return new UserInfoResponseDto(client.getId(), client.getName());
    }


    private AccessToken getAccessTokenFromJson(String token) throws JsonProcessingException {
        return objectMapper.readValue(decodeObject(token), AccessToken.class);
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
