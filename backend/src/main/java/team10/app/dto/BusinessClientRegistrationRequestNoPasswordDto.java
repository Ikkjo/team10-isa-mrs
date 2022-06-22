package team10.app.dto;

import lombok.Getter;
import lombok.Setter;
import team10.app.model.RegistrationRequest;
import team10.app.model.UserRole;

@Getter
@Setter
public class BusinessClientRegistrationRequestNoPasswordDto {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private UserRole role;
    private String phoneNumber;
    private String dateOfBirth;
    private AddressDto addressDto;
    private String registrationReason;

    public BusinessClientRegistrationRequestNoPasswordDto(RegistrationRequest rr) {
        this.id = rr.getId().toString();
        this.firstName = rr.getBusinessClient().getFirstName();
        this.lastName = rr.getBusinessClient().getLastName();
        this.email = rr.getBusinessClient().getEmail();
        this.role = rr.getBusinessClient().getRole();
        this.phoneNumber = rr.getBusinessClient().getPhoneNumber();
        this.dateOfBirth = rr.getBusinessClient().getDateOfBirth();
        this.addressDto = new AddressDto(rr.getBusinessClient().getAddress());
        this.registrationReason = rr.getDescription();
    }
}
