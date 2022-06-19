package team10.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import team10.app.model.Address;
import team10.app.model.Picture;
import team10.app.model.RentalEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class RentalEntityDto {
    protected UUID id;
    protected String title;
    protected AddressDto address;
    protected String description;
    protected String rulesOfConduct;
    protected String additionalServices;
    protected String type;
    protected int price;
    protected List<String> pictures;
    protected List<Long> availability;

    public RentalEntityDto(UUID id, String title, AddressDto address, String description, String rulesOfConduct,
                           String additionalServices, int price, List<String> pictures, List<Long> availability) {
        this.id = id;
        this.title = title;
        this.address = address;
        this.description = description;
        this.rulesOfConduct = rulesOfConduct;
        this.additionalServices = additionalServices;
        this.price = price;
        this.pictures = pictures;
        this.availability = availability;
    }

    protected RentalEntityDto(String title, AddressDto address, String description, String rulesOfConduct,
                              String additionalServices, int price, List<String> pictures, List<Long> availability) {
        this.title = title;
        this.address = address;
        this.description = description;
        this.rulesOfConduct = rulesOfConduct;
        this.additionalServices = additionalServices;
        this.price = price;
        this.pictures = pictures;
        this.availability = availability;
    }
}
