package team10.app.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import team10.app.model.BusinessClient;

@Getter
@Setter
public class BusinessClientDto extends UserDto {

    private AddressDto address;
    private String dateOfBirth;

    public BusinessClientDto(BusinessClient businessClient) {
        super(businessClient);
        this.address = new AddressDto(businessClient.getAddress());
        this.dateOfBirth = businessClient.getDateOfBirth();
    }
}
