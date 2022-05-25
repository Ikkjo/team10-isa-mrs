package team10.app.util.exceptions;

public class EmailInvalidException extends BusinessClientDetailsException {
    public EmailInvalidException(String email) {
        super(String.format("Invalid email: %s", email));
    }
}
