package team10.app.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Adventure extends RentalEntity {

    private String instructorBiography;
    @Column(name = "fishing", nullable = false)
    private String fishing;
    private boolean freeCancellation;
    private int maxCapacity;

    public Adventure(String name,
                     String description,
                     Set<Picture> pictures,
                     String rulesOfConduct,
                     int price,
                     String instructorBiography,
                     int maxCapacity,
                     String fishing,
                     boolean freeCancellation) {
        this.title = name;
        this.description = description;
        this.pictures = pictures;
        this.rulesOfConduct = rulesOfConduct;
        this.price = price;
        this.deleted = false;
        this.instructorBiography = instructorBiography;
        this.maxCapacity = maxCapacity;
        this.fishing = fishing;
        this.freeCancellation = freeCancellation;
    }
}
