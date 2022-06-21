package team10.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IndividualEarningsDto {
    private UUID rentalEntityId;
    private String rentalEntityTitle;
    private double earnings;

    public void addEarnings(double earnings) {
        this.earnings += earnings;
    }
}
