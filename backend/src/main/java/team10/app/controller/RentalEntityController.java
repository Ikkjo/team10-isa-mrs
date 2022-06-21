package team10.app.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import team10.app.dto.ActionDto;
import team10.app.dto.AddressDto;
import team10.app.dto.RentalEntityDto;
import team10.app.dto.CreateReservationDto;
import team10.app.security.auth.AuthUtil;
import team10.app.security.auth.JWTProvider;
import team10.app.service.RentalEntityService;
import team10.app.util.exceptions.InvalidRentalEntityOwnerException;
import team10.app.util.exceptions.RentalEntityNotFoundException;
import team10.app.util.exceptions.RentalEntityReservedException;

import java.security.Principal;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Controller
@RequestMapping(path = "/api/v1/rental-entity")
@AllArgsConstructor
public class RentalEntityController {

    private final RentalEntityService rentalEntityService;
    private final AuthUtil authUtil;

    @GetMapping(value = "/{id}")
//    @PreAuthorize("hasAnyRole('HOUSE_OWNER', 'SHIP_OWNER', 'FISHING_INSTRUCTOR', 'CLIENT')")
//    No need for authorization because unauthorized users can view
    public ResponseEntity<RentalEntityDto> findById(@PathVariable UUID id) {
        try {
            return new ResponseEntity<>(rentalEntityService.rentalEntityToDto(id, false), HttpStatus.OK);
        }
        catch (RuntimeException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('HOUSE_OWNER', 'SHIP_OWNER', 'FISHING_INSTRUCTOR')")
    public ResponseEntity<Set<RentalEntityDto>> getAllRentalEntities(Principal principal) {
        try {
            return new ResponseEntity<>(rentalEntityService.getAllActiveByOwnerEmail(principal.getName()), HttpStatus.OK);
        }
        catch (UsernameNotFoundException ex)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Transactional
    @PutMapping("/delete/{id}")
    @PreAuthorize("hasAnyRole('HOUSE_OWNER', 'SHIP_OWNER', 'FISHING_INSTRUCTOR')")
    public ResponseEntity<HttpStatus> delete(Principal principal, @PathVariable UUID id) {
        try {
            rentalEntityService.delete(principal.getName(), id);
        } catch (ObjectOptimisticLockingFailureException ex) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } catch (InvalidRentalEntityOwnerException | RentalEntityReservedException ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }



    @GetMapping("/titles")
    @PreAuthorize("hasAnyRole('HOUSE_OWNER', 'SHIP_OWNER', 'FISHING_INSTRUCTOR')")
    public ResponseEntity<List<String>> getAllRentalEntityTitles(Principal principal) {
        try {
            return new ResponseEntity<>(rentalEntityService.getAllActiveRentalEntityTitlesByOwnerEmail(principal.getName()), HttpStatus.OK);
        }
        catch (UsernameNotFoundException ex)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/{id}/availability")
    @PreAuthorize("hasAnyRole('HOUSE_OWNER', 'SHIP_OWNER', 'FISHING_INSTRUCTOR')")
    public ResponseEntity<List<Long>> getAvailability(@PathVariable UUID id) {
        try {
            return new ResponseEntity<>(rentalEntityService.getAvailability(id), HttpStatus.OK);
        }
        catch (RentalEntityNotFoundException ex)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/{id}/taken-dates")
    @PreAuthorize("hasAnyRole('HOUSE_OWNER', 'SHIP_OWNER', 'FISHING_INSTRUCTOR')")
    public ResponseEntity<List<Long>> getTakenDates(@PathVariable UUID id) {
        try {
            return new ResponseEntity<>(rentalEntityService.getTakenDates(id), HttpStatus.OK);
        }
        catch (RentalEntityNotFoundException ex)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/{id}/add-action")
    @PreAuthorize("hasAnyRole('HOUSE_OWNER', 'SHIP_OWNER', 'FISHING_INSTRUCTOR')")
    public ResponseEntity<ActionDto> addAction(@RequestHeader (name="Authorization") String token,
                                               @PathVariable(name = "id") UUID id,
                                               @RequestBody ActionDto actionDto) {
        try {
            rentalEntityService.addAction(authUtil.getEmailFromToken(token), id, actionDto);
            return new ResponseEntity<>(actionDto, HttpStatus.OK);
        }
        catch (RentalEntityNotFoundException ex)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (ObjectOptimisticLockingFailureException ex) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PostMapping(value = "/{id}/add-reservation")
    @PreAuthorize("hasAnyRole('HOUSE_OWNER', 'SHIP_OWNER', 'FISHING_INSTRUCTOR')")
    public ResponseEntity<CreateReservationDto> addReservation(@RequestHeader (name="Authorization") String token,
                                                               @PathVariable(name = "id") UUID id,
                                                               @RequestBody CreateReservationDto createReservationDto) {
        try {
            rentalEntityService.addReservation(authUtil.getEmailFromToken(token), id, createReservationDto);
            return new ResponseEntity<>(createReservationDto, HttpStatus.OK);
        }
        catch (RentalEntityNotFoundException ex)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (ObjectOptimisticLockingFailureException ex) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @Transactional
    @PutMapping("/update/{id}/title")
    @PreAuthorize("hasAnyRole('HOUSE_OWNER', 'SHIP_OWNER', 'FISHING_INSTRUCTOR')")
    public ResponseEntity<String> updateTitle(@RequestHeader (name="Authorization") String token,
                                              @PathVariable UUID id, @RequestBody String title) {
        try {
            rentalEntityService.updateTitle(authUtil.getEmailFromToken(token), title, id);
        } catch (ObjectOptimisticLockingFailureException ex) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(title, HttpStatus.OK);

    }

    @Transactional
    @PutMapping("/update/{id}/address")
    @PreAuthorize("hasAnyRole('HOUSE_OWNER', 'SHIP_OWNER', 'FISHING_INSTRUCTOR')")
    public ResponseEntity<AddressDto> updateAddress(@RequestHeader (name="Authorization") String token,
                                                    @PathVariable UUID id, @RequestBody AddressDto address) {
        try {
            rentalEntityService.updateAddress(authUtil.getEmailFromToken(token), address, id);
        } catch (ObjectOptimisticLockingFailureException ex) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(address, HttpStatus.OK);
    }

    @Transactional
    @PutMapping("/update/{id}/description")
    @PreAuthorize("hasAnyRole('HOUSE_OWNER', 'SHIP_OWNER', 'FISHING_INSTRUCTOR')")
    public ResponseEntity<String> updateDescription(@RequestHeader (name="Authorization") String token,
                                                    @PathVariable UUID id, @RequestBody String description) {
        try {
            rentalEntityService.updateDescription(authUtil.getEmailFromToken(token), description, id);
        } catch (ObjectOptimisticLockingFailureException ex) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(description, HttpStatus.OK);
    }

    @Transactional
    @PutMapping("/update/{id}/availability")
    @PreAuthorize("hasAnyRole('HOUSE_OWNER', 'SHIP_OWNER', 'FISHING_INSTRUCTOR')")
    public ResponseEntity<Long[]> updateAvailability(@RequestHeader (name="Authorization") String token,
                                                     @PathVariable UUID id, @RequestBody Long[] availability) {
        try {
            rentalEntityService.updateAvailability(authUtil.getEmailFromToken(token), List.of(availability), id);
        } catch (ObjectOptimisticLockingFailureException ex) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(availability, HttpStatus.OK);
    }

    @Transactional
    @PutMapping("/update/{id}/rules-of-conduct")
    @PreAuthorize("hasAnyRole('HOUSE_OWNER', 'SHIP_OWNER', 'FISHING_INSTRUCTOR')")
    public ResponseEntity<String> updateRulesOfConduct(@RequestHeader (name="Authorization") String token,
                                                       @PathVariable UUID id, @RequestBody String rulesOfConduct) {
        try {
            rentalEntityService.updateRulesOfConduct(authUtil.getEmailFromToken(token), rulesOfConduct, id);
        } catch (ObjectOptimisticLockingFailureException ex) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(rulesOfConduct, HttpStatus.OK);
    }

    @Transactional
    @PutMapping("/update/{id}/additional-services")
    @PreAuthorize("hasAnyRole('HOUSE_OWNER', 'SHIP_OWNER', 'FISHING_INSTRUCTOR')")
    public ResponseEntity<String> updateAdditionalServices(@RequestHeader (name="Authorization") String token,
                                                           @PathVariable UUID id, @RequestBody String additionalServices) {
        try {
            rentalEntityService.updateAdditionalServices(authUtil.getEmailFromToken(token), additionalServices, id);
        } catch (ObjectOptimisticLockingFailureException ex) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(additionalServices, HttpStatus.OK);
    }

    @Transactional
    @PutMapping("/update/{id}/price/{price}")
    @PreAuthorize("hasAnyRole('HOUSE_OWNER', 'SHIP_OWNER', 'FISHING_INSTRUCTOR')")
    public ResponseEntity<Integer> updatePrice(@RequestHeader (name="Authorization") String token,
                                               @PathVariable(name = "id") UUID id, @PathVariable(name = "price") int price) {
        try {
            rentalEntityService.updatePrice(authUtil.getEmailFromToken(token), price, id);
        } catch (ObjectOptimisticLockingFailureException ex) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(price, HttpStatus.OK);
    }

    @Transactional
    @PutMapping("/update/{id}/pictures")
    @PreAuthorize("hasAnyRole('HOUSE_OWNER', 'SHIP_OWNER', 'FISHING_INSTRUCTOR')")
    public ResponseEntity<List<String>> updatePictures(@RequestHeader (name="Authorization") String token,
                                                       @PathVariable UUID id, @RequestBody List<String> pictures) {
        try {
            rentalEntityService.updatePictures(authUtil.getEmailFromToken(token), pictures, id);
        } catch (ObjectOptimisticLockingFailureException ex) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(pictures, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<RentalEntityDto>> searchRentalEntities(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
            @RequestParam(name = "owner", defaultValue = "%") String owner,
            @RequestParam(name = "title", defaultValue = "%") String title,
            @RequestParam(name = "country", defaultValue = "%") String country,
            @RequestParam(name = "city", defaultValue = "%") String city,
            @RequestParam(name = "address", defaultValue = "%") String address,
            @RequestParam(name = "fromDate", defaultValue = "0") long fromDate,
            @RequestParam(name = "toDate", defaultValue = "0") long toDate,
            @RequestParam(name = "ofType", defaultValue = "") String ofType,
            @RequestParam(name = "minPrice", defaultValue = "0") int minPrice,
            @RequestParam(name = "maxPrice", defaultValue = "10000") int maxPrice) {
        int DEFAULT_PAGE_SIZE = 20;

        try{
            return ResponseEntity.ok(rentalEntityService.rentalEntitySearch(page, pageSize, owner, title, country, city,
                    address, fromDate, toDate, ofType, minPrice, maxPrice));
        } catch(Exception e) {
            return ResponseEntity.ok(rentalEntityService.getAllRentalEntitiesPage(0, DEFAULT_PAGE_SIZE));
        }
    }
}
