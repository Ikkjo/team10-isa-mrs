package team10.app.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Entity(name = "Loyalty")
@Table(name = "loyalty")
public class Loyalty {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private int loyaltyPoints = 0;
    private LoyaltyStatus loyaltyStatus = LoyaltyStatus.REGULAR;

}
