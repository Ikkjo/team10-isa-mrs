package team10.app.repository;

import org.springframework.stereotype.Repository;
import team10.app.model.ShipOwner;
import team10.app.model.User;

import java.util.Optional;

@Repository
public interface UserRepository {

    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);

}
