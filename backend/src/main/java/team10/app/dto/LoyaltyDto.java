package team10.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import team10.app.model.Loyalty;
import team10.app.model.LoyaltyStatus;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoyaltyDto {

    private String id = "";
    private int loyaltyPoints = 0;
    private String loyaltyStatus = "REGULAR";

    public LoyaltyDto(Loyalty l) {
        if (l != null){
            this.id = l.getId().toString();
            this.loyaltyPoints = l.getLoyaltyPoints();
            this.loyaltyStatus = l.getLoyaltyStatus().name();
        }

    }
}
