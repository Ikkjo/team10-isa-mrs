package team10.app.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import team10.app.dto.ShipDto;
import team10.app.model.*;
import team10.app.repository.*;
import team10.app.util.Validator;


@Service
@AllArgsConstructor
public class ShipOwnerService {

    private final ShipOwnerRepository shipOwnerRepository;
    private final ShipRepository shipRepository;
    private final Validator validator;

    @Autowired
    public ShipOwnerService(ShipOwnerRepository shipOwnerRepository,
                            ShipRepository shipRepository,
                            AddressRepository addressRepository,
                            PictureService pictureService,
                            PictureRepository pictureRepository,
                            Validator validator) {

        this.shipOwnerRepository = shipOwnerRepository;
        this.shipRepository = shipRepository;
        this.validator = validator;
    }


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
               PictureService.buildPictureSet(shipDto.getPictures()),
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

}
