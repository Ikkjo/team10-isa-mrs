package team10.app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import team10.app.model.Loyalty;

import java.util.UUID;

public interface LoyaltyRepository extends JpaRepository<Loyalty, UUID> {

    Loyalty findLoyaltyById(UUID id);

    Page<Loyalty> findAll(Pageable pageable);

}
