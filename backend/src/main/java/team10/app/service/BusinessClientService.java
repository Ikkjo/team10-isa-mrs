package team10.app.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import team10.app.dto.AddressDto;
import team10.app.dto.BusinessClientDto;
import team10.app.model.Address;
import team10.app.model.BusinessClient;
import team10.app.repository.BusinessClientRepository;
import team10.app.repository.UserRepository;
import team10.app.util.Validator;
import team10.app.util.exceptions.FirstNameInvalidException;
import team10.app.util.exceptions.PhoneNumberInvalidException;

@Service
@AllArgsConstructor
public class BusinessClientService {

    private final BusinessClientRepository businessClientRepository;
    private final UserRepository userRepository;
    private final AddressService addressService;
    private final Validator validator;

    public BusinessClientDto getUserDetails(String email) throws UsernameNotFoundException {
        BusinessClient businessClient = businessClientRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("VacationHomeOwner not found!"));
        return new BusinessClientDto(businessClient);
    }

    public void updateFirstName(String firstName, String email) throws RuntimeException {
        if (!validator.validateFirstName(firstName))
            throw new FirstNameInvalidException(firstName);
        userRepository.updateFirstName(firstName, email);
    }

    public void updateLastName(String lastName, String email) {
        if (!validator.validateLastName(lastName))
            throw new FirstNameInvalidException(lastName);
        userRepository.updateLastName(lastName, email);

    }

    public void updatePhoneNumber(String phoneNumber, String email) {
        if (!validator.validatePhoneNumber(phoneNumber))
            throw new PhoneNumberInvalidException(phoneNumber);
        userRepository.updatePhoneNumber(phoneNumber, email);
    }

    public void updateAddress(AddressDto addressDto, String email) {
        businessClientRepository.updateAddress(addressService.getAddress(addressDto), email);
    }
}
