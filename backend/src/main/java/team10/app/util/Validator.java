package team10.app.util;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import team10.app.dto.VacationHomeDTO;
import team10.app.model.Address;

@Service
@AllArgsConstructor
public class Validator {
    public final AddressValidator addressValidator;

    public boolean validateVacationHomeDTO(VacationHomeDTO request) {
        return addressValidator.test(request.getAddress());
    }
}
