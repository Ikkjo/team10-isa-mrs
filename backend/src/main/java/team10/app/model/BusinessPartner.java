package team10.app.model;

import lombok.Getter;

@Getter
public class BusinessPartner extends User {
    private Loyalty loyalty;

    public BusinessPartner(String firstName, String lastName, String email, String password, UserRole appUserRole) {
        super(firstName, lastName, email, password, appUserRole);
        this.loyalty = new Loyalty();
    }
}
