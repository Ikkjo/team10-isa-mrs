package team10.app.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import team10.app.repository.ShipRepository;
import team10.app.util.Validator;
import team10.app.util.exceptions.*;

import java.util.UUID;

@Service
@AllArgsConstructor
public class ShipService {
    
    private final ShipRepository shipRepository;
    private final Validator validator;
    
    public void updateType(String type, UUID id) {
        if (!validator.validateShipType(type))
            throw new ShipTypeInvalidException(type);
        shipRepository.updateType(type, id);
    }

    public void updateLength(double length, UUID id) {
        if (!validator.validateShipLength(length))
            throw new ShipLengthInvalidException(length);
        shipRepository.updateLength(length, id);
    }

    public void updateEngineCount(int engineCount, UUID id) {
        if (!validator.validateShipEngineCount(engineCount))
            throw new ShipEngineCountInvalidException(engineCount);
        shipRepository.updateEngineCount(engineCount, id);
    }

    public void updateEnginePower(int enginePower, UUID id) {
        if (!validator.validateShipEnginePower(enginePower))
            throw new ShipEnginePowerInvalidException(enginePower);
        shipRepository.updateEnginePower(enginePower, id);
    }

    public void updateMaxSpeed(int maxSpeed, UUID id) {
        if (!validator.validateShipMaxSpeed(maxSpeed))
            throw new ShipMaxSpeedInvalidException(maxSpeed);
        shipRepository.updateMaxSpeed(maxSpeed, id);
    }

    public void updateNavigationEquipment(String navigationEquipment, UUID id) {
        if (!validator.validateShipNavigationEquipment(navigationEquipment))
            throw new ShipNavigationEquipmentInvalidException(navigationEquipment);
        shipRepository.updateNavigationEquipment(navigationEquipment, id);
    }

    public void updateFishingEquipment(String fishingEquipment, UUID id) {
        if (!validator.validateShipFishingEquipment(fishingEquipment))
            throw new ShipFishingEquipmentInvalidException(fishingEquipment);
        shipRepository.updateFishingEquipment(fishingEquipment, id);
    }

    public void updateCapacity(int capacity, UUID id) {
        if (!validator.validateShipCapacity(capacity))
            throw new ShipCapacityInvalidException(capacity);
        shipRepository.updateCapacity(capacity, id);
    }

    public void updateFreeCancellation(Boolean isFreeCancellation, UUID id) {
        shipRepository.updateFreeCancellation(isFreeCancellation, id);
    }
}
