package team10.app.util.exceptions;

public class ShipTypeInvalidException extends RentalEntityDetailsException {
    public ShipTypeInvalidException(String type) {
        super(String.format("Ship type invalid: %s", type));
    }
}
