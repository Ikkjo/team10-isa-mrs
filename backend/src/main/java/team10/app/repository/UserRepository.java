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

    private final VacationHomeOwnerRepository vacationHomeOwnerRepository;
    private final ShipOwnerRepository shipOwnerRepository;
    private final FishingInstructorRepository fishingInstructorRepository;
    private final ClientRepository clientRepository;

    public Optional<User> findByEmail(String email) {
        for (User u : getAll()) {
            if (u.getEmail().equals(email)){
                return Optional.of(u);
            }

        }
        return Optional.empty();
    }

    private Set<User> getAll() {
    Set<User> users = new HashSet<>();

    users.addAll(vacationHomeOwnerRepository.findAll());
    users.addAll(shipOwnerRepository.findAll());
    users.addAll(fishingInstructorRepository.findAll());
    users.addAll(clientRepository.findAll());

    return users;
    }

    public boolean userExists(String email) {
        return vacationHomeOwnerRepository.findByEmail(email).isPresent()
                || shipOwnerRepository.findByEmail(email).isPresent()
                || clientRepository.findByEmail(email).isPresent();
    }

}
