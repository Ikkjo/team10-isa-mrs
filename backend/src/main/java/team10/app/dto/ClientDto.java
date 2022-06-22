package team10.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import team10.app.model.Client;

@Getter
@Setter
@AllArgsConstructor
public class ClientDto extends UserDto{

    private int loyaltyPoints;
    private String loyaltyColor;
    private String loyaltyStatus;
    private boolean isPenalized;
    private int penaltyPoints;

    public ClientDto(Client c) {
        super(c);
        this.loyaltyPoints = c.getLoyaltyPoints();
        this.isPenalized = c.getIsPenalized();
        this.penaltyPoints = c.getPenaltyPoints();
    }

}
