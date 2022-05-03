package team10.app.service;

import lombok.AllArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;
import team10.app.dto.VacationHomeDto;
import team10.app.model.VacationHome;
import team10.app.repository.*;
import team10.app.util.Validator;

import java.io.IOException;

@Service
@AllArgsConstructor
public class VacationHomeOwnerService {

    private final VacationHomeOwnerRepository vacationHomeOwnerRepository;
    private final VacationHomeRepository vacationHomeRepository;
    private final AddressRepository addressRepository;
    private final PictureService pictureService;
    private final PictureRepository pictureRepository;
    private final Validator validator;

    public String addVacationHome(VacationHomeDto request) throws IOException {
        if (!validator.validateVacationHomeDTO(request)) {}// return bad request


        VacationHome vacationHome = this.buildVacationHome(request);
        saveVacationHome(vacationHome);
        return "succeess";
    }

    public void saveVacationHome(VacationHome vacationHome) {
        addressRepository.save(vacationHome.getAddress());
        pictureRepository.saveAll(vacationHome.getPictures());
        vacationHomeRepository.save(vacationHome);

    }

    public VacationHome buildVacationHome(VacationHomeDto vacationHomeDto) {
        return new VacationHome(
                vacationHomeDto.getTitle(),
                vacationHomeDto.getAddress(),
                vacationHomeDto.getDescription(),
                vacationHomeDto.getRulesOfConduct(),
                vacationHomeDto.getAdditionalServices(),
                vacationHomeDto.getPrice(),
                pictureService.buildPictureSet(vacationHomeDto.getPictures()),
                vacationHomeDto.getRooms(),
                vacationHomeDto.getBeds()
        );
    }
}
