package team10.app.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class BusinessPartner extends User {
    private Loyalty loyalty;

    public BusinessPartner(String firstName, String lastName, String email, String password, UserRole appUserRole) {
        super(firstName, lastName, email, password, appUserRole);
        this.loyalty = new Loyalty();
    }
}
