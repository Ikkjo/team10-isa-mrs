package team10.app.dto;

import lombok.Getter;
import lombok.Setter;
import team10.app.model.Address;

import java.util.List;

@Getter
@Setter
public class AdventureDto extends RentalEntityDto {
    private String biography;
    private String fishingEquipment;
    private int capacity;
    private boolean freeCancellation;

    public AdventureDto(
            String title,
            Address address,
            String description,
            String rulesOfConduct,
            String additionalServices,
            int price,
            List<String> pictures,
            String biography,
            String fishingEquipment,
            int capacity,
            boolean freeCancellation
            ) {
        super(title, address, description, rulesOfConduct, additionalServices, price, pictures);
        this.biography = biography;
        this.fishingEquipment = fishingEquipment;
        this.capacity = capacity;
        this.freeCancellation = freeCancellation;
    }

}
