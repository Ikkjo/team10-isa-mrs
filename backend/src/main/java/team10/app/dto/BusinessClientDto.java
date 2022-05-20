package team10.app.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import team10.app.model.Address;
import team10.app.model.BusinessClient;
import team10.app.model.Loyalty;
import team10.app.model.UserRole;

@Getter
@Setter
@NoArgsConstructor
public class BusinessClientDto {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String userRole;
    private AddressDto address;
    private String dateOfBirth;
//    private Loyalty loyalty;

    public BusinessClientDto(BusinessClient businessClient) {
        this.firstName = businessClient.getFirstName();
        this.lastName = businessClient.getLastName();
        this.email = businessClient.getEmail();
        this.phoneNumber = businessClient.getPhoneNumber();
        this.userRole = businessClient.getUserRole().name();
        this.address = new AddressDto(businessClient.getAddress());
        this.dateOfBirth = businessClient.getDateOfBirth();
//        this.loyalty = businessClient.getLoyalty();
    }
}
