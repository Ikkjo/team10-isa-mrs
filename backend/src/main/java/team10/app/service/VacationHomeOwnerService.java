package team10.app.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import team10.app.dto.BusinessClientDto;
import team10.app.dto.VacationHomeDto;
import team10.app.model.Address;
import team10.app.model.Picture;
import team10.app.model.VacationHome;
import team10.app.model.VacationHomeOwner;
import team10.app.repository.*;
import team10.app.util.Validator;

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
                pictureService.buildPictureSet(vacationHomeDto.getPictures()),
                vacationHomeDto.getRooms(),
                vacationHomeDto.getBeds()
        );
    }

    public Set<VacationHomeDto> getAllActiveVacationHomesByOwnerEmail(String email) throws UsernameNotFoundException{
        VacationHomeOwner vacationHomeOwner = vacationHomeOwnerRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("VacationHomeOwner not found!"));

        List<VacationHome> vacationHomes = vacationHomeRepository.findAllByOwner(vacationHomeOwner);
        Set<VacationHomeDto> vacationHomeDtos = new HashSet<>();
        if (!vacationHomes.isEmpty())
            vacationHomeDtos = vacationHomes.stream().map((vacationHome) ->
            {
                vacationHome.setPictures(decompressPictures(vacationHome.getPictures()));
                return new VacationHomeDto(vacationHome);
            }).collect(Collectors.toSet());
        return vacationHomeDtos;
    }

    private Set<Picture> decompressPictures(Set<Picture> pictures) {
        return pictures.stream().map(
                picture -> new Picture(picture.getType(), PictureService.decompressBytes(picture.getBytes()))
        ).collect(Collectors.toSet());
    }

}
