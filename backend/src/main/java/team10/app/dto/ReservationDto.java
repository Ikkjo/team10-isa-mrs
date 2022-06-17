package team10.app.dto;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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

}
