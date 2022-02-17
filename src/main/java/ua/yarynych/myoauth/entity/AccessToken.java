package ua.yarynych.myoauth.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AccessToken {

    private long id;

    private String client_id;

    private String username;

    private long expires_in;

    private String scope;

    private String type;

    private long time;


    public AccessToken() {
    }

    public AccessToken(String client_id, String username, long expires_in, String scope, String type) {
        this.client_id = client_id;
        this.username = username;
        this.expires_in = expires_in;
        this.scope = scope;
        this.type = type;
        this.time = System.currentTimeMillis() + expires_in;
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

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccessToken that = (AccessToken) o;
        return id == that.id && expires_in == that.expires_in && client_id.equals(that.client_id)
                             && username.equals(that.username) && Objects.equals(scope, that.scope)
                             && Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, client_id, username, expires_in, scope, type);
    }
}
