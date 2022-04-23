package team10.app.model;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;

import static team10.app.model.UserRole.HOUSE_OWNER;

@Entity
@NoArgsConstructor
public class VacationHomeOwner extends BusinessPartner {

    public VacationHomeOwner(String firstName, String lastName, String email, String password) {
        super(firstName, lastName, email, password, HOUSE_OWNER);
    }

    public VacationHomeOwner(User user) {
        super(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(), HOUSE_OWNER);
    }
}
