package team10.app.util.exceptions;

import java.util.UUID;

public class InvalidReservationBusinessClientException extends RuntimeException {
    public InvalidReservationBusinessClientException(UUID id, String email) {
        super(String.format("BusinessClient %s is not the owner of Reservation %s", email, id));
    }
}
