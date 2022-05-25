package team10.app.util.exceptions;

public class DateOfBirthInvalidException extends BusinessClientDetailsException {
    public DateOfBirthInvalidException(String dateOfBirth) {
        super(String.format("Invalid date of birth: %s", dateOfBirth));
    }
}
