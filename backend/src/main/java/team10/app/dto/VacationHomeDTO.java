package team10.app.dto;

import lombok.Getter;
import lombok.Setter;
import team10.app.model.Address;
import team10.app.model.Room;
import team10.app.model.RulesOfConduct;

import java.util.List;

@Getter
@Setter
public class VacationHomeDTO {
    private String title;
    private Address address;
    private List<Room> rooms;
    private int price;
    private RulesOfConduct rulesOfConduct;
    private String additionalServices;
}
