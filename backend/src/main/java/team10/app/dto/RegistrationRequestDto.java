package team10.app.dto;

import lombok.Getter;
import lombok.Setter;
import team10.app.model.UserRole;

@Getter
@Setter
public class RegistrationRequestDto {
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private UserRole role;
}
