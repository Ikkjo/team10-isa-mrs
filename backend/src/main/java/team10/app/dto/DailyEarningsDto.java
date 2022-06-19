package team10.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DailyEarningsDto {
    private long day;
    private double earnings;

    public void addEarnings(double earnings) {
        this.earnings += earnings;
    }
}