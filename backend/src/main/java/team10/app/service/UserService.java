package team10.app.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import team10.app.dto.BusinessUserRegistrationRequestDto;
import team10.app.dto.RegistrationRequestDto;
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

    public boolean userExists(String email) {
        return userRepository.userExists(email);
    }

    public User buildUser(RegistrationRequestDto rr) throws IllegalArgumentException {
            if (rr.getRole().equals(HOUSE_OWNER))
                return new VacationHomeOwner(rr.getFirstName(), rr.getLastName(), rr.getEmail(), rr.getPassword());
            else if (rr.getRole().equals(SHIP_OWNER))
                return new ShipOwner(rr.getFirstName(), rr.getLastName(), rr.getEmail(), rr.getPassword());
            else if (rr.getRole().equals(FISHING_INSTRUCTOR))
                return new FishingInstructor(rr.getFirstName(), rr.getLastName(), rr.getEmail(), rr.getPassword());
            else if (rr.getRole().equals(CLIENT))
                return new Client(rr.getFirstName(), rr.getLastName(), rr.getEmail(), rr.getPassword(), rr.getPhoneNumber());
            else
                throw new IllegalArgumentException();
    }

    public void saveUser(User user) throws IllegalStateException {
        if (user.getUserRole().equals(HOUSE_OWNER))
            vacationHomeOwnerRepository.save(new VacationHomeOwner(user));
        else if (user.getUserRole().equals(SHIP_OWNER))
            shipOwnerRepository.save(new ShipOwner(user));
        else if (user.getUserRole().equals(FISHING_INSTRUCTOR))
            fishingInstructorRepository.save(new FishingInstructor(user));
        else if (user.getUserRole().equals(CLIENT))
            clientRepository.save(new Client(user));
        else
            throw new IllegalStateException("Error! User type is not BusinessUser.");

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
