package team10.app.dto;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import team10.app.model.Client;

@Getter
@Setter
@NoArgsConstructor
public class ClientDto extends UserDto{

    private LoyaltyDto loyalty;
    private boolean isPenalized;
    private int penaltyPoints;
//    private List<ReviewDto> reviews;
//    private List<ReservationDto> reservations;

    public ClientDto(Client c) {
        super(c.getFirstName(), c.getLastName(), c.getEmail(), c.getPhoneNumber(), c.getRole().name());
        this.loyalty = new LoyaltyDto(c.getLoyalty());
        this.isPenalized = c.getIsPenalized();
        this.penaltyPoints = c.getPenaltyPoints();
    }

}
