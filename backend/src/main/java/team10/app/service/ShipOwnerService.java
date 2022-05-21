package team10.app.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import team10.app.dto.AddressDto;
import team10.app.dto.ShipDto;
import team10.app.dto.VacationHomeDto;
import team10.app.model.*;
import team10.app.repository.AddressRepository;
import team10.app.repository.PictureRepository;
import team10.app.repository.ShipOwnerRepository;
import team10.app.repository.ShipRepository;
import team10.app.util.Validator;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ShipOwnerService {

    private ShipOwnerRepository shipOwnerRepository;
    private ShipRepository shipRepository;
    private final AddressRepository addressRepository;
    private PictureService pictureService;
    private PictureRepository pictureRepository;
    private Validator validator;



    public void addShip(ShipDto request, String email) throws RuntimeException {
        if (!validator.validateShipDto(request)) {
            throw new RuntimeException();
        }
        ShipOwner shipOwner = shipOwnerRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Ship owner: %s, not found!", email)));
        Ship ship = this.buildShip(request);
        ship.setOwner(shipOwner);
        shipRepository.save(ship);
    }

    private Ship buildShip(ShipDto shipDto) {
       return new Ship(
               shipDto.getTitle(),
               new Address(shipDto.getAddress()),
               shipDto.getDescription(),
               shipDto.getRulesOfConduct(),
               shipDto.getAdditionalServices(),
               shipDto.getPrice(),
               pictureService.buildPictureSet(shipDto.getPictures()),
               shipDto.getType(),
               shipDto.getLength(),
               shipDto.getEngineCount(),
               shipDto.getEnginePower(),
               shipDto.getMaxSpeed(),
               shipDto.getNavigationEquipment(),
               shipDto.getFishingEquipment(),
               shipDto.getCapacity(),
               shipDto.isFreeCancellation()
       );
    }

    public Set<ShipDto> getAllActiveShipsByOwnerEmail(String email) {

        ShipOwner shipOwner = shipOwnerRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("ShipOwner: %s, not found!", email)));

        List<Ship> ships = shipRepository.findAllByOwner(shipOwner);
        Set<ShipDto> shipDtoSet = new HashSet<>();
        if (!ships.isEmpty())
            shipDtoSet = ships.stream().map((ship) ->
            {
                ship.setPictures(pictureService.decompressPictures(ship.getPictures()));
                return new ShipDto(ship);
            }).collect(Collectors.toSet());
        return shipDtoSet;
    }
}
