package team10.app.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import team10.app.dto.AddressDto;
import team10.app.dto.ShipDto;
import team10.app.model.*;
import team10.app.repository.AddressRepository;
import team10.app.repository.ShipOwnerRepository;
import team10.app.repository.ShipRepository;
import team10.app.util.Validator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ShipOwnerServiceTest {

    @Mock
    private ShipOwnerRepository shipOwnerRepository;
    @Mock
    private ShipRepository shipRepository;
    @Mock
    private AddressRepository addressRepository;
    @Mock
    private Validator validator;

    private ShipOwnerService shipOwnerService;


    @BeforeEach
    void setUp() {
        shipOwnerService = new ShipOwnerService(
                shipOwnerRepository,
                shipRepository,
                validator
        );
    }

    @Test
    void shouldAddShip() {
        // given
        ShipDto shipDto = new ShipDto(
                "Fishinig ship",
                new AddressDto("Street", "City", "Country"),
                "Some description",
                "Rules",
                "Services",
                12,
                Arrays.asList("a,c2Rmc2Rmc2RmIHNkZiBzZGY=", "b,c2Rmc2Rmc2RmIHNkZiBzZGY="),
                "Type",
                12.1,
                2,
                100,
                50,
                "gps, radar",
                "bait, rods",
                10,
                false,
                new ArrayList<>()
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
                false,
                shipDto.getAvailability().stream().map(Availability::new).collect(Collectors.toSet())

        );
        // when
        when(validator.validateShipDto(Mockito.any(ShipDto.class))).thenReturn(true);
        ShipOwner shipOwner = new ShipOwner();
        when(shipOwnerRepository.findByEmail(Mockito.anyString())).thenReturn(Optional.of(shipOwner));
        shipOwnerService.addShip(shipDto, "test@gmail.com");
        // then
        ArgumentCaptor<Ship> argumentCaptor = ArgumentCaptor.forClass(Ship.class);
        verify(shipRepository).save(argumentCaptor.capture());
        Ship capturedShip = argumentCaptor.getValue();
        assertThat(capturedShip.getAddress()).isEqualTo(ship.getAddress());
    }

    @Test
    void shouldNotAddShipAndShouldThrowRuntimeException() {
        // given
        ShipDto shipDto = new ShipDto(
                "Fish",
                new AddressDto("Street", "City", "Country"),
                "Some description",
                "Rules",
                "Services",
                12,
                Arrays.asList("a,c2Rmc2Rmc2RmIHNkZiBzZGY=", "b,c2Rmc2Rmc2RmIHNkZiBzZGY="),
                "Type",
                12.1,
                2,
                100,
                50,
                "gps, radar",
                "bait, rods",
                10,
                false,
                new ArrayList<>()
        );
        given(validator.validateShipDto(Mockito.any(ShipDto.class))).willReturn(false);
        //then
        assertThatThrownBy(() -> shipOwnerService.addShip(shipDto, "test@gmail.com")).isInstanceOf(RuntimeException.class);

        verify(shipRepository, never()).save(any());
    }

    @Test
        void shouldNotAddVacationHome() {
            // given
            Address address = new Address("Street", "City", "Country");
            AddressDto addressDto = new AddressDto("Street", "City", "Country");
            addressRepository.save(address);
            ShipDto shipDto = new ShipDto(
                    "Fish",
                    new AddressDto("Street", "City", "Country"),
                "Some description",
                "Rules",
                "Services",
                12,
                Arrays.asList("a,c2Rmc2Rmc2RmIHNkZiBzZGY=", "b,c2Rmc2Rmc2RmIHNkZiBzZGY="),
                "Type",
                12.1,
                2,
                100,
                50,
                "gps, radar",
                "bait, rods",
                10,
                false,
                new ArrayList<>()
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
                false,
                shipDto.getAvailability().stream().map(Availability::new).collect(Collectors.toSet())
        );
        when(validator.validateShipDto(Mockito.any(ShipDto.class))).thenReturn(true);
        ShipOwner shipOwner = new ShipOwner();
        when(shipOwnerRepository.findByEmail(Mockito.anyString())).thenReturn(Optional.of(shipOwner));
        // when
        shipOwnerService.addShip(shipDto, "test@gmail.com");
    }
}