package team10.app.model;

import lombok.*;
import team10.app.dto.VacationHomeDto;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

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

    public VacationHome(String title,
                        Address address,
                        String description,
                        String rulesOfConduct,
                        String additionalServices,
                        int price,
                        Set<Picture> pictures,
                        int rooms,
                        int beds,
                        Set<Availability> availability) {
        this.title = title;
        this.address = address;
        this.description = description;
        this.rulesOfConduct = rulesOfConduct;
        this.additionalServices = additionalServices;
        this.price = price;
        this.pictures = pictures;
        this.rooms = rooms;
        this.beds = beds;
        this.availability = availability;
    }
}
