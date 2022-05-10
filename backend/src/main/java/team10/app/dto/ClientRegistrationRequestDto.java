package team10.app.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import team10.app.model.UserRole;

@Getter
@ToString
public class ClientRegistrationRequestDto extends RegistrationRequestDto {

    public ClientRegistrationRequestDto(String firstName, String lastName, String email, String password, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = UserRole.CLIENT;
        this.phoneNumber = phoneNumber;
    }

}
