package team10.app.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import team10.app.model.Address;
import team10.app.model.Availability;
import team10.app.model.Picture;
import team10.app.model.Ship;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class ShipDto extends RentalEntityDto {

    private String type;
    private double length;
    private int engineCount;
    private int enginePower;
    private int maxSpeed;
    private String navigationEquipment;
    private String fishingEquipment;
    private int capacity;
    private boolean freeCancellation;

    public ShipDto(String title, AddressDto address, String description, String rulesOfConduct, String additionalServices,
                   int price, List<String> pictures, String type, double length, int engineCount, int enginePower,
                   int maxSpeed, String navigationEquipment, String fishingEquipment, int capacity,
                   boolean freeCancellation, List<Long> availability) {
        super(title, address, description, rulesOfConduct, additionalServices, price, pictures, availability);
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

    public ShipDto(Ship ship) {
        super(
                ship.getId(),
                ship.getTitle(),
                new AddressDto(ship.getAddress()),
                ship.getDescription(),
                ship.getRulesOfConduct(),
                ship.getAdditionalServices(),
                ship.getPrice(),
                ship.getPictures().stream().map(Picture::asString).collect(Collectors.toList()),
                ship.getAvailability().stream().map(Availability::getDate).collect(Collectors.toList())
        );
        this.type = ship.getType();
        this.length = ship.getLength();
        this.engineCount = ship.getEngineCount();
        this.enginePower = ship.getEnginePower();
        this.maxSpeed = ship.getMaxSpeed();
        this.navigationEquipment = ship.getNavigationEquipment();
        this.fishingEquipment = ship.getFishingEquipment();
        this.capacity = ship.getCapacity();
        this.freeCancellation = ship.isFreeCancellation();
    }
}
