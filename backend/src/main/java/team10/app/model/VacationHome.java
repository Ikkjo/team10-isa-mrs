package team10.app.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import team10.app.dto.VacationHomeDTO;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name="vacation_homes")
public class VacationHome extends RentalEntity {

    @Column(nullable = false)
    private int rooms;
    @Column(nullable = false)
    private int beds;

    public VacationHome(VacationHomeDTO vacationHomeDTO) {
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
