package team10.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import team10.app.model.Address;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {
    private String address;
    private String city;
    private String country;

    public AddressDto(Address address) {
        this.address = address.getAddress();
        this.city = address.getCity();
        this.country = address.getCountry();
    }
}
