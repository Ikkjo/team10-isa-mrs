package team10.app.util.exceptions;

public abstract class BusinessClientDetailsException extends RuntimeException {
    protected BusinessClientDetailsException(String message) {
        super(message);
    }
}
