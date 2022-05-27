package team10.app.util.exceptions;

public class ShipEnginePowerInvalidException extends RentalEntityDetailsException {
    public ShipEnginePowerInvalidException(int enginePower) {
        super(String.format("Ship engine power invalid: %d", enginePower));
    }
}
