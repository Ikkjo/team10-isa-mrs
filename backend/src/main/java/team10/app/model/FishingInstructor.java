package team10.app.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static team10.app.model.UserRole.FISHING_INSTRUCTOR;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class FishingInstructor extends BusinessClient {
    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "fishing_instructor_adventures", joinColumns = @JoinColumn(name = "fishing_instructor_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "adventures_id", referencedColumnName = "id"))
    private Set<Adventure> adventures;

    public FishingInstructor(String firstName, String lastName, String email, String password, String phoneNumber, Address address, String dateOfBirth) {
        super(firstName, lastName, email, password, FISHING_INSTRUCTOR, phoneNumber, address, dateOfBirth);
        adventures = new HashSet<>();
    }

    public FishingInstructor(BusinessClient user) {
        super(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(), FISHING_INSTRUCTOR, user.getPhoneNumber(), user.getAddress(), user.getDateOfBirth());
        adventures = new HashSet<>();
    }
}
