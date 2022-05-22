package team10.app.util.exceptions;

public class FirstNameInvalidException extends BusinessClientDetailsException {
    public FirstNameInvalidException(String firstName) {
        super(String.format("First name: %s invalid!", firstName));
    }
}
