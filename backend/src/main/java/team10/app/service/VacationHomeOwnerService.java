package team10.app.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import team10.app.dto.VacationHomeDTO;
import team10.app.model.VacationHome;
import team10.app.repository.VacationHomeOwnerRepository;
import team10.app.repository.VacationHomeRepository;

@Service
@AllArgsConstructor
public class VacationHomeOwnerService {

    private static VacationHomeOwnerRepository vacationHomeOwnerRepository;
    private static VacationHomeRepository vacationHomeRepository;

    public String addVacationHome(VacationHomeDTO request) {
        VacationHome vacationHome = new VacationHome(request);
        vacationHomeRepository.save(vacationHome);
        return "suceess";
    }
}
