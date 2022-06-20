package team10.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team10.app.model.Loyalty;

import java.util.UUID;

public interface LoyaltyRepository extends JpaRepository<Loyalty, UUID> {


}
