package team10.app.util;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import team10.app.dto.AdventureDto;
import team10.app.dto.RentalEntityDto;
import team10.app.dto.ShipDto;
import team10.app.dto.VacationHomeDto;

@Service
@AllArgsConstructor
public class Validator {
    // Rental entity
    private static final int TITLE_MIN_LENGTH = 5;
    private static final int TITLE_MAX_LENGTH = 50;
    private static final int DESCRIPTION_MIN_LENGTH = 5;
    private static final int DESCRIPTION_MAX_LENGTH = 500;
    private static final int RULES_OF_CONDUCT_MIN_LENGTH = 5;
    private static final int RULES_OF_CONDUCT_MAX_LENGTH = 200;
    private static final int ADDITIONAL_SERVICES_MIN_LENGTH = 2;
    private static final int ADDITIONAL_SERVICES_MAX_LENGTH = 200;
    private static final int MIN_PRICE = 5;
    private static final int MAX_PRICE = 10000;
    private static final int MIN_PICTURES = 5;
    private static final int MAX_PICTURES = 10;

    // Vacation home
    private static final int VACATION_HOME_MIN_ROOMS = 1;
    private static final int VACATION_HOME_MAX_ROOMS = 20;
    private static final int VACATION_HOME_MIN_BEDS = 1;
    private static final int VACATION_HOME_MAX_BEDS = 100;

    // Ship
    private static final int SHIP_TYPE_MIN_LENGTH = 5;
    private static final int SHIP_TYPE_MAX_LENGTH = 50;
    private static final double SHIP_MIN_LENGTH = 1;
    private static final double SHIP_MAX_LENGTH = 50;
    private static final int SHIP_MIN_ENGINE_COUNT = 1;
    private static final int SHIP_MAX_ENGINE_COUNT = 5;
    private static final int SHIP_MIN_POWER = 1;
    private static final int SHIP_MAX_POWER = 10000;
    private static final int SHIP_MIN_SPEED = 1;
    private static final int SHIP_MAX_SPEED = 200;
    private static final int SHIP_MIN_NAVIGATION_EQUIPMENT_LENGTH = 3;
    private static final int SHIP_MAX_NAVIGATION_EQUIPMENT_LENGTH = 500;
    private static final int SHIP_MIN_FISHING_EQUIPMENT_LENGTH = 0;
    private static final int SHIP_MAX_FISHING_EQUIPMENT_LENGTH = 500;
    private static final int SHIP_MIN_CAPACITY = 1;
    private static final int SHIP_MAX_CAPACITY = 100;

    // Adventure
    private static final int ADVENTURE_BIOGRAPHY_MIN_LENGTH = 5;
    private static final int ADVENTURE_BIOGRAPHY_MAX_LENGTH = 200;
    private static final int ADVENTURE_MIN_CAPACITY = 1;
    private static final int ADVENTURE_MAX_CAPACITY = 20;
    private static final int ADVENTURE_FISHING_EQUIPMENT_MIN_LENGTH = 5;
    private static final int ADVENTURE_FISHING_EQUIPMENT_MAX_LENGTH = 500;


    private final AddressValidator addressValidator;

    private boolean validateRentalEntity(RentalEntityDto rentalEntityDto) {
        return inRange(TITLE_MIN_LENGTH, TITLE_MAX_LENGTH, rentalEntityDto.getTitle().length())
                && inRange(DESCRIPTION_MIN_LENGTH, DESCRIPTION_MAX_LENGTH, rentalEntityDto.getDescription().length())
                && inRange(RULES_OF_CONDUCT_MIN_LENGTH, RULES_OF_CONDUCT_MAX_LENGTH, rentalEntityDto.getRulesOfConduct().length())
                && inRange(ADDITIONAL_SERVICES_MIN_LENGTH, ADDITIONAL_SERVICES_MAX_LENGTH, rentalEntityDto.getAdditionalServices().length())
                && inRange(MIN_PRICE, MAX_PRICE, rentalEntityDto.getPrice())
                && inRange(MIN_PICTURES, MAX_PICTURES, rentalEntityDto.getPictures().size());
    }


    public boolean validateVacationHomeDTO(VacationHomeDto vacationHomeDto) {
        return validateRentalEntity(vacationHomeDto)
                && inRange(VACATION_HOME_MIN_ROOMS, VACATION_HOME_MAX_ROOMS, vacationHomeDto.getRooms())
                && inRange(VACATION_HOME_MIN_BEDS, VACATION_HOME_MAX_BEDS, vacationHomeDto.getBeds())
                && addressValidator.testVacationHome(vacationHomeDto.getAddress());
    }

    public boolean validateShipDto(ShipDto shipDto) {
        return validateRentalEntity(shipDto)
                && inRange(SHIP_TYPE_MIN_LENGTH, SHIP_TYPE_MAX_LENGTH, shipDto.getType().length())
                && inRange(SHIP_MIN_LENGTH, SHIP_MAX_LENGTH, shipDto.getLength())
                && inRange(SHIP_MIN_ENGINE_COUNT, SHIP_MAX_ENGINE_COUNT, shipDto.getEngineCount())
                && inRange(SHIP_MIN_POWER, SHIP_MAX_POWER, shipDto.getEnginePower())
                && inRange(SHIP_MIN_SPEED, SHIP_MAX_SPEED, shipDto.getEngineCount())
                && inRange(SHIP_MIN_NAVIGATION_EQUIPMENT_LENGTH, SHIP_MAX_NAVIGATION_EQUIPMENT_LENGTH, shipDto.getNavigationEquipment().length())
                && inRange(SHIP_MIN_FISHING_EQUIPMENT_LENGTH, SHIP_MAX_FISHING_EQUIPMENT_LENGTH, shipDto.getFishingEquipment().length())
                && inRange(SHIP_MIN_CAPACITY, SHIP_MAX_CAPACITY, shipDto.getCapacity())
                && addressValidator.testShip(shipDto.getAddress());

    }

    public boolean validateAdventureDto(AdventureDto adventureDto) {
        return validateRentalEntity(adventureDto)
                && inRange(ADVENTURE_BIOGRAPHY_MIN_LENGTH, ADVENTURE_BIOGRAPHY_MAX_LENGTH, adventureDto.getBiography().length())
                && inRange(ADVENTURE_MIN_CAPACITY, ADVENTURE_MAX_CAPACITY, adventureDto.getCapacity())
                && inRange(ADVENTURE_FISHING_EQUIPMENT_MIN_LENGTH, ADVENTURE_FISHING_EQUIPMENT_MAX_LENGTH, adventureDto.getFishingEquipment().length())
                && addressValidator.testVacationHome(adventureDto.getAddress());
    }

    private boolean inRange(int min, int max, int num) {
        return num >= min && num <= max;
    }
    private boolean inRange(double min, double max, double num) {
        return num >= min && num <= max;
    }

    public boolean validateFirstName(String firstName) {
        return inRange(2, 20, firstName.length());
    }

    public boolean validateLastName(String lastName) {
        return inRange(2, 20, lastName.length());
    }
}
