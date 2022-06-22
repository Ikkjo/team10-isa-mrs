package team10.app.util.exceptions;

public class EmailTakenException extends BusinessClientDetailsException {
    public EmailTakenException(String email) {
        super(String.format("Email already taken: %s", email));
    }
}
