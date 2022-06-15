package team10.app.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import team10.app.dto.*;
import team10.app.model.*;
import team10.app.repository.BusinessClientRepository;
import team10.app.repository.RentalEntityRepository;
import team10.app.util.Validator;
import team10.app.util.exceptions.*;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RentalEntityService {

    private final RentalEntityRepository rentalEntityRepository;
    private final BusinessClientRepository businessClientRepository;
    private final AddressService addressService;
    private final PictureService pictureService;
    private final Validator validator;

    public RentalEntityDto rentalEntityToDto(UUID id) {
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

    private RentalEntity getById(UUID id) {
        return rentalEntityRepository.findById(id).orElseThrow(() -> new RentalEntityNotFoundException(id));
    }

    public Set<RentalEntityDto> getAllActiveByOwnerEmail(String email) {
        BusinessClient businessClient = businessClientRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(email));

        List<RentalEntity> rentalEntities = rentalEntityRepository.findAllByOwner(businessClient);
        Set<RentalEntityDto> rentalEntityDtoSet = new HashSet<>();
        if (!rentalEntities.isEmpty())
            rentalEntityDtoSet = rentalEntities.stream().map((rentalEntity) ->
            {
                rentalEntity.setPictures(PictureService.decompressPictures(rentalEntity.getPictures()));
                return buildRentalEntityDto(rentalEntity);
            }).collect(Collectors.toSet());
        return rentalEntityDtoSet;
    }

    public void updateTitle(String title, UUID id) {
        if (!validator.validateRentalEntityTitle(title))
            throw new RentalEntityTitleInvalidException(title);
        rentalEntityRepository.updateTitle(title, id);
    }

    public void updateAddress(AddressDto addressDto, UUID id) {
        if (!validator.validateRentalEntityAddress(addressDto, getById(id)))
            throw new RentalEntityAddressInvalidException(addressDto);
        rentalEntityRepository.updateAddress(addressService.getAddress(addressDto), id);
    }

    public void updateDescription(String description, UUID id) {
        if (!validator.validateRentalEntityDescription(description))
            throw new RentalEntityDescriptionInvalidException(description);
        rentalEntityRepository.updateDescription(description, id);
    }

    public void updateRulesOfConduct(String rulesOfConduct, UUID id) {
        if (!validator.validateRentalEntityRulesOfConduct(rulesOfConduct))
            throw new RentalEntityRulesOfConductInvalidException(rulesOfConduct);
        rentalEntityRepository.updateRulesOfConduct(rulesOfConduct, id);
    }

    public void updateAdditionalServices(String additionalServices, UUID id) {
        if (!validator.validateRentalEntityAdditionalServices(additionalServices))
            throw new RentalEntityAdditionalServicesInvalidException(additionalServices);
        rentalEntityRepository.updateAdditionalServices(additionalServices, id);
    }

    public void updatePrice(int price, UUID id) {
        if (!validator.validateRentalEntityPrice(price))
            throw new RentalEntityPriceInvalidException(price);
        rentalEntityRepository.updatePrice(price, id);
    }

    public void updatePictures(List<String> pictures, UUID id) {
        if (!validator.validateRentalEntityPictures(pictures))
            throw new RentalEntityPicturesInvalidException();
        RentalEntity rentalEntity = rentalEntityRepository.getById(id);
        rentalEntity.setPictures(PictureService.buildPictureSet(pictures));
        rentalEntityRepository.saveAndFlush(rentalEntity);
    }

    public void updateAvailability(List<Long> availability, UUID id) {
        if (!validator.validateRentalEntityAvailability(availability))
            throw new RentalEntityAvailabilityInvalidException(availability.toString());
        RentalEntity rentalEntity = rentalEntityRepository.getById(id);
        rentalEntity.setAvailability(availability.stream().map(Availability::new).collect(Collectors.toSet()));
        rentalEntityRepository.saveAndFlush(rentalEntity);
    }

    public List<Long> getAvailability(UUID id) {
        return rentalEntityRepository.getById(id).getAvailability().stream().map(Availability::getDate).collect(Collectors.toList());
    }

    public void addAction(String email, UUID id, ActionDto actionDto) {
        BusinessClient businessClient = businessClientRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(email));
        RentalEntity rentalEntity = this.getById(id);
        if (rentalEntity.getOwner() != businessClient)
            throw new InvalidRentalEntityOwnerException(rentalEntity.getId(), businessClient.getId());
        rentalEntity.addAction(new Action(actionDto));
        rentalEntityRepository.saveAndFlush(rentalEntity);
    }

    private Action buildAction(ActionDto actionDto) {
        return new Action(actionDto);
    }
}
