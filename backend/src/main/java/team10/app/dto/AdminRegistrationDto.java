package team10.app.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import team10.app.model.Admin;

@Getter
@Setter
@NoArgsConstructor
public class AdminRegistrationDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String role;

    public AdminRegistrationDto(Admin admin) {
        this.firstName = admin.getFirstName();
        this.lastName = admin.getLastName();
        this.email = admin.getEmail();
        this.password = admin.getPassword();
        this.role = admin.getRole().name();
    }
}
