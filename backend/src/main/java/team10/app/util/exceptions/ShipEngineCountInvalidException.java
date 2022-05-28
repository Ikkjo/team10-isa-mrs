package team10.app.util.exceptions;

public class ShipEngineCountInvalidException extends RentalEntityDetailsException {
    public ShipEngineCountInvalidException(int engineCount) {
        super(String.format("Ship engine count invalid: %d", engineCount));
    }
}
