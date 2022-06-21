package team10.app.dto;

import lombok.Getter;
import lombok.Setter;
import team10.app.model.RentalEntity;

import java.util.UUID;

@Getter
@Setter
public class AdminRentalEntityDto {
    private UUID id;
    private String type;
    private String title;
    private String owner;
    private String address;
    private String city;
    private String country;
    private int price;
    private boolean deleted;

    public AdminRentalEntityDto(RentalEntity rentalEntity) {
        this.id = rentalEntity.getId();
        this.type = rentalEntity.getClass().getSimpleName();
        this.title = rentalEntity.getTitle();
        this.owner = rentalEntity.getOwner().getEmail();
        this.address = rentalEntity.getAddress().getAddress();
        this.city = rentalEntity.getAddress().getCity();
        this.country = rentalEntity.getAddress().getCountry();
        this.price = rentalEntity.getPrice();
        this.deleted = rentalEntity.isDeleted();
    }
}
