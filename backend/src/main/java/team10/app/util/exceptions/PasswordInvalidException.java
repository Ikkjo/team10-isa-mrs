package team10.app.util.exceptions;

public class PasswordInvalidException extends BusinessClientDetailsException {
    public PasswordInvalidException(String password) {
        super(String.format("Invalid password: %s", password));
    }
}
