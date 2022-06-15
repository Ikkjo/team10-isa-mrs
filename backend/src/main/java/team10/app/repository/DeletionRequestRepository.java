package team10.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import team10.app.model.DeletionRequest;

import java.util.List;
import java.util.UUID;

@Repository
public interface DeletionRequestRepository extends JpaRepository<DeletionRequest, UUID> {
    @Modifying
    @Query("update DeletionRequest dr set dr.reviewed = true where dr.id = ?1")
    void review(UUID id);

    @Query(value="select count(dr) > 1 from deletion_request dr where dr.reviewed=false and dr.user_id= ?1", nativeQuery=true)
    boolean hasActiveDeletionRequest(UUID userId);

    @Query("select dr from DeletionRequest dr where dr.reviewed = false")
    List<DeletionRequest> findAllNotReviewed();
}
