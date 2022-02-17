package ua.yarynych.myoauth.dto;

public class UserInfoResponseDto {
    private long client_id;

    private String client_name;

    public UserInfoResponseDto() {
    }

    public UserInfoResponseDto(long client_id, String client_name) {
        this.client_id = client_id;
        this.client_name = client_name;
    }

    public long getClient_id() {
        return client_id;
    }

    public void setClient_id(long client_id) {
        this.client_id = client_id;
    }

    public String getClient_name() {
        return client_name;
    }

    public void setClient_name(String client_name) {
        this.client_name = client_name;
    }
}
