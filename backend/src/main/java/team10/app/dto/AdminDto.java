package team10.app.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import team10.app.model.Admin;

@Getter
@Setter
@NoArgsConstructor
public class AdminDto {
    private String firstName;
    private String lastName;
    private String email;
    private String role;
    private String phoneNumber;

    public AdminDto(Admin admin) {
        this.firstName = admin.getFirstName();
        this.lastName = admin.getLastName();
        this.email = admin.getEmail();
        this.role = admin.getRole().name();
        this.phoneNumber = admin.getPhoneNumber();
    }
}
