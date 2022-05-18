package team10.app.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team10.app.model.VacationHome;
import team10.app.model.VacationHomeOwner;
import team10.app.repository.VacationHomeOwnerRepository;
import team10.app.repository.VacationHomeRepository;
import team10.app.util.exceptions.UserNotFoundException;

import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/vacation-homes")
@AllArgsConstructor
public class VacationHomeController {

//    private final VacationHomeService vacationHomeService;
    private final VacationHomeRepository vacationHomeRepository;
    private final VacationHomeOwnerRepository vacationHomeOwnerRepository;


    @GetMapping(value = "/{id}")
    Set<VacationHome> getVacationHomesByUserId(@PathVariable("id") UUID id) {
        VacationHomeOwner vacationHomeOwner = vacationHomeOwnerRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException(id)
        );
        return vacationHomeOwner.getVacationHomes();
    }

}
