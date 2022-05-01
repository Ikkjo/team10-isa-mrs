package team10.app.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import team10.app.dto.VacationHomeDTO;
import team10.app.model.Address;
import team10.app.model.VacationHome;
import team10.app.repository.AddressRepository;
import team10.app.repository.VacationHomeOwnerRepository;
import team10.app.repository.VacationHomeRepository;
import team10.app.util.Validator;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

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
                addressRepository,
                validator);
    }

    @Test
    void shouldAddVacationHome() {
        // given
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
        // when
        vacationHomeOwnerService.addVacationHome(vacationHomeDTO);
        // then
        ArgumentCaptor<VacationHome> argumentCaptor = ArgumentCaptor.forClass(VacationHome.class);
        verify(vacationHomeRepository).save(argumentCaptor.capture());
        VacationHome capturedVacationHome = argumentCaptor.getValue();
        assertThat(capturedVacationHome).isEqualTo(vacationHome);
    }

    @Test
    void shouldNotAddVacationHome() {
        // given
        Address address = new Address("Ulica b.b.", "Grad", "Drzava");
        addressRepository.save(address);
        VacationHomeDTO vacationHomeDTO = new VacationHomeDTO(
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
        VacationHome vacationHome = new VacationHome(vacationHomeDTO);
        // when
        vacationHomeOwnerService.addVacationHome(vacationHomeDTO);
        //then
        assertThat(vacationHomeRepository.count()).isEqualTo(0);
    }

    @Test
    void saveVacationHome() {
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
        vacationHomeOwnerService.saveVacationHome(vacationHome);

        verify(addressRepository).save(vacationHome.getAddress());
        verify(vacationHomeRepository).save(vacationHome);
    }
}