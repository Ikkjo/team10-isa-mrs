package team10.app.util.exceptions;

public abstract class RentalEntityDetailsException extends RuntimeException {
    protected RentalEntityDetailsException(String message) {
        super(message);
    }
}
