package team10.app.model;

import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;

import static team10.app.model.UserRole.HOUSE_OWNER;

@Entity
@NoArgsConstructor
public class VacationHomeOwner extends BusinessPartner {

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "vacation_home_owner_homes", joinColumns = @JoinColumn(name = "vacation_home_owner_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "vacation_home_id", referencedColumnName = "id"))
    private Set<VacationHome> vacationHomes;

    public VacationHomeOwner(String firstName, String lastName, String email, String password, String phoneNumber, Address address) {
        super(firstName, lastName, email, password, HOUSE_OWNER, phoneNumber, address);
        this.vacationHomes = new HashSet<>();
    }

    public VacationHomeOwner(BusinessPartner user) {
        super(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(), HOUSE_OWNER, user.getPhoneNumber(), user.getAddress());
        this.vacationHomes = new HashSet<>();
    }
}
