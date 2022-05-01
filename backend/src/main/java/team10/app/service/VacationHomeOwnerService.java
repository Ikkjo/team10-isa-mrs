package team10.app.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import team10.app.dto.VacationHomeDTO;
import team10.app.model.VacationHome;
import team10.app.repository.*;

@Service
@AllArgsConstructor
public class VacationHomeOwnerService {

    private final VacationHomeOwnerRepository vacationHomeOwnerRepository;
    private final VacationHomeRepository vacationHomeRepository;
    private final AddressRepository addressRepository;

    public String addVacationHome(VacationHomeDTO request) {
        VacationHome vacationHome = new VacationHome(request);
        saveVacationHome(vacationHome);
        return "succeess";
    }

    public void saveVacationHome(VacationHome vacationHome) {
        addressRepository.save(vacationHome.getAddress());
        vacationHomeRepository.save(vacationHome);

    }
}
