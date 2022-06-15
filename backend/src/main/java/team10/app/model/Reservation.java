package team10.app.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

import static javax.persistence.InheritanceType.TABLE_PER_CLASS;


@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
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
    private Client client;

    public Reservation(long startDate, long endDate, int price) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
        this.status = ReservationStatus.CREATED;
    }

}
