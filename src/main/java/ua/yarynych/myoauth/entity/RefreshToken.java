package ua.yarynych.myoauth.entity;

public class RefreshToken {

    private String client_id;

    private String username;

    private String scope;

    private int accessToken_id;


    public RefreshToken() {
    }

    public RefreshToken(String client_id, String username, String scope) {
        this.client_id = client_id;
        this.username = username;
        this.scope = scope;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public int getAccessToken_id() {
        return accessToken_id;
    }

    public void setAccessToken_id(int accessToken_id) {
        this.accessToken_id = accessToken_id;
    }
}
