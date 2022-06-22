package team10.app.util.exceptions;

import team10.app.dto.CreateReservationDto;

public class ReservationInvalidException extends RuntimeException {
    public ReservationInvalidException(CreateReservationDto createReservationDto) {
        super(String.format("Reservation invalid: %n%s", createReservationDto));
    }
}
