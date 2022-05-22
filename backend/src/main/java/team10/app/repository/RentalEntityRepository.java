package team10.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team10.app.model.RentalEntity;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RentalEntityRepository extends JpaRepository<RentalEntity, UUID> {
    @Override
    Optional<RentalEntity> findById(UUID uuid);
}
