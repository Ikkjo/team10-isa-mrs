package team10.app.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;

import static team10.app.model.UserRole.SHIP_OWNER;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class ShipOwner extends BusinessClient {

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "ship_owner_ships", joinColumns = @JoinColumn(name = "ship_owner_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "ship_id", referencedColumnName = "id"))
    private Set<Ship> ships;

    public ShipOwner(String firstName, String lastName, String email, String password, String phoneNumber, Address address, String dateOfBirth) {
        super(firstName, lastName, email, password, SHIP_OWNER, phoneNumber, address, dateOfBirth);
        this.ships = new HashSet<>();
    }

    public ShipOwner(BusinessClient user) {
        super(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(), SHIP_OWNER, user.getPhoneNumber(), user.getAddress(), user.getDateOfBirth());
        this.ships = new HashSet<>();
    }
}
