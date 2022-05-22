package team10.app.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import team10.app.dto.VacationHomeDto;
import team10.app.model.Address;
import team10.app.model.VacationHome;
import team10.app.model.VacationHomeOwner;
import team10.app.repository.*;
import team10.app.util.Validator;


@Service
@AllArgsConstructor
public class VacationHomeOwnerService {

    private final VacationHomeOwnerRepository vacationHomeOwnerRepository;
    private final VacationHomeRepository vacationHomeRepository;
    private final Validator validator;

    public void addVacationHome(VacationHomeDto request, String email) throws RuntimeException { // TODO: Add custom exception
        if (!validator.validateVacationHomeDTO(request)) {
            throw new RuntimeException();
        }
        VacationHomeOwner vacationHomeOwner = vacationHomeOwnerRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Vacation home owner: %s, not found!", email)));
        VacationHome vacationHome = this.buildVacationHome(request);
        vacationHome.setOwner(vacationHomeOwner);
        vacationHomeRepository.save(vacationHome);
    }

    public VacationHome buildVacationHome(VacationHomeDto vacationHomeDto) {
        return new VacationHome(
                vacationHomeDto.getTitle(),
                new Address(vacationHomeDto.getAddress()),
                vacationHomeDto.getDescription(),
                vacationHomeDto.getRulesOfConduct(),
                vacationHomeDto.getAdditionalServices(),
                vacationHomeDto.getPrice(),
                PictureService.buildPictureSet(vacationHomeDto.getPictures()),
                vacationHomeDto.getRooms(),
                vacationHomeDto.getBeds()
        );
    }
}
