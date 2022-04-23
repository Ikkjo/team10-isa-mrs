package team10.app.model;

import static team10.app.model.UserRole.HOUSE_OWNER;

public class VacationHomeOwner extends BusinessPartner {

    public VacationHomeOwner(String firstName, String lastName, String email, String password) {
        super(firstName, lastName, email, password, HOUSE_OWNER);
    }
}
