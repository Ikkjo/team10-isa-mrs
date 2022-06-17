package team10.app.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import team10.app.dto.ActionDto;
import team10.app.dto.AddressDto;
import team10.app.dto.RentalEntityDto;
import team10.app.repository.specification.search.SearchCriteria;
import team10.app.security.auth.JWTProvider;
import team10.app.service.RentalEntityService;
import team10.app.util.exceptions.RentalEntityNotFoundException;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Controller
@RequestMapping(path = "/api/v1/rental-entity")
@AllArgsConstructor
public class RentalEntityController {

    private final RentalEntityService rentalEntityService;
    private final JWTProvider jwtProvider;

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole('HOUSE_OWNER', 'SHIP_OWNER', 'FISHING_INSTRUCTOR')")
    public ResponseEntity<RentalEntityDto> findById(@PathVariable UUID id) {
        try {
            return new ResponseEntity<>(rentalEntityService.rentalEntityToDto(id), HttpStatus.OK);
        }
        catch (RuntimeException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('HOUSE_OWNER', 'SHIP_OWNER', 'FISHING_INSTRUCTOR')")
    public ResponseEntity<Set<RentalEntityDto>> getAllVacationHomes(Principal principal) {
        try {
            return new ResponseEntity<>(rentalEntityService.getAllActiveByOwnerEmail(principal.getName()), HttpStatus.OK);
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

    @PostMapping(value = "/{id}/add-action")
    @PreAuthorize("hasAnyRole('HOUSE_OWNER', 'SHIP_OWNER', 'FISHING_INSTRUCTOR')")
    public ResponseEntity<ActionDto> addAction(@RequestHeader (name="Authorization") String token, @PathVariable(name = "id") UUID id, @RequestBody ActionDto actionDto) {
        try {
            rentalEntityService.addAction(jwtProvider.getAuthentication(token.substring(7)).getName(), id, actionDto);
            return new ResponseEntity<>(actionDto, HttpStatus.OK);
        }
        catch (RentalEntityNotFoundException ex)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Transactional
    @PutMapping("/update/{id}/title")
    @PreAuthorize("hasAnyRole('HOUSE_OWNER', 'SHIP_OWNER', 'FISHING_INSTRUCTOR')")
    public ResponseEntity<String> updateTitle(@PathVariable UUID id, @RequestBody String title) {
        try {
            rentalEntityService.updateTitle(title, id);
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(title, HttpStatus.OK);
    }

    @Transactional
    @PutMapping("/update/{id}/address")
    @PreAuthorize("hasAnyRole('HOUSE_OWNER', 'SHIP_OWNER', 'FISHING_INSTRUCTOR')")
    public ResponseEntity<AddressDto> updateAddress(@PathVariable UUID id, @RequestBody AddressDto address) {
        try {
            rentalEntityService.updateAddress(address, id);
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(address, HttpStatus.OK);
    }

    @Transactional
    @PutMapping("/update/{id}/description")
    @PreAuthorize("hasAnyRole('HOUSE_OWNER', 'SHIP_OWNER', 'FISHING_INSTRUCTOR')")
    public ResponseEntity<String> updateDescription(@PathVariable UUID id, @RequestBody String description) {
        try {
            rentalEntityService.updateDescription(description, id);
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(description, HttpStatus.OK);
    }

    @Transactional
    @PutMapping("/update/{id}/availability")
    @PreAuthorize("hasAnyRole('HOUSE_OWNER', 'SHIP_OWNER', 'FISHING_INSTRUCTOR')")
    public ResponseEntity<Long[]> updateAvailability(@PathVariable UUID id, @RequestBody Long[] availability) {
        try {
            rentalEntityService.updateAvailability(List.of(availability), id);
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(availability, HttpStatus.OK);
    }

    @Transactional
    @PutMapping("/update/{id}/rules-of-conduct")
    @PreAuthorize("hasAnyRole('HOUSE_OWNER', 'SHIP_OWNER', 'FISHING_INSTRUCTOR')")
    public ResponseEntity<String> updateRulesOfConduct(@PathVariable UUID id, @RequestBody String rulesOfConduct) {
        try {
            rentalEntityService.updateRulesOfConduct(rulesOfConduct, id);
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(rulesOfConduct, HttpStatus.OK);
    }

    @Transactional
    @PutMapping("/update/{id}/additional-services")
    @PreAuthorize("hasAnyRole('HOUSE_OWNER', 'SHIP_OWNER', 'FISHING_INSTRUCTOR')")
    public ResponseEntity<String> updateAdditionalServices(@PathVariable UUID id, @RequestBody String additionalServices) {
        try {
            rentalEntityService.updateAdditionalServices(additionalServices, id);
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(additionalServices, HttpStatus.OK);
    }

    @Transactional
    @PutMapping("/update/{id}/price/{price}")
    @PreAuthorize("hasAnyRole('HOUSE_OWNER', 'SHIP_OWNER', 'FISHING_INSTRUCTOR')")
    public ResponseEntity<Integer> updatePrice(@PathVariable(name = "id") UUID id, @PathVariable(name = "price") int price) {
        try {
            rentalEntityService.updatePrice(price, id);
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(price, HttpStatus.OK);
    }

    @Transactional
    @PutMapping("/update/{id}/pictures")
    @PreAuthorize("hasAnyRole('HOUSE_OWNER', 'SHIP_OWNER', 'FISHING_INSTRUCTOR')")
    public ResponseEntity<List<String>> updatePictures(@PathVariable UUID id, @RequestBody List<String> pictures) {
        try {
            rentalEntityService.updatePictures(pictures, id);
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(pictures, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<RentalEntityDto>> searchRentalEntities(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "pageSize", defaultValue = "50") Integer pageSize,
            @RequestParam(name = "title", defaultValue = "") String title,
            @RequestParam(name = "country", defaultValue = "") String country,
            @RequestParam(name = "city", defaultValue = "") String city,
            @RequestParam(name = "fromDate", defaultValue = "0") Long fromDate,
            @RequestParam(name = "toDate", defaultValue = "0") Long toDate) {
        int DEFAULT_PAGE_SIZE = 50;

        try{
            return ResponseEntity.ok(rentalEntityService.rentalEntitySearch(page, pageSize, title, country, city,
                    fromDate, toDate));
        } catch(Exception e) {
            return ResponseEntity.ok(rentalEntityService.getAllRentalEntitiesPage(0, DEFAULT_PAGE_SIZE));
        }
    }
}
