package team10.app.util.exceptions;

public class LastNameInvalidException extends BusinessClientDetailsException {
    protected LastNameInvalidException(String lastName) {
        super(String.format("Last name: %s invalid!", lastName));
    }
}
