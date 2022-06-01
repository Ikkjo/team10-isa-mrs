package team10.app.util.exceptions;

public class RentalEntityAvailabilityInvalidException extends RentalEntityDetailsException {
    public RentalEntityAvailabilityInvalidException(String availability) {
        super(String.format("RentalEntity availability invalid: %s", availability));
    }
}
