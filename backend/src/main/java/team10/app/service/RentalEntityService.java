package team10.app.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import team10.app.dto.*;
import team10.app.model.*;
import team10.app.repository.BusinessClientRepository;
import team10.app.repository.RentalEntityRepository;
import team10.app.repository.specification.RentalEntitySpecification;
import team10.app.repository.specification.search.SearchCriteria;
import team10.app.repository.ReservationRepository;
import team10.app.util.DateTimeUtil;
import team10.app.util.Validator;
import team10.app.util.exceptions.*;

import javax.persistence.*;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RentalEntityService {

    @PersistenceContext
    private final EntityManager entityManager;

    private final RentalEntityRepository rentalEntityRepository;
    private final BusinessClientRepository businessClientRepository;
    private final ReservationRepository reservationRepository;
    private final AddressService addressService;
    private final ClientService clientService;
    private final BusinessClientService businessClientService;
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
        List<RentalEntity> rentalEntities = rentalEntityRepository.findAllByOwner(businessClientService.getByEmail(email));
        Set<RentalEntityDto> rentalEntityDtoSet = new HashSet<>();
        if (!rentalEntities.isEmpty())
            rentalEntityDtoSet = rentalEntities.stream().map((rentalEntity) ->
            {
                rentalEntity.setPictures(PictureService.decompressPictures(rentalEntity.getPictures()));
                return buildRentalEntityDto(rentalEntity);
            }).collect(Collectors.toSet());
        return rentalEntityDtoSet;
    }

    public List<String> getAllActiveRentalEntityTitlesByOwnerEmail(String email) {
        return rentalEntityRepository.getAllTitlesByOwner(businessClientService.getByEmail(email));
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
        return this.getById(id).getAvailability().stream().map(Availability::getDate).collect(Collectors.toList());
    }

    public void addAction(String email, UUID id, ActionDto actionDto) {
        if (!validator.validateActionDto(actionDto))
            throw new ActionInvalidException();
        BusinessClient businessClient = businessClientRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(email));
        RentalEntity rentalEntity = this.getRentalEntityByOwner(businessClient, id);
        if (!validator.validateRentalEntityDateNotTaken(rentalEntity, actionDto.getDateRange()))
            throw new RentalEntityDateTaken(rentalEntity.getId(), actionDto.getDateRange());
        rentalEntity.addAction(new Action(actionDto));
        rentalEntityRepository.saveAndFlush(rentalEntity);
    }
  
        public List<Long> getTakenDates(UUID id) {
        return new ArrayList<>(this.getById(id).getTakenDates());
    }

    public void addReservation(String email, UUID id, CreateReservationDto createReservationDto) {
        BusinessClient businessClient = businessClientRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(email));
        RentalEntity rentalEntity = this.getRentalEntityByOwner(businessClient, id);
        // validate
        if (!validator.validateReservationDto(createReservationDto))
            throw new ReservationInvalidException(createReservationDto);
        if (!validator.validateRentalEntityDateNotTaken(rentalEntity, createReservationDto.getDateRange()))
            throw new RentalEntityDateTaken(rentalEntity.getId(), createReservationDto.getDateRange());
        // create reservation
        Client client = clientService.getByUsername(createReservationDto.getUsername());
        Reservation reservation = new Reservation(createReservationDto, businessClient, client, rentalEntity);
        // save
        reservation = reservationRepository.saveAndFlush(reservation);
        businessClientService.addReservation(email, reservation);
        rentalEntity.addReservation(reservation);
        clientService.addReservation(client, reservation);
    }

    private RentalEntity getRentalEntityByOwner(BusinessClient businessClient, UUID id) {
        RentalEntity rentalEntity = this.getById(id);
        if (rentalEntity.getOwner() != businessClient)
            throw new InvalidRentalEntityOwnerException(rentalEntity.getId(), businessClient.getId());
        return rentalEntity;
    }
  
    public List<RentalEntityDto> getAllRentalEntitiesPage(int page, int size) {
        List<RentalEntity> rentalEntityPage = rentalEntityRepository.findAll(PageRequest.of(page, size)).toList();
        List<RentalEntityDto> rentalEntityDtoList = new ArrayList<>();

        for (RentalEntity re : rentalEntityPage) {
            rentalEntityDtoList.add(rentalEntityToDto(re.getId()));
        }
        return rentalEntityDtoList;
    }

    public List<RentalEntityDto> rentalEntitySearch(
            int page,
            int pageSize,
//            Long ownerId,
            String title,
            String country,
            String city,
            String address,
            long fromDate,
            long toDate
    ) {
        boolean shouldCheckAvailability = !(fromDate < DateTimeUtil.getTodayEpochMillisecond());
        TypedQuery<RentalEntity> query = entityManager.createQuery(
        String.format("SELECT re FROM RentalEntity re " +

                "LEFT JOIN Address addr on addr.id=re.address " +
                "WHERE re.title LIKE '%s' AND " +
                "addr.country LIKE '%s' AND " +
                "addr.city LIKE '%s' AND " +
                "addr.address LIKE '%s'",
                "%"+title+"%", country, city, address), RentalEntity.class);

        List<RentalEntity> results = query.getResultList();

        List<RentalEntityDto> rentalEntityDtos = new ArrayList<>();
        for (RentalEntity rE:  results) {
            if(isRentalEntityAvailable(fromDate, toDate, rE, shouldCheckAvailability))
                rentalEntityDtos.add(setRentalEntityDtoType(rE, rentalEntityToDto(rE.getId())));
        }

        return rentalEntityDtos;
    }

    private boolean isRentalEntityAvailable(long from, long to, RentalEntity rentalEntity, boolean shouldCheck) {
        if(!shouldCheck) {
            return true;
        }
        Set<Availability> availableObj = rentalEntity.getAvailability();
        Set<Long> available = new HashSet<>();

        for (Availability a : availableObj) {
            available.add(a.getDate());
        }

        boolean isAvailable = true;
        for(long day = from; day <= to; day++ ){
            isAvailable = isAvailable && available.contains(day);
        }

        return isAvailable;
    }

    private RentalEntityDto setRentalEntityDtoType(RentalEntity rentalEntity, RentalEntityDto rentalEntityDto){
        if (rentalEntity instanceof VacationHome){
            rentalEntityDto.setType("VacationHome");
        } else if (rentalEntity instanceof Ship) {
            rentalEntityDto.setType("Ship");
        } else if (rentalEntity instanceof Adventure) {
            rentalEntityDto.setType("Adventure");
        }
        return rentalEntityDto;
    }

}
