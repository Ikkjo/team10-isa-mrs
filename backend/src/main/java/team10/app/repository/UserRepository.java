package team10.app.repository;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import team10.app.model.User;
import team10.app.model.VacationHomeOwner;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Repository
@AllArgsConstructor
public class UserRepository {

    // TODO: Add other repos

    private final FishingInstructorRepository fishingInstructorRepository;
    private final VacationHomeOwnerRepository vacationHomeOwnerRepository;
    private final ShipOwnerRepository shipOwnerRepository;
    private final ClientRepository clientRepository;



    public boolean userExists(String email) {
        return vacationHomeOwnerRepository.findByEmail(email).isPresent()
                || shipOwnerRepository.findByEmail(email).isPresent()
                || clientRepository.findByEmail(email).isPresent();
    }

    public User getByEmail(String email) {
        for (User user : getAllUsers()) {
            if(user.getEmail().equals(email))
                    return user;
        }
        return null;
    }

    private Set<User> getAllUsers() {
        Set<User> users = new HashSet<User>();

        users.addAll(clientRepository.findAll());
        users.addAll(fishingInstructorRepository.findAll());
        users.addAll(vacationHomeOwnerRepository.findAll());
        users.addAll(shipOwnerRepository.findAll());

        return users;
    }

}
