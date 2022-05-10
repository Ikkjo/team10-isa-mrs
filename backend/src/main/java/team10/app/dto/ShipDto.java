package team10.app.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import team10.app.model.Address;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ShipDto extends RentalEntityDto {

    private String type;
    private double length;
    private int engineCount;
    private int enginePower;
    private int maxSpeed;
    private List<String> navigationEquipment;
    private List<String> fishingEquipment;
    private int capacity;
    private boolean freeCancellation;

    public ShipDto(String title, Address address, String description, String rulesOfConduct, String additionalServices,
                   int price, List<String> pictures, String type, double length, int engineCount, int enginePower,
                   int maxSpeed, List<String> navigationEquipment, List<String> fishingEquipment, int capacity,
                   boolean freeCancellation) {
        super(title, address, description, rulesOfConduct, additionalServices, price, pictures);
        this.type = type;
        this.length = length;
        this.engineCount = engineCount;
        this.enginePower = enginePower;
        this.maxSpeed = maxSpeed;
        this.navigationEquipment = navigationEquipment;
        this.fishingEquipment = fishingEquipment;
        this.capacity = capacity;
        this.freeCancellation = freeCancellation;
    }
}
