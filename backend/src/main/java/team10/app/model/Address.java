package team10.app.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name="addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
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
}
