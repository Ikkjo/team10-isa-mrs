package team10.app.util;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import team10.app.dto.RentalEntityDto;
import team10.app.dto.VacationHomeDto;

@Service
@AllArgsConstructor
public class Validator {

    private static final int TITLE_MIN_LENGTH = 10;
    private static final int TITLE_MAX_LENGTH = 50;
    private static final int DESCRIPTION_MIN_LENGTH = 5;
    private static final int DESCRIPTION_MAX_LENGTH = 500;
    private static final int RULES_OF_CONDUCT_MIN_LENGTH = 5;
    private static final int RULES_OF_CONDUCT_MAX_LENGTH = 200;
    private static final int ADDITIONAL_SERVICES_MIN_LENGTH = 4;
    private static final int ADDITIONAL_SERVICES_MAX_LENGTH = 200;
    private static final int MIN_PRICE = 5;
    private static final int MAX_PRICE = 10000;
    private static final int MIN_PICTURES = 5;
    private static final int MAX_PICTURES = 11;
    private static final int MIN_ROOMS = 1;
    private static final int MAX_ROOMS = 20;
    private static final int MIN_BEDS = 1;
    private static final int MAX_BEDS = 100;

    private final AddressValidator addressValidator;

    private boolean validateRentalEntity(RentalEntityDto rentalEntityDto) {
        return addressValidator.test(rentalEntityDto.getAddress())
                && inRange(TITLE_MIN_LENGTH, TITLE_MAX_LENGTH, rentalEntityDto.getTitle().length())
                && inRange(DESCRIPTION_MIN_LENGTH, DESCRIPTION_MAX_LENGTH, rentalEntityDto.getDescription().length())
                && inRange(RULES_OF_CONDUCT_MIN_LENGTH, RULES_OF_CONDUCT_MAX_LENGTH, rentalEntityDto.getRulesOfConduct().length())
                && inRange(ADDITIONAL_SERVICES_MIN_LENGTH, ADDITIONAL_SERVICES_MAX_LENGTH, rentalEntityDto.getAdditionalServices().length())
                && inRange(MIN_PRICE, MAX_PRICE, rentalEntityDto.getPrice())
                && inRange(MIN_PICTURES, MAX_PICTURES, rentalEntityDto.getPictures().size());
    }


    public boolean validateVacationHomeDTO(VacationHomeDto vacationHomeDto) {
        return validateRentalEntity(vacationHomeDto)
            && inRange(MIN_ROOMS, MAX_ROOMS, vacationHomeDto.getRooms())
            && inRange(MIN_BEDS, MAX_BEDS, vacationHomeDto.getBeds());
    }

    private boolean inRange(int min, int max, int num) {
        return num >= min && num <= max;
    }
}
