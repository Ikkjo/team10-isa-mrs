package team10.app.util.exceptions;

public class ShipCapacityInvalidException extends RentalEntityDetailsException {
    public ShipCapacityInvalidException(int capacity) {
        super(String.format("Ship capacity invalid: %d", capacity));
    }
}
