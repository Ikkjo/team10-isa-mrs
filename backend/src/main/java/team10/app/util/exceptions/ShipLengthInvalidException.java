package team10.app.util.exceptions;

public class ShipLengthInvalidException extends RentalEntityDetailsException {
    public ShipLengthInvalidException(double length) {
        super(String.format("Ship length invalid: %,.2f", length));
    }
}
