package team10.app.model;

import lombok.*;
import team10.app.dto.ReservationDto;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

import static javax.persistence.InheritanceType.TABLE_PER_CLASS;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "reservation")
@Inheritance(strategy=TABLE_PER_CLASS)
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private long startDate;
    private long endDate;
    private int price;
    private ReservationStatus status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="client_id")
    private Client client;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="rental_entity_id")
    private RentalEntity rentalEntity;

    public Reservation(long startDate, long endDate, int price) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
        this.status = ReservationStatus.CREATED;
    }

    public Reservation(ReservationDto reservationDto, Client client, RentalEntity rentalEntity) {
        this.startDate = reservationDto.getStartDate();
        this.endDate = reservationDto.getEndDate();
        this.price = reservationDto.getPrice();
        this.client = client;
        this.status = ReservationStatus.CREATED;
        this.rentalEntity = rentalEntity;
    }
}
