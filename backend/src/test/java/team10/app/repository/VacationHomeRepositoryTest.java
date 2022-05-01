package team10.app.repository;

import org.assertj.core.util.Lists;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import team10.app.dto.VacationHomeDTO;
import team10.app.model.Address;
import team10.app.model.VacationHome;

import java.util.Arrays;
import java.util.Optional;

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
        VacationHomeDTO vacationHomeDTO = new VacationHomeDTO(
                "Stan na dan",
                new Address("Ulica b.b.", "Grad", "Drzava"),
                "Stan na dan za jedan dan stan",
                "Ponasalje mora biti lijepo",
                "Svasta nesto nudimo",
                12,
                Arrays.asList("12", "2"),
                10,
                20
        );
        VacationHome vacationHome = new VacationHome(vacationHomeDTO);

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