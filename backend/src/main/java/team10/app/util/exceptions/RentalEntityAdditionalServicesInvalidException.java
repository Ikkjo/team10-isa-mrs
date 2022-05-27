package team10.app.util.exceptions;

public class RentalEntityAdditionalServicesInvalidException extends BusinessClientDetailsException {
    public RentalEntityAdditionalServicesInvalidException(String additionalServices) {
        super(String.format("RentalEntity additional services invalid:%n%s", additionalServices));
    }
}
