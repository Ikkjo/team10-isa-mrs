package team10.app.model;

import lombok.Data;

import java.util.List;

@Data
public abstract class RentalEntity {
    protected String name;
    protected String description;
    protected List<String> pictures;
    protected List<String> rulesOfConduct;
    protected double price;
    protected boolean deleted = false;
}
