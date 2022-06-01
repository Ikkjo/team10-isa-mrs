package team10.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import team10.app.model.Address;
import team10.app.model.Availability;
import team10.app.model.Picture;
import team10.app.model.VacationHome;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class VacationHomeDto extends RentalEntityDto {
    private int rooms;
    private int beds;

    public VacationHomeDto(String title, AddressDto addressDto, String description, String rulesOfConduct,
                           String additionalServices, int price, List<String> pictures, int rooms, int beds, List<Long> availiability) {
        super(title, addressDto, description, rulesOfConduct, additionalServices, price, pictures, availiability);
        this.rooms = rooms;
        this.beds = beds;
    }


    public VacationHomeDto(VacationHome vacationHome) {
        super(
                vacationHome.getId(),
                vacationHome.getTitle(),
                new AddressDto(vacationHome.getAddress()),
                vacationHome.getDescription(),
                vacationHome.getRulesOfConduct(),
                vacationHome.getAdditionalServices(),
                vacationHome.getPrice(),
                vacationHome.getPictures().stream().map(Picture::asString).collect(Collectors.toList()),
                vacationHome.getAvailability().stream().map(Availability::getDate).collect(Collectors.toList())

        );
        this.rooms = vacationHome.getRooms();
        this.beds = vacationHome.getBeds();
    }
}
