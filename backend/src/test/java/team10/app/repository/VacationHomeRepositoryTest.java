package team10.app.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import team10.app.dto.AddressDto;
import team10.app.dto.VacationHomeDto;
import team10.app.model.*;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class VacationHomeRepositoryTest {

    @Autowired
    private VacationHomeRepository vacationHomeRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private VacationHomeOwnerRepository vacationHomeOwnerRepository;

    @AfterEach
    void tearDown() {
        addressRepository.deleteAll();
        vacationHomeOwnerRepository.deleteAll();
        vacationHomeRepository.deleteAll();
    }

    @Test
    void itShouldNotFindVacationHomeByAddress() {
        Address address = new Address("1", "2", "3");
        addressRepository.save(address);
        assertThat(vacationHomeRepository.findByAddress(address)).isEmpty();
    }
}