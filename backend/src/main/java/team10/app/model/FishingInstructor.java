package team10.app.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class FishingInstructor extends BusinessPartner {
    private List<Adventure> adventures;

    public FishingInstructor(String firstName, String lastName, String email, String password) {
        super(firstName, lastName, email, password, UserRole.FISHING_INSTRUCTOR);
        adventures = new ArrayList<Adventure>();
    }
}
