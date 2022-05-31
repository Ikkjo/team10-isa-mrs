package team10.app.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "Admin")
@Table(name = "admins")
public class Admin extends User {
    private boolean main;
    private boolean initialPasswordChanged;

    public Admin(String firstName, String lastName, String email, String password) {
        super(firstName, lastName, email, password, UserRole.ADMIN);
        this.main = false;
        this.initialPasswordChanged = false;
    }
}
