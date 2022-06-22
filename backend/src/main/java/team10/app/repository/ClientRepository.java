package team10.app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import team10.app.model.Client;

import java.util.Optional;
import java.util.UUID;

@Repository
//@Transactional(readOnly = true)
public interface ClientRepository extends JpaRepository<Client, UUID> {

    Optional<Client> findClientById(UUID id);

    Page<Client> findAll(Pageable pageable);

    Optional<Client> findClientByEmail(String email);

    Optional<Client> findByEmail(String email);

    @Modifying
    @Query("update Client c set c.penaltyPoints = c.penaltyPoints+1 where c.id = ?1")
    void addPenalty(UUID id);
}
