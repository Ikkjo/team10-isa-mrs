package team10.app.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import team10.app.dto.*;
import team10.app.model.*;
import team10.app.repository.*;
import team10.app.util.Validator;
import team10.app.util.exceptions.*;

import java.util.Optional;
import java.util.UUID;

import static team10.app.model.UserRole.*;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final VacationHomeOwnerRepository vacationHomeOwnerRepository;
    private final ShipOwnerRepository shipOwnerRepository;
    private final FishingInstructorRepository fishingInstructorRepository;
    private final ClientRepository clientRepository;
    private final Validator validator;
    private final AddressRepository addressRepository;
    private final LoyaltyRepository loyaltyRepository;
    private final DeletionRequestRepository deletionRequestRepository;
    private final PasswordEncoder passwordEncoder;
    private final AddressService addressService;

    public BusinessClient buildBusinessUser(BusinessClientRegistrationRequestDto dto) throws IllegalArgumentException {
            Address address = addressService.getAddress(new AddressDto(dto.getAddress(), dto.getCity(), dto.getCountry()));
            if (dto.getRole().equals(HOUSE_OWNER))
                return new VacationHomeOwner(dto.getFirstName(), dto.getLastName(), dto.getEmail(), dto.getPassword(),
                        dto.getPhoneNumber(), address, dto.getDateOfBirth());
            else if (dto.getRole().equals(SHIP_OWNER))
                return new ShipOwner(dto.getFirstName(), dto.getLastName(), dto.getEmail(), dto.getPassword(),
                        dto.getPhoneNumber(), address, dto.getDateOfBirth());
            else if (dto.getRole().equals(FISHING_INSTRUCTOR))
                return new FishingInstructor(dto.getFirstName(), dto.getLastName(), dto.getEmail(), dto.getPassword(),
                        dto.getPhoneNumber(), address, dto.getDateOfBirth());
            else
                throw new IllegalArgumentException();
    }

    public Client buildClient(ClientRegistrationRequestDto dto) throws IllegalArgumentException {
        return new Client(dto.getFirstName(), dto.getLastName(), dto.getEmail(), dto.getPassword(), dto.getPhoneNumber());
    }

    public Admin buildAdmin(AdminRegistrationDto dto) throws IllegalArgumentException {
        return new Admin(dto.getFirstName(), dto.getLastName(), dto.getEmail(), dto.getPassword());
    }

    public void saveBusinessUser(BusinessClient user) {
        if (user.getRole().equals(HOUSE_OWNER))
            vacationHomeOwnerRepository.save(new VacationHomeOwner(user));
        else if (user.getRole().equals(SHIP_OWNER))
            shipOwnerRepository.save(new ShipOwner(user));
        else if (user.getRole().equals(FISHING_INSTRUCTOR))
            fishingInstructorRepository.save(new FishingInstructor(user));
    }

    public void saveClient(User user) {
        clientRepository.save(new Client(user));
    }

    public Optional<? extends BusinessClient> getBusinessPartnerByEmail(String email, UserRole userRole) {
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

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found!"));
    }

    public boolean userExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    public void updateEmail(String newEmail, String email) {
        if (!validator.validateEmail(newEmail))
            throw new EmailInvalidException(newEmail);
        if (userRepository.findByEmail(newEmail).isPresent())
            throw new EmailTakenException(newEmail);
        userRepository.updateEmail(newEmail, email);
    }

    public void updatePassword(PasswordChangeDto passwordChangeDto, String email) {
        if (!validator.validatePassword(passwordChangeDto.getNewPassword()))
            throw new PasswordInvalidException(passwordChangeDto.getNewPassword());

        String encodedPassword = passwordEncoder.encode(passwordChangeDto.getCurrentPassword());
        if (!passwordEncoder.matches(passwordChangeDto.getCurrentPassword(), userRepository.getByEmail(email).getPassword()))
            throw new PasswordInvalidException(passwordChangeDto.getCurrentPassword());
        userRepository.updatePassword(encodedPassword, email);
    }

    public void updateFirstName(String firstName, String email) {
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

    public void deleteUser(String email) {
        userRepository.deleteUser(email);
    }

    public void requestDeletion(String deletionReason, String email) {
        if (!validator.validateDeletionReason(deletionReason))
            throw new DeletionReasonInvalidException(deletionReason);
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email));
        if (deletionRequestRepository.hasActiveDeletionRequest(user.getId()))
            throw new DeletionRequestAlreadyPresentException(email);
        DeletionRequest deletionRequest = new DeletionRequest(user, deletionReason);
        deletionRequestRepository.save(deletionRequest);
    }
  
}
