package ua.yarynych.myoauth.repository.store;

import org.springframework.stereotype.Service;
import ua.yarynych.myoauth.entity.AccessToken;
import ua.yarynych.myoauth.repository.AccessTokenRepository;

import java.util.ArrayList;
import java.util.List;


@Service
public class AccessTokenStore implements AccessTokenRepository {

    private final List<AccessToken> tokens = new ArrayList<>();

    public AccessTokenStore() {
    }

    @Override
    public void saveToken(AccessToken accessToken) {
        accessToken.setId(tokens.size());
        tokens.add(accessToken);
    }

    @Override
    public List<AccessToken> findAllTokens() {
        return tokens;
    }

}
