package team10.app.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class BusinessUserRegistrationRequestDto extends RegistrationRequestDto {
    private String dateOfBirth;
    private String address;
    private String city;
    private String country;
    private String registrationReason;
}
