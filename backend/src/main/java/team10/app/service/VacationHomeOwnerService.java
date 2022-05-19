package team10.app.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import team10.app.dto.VacationHomeDto;
import team10.app.model.Picture;
import team10.app.model.RentalEntity;
import team10.app.model.VacationHome;
import team10.app.model.VacationHomeOwner;
import team10.app.repository.*;
import team10.app.util.Validator;
import team10.app.util.exceptions.UserNotFoundException;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class VacationHomeOwnerService {

    private final VacationHomeOwnerRepository vacationHomeOwnerRepository;
    private final VacationHomeRepository vacationHomeRepository;
    private final AddressRepository addressRepository;
    private final PictureService pictureService;
    private final PictureRepository pictureRepository;
    private final Validator validator;

    public void addVacationHome(VacationHomeDto request) throws RuntimeException { // TODO: Add custom exception
        if (!validator.validateVacationHomeDTO(request)) {
            throw new RuntimeException();
        }
        VacationHome vacationHome = this.buildVacationHome(request);
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

    public Set<VacationHomeDto> getAllActiveVacationHomesByOwnerEmail(String email) throws UsernameNotFoundException{
        VacationHomeOwner vacationHomeOwner = vacationHomeOwnerRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("VacationHomeOwner not found!"));

        return vacationHomeOwner.getVacationHomes().stream().filter(vacationHome -> !vacationHome.isDeleted())
                .map((vacationHome) ->
                {
                    vacationHome.setPictures(decompressPictures(vacationHome.getPictures()));
                    return new VacationHomeDto(vacationHome);
                }).collect(Collectors.toSet());

    }

    private Set<Picture> decompressPictures(Set<Picture> pictures) {
        return pictures.stream().map(
                picture -> new Picture(picture.getType(), PictureService.decompressBytes(picture.getPicByte()))
        ).collect(Collectors.toSet());
    }
}
