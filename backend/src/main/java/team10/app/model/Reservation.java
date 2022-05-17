package team10.app.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

import static javax.persistence.InheritanceType.TABLE_PER_CLASS;


@AllArgsConstructor
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
    private LocalDate startDate;
    private LocalDate endDate;
    private Double price;
    private ReservationStatus status;
    @ManyToOne(fetch = FetchType.LAZY)
    private Client client;

    public Reservation() {

    }
}
