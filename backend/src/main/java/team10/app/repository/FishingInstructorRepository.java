package team10.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import team10.app.model.FishingInstructor;

import java.util.Optional;
import java.util.UUID;

@Repository
@Transactional(readOnly = true) // provjeriti da li treba
public interface FishingInstructorRepository extends JpaRepository<FishingInstructor, UUID> {

        Optional<FishingInstructor> findByEmail(String email);

        @Transactional
        @Modifying
        @Query("UPDATE User u SET u.enabled = TRUE WHERE u.email = ?1")
        int enableFishingInstructor(String email);

}
