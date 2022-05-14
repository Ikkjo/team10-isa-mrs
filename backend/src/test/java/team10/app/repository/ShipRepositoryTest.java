package team10.app.repository;

import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import team10.app.dto.ShipDto;
import team10.app.model.Address;
import team10.app.model.Picture;
import team10.app.model.Ship;
import team10.app.model.VacationHome;

import java.nio.charset.StandardCharsets;
import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@DataJpaTest
class ShipRepositoryTest {

    @Autowired
    private ShipRepository shipRepository;
    @Autowired
    private AddressRepository addressRepository;

    @AfterEach
    void tearDown() {
        addressRepository.deleteAll();
        shipRepository.deleteAll();
    }

    @Test
    void itShouldFindShipByAddress() {
        Address address = new Address("Bulevar Oslobodjena", "Novi Sad", "Serbia");
        Ship ship = new Ship(
                "Fishinig ship",
                address,
                "Some description",
                "Rules",
                "Services",
                12,
                new HashSet<>(Arrays.asList(new Picture(), new Picture())),
                "Type",
                12.1,
                2,
                100,
                50,
                "gps, radar",
                "bait, rods",
                10,
                false
        );
        addressRepository.save(ship.getAddress());
        shipRepository.save(ship);
        assertThat(shipRepository.findByAddress(ship.getAddress())).isEqualTo(Optional.of(ship));

    }

    @Test
    void itShouldNotFindShipByAddress() {
        Address address = new Address("1", "2", "3");
        addressRepository.save(address);
        assertThat(shipRepository.findByAddress(address)).isEmpty();
    }
}