package team10.app.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import team10.app.dto.VacationHomeDto;
import team10.app.model.VacationHome;
import team10.app.repository.*;
import team10.app.util.Validator;

@Service
@AllArgsConstructor
public class VacationHomeOwnerService {

    private final VacationHomeOwnerRepository vacationHomeOwnerRepository;
    private final VacationHomeRepository vacationHomeRepository;
    private final AddressRepository addressRepository;
    private final Validator validator;

    public boolean addVacationHome(VacationHomeDto request) throws RuntimeException { // TODO: Add custom exception
        if (!validator.validateVacationHomeDTO(request)) {
            throw new RuntimeException();
        }
        VacationHome vacationHome = new VacationHome(request);
        saveVacationHome(vacationHome);
        return true;
    }

    public void saveVacationHome(VacationHome vacationHome) {
        addressRepository.save(vacationHome.getAddress());
        vacationHomeRepository.save(vacationHome);

    }
}
