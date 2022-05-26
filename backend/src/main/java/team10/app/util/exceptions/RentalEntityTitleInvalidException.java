package team10.app.util.exceptions;

public class RentalEntityTitleInvalidException extends RentalEntityDetailsException {
    public RentalEntityTitleInvalidException(String title) {
        super(String.format("RentalEntity title invalid: %s", title));
    }
}
