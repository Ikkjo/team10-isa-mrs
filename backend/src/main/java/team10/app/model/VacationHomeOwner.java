package team10.app.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import team10.app.model.VacationHome;

import javax.persistence.*;

import java.util.Set;

import static team10.app.model.UserRole.HOUSE_OWNER;

@Entity
@NoArgsConstructor
public class VacationHomeOwner extends BusinessPartner {

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "vacation_home_ownner_homes", joinColumns = @JoinColumn(name = "vacation_home_owner_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "vacation_home_id", referencedColumnName = "id"))
    private Set<VacationHome> vacationHomes;

    public VacationHomeOwner(String firstName, String lastName, String email, String password) {
        super(firstName, lastName, email, password, HOUSE_OWNER);
    }

    public VacationHomeOwner(User user) {
        super(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(), HOUSE_OWNER);
    }
}
