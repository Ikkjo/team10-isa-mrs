package team10.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import team10.app.model.Reservation;
import team10.app.model.ReservationStatus;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MakeReservationDto {

    private long startDate;
    private long endDate;
    private int price;
    private String clientEmail;
    private String rentalEntityId;
    private String rentalEntityTitle;

}
