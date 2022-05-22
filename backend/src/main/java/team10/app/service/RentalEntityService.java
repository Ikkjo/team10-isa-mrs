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

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RentalEntityService {

    private final RentalEntityRepository rentalEntityRepository;
    private final BusinessClientRepository businessClientRepository;

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

    public Set<RentalEntityDto> getAllActiveByOwnerEmail(String email) {
        BusinessClient businessClient = businessClientRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(email));

        List<RentalEntity> rentalEntities = rentalEntityRepository.findAllByOwner(businessClient);

        List<RentalEntity> vacationHomes = rentalEntityRepository.findAllByOwner(businessClient);
        Set<RentalEntityDto> rentalEntityDtoSet = new HashSet<>();
        if (!rentalEntities.isEmpty())
            rentalEntityDtoSet = rentalEntities.stream().map((rentalEntity) ->
            {
                rentalEntity.setPictures(PictureService.decompressPictures(rentalEntity.getPictures()));
                return buildRentalEntityDto(rentalEntity);
            }).collect(Collectors.toSet());
        return rentalEntityDtoSet;
    }
}
