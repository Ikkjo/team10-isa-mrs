package team10.app.util.exceptions;

public class ShipNavigationEquipmentInvalidException extends RentalEntityDetailsException {
    public ShipNavigationEquipmentInvalidException(String navigationEquipment) {
        super(String.format("Ship navigation equipment invalid: %s", navigationEquipment));
    }
}
