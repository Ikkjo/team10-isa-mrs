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
public class VacationHomeDto extends RentalEntityDto {
    private int rooms;
    private int beds;

    public VacationHomeDto(String title, Address address, String description, String rulesOfConduct, String additionalServices, int price, List<String> pictures, int rooms, int beds) {
        super(title, address, description, rulesOfConduct, additionalServices, price, pictures);
        this.rooms = rooms;
        this.beds = beds;
    }
}
