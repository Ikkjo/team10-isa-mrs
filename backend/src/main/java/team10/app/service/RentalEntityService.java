package team10.app.service;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import team10.app.dto.AdventureDto;
import team10.app.dto.RentalEntityDto;
import team10.app.dto.ShipDto;
import team10.app.dto.VacationHomeDto;
import team10.app.model.*;
import team10.app.repository.BusinessClientRepository;
import team10.app.repository.RentalEntityRepository;
import team10.app.util.exceptions.RentalEntityNotFoundException;

import java.util.UUID;

@Service
@AllArgsConstructor
public class RentalEntityService {

    private final RentalEntityRepository rentalEntityRepository;

    public RentalEntityDto findById(UUID id) {
        RentalEntity rentalEntity = rentalEntityRepository.findById(id)
                .orElseThrow(() -> new RentalEntityNotFoundException(id));
        rentalEntity.setPictures(PictureService.decompressPictures(rentalEntity.getPictures()));
        return buildRentalEntityDto(rentalEntity);
    }

    private RentalEntityDto buildRentalEntityDto(RentalEntity rentalEntity) {
        if (rentalEntity instanceof VacationHome)
            return new VacationHomeDto((VacationHome) rentalEntity);
        if (rentalEntity instanceof Ship)
            return new ShipDto((Ship) rentalEntity);
//        if (rentalEntity instanceof Adventure)
//            return new AdventureDto(rentalEntity)
        return null;
    }
}
