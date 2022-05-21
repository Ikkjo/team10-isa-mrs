package team10.app.util;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import team10.app.dto.AddressDto;
import team10.app.dto.VacationHomeDto;
import team10.app.model.Address;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ValidatorTest {

    @Mock
    private AddressValidator addressValidator;
    private Validator validator;

    @BeforeEach
    void setUp() {
        validator = new Validator(addressValidator);
    }

    @Test
    void shouldValidateVacationHomeDTOAsTrue() {
        VacationHomeDto vacationHomeDTO = new VacationHomeDto(
                "Stan na dan",
                new AddressDto("Ulica b.b.", "Grad", "Drzava"),
                "Stan na dan za jedan dan stan",
                "Ponasalje mora biti lijepo",
                "Svasta nesto nudimo",
                12,
                Arrays.asList("12", "2", "1", "2", "3", "3"),
                10,
                20
        );
        when(addressValidator.test(Mockito.any(AddressDto.class))).thenReturn(true);
        assertTrue(validator.validateVacationHomeDTO(vacationHomeDTO));
    }

    @Test
    void shouldValidateVacationHomeDTOAsFalse() {
        VacationHomeDto vacationHomeDTO = new VacationHomeDto(
                "Stan",
                new AddressDto("Ulica b.b.", "Grad", "Drzava"),
                "Stan na dan za jedan dan stan",
                "Ponas",
                "Svasta nesto nudimo",
                12,
                Arrays.asList("12", "2"),
                1,
                20
        );
        when(addressValidator.test(Mockito.any(AddressDto.class))).thenReturn(false);
        assertFalse(validator.validateVacationHomeDTO(vacationHomeDTO));
    }
}