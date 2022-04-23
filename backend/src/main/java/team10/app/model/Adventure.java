package team10.app.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Adventure extends RentalEntity {
    private String instructorBiography;
    private List<String> fishing;
    private String cancellationConditions;
    private int maxCapacity;

    public Adventure(String name,
                     String description,
                     List<String> pictures,
                     List<String> rulesOfConduct,
                     double price,
                     String instructorBiography,
                     int maxCapacity,
                     List<String> fishing,
                     String cancellationConditions) {
        this.name = name;
        this.description = description;
        this.pictures = pictures;
        this.rulesOfConduct = rulesOfConduct;
        this.price = price;
        this.deleted = false;
        this.instructorBiography = instructorBiography;
        this.maxCapacity = maxCapacity;
        this.fishing = fishing;
        this.cancellationConditions = cancellationConditions;
    }
}
