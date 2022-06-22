package team10.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import team10.app.model.UserRole;

@Getter
@Setter
@NoArgsConstructor
public abstract class RegistrationRequestDto {
    protected String firstName;
    protected String lastName;
    protected String email;
    protected String password;
    protected UserRole role;
    protected String phoneNumber;

}
