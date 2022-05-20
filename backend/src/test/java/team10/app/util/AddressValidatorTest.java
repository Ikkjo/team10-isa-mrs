package team10.app.util;

import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import team10.app.model.Address;
import team10.app.model.VacationHome;
import team10.app.repository.AddressRepository;
import team10.app.repository.AdventureRepository;
import team10.app.repository.ShipRepository;
import team10.app.repository.VacationHomeRepository;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AddressValidatorTest {

    @Mock
    private AddressRepository addressRepository;
    @Mock
    private VacationHomeRepository vacationHomeRepository;
    @Mock
    private ShipRepository shipRepository;
    @Mock
    private AdventureRepository adventureRepository;
    private AddressValidator addressValidator;

    @BeforeEach
    void setUp() {
        addressValidator = new AddressValidator(addressRepository, vacationHomeRepository, shipRepository, adventureRepository);
    }

    @Test
    void shouldReturnDoesntContainAddress() {
        assertTrue(addressValidator.test(new Address("123", "123", "123")));
    }

    @Test
    void shouldReturnContainsAddress() {
        when(addressRepository.getAddressByAddressCityCountry(Mockito.anyString(), Mockito.anyString(), Mockito.anyString())).thenReturn(Optional.of(new Address("123", "123", "123")));
        assertThat(addressValidator.test(new Address("123", "123", "123"))).isTrue();
    }

    @Test
    void testVacationHome_AddressNotTaken() {
        assertThat(addressValidator.testVacationHome(new Address("123", "123", "123"))).isFalse();
    }

    @Test
    void testVacationHome_AddressTaken1() {
        Address address = new Address("123", "123", "123");
        VacationHome vacationHome = new VacationHome();
        when(vacationHomeRepository.findByAddress(Mockito.any(Address.class))).thenReturn(Optional.of(vacationHome));
        assertThat(addressValidator.testVacationHome(address)).isFalse();
    }

    @Test
    void testVacationHome_AddressTaken2() {
        VacationHome vacationHome = new VacationHome();
        when(vacationHomeRepository.findByAddress(Mockito.any(Address.class))).thenReturn(Optional.of(vacationHome));
        assertThat(addressValidator.testVacationHome(new Address("123", "123", "123"))).isFalse();
    }
}