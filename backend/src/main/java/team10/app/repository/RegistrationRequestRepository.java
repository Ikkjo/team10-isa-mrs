package team10.app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import team10.app.model.RegistrationRequest;

import java.util.List;
import java.util.UUID;

@Repository
public interface RegistrationRequestRepository extends JpaRepository<RegistrationRequest, UUID> {
    @Modifying
    @Query("update RegistrationRequest rr set rr.reviewed = true where rr.id = ?1")
    void review(UUID id);

    @Query(
            value = "select rr from RegistrationRequest rr where rr.reviewed = false",
            countQuery = "select count(rr) from RegistrationRequest rr where rr.reviewed = false"
    )
    Page<RegistrationRequest> findAllNotReviewed(Pageable pageable);
}
