package team10.app.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import team10.app.model.*;
import team10.app.repository.ShipOwnerRepository;
import team10.app.repository.UserRepository;
import team10.app.repository.VacationHomeOwnerRepository;

import static team10.app.model.UserRole.*;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final VacationHomeOwnerRepository vacationHomeOwnerRepository;
    private final ShipOwnerRepository shipOwnerRepository;
    private final FishingInstructorRepository fishingInstructorRepository;

    public boolean userExists(User user) {
        return userRepository.findByEmail(user.getEmail()).isPresent()
                && userRepository.findByUsername(user.getUsername()).isPresent();
    }

    public void saveUser(User user) throws IllegalStateException {
        if (user.getUserRole().equals(HOUSE_OWNER))
            vacationHomeOwnerRepository.save(new VacationHomeOwner(user));
        else if (user.getUserRole().equals(SHIP_OWNER))
            shipOwnerRepository.save(new ShipOwner(user));
        else if (user.getUserRole().equals(FISHING_INSTRUCTOR))
            fishingInstructorRepository.save(new FishingInstructor(user));
        else
            throw new IllegalStateException("Error! User type is not BusinessUser.");

    }
}
