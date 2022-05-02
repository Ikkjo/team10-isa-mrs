package team10.app.model;

import lombok.*;
import team10.app.dto.VacationHomeDto;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="vacation_homes")
public class VacationHome extends RentalEntity {

    @Column(nullable = false)
    private int rooms;
    @Column(nullable = false)
    private int beds;

    public VacationHome(VacationHomeDto vacationHomeDTO) {
        this.title = vacationHomeDTO.getTitle();
        this.address = vacationHomeDTO.getAddress();
        this.description = vacationHomeDTO.getDescription();
        this.rulesOfConduct = vacationHomeDTO.getRulesOfConduct();
        this.additionalServices = vacationHomeDTO.getAdditionalServices();
        this.price = vacationHomeDTO.getPrice();
        this.pictures = vacationHomeDTO.getPictures();
        this.rooms = vacationHomeDTO.getRooms();
        this.beds = vacationHomeDTO.getBeds();
    }


}
