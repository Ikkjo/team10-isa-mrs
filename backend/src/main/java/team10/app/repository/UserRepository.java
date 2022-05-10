package team10.app.repository;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import team10.app.model.User;
import team10.app.model.VacationHomeOwner;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class UserRepository {

    // TODO: Add other repos

    private final VacationHomeOwnerRepository vacationHomeOwnerRepository;
    private final ShipOwnerRepository shipOwnerRepository;
    private final ClientRepository clientRepository;


    public boolean userExists(String email) {
        return vacationHomeOwnerRepository.findByEmail(email).isPresent()
                || shipOwnerRepository.findByEmail(email).isPresent()
                || clientRepository.findByEmail(email).isPresent();
    }

}
