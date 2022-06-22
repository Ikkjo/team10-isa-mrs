package team10.app.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import team10.app.dto.AddressDto;
import team10.app.dto.VacationHomeDto;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ValidatorTest {

    @Mock
    private AddressValidator addressValidator;
    private Validator validator;

    @BeforeEach
    void setUp() {
        validator = new Validator(addressValidator, null);
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
                Arrays.asList("a,c2Rmc2Rmc2RmIHNkZiBzZGY=", "b,c2Rmc2Rmc2RmIHNkZiBzZGY=", "b,c2Rmc2Rmc2RmIHNkZiBzZGY=", "b,c2Rmc2Rmc2RmIHNkZiBzZGY=", "b,c2Rmc2Rmc2RmIHNkZiBzZGY="),
                10,
                20,
                new ArrayList<>()
        );
        when(addressValidator.testVacationHome(Mockito.any(AddressDto.class))).thenReturn(true);
        assertThat(validator.validateVacationHomeDTO(vacationHomeDTO)).isTrue();
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
                20,
                new ArrayList<>()
        );
        assertThat(validator.validateVacationHomeDTO(vacationHomeDTO)).isFalse();
    }
}