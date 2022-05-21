package team10.app.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import team10.app.model.*;

import java.nio.charset.StandardCharsets;
import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class ShipRepositoryTest {

    @Autowired
    private ShipRepository shipRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private ShipOwnerRepository shipOwnerRepository;

    @AfterEach
    void tearDown() {
        addressRepository.deleteAll();
        shipRepository.deleteAll();
    }

    @Test
    void itShouldFindShipByAddress() {
        Address address = new Address("Bulevar Oslobodjena", "Novi Sad", "Serbia");
        Picture picture = new Picture("jpeg", "123".getBytes(StandardCharsets.UTF_8));
        Set<Picture> pictureSet = new HashSet<>(List.of(picture));
        Ship ship = new Ship(
                "Fishinig ship",
                address,
                "Some description",
                "Rules",
                "Services",
                12,
                pictureSet,
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
        ShipOwner shipOwner = new ShipOwner("Name", "Surname",
                "email@gmail.com", "123123123123", "+38166785415",
                new Address("123", "123", "123"), "12.05.2001.");
        ship.setOwner(shipOwner);
        shipOwner.setShips(Set.of(ship));
        shipOwnerRepository.save(shipOwner);
        addressRepository.save(address);
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