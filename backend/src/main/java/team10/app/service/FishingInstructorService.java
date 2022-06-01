package team10.app.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import team10.app.dto.AdventureDto;
import team10.app.dto.VacationHomeDto;
import team10.app.model.Adventure;
import team10.app.model.Availability;
import team10.app.model.VacationHome;
import team10.app.repository.AddressRepository;
import team10.app.repository.AdventureRepository;
import team10.app.repository.FishingInstructorRepository;
import team10.app.repository.PictureRepository;
import team10.app.util.Validator;

import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FishingInstructorService {

    private final AdventureRepository adventureRepository;
    private final AddressRepository addressRepository;
    private final PictureRepository pictureRepository;
    private final Validator validator;

    public void addAdventure(AdventureDto request) throws RuntimeException { // TODO: Add custom exception
        if (!validator.validateAdventureDto(request)) {
            throw new RuntimeException();
        }
        Adventure adventure = this.buildAdventure(request);
        saveAdventure(adventure);
    }

    public void saveAdventure(Adventure adventure) {
        addressRepository.save(adventure.getAddress());
        pictureRepository.saveAll(adventure.getPictures());
        adventureRepository.save(adventure);

    }

    public Adventure buildAdventure(AdventureDto adventureDto) {
        return new Adventure(
                adventureDto.getTitle(),
                adventureDto.getDescription(),
                PictureService.buildPictureSet(adventureDto.getPictures()),
                adventureDto.getRulesOfConduct(),
                adventureDto.getPrice(),
                adventureDto.getBiography(),
                adventureDto.getCapacity(),
                adventureDto.getFishingEquipment(),
                adventureDto.isFreeCancellation(),
                adventureDto.getAvailability().stream().map(Availability::new).collect(Collectors.toSet())
        );
    }
}
