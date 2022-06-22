package team10.app.util.exceptions;

public class VacationHomeRoomsInvalidException extends RentalEntityDetailsException {
    public VacationHomeRoomsInvalidException(int rooms) {
        super(String.format("VacationHome rooms invalid: %d", rooms));
    }
}
