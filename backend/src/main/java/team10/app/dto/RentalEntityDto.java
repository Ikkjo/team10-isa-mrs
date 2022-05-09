package team10.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import team10.app.model.Address;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class RentalEntityDto {
    private String title;
    private Address address;
    private String description;
    private String rulesOfConduct;
    private String additionalServices;
    private int price;
    private List<String> pictures;
}
