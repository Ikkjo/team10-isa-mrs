package team10.app.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import team10.app.dto.BusinessUserRegistrationRequestDto;
import team10.app.dto.ClientRegistrationRequestDto;
import team10.app.model.*;
import team10.app.repository.*;

import java.util.Optional;

import static team10.app.model.UserRole.*;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final VacationHomeOwnerRepository vacationHomeOwnerRepository;
    private final ShipOwnerRepository shipOwnerRepository;
    private final FishingInstructorRepository fishingInstructorRepository;
    private final ClientRepository clientRepository;
    private final AddressRepository addressRepository;

    public boolean userExists(String email) {
        return userRepository.userExists(email);
    }

    public BusinessPartner buildBusinessUser(BusinessUserRegistrationRequestDto dto) throws IllegalArgumentException {
            if (dto.getRole().equals(HOUSE_OWNER))
                return new VacationHomeOwner(dto.getFirstName(), dto.getLastName(), dto.getEmail(), dto.getPassword(),
                        dto.getPhoneNumber(), new Address(dto.getAddress(), dto.getCity(), dto.getCountry()));
            else if (dto.getRole().equals(SHIP_OWNER))
                return new ShipOwner(dto.getFirstName(), dto.getLastName(), dto.getEmail(), dto.getPassword(),
                        dto.getPhoneNumber(), new Address(dto.getAddress(), dto.getCity(), dto.getCountry()));
            else if (dto.getRole().equals(FISHING_INSTRUCTOR))
                return new FishingInstructor(dto.getFirstName(), dto.getLastName(), dto.getEmail(), dto.getPassword(),
                        dto.getPhoneNumber(), new Address(dto.getAddress(), dto.getCity(), dto.getCountry()));
            else
                throw new IllegalArgumentException();
    }

    public Client buildClient(ClientRegistrationRequestDto dto) throws IllegalArgumentException {
        return new Client(dto.getFirstName(), dto.getLastName(), dto.getEmail(), dto.getPassword(), dto.getPhoneNumber());
    }

    public void saveUser(BusinessPartner user) throws IllegalStateException {
        if (user.getUserRole().equals(HOUSE_OWNER)) {
            addressRepository.save(user.getAddress());
            vacationHomeOwnerRepository.save(new VacationHomeOwner(user));
        }
        else if (user.getUserRole().equals(SHIP_OWNER)) {
            addressRepository.save(user.getAddress());
            shipOwnerRepository.save(new ShipOwner(user));
        }
        else if (user.getUserRole().equals(FISHING_INSTRUCTOR)){
            addressRepository.save(user.getAddress());
            fishingInstructorRepository.save(new FishingInstructor(user));
        }
        else
            throw new IllegalStateException("Error! User type is not BusinessUser.");
    }

    public void saveClient(User user) {
        clientRepository.save(new Client(user));
    }

    public Optional<? extends BusinessPartner> getBusinessPartnerByEmail(String email, UserRole userRole) {
        if (userRole.equals(HOUSE_OWNER))
            return vacationHomeOwnerRepository.findByEmail(email);
        else if (userRole.equals(SHIP_OWNER))
            return shipOwnerRepository.findByEmail(email);
        else if (userRole.equals(FISHING_INSTRUCTOR))
            return fishingInstructorRepository.findByEmail(email);
        else
            throw new IllegalStateException("Error! User type is not BusinessUser.");
    }

    public Optional<? extends Client> getClientByEmail(String email) {
        return clientRepository.findByEmail(email);
    }

    public Optional<? extends User> getByEmail(String email, UserRole userRole) {
        if (userRole.equals(HOUSE_OWNER))
            return vacationHomeOwnerRepository.findByEmail(email);
        else if (userRole.equals(SHIP_OWNER))
            return shipOwnerRepository.findByEmail(email);
        else if (userRole.equals(FISHING_INSTRUCTOR))
            return fishingInstructorRepository.findByEmail(email);
        else if (userRole.equals(CLIENT))
            return clientRepository.findByEmail(email);
        else
            throw new IllegalStateException("Error! User type is not BusinessUser.");
    }
}
