package team10.app.dto;

import lombok.Getter;
import team10.app.model.UserRole;

@Getter
public abstract class RegistrationRequestDto {
    protected String firstName;
    protected String lastName;
    protected String email;
    protected String password;
    protected UserRole role;
    protected String phoneNumber;

}
