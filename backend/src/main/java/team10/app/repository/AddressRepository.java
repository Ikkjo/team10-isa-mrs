package team10.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import team10.app.model.Address;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {

    @Query("select a from Address a where a.address = ?1 and a.city = ?2 and a.country = ?3")
    Optional<Address> getAddressByAddressCityCountry(String address, String city, String country);
}
