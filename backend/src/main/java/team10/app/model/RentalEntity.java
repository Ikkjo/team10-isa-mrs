package team10.app.model;

import lombok.Data;

import java.util.List;

@Data
public class RentalEntity {
    private String name;
    private String description;
    private List<String> pictures;
    private List<String> rulesOfConduct;
    private Double price;
    private Boolean isDeleted = false;
}
