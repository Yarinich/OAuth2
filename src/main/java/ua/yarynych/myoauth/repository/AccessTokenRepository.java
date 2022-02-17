package ua.yarynych.myoauth.repository;

import ua.yarynych.myoauth.entity.AccessToken;

import java.util.List;

public interface AccessTokenRepository {
    void saveToken(AccessToken accessToken);

    List<AccessToken> findAllTokens();
}
