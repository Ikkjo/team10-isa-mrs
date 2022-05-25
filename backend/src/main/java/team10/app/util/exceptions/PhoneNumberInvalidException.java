package team10.app.util.exceptions;

public class PhoneNumberInvalidException extends BusinessClientDetailsException {
    public PhoneNumberInvalidException(String phoneNumber) {
        super(String.format("Invalid phone number: %s", phoneNumber));
    }
}
