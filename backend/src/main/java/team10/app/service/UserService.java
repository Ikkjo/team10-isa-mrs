package team10.app.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import team10.app.dto.RegistrationRequestDto;
import team10.app.model.*;
import team10.app.repository.ShipOwnerRepository;
import team10.app.repository.UserRepository;
import team10.app.repository.VacationHomeOwnerRepository;

import java.util.Optional;

import static team10.app.model.UserRole.*;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final VacationHomeOwnerRepository vacationHomeOwnerRepository;
    private final ShipOwnerRepository shipOwnerRepository;
//    private final FishingInstructorRepository fishingInstructorRepository;

    public boolean userExists(String email) {
        return userRepository.userExists(email);
    }

    public User buildUser(RegistrationRequestDto rr) {
            if (rr.getRole().equals(HOUSE_OWNER))
                return new VacationHomeOwner(rr.getFirstname(), rr.getLastname(), rr.getEmail(), rr.getPassword());
            else if (rr.getRole().equals(SHIP_OWNER))
                return new ShipOwner(rr.getFirstname(), rr.getLastname(), rr.getEmail(), rr.getPassword());
            else if (rr.getRole().equals(FISHING_INSTRUCTOR))
                return new FishingInstructor(rr.getFirstname(), rr.getLastname(), rr.getEmail(), rr.getPassword());
            // TODO: Add Client builder
            // TODO: Add exception
            else
                return null;

    }

    public void saveUser(User user) throws IllegalStateException {
        if (user.getUserRole().equals(HOUSE_OWNER))
            vacationHomeOwnerRepository.save(new VacationHomeOwner(user));
        else if (user.getUserRole().equals(SHIP_OWNER))
            shipOwnerRepository.save(new ShipOwner(user));
//        else if (user.getUserRole().equals(FISHING_INSTRUCTOR))
//            fishingInstructorRepository.save(new FishingInstructor(user));
        else
            throw new IllegalStateException("Error! User type is not BusinessUser.");

    }

    public Optional<? extends User> getByEmail(String email, UserRole userRole) {
        if (userRole.equals(HOUSE_OWNER))
            return vacationHomeOwnerRepository.findByEmail(email);
        else if (userRole.equals(SHIP_OWNER))
            return shipOwnerRepository.findByEmail(email);
//        else if (userRole.equals(FISHING_INSTRUCTOR))
//            fishingInstructorRepository.findByEmail(email);
        else
            throw new IllegalStateException("Error! User type is not BusinessUser.");
    }
}
