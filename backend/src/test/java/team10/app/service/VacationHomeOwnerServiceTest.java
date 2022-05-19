package team10.app.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import team10.app.dto.VacationHomeDto;
import team10.app.model.Address;
import team10.app.model.VacationHome;
import team10.app.repository.AddressRepository;
import team10.app.repository.PictureRepository;
import team10.app.repository.VacationHomeOwnerRepository;
import team10.app.repository.VacationHomeRepository;
import team10.app.util.Validator;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VacationHomeOwnerServiceTest {

    @Mock
    private VacationHomeOwnerRepository vacationHomeOwnerRepository;
    @Mock
    private VacationHomeRepository vacationHomeRepository;
    @Mock
    private AddressRepository addressRepository;
    @Mock
    private PictureService pictureService;
    @Mock
    private PictureRepository pictureRepository;
    @Mock
    private Validator validator;
    private VacationHomeOwnerService vacationHomeOwnerService;

    @BeforeEach
    void setUp() {
        vacationHomeOwnerService = new VacationHomeOwnerService(
                vacationHomeOwnerRepository,
                vacationHomeRepository,
                addressRepository,
                pictureService,
                pictureRepository,
                validator);
    }

    @Test
    void shouldAddVacationHome() {
        // given
        VacationHomeDto vacationHomeDTO = new VacationHomeDto(
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
        VacationHome vacationHome = vacationHomeOwnerService.buildVacationHome(vacationHomeDTO);
        when(validator.validateVacationHomeDTO(Mockito.any(VacationHomeDto.class))).thenReturn(true);
        // when
        vacationHomeOwnerService.addVacationHome(vacationHomeDTO);
        // then
        ArgumentCaptor<VacationHome> argumentCaptor = ArgumentCaptor.forClass(VacationHome.class);
        verify(vacationHomeRepository).save(argumentCaptor.capture());
        VacationHome capturedVacationHome = argumentCaptor.getValue();
        assertThat(capturedVacationHome).isEqualTo(vacationHome);
    }

    @Test
    void shouldNotAddVacationHomeAndShouldThrowRuntimeException() {
        // given
        Address address = new Address("Ulica b.b.", "Grad", "Drzava");
        VacationHomeDto vacationHomeDTO = new VacationHomeDto(
                "Stan na dan",
                address,
                "Stan na dan za jedan dan stan",
                "Ponasalje mora biti lijepo",
                "Svasta nesto nudimo",
                12,
                Arrays.asList("12", "2"),
                10,
                20
        );
        //given
        given(validator.validateVacationHomeDTO(Mockito.any(VacationHomeDto.class))).willReturn(false);
        // when
        //then
        assertThatThrownBy(() -> vacationHomeOwnerService.addVacationHome(vacationHomeDTO)).isInstanceOf(RuntimeException.class);

        verify(vacationHomeRepository, never()).save(any());
    }

    @Test
    void shouldNotAddVacationHome() {
        // given
        Address address = new Address("Ulica b.b.", "Grad", "Drzava");
        addressRepository.save(address);
        VacationHomeDto vacationHomeDTO = new VacationHomeDto(
                "Stan na dan",
                address,
                "Stan na dan za jedan dan stan",
                "Ponasalje mora biti lijepo",
                "Svasta nesto nudimo",
                12,
                Arrays.asList("12", "2"),
                10,
                20
        );
        VacationHome vacationHome = vacationHomeOwnerService.buildVacationHome(vacationHomeDTO);
        when(validator.validateVacationHomeDTO(Mockito.any(VacationHomeDto.class))).thenReturn(true);
        // when
        vacationHomeOwnerService.addVacationHome(vacationHomeDTO);
        //then

    }

    @Test
    void saveVacationHome() {
        VacationHomeDto vacationHomeDTO = new VacationHomeDto(
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
        VacationHome vacationHome = vacationHomeOwnerService.buildVacationHome(vacationHomeDTO);
        vacationHomeRepository.save(vacationHome);
        verify(vacationHomeRepository).save(vacationHome);
    }
}