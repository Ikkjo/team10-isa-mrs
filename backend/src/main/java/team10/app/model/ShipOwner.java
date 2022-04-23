package team10.app.model;

import static team10.app.model.UserRole.SHIP_OWNER;

public class ShipOwner extends BusinessPartner {

    public ShipOwner(String firstName, String lastName, String email, String password) {
        super(firstName, lastName, email, password, SHIP_OWNER);
    }

    public ShipOwner(User user) {
        super(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(), SHIP_OWNER);
    }
}
