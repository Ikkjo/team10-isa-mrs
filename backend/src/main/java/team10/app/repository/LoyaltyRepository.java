package team10.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import team10.app.model.Loyalty;

import java.util.UUID;

public interface LoyaltyRepository extends JpaRepository<Loyalty, UUID> {

    @Modifying
    @Query("update Loyalty l set l.title=?2 where l.id=?1")
    void updateTitle(UUID loyaltyId, String newTitle);

    @Modifying
    @Query("update Loyalty l set l.availableAtPoints=?2 where l.id=?1")
    void updateAvailableAtPoints(UUID loyaltyId, int points);

    @Modifying
    @Query("update Loyalty l set l.clientDiscount=?2 where l.id=?1")
    void updateClientDiscount(UUID loyaltyId, double percentage);

    @Modifying
    @Query("update Loyalty l set l.businessClientCut=?2 where l.id=?1")
    void updateBusinessClientCut(UUID loyaltyId, double percentage);

    @Modifying
    @Query("update Loyalty l set l.businessClientPointsPerReservation=?2 where l.id=?1")
    void updateBusinessClientPointsPerReservation(UUID loyaltyId, int points);

    @Modifying
    @Query("update Loyalty l set l.clientPointsPerReservation=?2 where l.id=?1")
    void updateClientPointsPerReservation(UUID loyaltyId, int points);

    @Modifying
    @Query("update Loyalty l set l.color=?2 where l.id=?1")
    void updateColor(UUID loyaltyId, String color);

    @Query("select count(l)>0 from Loyalty l where l.title=?1")
    boolean existsByTitle(String title);
}
