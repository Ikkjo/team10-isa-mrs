package team10.app.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import team10.app.dto.VacationHomeDto;
import team10.app.model.Address;
import team10.app.model.Picture;
import team10.app.model.VacationHome;

import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class VacationHomeRepositoryTest {

    @Autowired
    private VacationHomeRepository vacationHomeRepository;
    @Autowired
    private AddressRepository addressRepository;

    @AfterEach
    void tearDown() {
        addressRepository.deleteAll();
        vacationHomeRepository.deleteAll();
    }

    @Test
    void itShouldFindVacationHomeByAddress() {
        Picture picture = new Picture("jpeg", "123".getBytes(StandardCharsets.UTF_8));
        Set<Picture> pictureSet = new HashSet<>(List.of(picture));

        VacationHomeDto vacationHomeDTO = new VacationHomeDto(
                "Stan na dan",
                new Address("Ulica b.b.", "Grad", "Drzava"),
                "Stan na dan za jedan dan stan",
                "Ponasalje mora biti lijepo",
                "Svasta nesto nudimo",
                100,
                List.of("jpeg,123"),
                5,
                10
        );

        VacationHome vacationHome = new VacationHome(
                vacationHomeDTO.getTitle(),
                vacationHomeDTO.getAddress(),
                vacationHomeDTO.getDescription(),
                vacationHomeDTO.getRulesOfConduct(),
                vacationHomeDTO.getAdditionalServices(),
                vacationHomeDTO.getPrice(),
                pictureSet,
                vacationHomeDTO.getRooms(),
                vacationHomeDTO.getBeds()
        );

        addressRepository.save(vacationHome.getAddress());
        vacationHomeRepository.save(vacationHome);

        assertThat(vacationHomeRepository.findByAddress(vacationHome.getAddress())).isEqualTo(Optional.of(vacationHome));
    }

    @Test
    void itShouldNotFindVacationHomeByAddress() {
        Address address = new Address("1", "2", "3");
        addressRepository.save(address);
        assertThat(vacationHomeRepository.findByAddress(address)).isEmpty();
    }
}