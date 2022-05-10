package team10.app.dto;

import lombok.Getter;
import lombok.Setter;
import team10.app.model.UserRole;

@Getter
@Setter
public class RegistrationRequestDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private UserRole role;
    private String phone;
    private String dateOfBirth;
    private String address;
    private String city;
    private String country;
    private String registrationReason;
}
