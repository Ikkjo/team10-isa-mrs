package team10.app.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import team10.app.dto.ShipDto;
import team10.app.dto.VacationHomeDto;
import team10.app.model.Address;
import team10.app.model.Picture;
import team10.app.model.Ship;
import team10.app.model.VacationHome;
import team10.app.repository.AddressRepository;
import team10.app.repository.PictureRepository;
import team10.app.repository.ShipOwnerRepository;
import team10.app.repository.ShipRepository;
import team10.app.util.Validator;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ShipOwnerServiceTest {

    @Mock
    private ShipOwnerRepository shipOwnerRepository;
    @Mock
    private ShipRepository shipRepository;
    @Mock
    private AddressRepository addressRepository;
    @Mock
    private PictureService pictureService;
    @Mock
    private PictureRepository pictureRepository;
    @Mock
    private Validator validator;

    private ShipOwnerService shipOwnerService;


    @BeforeEach
    void setUp() {
        shipOwnerService = new ShipOwnerService(
                shipOwnerRepository,
                shipRepository,
                addressRepository,
                pictureService,
                pictureRepository,
                validator
        );
    }

    @Test
    void shouldAddVacationHome() {
        // given
        ShipDto shipDto = new ShipDto(
                "Fishinig ship",
                new Address("Street", "City", "Country"),
                "Some description",
                "Rules",
                "Services",
                12,
                Arrays.asList("12", "2"),
                "Type",
                12.1,
                2,
                100,
                50,
                "gps, radar",
                "bait, rods",
                10,
                false
        );
        Ship ship = new Ship(
                "Fishinig ship",
                new Address("Street", "City", "Country"),
                "Some description",
                "Rules",
                "Services",
                12,
                new HashSet<>(Arrays.asList(new Picture(), new Picture())),
                "Type",
                12.1,
                2,
                100,
                50,
                "gps, radar",
                "bait, rods",
                10,
                false
        );
        when(validator.validateShipDto(Mockito.any(ShipDto.class))).thenReturn(true);
        // when
        shipOwnerService.addShip(shipDto);
        // then
        ArgumentCaptor<Ship> argumentCaptor = ArgumentCaptor.forClass(Ship.class);
        verify(shipRepository).save(argumentCaptor.capture());
        Ship capturedShip = argumentCaptor.getValue();
        assertThat(capturedShip.getAddress()).isEqualTo(ship.getAddress());
    }

    @Test
    void shouldNotAddVacationHomeAndShouldThrowRuntimeException() {
        // given
        ShipDto shipDto = new ShipDto(
                "Fish",
                new Address("Street", "City", "Country"),
                "Some description",
                "Rules",
                "Services",
                12,
                Arrays.asList("12", "2"),
                "Type",
                12.1,
                2,
                100,
                50,
                "gps, radar",
                "bait, rods",
                10,
                false
        );
        given(validator.validateShipDto(Mockito.any(ShipDto.class))).willReturn(false);
        //then
        assertThatThrownBy(() -> shipOwnerService.addShip(shipDto)).isInstanceOf(RuntimeException.class);

        verify(shipRepository, never()).save(any());
    }

    @Test
    void shouldNotAddVacationHome() {
        // given
        Address address = new Address("Street", "City", "Country");
        addressRepository.save(address);
        ShipDto shipDto = new ShipDto(
                "Fish",
                address,
                "Some description",
                "Rules",
                "Services",
                12,
                Arrays.asList("12", "2"),
                "Type",
                12.1,
                2,
                100,
                50,
                "gps, radar",
                "bait, rods",
                10,
                false
        );
        Ship ship = new Ship(
                "Fishinig ship",
                address,
                "Some description",
                "Rules",
                "Services",
                12,
                new HashSet<>(Arrays.asList(new Picture(), new Picture())),
                "Type",
                12.1,
                2,
                100,
                50,
                "gps, radar",
                "bait, rods",
                10,
                false
        );
        when(validator.validateShipDto(Mockito.any(ShipDto.class))).thenReturn(true);
        // when
        shipOwnerService.addShip(shipDto);
    }
}