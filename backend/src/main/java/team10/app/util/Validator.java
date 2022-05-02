package team10.app.util;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import team10.app.dto.VacationHomeDto;

@Service
@AllArgsConstructor
public class Validator {
    private final AddressValidator addressValidator;

    public boolean validateVacationHomeDTO(VacationHomeDto request) {
        return addressValidator.test(request.getAddress());
    }
}
