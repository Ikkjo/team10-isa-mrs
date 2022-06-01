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
public class BusinessClientDto extends UserDto {

    private AddressDto address;
    private String dateOfBirth;
//    private Loyalty loyalty;

    public BusinessClientDto(BusinessClient businessClient) {
        super(businessClient.getFirstName(), businessClient.getLastName(), businessClient.getEmail(),
                businessClient.getPhoneNumber(), businessClient.getRole().name());
        this.address = new AddressDto(businessClient.getAddress());
        this.dateOfBirth = businessClient.getDateOfBirth();
//        this.loyalty = businessClient.getLoyalty();
    }
}
