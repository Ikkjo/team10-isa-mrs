package team10.app.repository;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import team10.app.model.User;
import team10.app.model.VacationHomeOwner;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    @Query("select u from User u where u.email = ?1")
    Optional<User> findByEmail(String email);

    @Modifying
    @Query("update User u set u.firstName = ?1 where u.email = ?2")
    void updateFirstName(String firstName, String email);

    @Modifying
    @Query("update User u set u.lastName = ?1 where u.email = ?2")
    void updateLastName(String firstName, String email);

    @Modifying
    @Query("update User u set u.phoneNumber = ?1 where u.email = ?2")
    void updatePhoneNumber(String phoneNumber, String email);
}
