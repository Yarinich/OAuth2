package ua.yarynych.myoauth.exeptions;

public class OAuth2Exception extends RuntimeException{

    private Exceptions error;

    private String error_description;

    public OAuth2Exception() {
    }

    public OAuth2Exception(Exceptions error, String error_description) {
        this.error = error;
        this.error_description = error_description;
    }

    public Exceptions getError() {
        return error;
    }

    public void setError(Exceptions error) {
        this.error = error;
    }

    public String getError_description() {
        return error_description;
    }

    public void setError_description(String error_description) {
        this.error_description = error_description;
    }
}
