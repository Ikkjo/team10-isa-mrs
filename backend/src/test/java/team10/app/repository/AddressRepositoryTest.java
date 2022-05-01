package team10.app.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import team10.app.model.Address;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class AddressRepositoryTest {

    @Autowired
    private AddressRepository addressRepository;

    @AfterEach
    void tearDown() {
        addressRepository.deleteAll();
    }

    @Test
    void shouldGetAddressByAddressCityCountry() {
        Address address = new Address("Bulevar Oslobodjenja 2", "Novi Sad", "Serbia");
        addressRepository.save(address);
        assertThat(addressRepository.getAddressByAddressCityCountry(
                address.getAddress(),
                address.getCity(),
                address.getCountry())).isEqualTo(Optional.of(address));
    }

    @Test
    void shouldNotGetAddressByAddressCityCountry() {
        Address address = new Address("Bulevar Oslobodjenja 2", "Novi Sad", "Serbia");
        assertThat(addressRepository.getAddressByAddressCityCountry(
                address.getAddress(),
                address.getCity(),
                address.getCountry())).isEmpty();
    }
}