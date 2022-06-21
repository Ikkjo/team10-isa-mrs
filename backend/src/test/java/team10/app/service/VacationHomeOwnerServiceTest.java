package team10.app.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import team10.app.dto.AddressDto;
import team10.app.dto.VacationHomeDto;
import team10.app.model.Address;
import team10.app.model.VacationHome;
import team10.app.model.VacationHomeOwner;
import team10.app.repository.AddressRepository;
import team10.app.repository.VacationHomeOwnerRepository;
import team10.app.repository.VacationHomeRepository;
import team10.app.util.Validator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

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
    private Validator validator;
    private VacationHomeOwnerService vacationHomeOwnerService;

    @BeforeEach
    void setUp() {
        vacationHomeOwnerService = new VacationHomeOwnerService(
                vacationHomeOwnerRepository,
                vacationHomeRepository,
                validator);
    }

    @Test
    void shouldAddVacationHome() {
        // given
        VacationHomeDto vacationHomeDTO = new VacationHomeDto(
                "Stan na dan",
                new AddressDto("Ulica b.b.", "Grad", "Drzava"),
                "Stan na dan za jedan dan stan",
                "Ponasalje mora biti lijepo",
                "Svasta nesto nudimo",
                12,
                Arrays.asList("a,c2Rmc2Rmc2RmIHNkZiBzZGY=", "b,c2Rmc2Rmc2RmIHNkZiBzZGY="),
                10,
                20,
                new ArrayList<>()
        );
        VacationHomeOwner vacationHomeOwner = new VacationHomeOwner();
        VacationHome vacationHome = vacationHomeOwnerService.buildVacationHome(vacationHomeDTO);
        when(validator.validateVacationHomeDTO(Mockito.any(VacationHomeDto.class))).thenReturn(true);
        when(vacationHomeOwnerRepository.findByEmail(Mockito.anyString())).thenReturn(Optional.of(vacationHomeOwner));
        // when
        vacationHomeOwnerService.addVacationHome(vacationHomeDTO, "test@gmail.com");
        // then
        ArgumentCaptor<VacationHome> argumentCaptor = ArgumentCaptor.forClass(VacationHome.class);
        verify(vacationHomeRepository).save(argumentCaptor.capture());
        VacationHome capturedVacationHome = argumentCaptor.getValue();
        assertThat(capturedVacationHome.getTitle()).isEqualTo(vacationHome.getTitle());
    }

    @Test
    void shouldNotAddVacationHomeAndShouldThrowRuntimeException() {
        // given
        AddressDto addressDto = new AddressDto("Ulica b.b.", "Grad", "Drzava");
        VacationHomeDto vacationHomeDTO = new VacationHomeDto(
                "Stan na dan",
                addressDto,
                "Stan na dan za jedan dan stan",
                "Ponasalje mora biti lijepo",
                "Svasta nesto nudimo",
                12,
                Arrays.asList("12", "2"),
                10,
                20,
                new ArrayList<>()
        );
        //given
        given(validator.validateVacationHomeDTO(Mockito.any(VacationHomeDto.class))).willReturn(false);
        // when
        //then
        assertThatThrownBy(() -> vacationHomeOwnerService.addVacationHome(vacationHomeDTO, "test@gmail.com")).isInstanceOf(RuntimeException.class);

        verify(vacationHomeRepository, never()).save(any());
    }

    @Test
    void shouldNotAddVacationHome() {
        // given
        Address address = new Address("Ulica b.b.", "Grad", "Drzava");
        AddressDto addressDto = new AddressDto("Ulica b.b.", "Grad", "Drzava");

        addressRepository.save(address);
        VacationHomeDto vacationHomeDTO = new VacationHomeDto(
                "Stan na dan",
                addressDto,
                "Stan na dan za jedan dan stan",
                "Ponasalje mora biti lijepo",
                "Svasta nesto nudimo",
                12,
                Arrays.asList("a,c2Rmc2Rmc2RmIHNkZiBzZGY=", "b,c2Rmc2Rmc2RmIHNkZiBzZGY="),
                10,
                20,
                new ArrayList<>()
        );
        VacationHome vacationHome = vacationHomeOwnerService.buildVacationHome(vacationHomeDTO);
        when(validator.validateVacationHomeDTO(Mockito.any(VacationHomeDto.class))).thenReturn(true);
        VacationHomeOwner vacationHomeOwner = new VacationHomeOwner();
        when(vacationHomeOwnerRepository.findByEmail(Mockito.anyString())).thenReturn(Optional.of(vacationHomeOwner));
        // when
        vacationHomeOwnerService.addVacationHome(vacationHomeDTO, "test@gmail.com");
        //then

    }

    @Test
    void saveVacationHome() {
        VacationHomeDto vacationHomeDTO = new VacationHomeDto(
                "Stan na dan",
                new AddressDto("Ulica b.b.", "Grad", "Drzava"),
                "Stan na dan za jedan dan stan",
                "Ponasalje mora biti lijepo",
                "Svasta nesto nudimo",
                12,
                Arrays.asList("a,c2Rmc2Rmc2RmIHNkZiBzZGY=", "b,c2Rmc2Rmc2RmIHNkZiBzZGY="),
                10,
                20,
                new ArrayList<>()
        );
        VacationHome vacationHome = vacationHomeOwnerService.buildVacationHome(vacationHomeDTO);
        vacationHomeRepository.save(vacationHome);
        verify(vacationHomeRepository).save(vacationHome);
    }
}