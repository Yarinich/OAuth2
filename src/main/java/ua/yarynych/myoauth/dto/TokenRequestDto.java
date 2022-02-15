package ua.yarynych.myoauth.dto;

public class TokenRequestDto {

    private String client_id;

    private String client_secret;

    private String grand_type;

    private String code;

    private String scope;

    private String redirect_uri;

    private String refresh_token;


    public TokenRequestDto() {
    }

    public TokenRequestDto(String client_id, String client_secret, String grand_type, String code, String scope,
                           String redirect_uri, String refresh_token) {
        this.client_id = client_id;
        this.client_secret = client_secret;
        this.grand_type = grand_type;
        this.code = code;
        this.scope = scope;
        this.redirect_uri = redirect_uri;
        this.refresh_token = refresh_token;
    }


    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getClient_secret() {
        return client_secret;
    }

    public void setClient_secret(String client_secret) {
        this.client_secret = client_secret;
    }

    public String getGrand_type() {
        return grand_type;
    }

    public void setGrand_type(String grand_type) {
        this.grand_type = grand_type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getRedirect_uri() {
        return redirect_uri;
    }

    public void setRedirect_uri(String redirect_uri) {
        this.redirect_uri = redirect_uri;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }
}
