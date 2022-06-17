package team10.app.model;

import lombok.*;
import org.apache.tomcat.jni.Local;
import team10.app.dto.CreateReservationDto;
import team10.app.util.DateTimeUtil;

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
    private BusinessClient businessClient;
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

    public Reservation(CreateReservationDto createReservationDto, BusinessClient businessClient, Client client, RentalEntity rentalEntity) {
        this.startDate = createReservationDto.getStartDate();
        this.endDate = createReservationDto.getEndDate();
        this.price = createReservationDto.getPrice();
        this.businessClient = businessClient;
        this.client = client;
        this.rentalEntity = rentalEntity;
        this.status = ReservationStatus.CREATED;
    }

    public void updateStatus() {
        if (this.status.value != 1) {
            long today = DateTimeUtil.getTodayEpochMillisecond();
            if (this.startDate >= today)
                this.status = ReservationStatus.ACTIVE;
            if (this.endDate < today)
                this.status = ReservationStatus.FINISHED;
            if (this.startDate < today && this.endDate > today)
                this.status = ReservationStatus.CREATED;
        }
    }
}
