package team10.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import team10.app.model.DeletionRequest;

import java.util.UUID;

public interface DeletionRequestRepository extends JpaRepository<DeletionRequest, UUID> {
    @Modifying
    @Query("update DeletionRequest dr set dr.reviewed = true where dr.id = ?1")
    void review(UUID id);
}
