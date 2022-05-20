package team10.app.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import team10.app.dto.ShipDto;
import team10.app.model.Ship;
import team10.app.model.ShipOwner;
import team10.app.repository.AddressRepository;
import team10.app.repository.PictureRepository;
import team10.app.repository.ShipOwnerRepository;
import team10.app.repository.ShipRepository;
import team10.app.util.Validator;

import java.util.HashSet;

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
        saveShip(ship);
    }

    private Ship buildShip(ShipDto shipDto) {
       return new Ship(
               shipDto.getTitle(),
               shipDto.getAddress(),
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

    private void saveShip(Ship ship) {
        addressRepository.save(ship.getAddress());
        pictureRepository.saveAll(ship.getPictures());
        shipRepository.save(ship);
    }


}
