package team10.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team10.app.model.BusinessClient;
import team10.app.model.RentalEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RentalEntityRepository extends JpaRepository<RentalEntity, UUID> {
    @Override
    Optional<RentalEntity> findById(UUID uuid);

    List<RentalEntity> findAllByOwner(BusinessClient businessClient);
}
