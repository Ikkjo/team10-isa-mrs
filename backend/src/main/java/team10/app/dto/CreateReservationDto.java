package team10.app.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CreateReservationDto {
    private String email;
    private List<Long> dateRange;
    private int price;
    private int maxPersons;

    public long getStartDate() {
        return this.dateRange.get(0);
    }

    public long getEndDate() {
        return this.dateRange.get(1);
    }
}
