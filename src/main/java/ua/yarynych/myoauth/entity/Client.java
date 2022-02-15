package ua.yarynych.myoauth.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Objects;

public class Client {

    private long id;

    private String name;

    private String secret;


    public Client() {
    }

    public Client(String name, String secret) {
        this.name = name;
        this.secret = secret;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return id == client.id && name.equals(client.name) && secret.equals(client.secret);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, secret);
    }
}
