package team10.app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import team10.app.model.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findClientById(Long id);

    Page<Client> findAll(Pageable pageable);

    Client findClientByEmail(String email);

}
