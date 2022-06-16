package team10.app.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import team10.app.repository.AdventureRepository;

import java.util.UUID;

@Service
@AllArgsConstructor
public class AdventureService {

    private final AdventureRepository adventureRepository;

    public void updateCapacity(int capacity, UUID id) {
        adventureRepository.updateCapacity(capacity, id);
    }

    public void updateFreeCancellation(boolean isFreeCancellation, UUID id) {
        adventureRepository.updateFreeCancellation(isFreeCancellation, id);
    }

    public void updateFishingEquipment(String fishingEquipment, UUID id) {
        adventureRepository.updateFishingEquipment(fishingEquipment, id);
    }

    public void updateBiography(String biography, UUID id) {
        adventureRepository.updateBiography(biography, id);
    }
}
