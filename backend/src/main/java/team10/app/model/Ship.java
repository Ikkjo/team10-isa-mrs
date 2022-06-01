package team10.app.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="ships")
public class Ship extends RentalEntity {

    @Column(nullable = false)
    private String type;
    @Column(nullable = false)
    private double length;
    @Column(nullable = false)
    private int engineCount;
    @Column(nullable = false)
    private int enginePower;
    @Column(nullable = false)
    private int maxSpeed;
    @Column(nullable = false, length = 500)
    private String navigationEquipment;
    @Column(nullable = false, length = 500)
    private String fishingEquipment;
    @Column(nullable = false)
    private int capacity;
    @Column(nullable = false)
    private boolean freeCancellation;

    public Ship(String title, Address address, String description, String rulesOfConduct, String additionalServices,
                int price, Set<Picture> pictures, String type, double length, int engineCount, int enginePower,
                int maxSpeed, String navigationEquipment, String fishingEquipment, int capacity,
                boolean freeCancellation, Set<Availability> availability) {
        this.title = title;
        this.address = address;
        this.description = description;
        this.rulesOfConduct = rulesOfConduct;
        this.additionalServices = additionalServices;
        this.price = price;
        this.pictures = pictures;
        this.type = type;
        this.length = length;
        this.engineCount = engineCount;
        this.enginePower = enginePower;
        this.maxSpeed = maxSpeed;
        this.navigationEquipment = navigationEquipment;
        this.fishingEquipment = fishingEquipment;
        this.capacity = capacity;
        this.freeCancellation = freeCancellation;
        this.availability = availability;

    }
}
