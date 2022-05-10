package team10.app.dto;

import lombok.Getter;
import lombok.Setter;
import team10.app.model.UserRole;

@Getter
@Setter
public class BusinessUserRegistrationRequestDto extends RegistrationRequestDto {
    private String dateOfBirth;
    private String address;
    private String city;
    private String country;
    private String registrationReason;

    public BusinessUserRegistrationRequestDto(String firstName, String lastName, String email, String password,
                                              UserRole role, String phoneNumber, String dateOfBirth, String address,
                                              String city, String country, String registrationReason) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.city = city;
        this.country = country;
        this.registrationReason = registrationReason;
    }
}
