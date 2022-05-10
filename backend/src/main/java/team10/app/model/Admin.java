package team10.app.model;

public class Admin extends User {

    public Admin(String firstName, String lastName, String email, String password) {
        super(firstName, lastName, email, password, UserRole.ADMIN);
    }
}
