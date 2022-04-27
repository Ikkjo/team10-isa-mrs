package team10.app.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

import static javax.persistence.InheritanceType.TABLE_PER_CLASS;


@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@Entity
@Inheritance(strategy=TABLE_PER_CLASS)
public class Reservation {

    @SequenceGenerator(
            name = "reservation_sequence",
            sequenceName = "reservation_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "reservation_sequence"
    )
    private Long id;

    private LocalDate startDate;
    private LocalDate endDate;
    private Double price;
    private ReservationStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    private Client client;

    @OneToOne(mappedBy = "reservation",
              fetch = FetchType.EAGER,
              cascade = CascadeType.ALL)
    private Rating rating;

    public Reservation() {

    }
}
