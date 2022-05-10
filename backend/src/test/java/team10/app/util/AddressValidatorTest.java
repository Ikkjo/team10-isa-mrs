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

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AddressValidatorTest {

    @Mock
    private AddressRepository addressRepository;
    private AddressValidator addressValidator;

    @BeforeEach
    void setUp() {
        addressValidator = new AddressValidator(addressRepository);
    }

    @Test
    void shouldReturnDoesntContainAddress() {
        assertTrue(addressValidator.test(new Address("123", "123", "123")));
    }

    @Test
    void shouldReturnContainsAddress() {
        when(addressRepository.getAddressByAddressCityCountry(Mockito.anyString(), Mockito.anyString(), Mockito.anyString())).thenReturn(Optional.of(new Address("123", "123", "123")));
        assertThat(addressValidator.test(new Address("123", "123", "123"))).isFalse();
    }
}