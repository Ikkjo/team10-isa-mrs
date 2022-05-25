package team10.app.util.exceptions;

public class dateOfBirthInvalidException extends BusinessClientDetailsException {
    public dateOfBirthInvalidException(String dateOfBirth) {
        super(String.format("Invalid date of birth: %s", dateOfBirth));
    }
}
