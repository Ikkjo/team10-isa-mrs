package team10.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import team10.app.model.Address;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class RentalEntityDto {
    protected String title;
    protected AddressDto address;
    protected String description;
    protected String rulesOfConduct;
    protected String additionalServices;
    protected int price;
    protected List<String> pictures;
}
