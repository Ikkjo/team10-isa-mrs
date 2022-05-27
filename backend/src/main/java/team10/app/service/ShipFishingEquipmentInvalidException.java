package team10.app.service;

import team10.app.util.exceptions.RentalEntityDetailsException;

public class ShipFishingEquipmentInvalidException extends RentalEntityDetailsException {
    public ShipFishingEquipmentInvalidException(String fishingEquipment) {
        super(String.format("Ship fishing equipment invalid: %s", fishingEquipment));
    }
}
