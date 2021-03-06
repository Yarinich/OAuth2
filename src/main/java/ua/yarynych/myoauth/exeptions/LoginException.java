package ua.yarynych.myoauth.exeptions;

public class LoginException extends RuntimeException {

    private String message;

    public LoginException() {
    }

    public LoginException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "LoginException: " +
                "message='" + message + '\'';
    }
}
