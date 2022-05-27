package team10.app.util.exceptions;

public class VacationHomeBedsInvalidException extends RentalEntityDetailsException {
    public VacationHomeBedsInvalidException(int beds) {
        super(String.format("VacationHome beds invalid: %d", beds));
    }
}
