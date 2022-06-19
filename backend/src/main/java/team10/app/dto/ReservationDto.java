package team10.app.dto;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import team10.app.model.Reservation;
import team10.app.model.ReservationStatus;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDto {

    private UUID id;
    private long startDate;
    private long endDate;
    private int price;
    private ReservationStatus status;
    private String clientEmail;
    private UUID clientId;
    private String businessClient;
    private UUID rentalEntityId;
    private String rentalEntityTitle;

    public ReservationDto(Reservation reservation) {
        this.id = reservation.getId();
        this.startDate = reservation.getStartDate();
        this.endDate = reservation.getEndDate();
        this.price = reservation.getPrice();
        this.status = reservation.getStatus();
        this.clientEmail = reservation.getClient().getEmail();
        this.clientId = reservation.getClient().getId();
        this.businessClient = reservation.getBusinessClient().getEmail();
        this.rentalEntityId = reservation.getRentalEntity().getId();
        this.rentalEntityTitle = reservation.getRentalEntity().getTitle();
    }
}
