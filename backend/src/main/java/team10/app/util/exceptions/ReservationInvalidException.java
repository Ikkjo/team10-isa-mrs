package team10.app.util.exceptions;

import team10.app.dto.ReservationDto;

public class ReservationInvalidException extends RuntimeException {
    public ReservationInvalidException(ReservationDto reservationDto) {
        super(String.format("Reservation invalid: %n%s", reservationDto));
    }
}
