package team10.app.util.exceptions;

public class ShipMaxSpeedInvalidException extends RentalEntityDetailsException {
    public ShipMaxSpeedInvalidException(int maxSpeed) {
        super(String.format("Ship max speed invalid: %d", maxSpeed));
    }
}
