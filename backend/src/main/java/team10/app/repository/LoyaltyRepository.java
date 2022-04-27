package team10.app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import team10.app.model.Loyalty;

public interface LoyaltyRepository extends JpaRepository<Loyalty, Long> {

    Loyalty findLoyaltyById(Long id);

    Page<Loyalty> findAll(Pageable pageable);

}
