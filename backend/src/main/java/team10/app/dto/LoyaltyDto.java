package team10.app.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import team10.app.model.Loyalty;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class LoyaltyDto {
    private UUID id;
    private String title;
    private int availableAtPoints;
    private double clientDiscount;
    private double businessClientCut;
    private int clientPointsPerReservation;
    private int businessClientPointsPerReservation;
    private String color;
    private boolean deletable;

    public LoyaltyDto(Loyalty loyalty) {
        this.id = loyalty.getId();
        this.title = loyalty.getTitle();
        this.availableAtPoints = loyalty.getAvailableAtPoints();
        this.clientDiscount = loyalty.getClientDiscount();
        this.clientPointsPerReservation = loyalty.getClientPointsPerReservation();
        this.businessClientCut = loyalty.getBusinessClientCut();
        this.businessClientPointsPerReservation = loyalty.getBusinessClientPointsPerReservation();
        this.color = loyalty.getColor();
        this.deletable = loyalty.isDeletable();
    }
}
