package team10.app.util.exceptions;

public class RentalEntityPriceInvalidException extends RentalEntityDetailsException {
    public RentalEntityPriceInvalidException(String price) {
        super(String.format("RentalEntity price invalid: %s", price));
    }
}
