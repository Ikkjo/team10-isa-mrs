package team10.app.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
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
    @ElementCollection
    @CollectionTable(name = "my_navigation_equipment", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "navigation_equipment", nullable = false)
    private Set<String> navigationEquipment;
    @ElementCollection
    @CollectionTable(name = "my_fishing_equipment", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "fishing_equipment", nullable = false)
    private Set<String> fishingEquipment;
    @Column(nullable = false)
    private int capacity;
    @Column(nullable = false)
    private boolean freeCancellation;

    public Ship(String title, Address address, String description, String rulesOfConduct, String additionalServices,
                int price, Set<Picture> pictures, String type, double length, int engineCount, int enginePower,
                int maxSpeed, Set<String> navigationEquipment, Set<String> fishingEquipment, int capacity,
                boolean freeCancellation) {
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
    }
}
