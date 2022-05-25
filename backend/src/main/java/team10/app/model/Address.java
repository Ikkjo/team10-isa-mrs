package team10.app.model;

import lombok.*;
import team10.app.dto.AddressDto;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false, unique = true)
    private String address;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private String country;

    public Address(String address, String city, String country) {
        this.address = address;
        this.city = city;
        this.country = country;
    }

    public Address(AddressDto addressDto) {
        this.address = addressDto.getAddress();
        this.city = addressDto.getCity();
        this.country = addressDto.getCountry();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address1 = (Address) o;
        return address.equals(address1.address) && city.equals(address1.city) && country.equals(address1.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, city, country);
    }
}
