package team10.app.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Loyalty {
    private int loyaltyPoints = 0;
    private LoyaltyStatus loyaltyStatus = LoyaltyStatus.REGULAR;
}
