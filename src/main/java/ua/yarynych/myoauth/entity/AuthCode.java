package ua.yarynych.myoauth.entity;

public class AuthCode {

    private long id;

    private String client_id;

    private String username;

    private long expires_in;


    public AuthCode() {
    }

    public AuthCode(String client_id, String username, long expires_in) {
        this.client_id = client_id;
        this.username = username;
        this.expires_in = expires_in;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public long getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(long expires_in) {
        this.expires_in = expires_in;
    }
}
