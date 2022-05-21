package team10.app.model;

import lombok.*;
import team10.app.dto.AddressDto;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name="addresses")
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
}
