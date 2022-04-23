package team10.app.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static team10.app.model.UserRole.FISHING_INSTRUCTOR;

@Getter
@Setter
public class FishingInstructor extends BusinessPartner {
    private List<Adventure> adventures;

    public FishingInstructor(String firstName, String lastName, String email, String password) {
        super(firstName, lastName, email, password, FISHING_INSTRUCTOR);
        adventures = new ArrayList<>();
    }

    public FishingInstructor(User user) {
        super(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(), FISHING_INSTRUCTOR);
    }
}
