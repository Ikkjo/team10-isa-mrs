package team10.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ActionDto {
    private Long expiresOn;
    private List<Long> dateRange;
    private int price;
    private int maxPersons;
}
