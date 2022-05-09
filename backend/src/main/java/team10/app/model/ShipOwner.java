package team10.app.model;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;

import static team10.app.model.UserRole.SHIP_OWNER;

@Entity
@NoArgsConstructor
public class ShipOwner extends BusinessPartner {

    public ShipOwner(String firstName, String lastName, String email, String password) {
        super(firstName, lastName, email, password, SHIP_OWNER);
    }

    public ShipOwner(User user) {
        super(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(), SHIP_OWNER);
    }
}
