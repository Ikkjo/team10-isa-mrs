package team10.app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import team10.app.model.Client;

import java.util.Optional;

@Repository
//@Transactional(readOnly = true)
public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> findClientById(Long id);

    Page<Client> findAll(Pageable pageable);

    Optional<Client> findClientByEmail(String email);

    Optional<Client> findByEmail(String email);
}
