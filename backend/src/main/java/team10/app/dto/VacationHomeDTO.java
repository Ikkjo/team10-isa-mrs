package team10.app.dto;

import lombok.Getter;
import lombok.Setter;
import team10.app.model.Address;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class VacationHomeDTO {
    private String title;
    private Address address;
    private String description;
    private String rulesOfConduct;
    private String additionalServices;
    private int price;
    private List<String> pictures;
    private int rooms;
    private int beds;
}
