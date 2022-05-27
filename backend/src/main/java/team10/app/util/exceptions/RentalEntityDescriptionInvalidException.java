package team10.app.util.exceptions;

public class RentalEntityDescriptionInvalidException extends RentalEntityDetailsException {
    public RentalEntityDescriptionInvalidException(String description) {
        super(String.format("RentalEntity description invalid:%n%s", description));
    }
}
