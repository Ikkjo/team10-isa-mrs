package team10.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team10.app.model.BusinessClient;

import java.util.Optional;
import java.util.UUID;

public interface BusinessClientRepository extends JpaRepository<BusinessClient, UUID> {

    Optional<BusinessClient> findByEmail(String email);

}
