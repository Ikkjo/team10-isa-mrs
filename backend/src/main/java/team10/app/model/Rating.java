package team10.app.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private int rating;
    @ManyToOne
    private Client client;
    @ManyToOne
    @JoinColumn(name="rental_entity_id")
    private RentalEntity rentalEntity;
}
