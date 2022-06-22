package team10.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import team10.app.model.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ShipRepository extends JpaRepository<Ship, UUID> {

    @Query("select s from Ship s where s.address = ?1")
    Optional<Ship> findByAddress(Address address);

    @Query("select s from Ship s where s.address.address = ?1 and s.address.city = ?2 and s.address.country = ?3")
    Optional<Ship> findByAddress(String address, String city, String country);

    @Query("select s from Ship s where s.owner = ?1 and s.deleted = false")
    List<Ship> findAllByOwner(ShipOwner owner);

    @Modifying
    @Query("update Ship s set s.type = ?1 where s.id = ?2")
    void updateType(String type, UUID id);

    @Modifying
    @Query("update Ship s set s.length = ?1 where s.id = ?2")
    void updateLength(double length, UUID id);

    @Modifying
    @Query("update Ship s set s.engineCount = ?1 where s.id = ?2")
    void updateEngineCount(int engineCount, UUID id);

    @Modifying
    @Query("update Ship s set s.enginePower = ?1 where s.id = ?2")
    void updateEnginePower(int enginePower, UUID id);

    @Modifying
    @Query("update Ship s set s.maxSpeed = ?1 where s.id = ?2")
    void updateMaxSpeed(int maxSpeed, UUID id);

    @Modifying
    @Query("update Ship s set s.navigationEquipment = ?1 where s.id = ?2")
    void updateNavigationEquipment(String navigationEquipment, UUID id);

    @Modifying
    @Query("update Ship s set s.fishingEquipment = ?1 where s.id = ?2")
    void updateFishingEquipment(String fishingEquipment, UUID id);

    @Modifying
    @Query("update Ship s set s.capacity = ?1 where s.id = ?2")
    void updateCapacity(int capacity, UUID id);

    @Modifying
    @Query("update Ship s set s.freeCancellation = ?1 where s.id = ?2")
    void updateFreeCancellation(Boolean isFreeCancellation, UUID id);
}
