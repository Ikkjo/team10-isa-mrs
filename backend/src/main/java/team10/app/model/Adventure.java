package team10.app.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Adventure extends RentalEntity {
    private String instructorBiography;
    @ElementCollection
    @CollectionTable(name = "my_fishing", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "fishing", nullable = false)
    private List<String> fishing;
    private String cancellationConditions;
    private int maxCapacity;
    @Id
    private Long id;

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

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
