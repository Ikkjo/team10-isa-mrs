package team10.app.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "Loyalty")
@Table(name = "loyalty")
public class Loyalty {

    @SequenceGenerator(
            name = "loyalty_sequence",
            sequenceName = "loyalty_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "loyalty_sequence"
    )

    private Long id;
    private int loyaltyPoints = 0;
    private LoyaltyStatus loyaltyStatus = LoyaltyStatus.REGULAR;

}
