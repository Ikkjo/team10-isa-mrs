package team10.app.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static team10.app.model.UserRole.HOUSE_OWNER;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class VacationHomeOwner extends BusinessClient {

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "vacation_home_owner_homes", joinColumns = @JoinColumn(name = "vacation_home_owner_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "vacation_home_id", referencedColumnName = "id"))
    private Set<VacationHome> vacationHomes;

    public VacationHomeOwner(String firstName, String lastName, String email, String password, String phoneNumber, Address address, String dateOfBirth) {
        super(firstName, lastName, email, password, HOUSE_OWNER, phoneNumber, address, dateOfBirth);
        this.vacationHomes = new HashSet<>();
    }

    public VacationHomeOwner(BusinessClient user) {
        super(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(), HOUSE_OWNER, user.getPhoneNumber(), user.getAddress(), user.getDateOfBirth());
        this.vacationHomes = new HashSet<>();
    }
}
