package team10.app.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static team10.app.model.UserRole.HOUSE_OWNER;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class VacationHomeOwner extends BusinessClient {

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<VacationHome> vacationHomes = new HashSet<>();

    public VacationHomeOwner(String firstName, String lastName, String email, String password, UserRole appUserRole, String phoneNumber, Address address, String dateOfBirth, Set<VacationHome> vacationHomes) {
        super(firstName, lastName, email, password, appUserRole, phoneNumber, address, dateOfBirth);
        this.vacationHomes = vacationHomes;
    }

    public VacationHomeOwner(String firstName, String lastName, String email, String password, String phoneNumber, Address address, String dateOfBirth) {
        super(firstName, lastName, email, password, HOUSE_OWNER, phoneNumber, address, dateOfBirth);
    }

    public VacationHomeOwner(BusinessClient user) {
        super(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(), HOUSE_OWNER, user.getPhoneNumber(), user.getAddress(), user.getDateOfBirth());
    }

    public void addVacationHome(VacationHome vacationHome) {
        this.vacationHomes.add(vacationHome);
    }
}
