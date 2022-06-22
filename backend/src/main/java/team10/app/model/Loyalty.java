package team10.app.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import team10.app.dto.LoyaltyDto;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "loyalty")
@NoArgsConstructor
public class Loyalty {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type="uuid-char")
    private UUID id;
    private String title;
    private int availableAtPoints;
    private double clientDiscount;
    private double businessClientCut;
    private int clientPointsPerReservation;
    private int businessClientPointsPerReservation;
    private String color;
    private boolean deletable = true;

    public Loyalty(LoyaltyDto loyaltyDto) {
        this.title = loyaltyDto.getTitle();
        this.availableAtPoints = loyaltyDto.getAvailableAtPoints();
        this.clientDiscount = loyaltyDto.getClientDiscount();
        this.businessClientCut = loyaltyDto.getBusinessClientCut();
        this.clientPointsPerReservation = loyaltyDto.getClientPointsPerReservation();
        this.businessClientPointsPerReservation = loyaltyDto.getBusinessClientPointsPerReservation();
        this.color = loyaltyDto.getColor();
    }
}
