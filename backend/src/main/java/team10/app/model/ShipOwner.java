package team10.app.model;

import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;

import static team10.app.model.UserRole.SHIP_OWNER;

@Entity
@NoArgsConstructor
public class ShipOwner extends BusinessClient {

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "ship_owner_ships", joinColumns = @JoinColumn(name = "ship_owner_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "ship_id", referencedColumnName = "id"))
    private Set<VacationHome> ships;

    public ShipOwner(String firstName, String lastName, String email, String password, String phoneNumber, Address address) {
        super(firstName, lastName, email, password, SHIP_OWNER, phoneNumber, address);
        this.ships = new HashSet<>();
    }

    public ShipOwner(BusinessClient user) {
        super(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(), SHIP_OWNER, user.getPhoneNumber(), user.getAddress());
        this.ships = new HashSet<>();
    }
}
