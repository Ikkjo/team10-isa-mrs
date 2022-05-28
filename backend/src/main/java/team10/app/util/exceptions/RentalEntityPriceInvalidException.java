package team10.app.util.exceptions;

public class RentalEntityPriceInvalidException extends RentalEntityDetailsException {
    public RentalEntityPriceInvalidException(int price) {
        super(String.format("RentalEntity price invalid: %d", price));
    }
}
