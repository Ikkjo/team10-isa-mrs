package team10.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team10.app.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
