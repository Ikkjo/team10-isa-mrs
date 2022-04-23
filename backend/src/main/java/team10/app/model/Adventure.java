package team10.app.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Adventure { // TODO: extends Entity
    private String instructorBiography;
    private List<String> fishing;
    private String cancellationConditions;
    private int maxCapacity;

    public Adventure(String instructorBiography, List<String> fishing, String cancellationConditions, int maxCapacity) {
        // super(...)
        this.instructorBiography = instructorBiography;
        this.fishing = fishing;
        this.cancellationConditions = cancellationConditions;
        this.maxCapacity = maxCapacity;
    }
}
